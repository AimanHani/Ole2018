/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.PrivateLeagueDAO;
import controller.ScoreBoardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateLeague;
import model.PrivateLeagueProfile;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "LeaderBoardPrivateServlet", urlPatterns = {"/LeaderBoardPrivateServlet"})
public class LeaderBoardPrivateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    PrivateLeagueProfile plf;
    ArrayList<PrivateLeagueProfile> pList = new ArrayList();
    ArrayList<Integer> privateLeagueIDs = new ArrayList();
    RequestDispatcher rd = null;
    ArrayList<PrivateLeagueProfile> pListWithRanking;
    String requests = null;
    ArrayList<PrivateLeague> privateLeagueList = null;
    int rank = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        privateLeagueList = PrivateLeagueDAO.getAllPrivateLeague();
        String userIndex = request.getParameter("index");
        int leagueId = Integer.parseInt(request.getParameter("leagueId"));
//        
//        PrivateLeague pl = new PrivateLeague();
//        pl = privateLeagueList.get(Integer.parseInt(userIndex));
        pListWithRanking = new ArrayList();
        pList = ScoreBoardDAO.getUsersAndTheirTotalPointsPrivate(leagueId);

        if (!pList.isEmpty()) {
            Collections.sort(pList);

            int previousPoints = 0;
            for (int j = 0; j < pList.size(); j++) {
                plf = new PrivateLeagueProfile();
                plf = pList.get(j);
                if (previousPoints != plf.getTotalPoints() && j > 0) {
                    rank++;
                }
                previousPoints = plf.getTotalPoints();
                pListWithRanking.add(new PrivateLeagueProfile(plf.getUsername(), plf.getLeagueID(), plf.getLeagueName(), plf.getTotalPoints(), rank, ScoreBoardDAO.getUserCountry(plf.getUsername())));
            }
        }

        request.setAttribute("privateProfileList", pListWithRanking);
        rd = request.getRequestDispatcher("leaderBoardPrivateDetails.jsp");
        rd.forward(request, response);
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
