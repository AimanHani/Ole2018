/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.AskOleDAO;
import controller.GetMatchesDAO;
import controller.JoinPublicLeaguesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FAQ;
import model.Match;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "AskOleJson", urlPatterns = {"/json/askOle"})
public class AskOle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AskOle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AskOle at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String question = request.getParameter("question");
        String category = request.getParameter("category");
        String status = "";
        try {
            if (question != null && category != null) {

                status = AskOleDAO.insertQuestion(username, question, category);
                if (status.equals("successful")) {

                    json.put("status", "successful");
                } else {

                    json.put("status", "error");
                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                }
                out.print(json);
                out.flush();
            } else {
                JSONArray list = new JSONArray();
                JSONObject parentJson = new JSONObject();
                response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
                response.setCharacterEncoding("utf-8");

                HashMap<Integer, FAQ> questionAndAnswerList = AskOleDAO.retrieveFAQ(username);

                Iterator it = questionAndAnswerList.entrySet().iterator();
                while (it.hasNext()) {
                    JSONObject json2 = new JSONObject();
                    Map.Entry pair = (Map.Entry) it.next();
                    FAQ faq = new FAQ();
                    //System.out.println(pair.getKey() + " = " + pair.getValue());
                    faq = (FAQ) pair.getValue();
                    json2.put("Id", faq.getId());
                    json2.put("question", faq.getQuestion());
                    json2.put("answer", faq.getAnswer());
                    json2.put("category", faq.getCategory());

                    it.remove(); // avoids a ConcurrentModificationException
                    list.put(json2);

                }
                parentJson.put("results", list);
                out.print(parentJson);
                out.flush();

            }

        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
