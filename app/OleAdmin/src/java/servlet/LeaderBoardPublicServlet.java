/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.ScoreBoardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateLeague;
import model.PrivateLeagueProfile;
import model.PublicLeagueProfile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "LeaderBoardPublicServlet", urlPatterns = {"/LeaderBoardPublicServlet"})
public class LeaderBoardPublicServlet extends HttpServlet {

    RequestDispatcher rd = null;
    String requests = null;

    ArrayList<PublicLeagueProfile> pListWithRank = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String league = request.getParameter("league");
        requests = request.getParameter("param");

        if (requests != null && requests.equals("loadAll")) {

            PublicLeagueProfile plf;
            ArrayList<PublicLeagueProfile> pList = new ArrayList();

            pList = ScoreBoardDAO.getUsersAndTheirTotalPoints(2);
            Collections.sort(pList);
            if (pList.size() != 0) {
                Collections.sort(pList);
                int rank = 1;
                int previousPoints = 0;
                for (int i = 0; i < pList.size(); i++) {

                    plf = new PublicLeagueProfile();
                    plf = pList.get(i);

                    if (previousPoints != plf.getTotalPoints() && i > 0) {
                        rank++;
                    }

                    pListWithRank.add(new PublicLeagueProfile(plf.getUsername(), plf.getLeagueID(), plf.getTotalPoints(), rank,ScoreBoardDAO.getUserCountry(plf.getUsername())));

                    previousPoints = plf.getTotalPoints();

                }

            }
            request.setAttribute("publicProfileList", pListWithRank);
            rd = request.getRequestDispatcher("leaderBoardPublic.jsp");
            rd.forward(request, response);
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
