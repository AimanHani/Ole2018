/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.AskOleDAO;
import controller.PrivateLeagaueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "PrivateLeagueJson", urlPatterns = {"/json/privateLeague"})
public class PrivateLeague extends HttpServlet {

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
            out.println("<title>Servlet PrivateLeague</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PrivateLeague at " + request.getContextPath() + "</h1>");
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
        String password = request.getParameter("password");
        String prize = request.getParameter("prize");
        String leagueName = request.getParameter("leagueName");
        int leagueId = Integer.parseInt(request.getParameter("leagueId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        String status = "";
        try {
            

                status = PrivateLeagaueDAO.createPrivateLeague(leagueName,prize ,password, startDate ,endDate ,username ,leagueId);
                if (status.equals("successful")) {

                    json.put("status", "successful");
                }
                else if(status.equals("start date and end date is not in valid format")){
                    json.put("status", "start date or end date is not in valid format");
                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                }
                else {

                    json.put("status", "error");
                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                }
                out.print(json);
                out.flush();
           

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
