/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.GetMatchesDAO;
import controller.JoinPublicLeaguesDAO;
import controller.PrivateLeagueMatchesDAO;
import controller.PublicLeagueDAO;
import controller.UserDAO;
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
import model.AllPublicLeague;
import model.Match;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "GetPrivateMatchesJson", urlPatterns = {"/json/getPrivateMatches"})
public class PrivateLeagueMatches extends HttpServlet {

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
            out.println("<title>Servlet GetMatch</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetMatch at " + request.getContextPath() + "</h1>");
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
        Match m;
        String parameter = request.getParameter("matchStatus");

        if (parameter != null && parameter.equals("future")) {
            String username = request.getParameter("username");
            int leagueId = Integer.parseInt(request.getParameter("leagueId"));
            int logId = UserDAO.getLogId(username, leagueId);
            HashMap<Integer, JSONObject> existingMatchPrediction = PrivateLeagueMatchesDAO.getExistingMatchPrediction(logId);
            HashMap<Integer, Match> recentMatches = PrivateLeagueMatchesDAO.getRecentMatches(leagueId);
            try {
                Iterator it = recentMatches.entrySet().iterator();
                while (it.hasNext()) {
                    JSONObject json = new JSONObject();
                    Map.Entry pair = (Map.Entry) it.next();
                    int numberOfParticipants = 0;
                    m = (Match) pair.getValue();
                    int matchId = m.getMatchID();
                    json.put("matchId", matchId);
                    json.put("tournamentId", m.getTournamentID());
                    json.put("matchDate", m.getMatchDate());
                    json.put("matchTime", m.getMatchTime());
                    json.put("team1", m.getTeam1());
                    json.put("team2", m.getTeam2());

                    if (existingMatchPrediction.containsKey(matchId)) {
                        JSONObject obj = existingMatchPrediction.get(matchId);
                        json.put("team1Score", obj.get("team1_prediction"));
                        json.put("team2Score", obj.get("team2_prediction"));
                    } else {
                        json.put("team1Score", m.getTeam1Score());
                        json.put("team2Score", m.getTeam2Score());
                    }

                    it.remove(); // avoids a ConcurrentModificationException
                    list.put(json);

                }
                System.out.println("future" + list);
                parentJson.put("results", list);
                parentJson.put("status", "success");
                parentJson.put("period", "future");
                out.print(parentJson);
                out.flush();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }if (parameter != null && parameter.equals("past")) {
            String username = request.getParameter("username");
            int leagueId = Integer.parseInt(request.getParameter("leagueId"));
            int logId = UserDAO.getLogId(username, leagueId);
            HashMap<Integer, Match> pastMatches = PrivateLeagueMatchesDAO.getPastMatches(leagueId);
            try {
                Iterator it = pastMatches.entrySet().iterator();
                while (it.hasNext()) {
                    JSONObject json = new JSONObject();
                    Map.Entry pair = (Map.Entry) it.next();
                    int numberOfParticipants = 0;
                    //System.out.println(pair.getKey() + " = " + pair.getValue());
                    m = (Match) pair.getValue();
                    json.put("matchId", m.getMatchID());
                    json.put("tournamentId", m.getTournamentID());
                    json.put("matchDate", m.getMatchDate());
                    json.put("matchTime", m.getMatchTime());
                    json.put("team1", m.getTeam1());
                    json.put("team2", m.getTeam2());
                    json.put("team1Score", m.getTeam1Score());
                    json.put("team2Score", m.getTeam2Score());
                    it.remove(); // avoids a ConcurrentModificationException
                    list.put(json);

                }
                parentJson.put("results", list);
                parentJson.put("status", "success");
                parentJson.put("period", "past");
                out.print(parentJson);
                out.flush();

            } catch (JSONException e) {
                e.printStackTrace();
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
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        int matchID = Integer.parseInt(request.getParameter("matchId"));
        String status = "";
        Match m;

        try {

            m = PrivateLeagueMatchesDAO.getOneMatch(matchID);
            if (m != null) {
                JSONObject json = new JSONObject();
                json.put("matchId", m.getMatchID());
                json.put("matchDate", m.getMatchDate());
                json.put("matchTime", m.getMatchTime());
                json.put("team1", m.getTeam1());
                json.put("team2", m.getTeam2());

                list.put(json);
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
