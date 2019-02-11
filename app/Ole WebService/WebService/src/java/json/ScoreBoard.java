/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.GetMatchesDAO;
import controller.ScoreBoardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Match;
import model.PrivateLeagueProfile;
import model.PublicLeagueProfile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "ScoreBoardJson", urlPatterns = {"/json/scoreBoard"})
public class ScoreBoard extends HttpServlet {

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
            out.println("<title>Servlet ScoreBoard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ScoreBoard at " + request.getContextPath() + "</h1>");
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

        String league = request.getParameter("league");
        String username = request.getParameter("username");

        if (league != null && league.equals("public")) {

            PublicLeagueProfile plf;
            ArrayList<PublicLeagueProfile> pList = new ArrayList();

            try {

                pList = ScoreBoardDAO.getUsersAndTheirTotalPoints(2);
                if (pList.size() != 0) {
                    Collections.sort(pList);
                    JSONObject json = new JSONObject();
                    int rank = 0;
                    int previousPoints = 0;
                    for (int i = 0; i < pList.size(); i++) {
                        json = new JSONObject();
                        plf = new PublicLeagueProfile();
                        plf = pList.get(i);
                        json.put("leagueId", plf.getLeagueID());
                        json.put("league", "public league");
                        json.put("username", plf.getUsername());
                        json.put("totalPoints", plf.getTotalPoints());
                        if (previousPoints != plf.getTotalPoints()) {
                            rank++;
                        }
                        json.put("rank", rank);

                        previousPoints = plf.getTotalPoints();

                        list.put(json);
                    }

                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                } else {
                    JSONObject json = new JSONObject();

                    json.put("status", "error");
                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();
                }

            } catch (JSONException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            PrivateLeagueProfile plf;
            ArrayList<PrivateLeagueProfile> pList;
            ArrayList<Integer> privateLeagueIDs = new ArrayList();
            try {
                privateLeagueIDs = ScoreBoardDAO.privateLeagueIDsOfTheSpecificUserJoined(username);
                if (privateLeagueIDs.size() != 0) {
                    for (int i = 0; i < privateLeagueIDs.size(); i++) {
                        pList = new ArrayList();
                        pList = ScoreBoardDAO.getUsersAndTheirTotalPointsPrivate(privateLeagueIDs.get(i));
                        if (pList.size() != 0) {
                            Collections.sort(pList);
                            int rank = 0;
                            int previousPoints = 0;
                            JSONObject json = new JSONObject();
                            for (int j = 0; j < pList.size(); j++) {

                                json = new JSONObject();
                                plf = new PrivateLeagueProfile();
                                plf = pList.get(j);
                                json.put("leagueId", plf.getLeagueID());
                                json.put("league", "private league");
                                json.put("username", plf.getUsername());
                                json.put("totalPoints", plf.getTotalPoints());
                                if (previousPoints != plf.getTotalPoints()) {
                                    rank++;
                                }
                                json.put("rank", rank);
                                previousPoints = plf.getTotalPoints();
                                list.put(json);
                            }

                        }
                    }
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();
                }
            } catch (JSONException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

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
