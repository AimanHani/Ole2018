/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.GetMatchesDAO;
import controller.JoinPublicLeaguesDAO;
import controller.ManageMatchesDAO;
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
import model.Match;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "ManageMatchesJson", urlPatterns = {"/json/manageMatches"})
public class ManageMatches extends HttpServlet {

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
            out.println("<title>Servlet ManageMatches</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageMatches at " + request.getContextPath() + "</h1>");
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
        HashMap<Integer, Match> recentMatches = GetMatchesDAO.getRecentMatches();
        try {
            Iterator it = recentMatches.entrySet().iterator();
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
            out.print(parentJson);
            out.flush();

        } catch (JSONException e) {
            e.printStackTrace();
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
        JSONObject json = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        System.out.println("logId " + request.getParameter("logId") + " " + request.getParameter("params"));

        int logID = Integer.parseInt(request.getParameter("logId"));
        JSONArray paramsValue = null;
        String results = "error";

        try {
            JSONObject params = new JSONObject("{" + request.getParameter("params") + "}");
            paramsValue = params.getJSONArray("params");

            if (paramsValue != null && ManageMatchesDAO.insertMultipleMatchLogs(paramsValue).equals("successful")) {
                results = "successful";
                json.put("status", results);

            } else {
                json.put("status", results);
            }
        } catch (JSONException ex) {
            Logger.getLogger(ManageMatches.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.print(json);
        out.flush();

//        int logID = Integer.parseInt(request.getParameter("logId"));
//        int matchID = Integer.parseInt(request.getParameter("matchId"));
//        int team1Prediction = Integer.parseInt(request.getParameter("team1Prediction"));
//        int team2Prediction = Integer.parseInt(request.getParameter("team2Prediction"));
//        boolean doubleIt = Boolean.parseBoolean(request.getParameter("doubleIt"));
//
//        String status = "";
//
//        try {
//            status = ManageMatchesDAO.insertMLog(logID, matchID, team1Prediction, team2Prediction, doubleIt);
//            if (!status.equals("error")) {
//                json.put("status", "successful");
//            } else {
//                status = ManageMatchesDAO.updateMatchesLog(logID, matchID, team1Prediction, team2Prediction, doubleIt);
//                if (!status.equals("error")) {
//                    json.put("status", "successful");
//                } else {
//                    json.put("status", "error");
//                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
//                    String[] invalidString = {invalidMsg};
//                    json.put("messages", invalidString);
//                }
//            }
//            out.print(json);
//            out.flush();
//
//        } catch (JSONException ex) {
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
