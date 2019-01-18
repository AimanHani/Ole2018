/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.MatchesDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "APICallServlet", urlPatterns = {"/APICallServlet"})
public class APICallServlet extends HttpServlet {

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
            out.println("<title>Servlet APICallServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet APICallServlet at " + request.getContextPath() + "</h1>");
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
        JSONObject json = MatchesDAO.loadAPIMatch();
        JSONObject result = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONArray array = null;
        try {
            array = json.getJSONArray("array");
        } catch (JSONException ex) {
            Logger.getLogger(APICallServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject jb = null;
        for (int i = 0; i < array.length(); i++) {
            try {
                jb = array.getJSONObject(i);
                result.put("result", "fuck");
            } catch (JSONException ex) {
                Logger.getLogger(APICallServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jb1 = null;
            try {
                jb1 = jb.getJSONObject("api");
            } catch (JSONException ex) {
                Logger.getLogger(APICallServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            JSONObject jb2 = null;

            JSONObject jb3 = null;
            try {
                jb3 = jb1.getJSONObject("fixtures");
                Iterator it = jb3.keys();
                while (it.hasNext()) {
                    JSONObject json2 = new JSONObject();
                    String keyStr = (String) it.next();
                    Object keyvalue = jb3.get(keyStr);
                    JSONObject eachMatch = (JSONObject)jb3.get(keyStr);
                    String leagueID = (String) eachMatch.get("league_id");
                    String strDate = (String) eachMatch.get("event_date");
                    String eTime = (String) eachMatch.get("event_timestamp");
                    String team1 = (String) eachMatch.get("homeTeam_id");
                    String team2 = (String) eachMatch.get("awayTeam_id");
                    //String team1_score = (String) eachMatch.get("goalsHomeTeam");
                    //String team2_score = (String) eachMatch.get("goalsAwayTeam");
                    json2.put("test", leagueID);
                    json2.put("start", strDate);
                    json2.put("end", eTime);
                    json2.put("team1", team1);
                    json2.put("team2", team2);
                    //json2.put("t1 score", team1_score);
                    //json2.put("t2 score", team2_score);

                    it.remove(); // avoids a ConcurrentModificationException
                    list.put(json2);

                }

            } catch (JSONException ex) {
                Logger.getLogger(APICallServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.print(list);
            out.flush();
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