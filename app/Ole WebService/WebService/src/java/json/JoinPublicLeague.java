/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.JoinPublicLeaguesDAO;
import controller.SignUpDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "JoinPublicLeagueJson", urlPatterns = {"/json/joinPublicLeague"})
public class JoinPublicLeague extends HttpServlet {

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
            out.println("<title>Servlet JoinPublicLeagues</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JoinPublicLeagues at " + request.getContextPath() + "</h1>");
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
        int leagueID = Integer.parseInt(request.getParameter("leagueId"));
        String status = "";
        int logID = 0;
        try {

            status = JoinPublicLeaguesDAO.insertToLog(username, leagueID);
            if (status.equals("successful")) {
                logID = JoinPublicLeaguesDAO.selectLog(username, leagueID);
                System.out.println("log id "+logID);
                status = JoinPublicLeaguesDAO.insertSpecialsLog(logID, leagueID);
                if (status.equals("successful")) {

                    json.put("status", "successful");

                } else {
                    json.put("status", "error");
                    String invalidMsg = "Something is wrong check check" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                }
            } else {

                json.put("status", "error");
                String invalidMsg = "Something is wrong check check" + "/" + "";
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
