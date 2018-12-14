/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.PublicLeagueProfileDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import model.PublicLeagueProfile;
import org.json.JSONException;

/**
 *
 * @author user
 */
@WebServlet(name = "PublicLeagueProfileJson", urlPatterns = {"/json/publicLeagueProfile"})
public class PublicLeagueProfileJson extends HttpServlet {

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
            out.println("<title>Servlet PublicLeagueProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PublicLeagueProfile at " + request.getContextPath() + "</h1>");
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
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        PublicLeagueProfile plProfile = PublicLeagueProfileDAO.getUserPrediction(username);
        try {

            if (plProfile != null) {
                JSONObject json = new JSONObject();

                //System.out.println(pair.getKey() + " = " + pair.getValue());
                json.put("logId", plProfile.getLogID());
                json.put("username", plProfile.getUsername());
                json.put("leagueId", plProfile.getLeagueID());
                json.put("leagueName", plProfile.getLeagueName());
                json.put("logId", plProfile.getLogID());
                json.put("team1_prediction", plProfile.getTeam1Prediction());
                json.put("team2_prediction", plProfile.getTeam2Prediction());
                json.put("date", plProfile.getMatchDate());
                json.put("time", plProfile.getMatchTime());
                json.put("team1", plProfile.getTeam1());
                json.put("team2", plProfile.getTeam2());

                list.put(json);
                parentJson.put("results", list);
                out.print(parentJson);
                out.flush();

            } else {
                JSONObject json = new JSONObject();
                json.put("Error","Cannot get any information");
                list.put(json);
                parentJson.put("results", list);
                out.print(parentJson);
                out.flush();
            }

        } catch (JSONException e) {
            e.printStackTrace();
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
