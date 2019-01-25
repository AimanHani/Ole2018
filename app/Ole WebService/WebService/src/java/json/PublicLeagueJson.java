/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.PublicLeagueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import model.AllPublicLeague;
import model.PublicLeague;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Hazirah
 */
@WebServlet(name = "PublicLeagueJson", urlPatterns = {"/json/PublicLeagueJson"})
public class PublicLeagueJson extends HttpServlet {

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
            out.println("<title>Servlet PublicLeagueJson</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicLeagueJson at " + request.getContextPath() + "</h1>");
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
        
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        ArrayList<PublicLeague> publicLeagueList = PublicLeagueDAO.getPublicLeagues();
        
        System.out.println(publicLeagueList.size());
        try {
            for (int i = 0; i < publicLeagueList.size(); i++) {

                JSONObject json = new JSONObject();
                PublicLeague publicLeague = publicLeagueList.get(i);
                System.out.println(publicLeague.getLeagueName());
                json.put("leagueID", publicLeague.getLeagueID());
                json.put("prize", publicLeague.getPrize());
                json.put("tournamentID", publicLeague.getTournamentID());
                json.put("pointsAllocated", publicLeague.getPointsAllocated());
                json.put("leagueName", publicLeague.getLeagueName());
                json.put("tournamentName", publicLeague.getTournamentName());
                json.put("logId", publicLeague.getLogId()
                );
                list.put(json);
            }

            parentJson.put("results", list);
            out.print(parentJson);
            out.flush();
            
        } catch (JSONException ex) {
            Logger.getLogger(PublicLeagueJson.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        processRequest(request, response);
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
