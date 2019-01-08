/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.PublicLeagueProfileDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import model.PublicLeagueProfile;
import model.User;
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
        ArrayList<PublicLeagueProfile> leagueList = PublicLeagueProfileDAO.getUserPrediction(username);
        User u = PublicLeagueProfileDAO.getUserInfo(username);
        try {

            if (leagueList != null) {
                JSONObject user = new JSONObject();
                user.put("name", u.getName());
                user.put("dob", u.getDateOfBirth());
                user.put("country", u.getCountry());
                user.put("contactNum", u.getContactNumber());
                user.put("email", u.getEmail());
                user.put("favoriteTeam", u.getFavoriteTeam());
                //System.out.println(pair.getKey() + " = " + pair.getValue());
                for (int i = 0; i < leagueList.size(); i++) {
                    PublicLeagueProfile p = leagueList.get(i);
                    JSONObject json = new JSONObject();
                    json.put("logId", p.getLogID());
                    json.put("username", p.getUsername());
                    json.put("leagueId", p.getLeagueID());
                    json.put("leagueName", p.getLeagueName());
                    json.put("logId", p.getLogID());
                    json.put("team1_prediction", p.getTeam1Prediction());
                    json.put("team2_prediction", p.getTeam2Prediction());
                    json.put("date", p.getMatchDate());
                    json.put("time", p.getMatchTime());
                    json.put("team1", p.getTeam1());
                    json.put("team2", p.getTeam2());

                    list.put(json);

                }
                parentJson.put("predictions", list);
                parentJson.put("user", user);
                out.print(parentJson);
                out.flush();
            } else {
                JSONObject json = new JSONObject();
                json.put("Error", "Cannot get any information");
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
