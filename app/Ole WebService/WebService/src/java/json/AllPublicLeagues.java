/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.PublicLeagueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AllPublicLeague;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "AllPublicLeaguesJson", urlPatterns = {"/json/allPublicLeagues"})
public class AllPublicLeagues extends HttpServlet {

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
            out.println("<title>Servlet AllPublicLeagues</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllPublicLeagues at " + request.getContextPath() + "</h1>");
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
        AllPublicLeague apl;
        HashMap<Integer, AllPublicLeague> AllPublicLeagues = PublicLeagueDAO.retrieveAllPublicLeagues();
        try {
            Iterator it = AllPublicLeagues.entrySet().iterator();
            while (it.hasNext()) {
                JSONObject json = new JSONObject();
                Map.Entry pair = (Map.Entry) it.next();
                int numberOfParticipants = 0;
                //System.out.println(pair.getKey() + " = " + pair.getValue());
                apl = (AllPublicLeague) pair.getValue();
                json.put("leagueID", apl.getLeagueID());
                json.put("prize", apl.getPrize());
                json.put("tournamentID", apl.getTournamentID());
                json.put("pointsAllocated", apl.getPointsAllocated());
                json.put("leagueName", apl.getLeagueName());
                json.put("tournamentName", apl.getTournamentName());
                numberOfParticipants = PublicLeagueDAO.getNumbersOfParticipantsInTheLeague(apl.getLeagueID());
                json.put("numberOfParticipants", numberOfParticipants);
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
