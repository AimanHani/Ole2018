/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.PrivateLeagueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateLeague;

/**
 *
 * @author user
 */
@WebServlet(name = "PrivateLeagueServlet", urlPatterns = {"/PrivateLeagueServlet"})
public class PrivateLeagueServlet extends HttpServlet {

    ArrayList<PrivateLeague> privateLeagueList = null;
    RequestDispatcher rd = null;
    String requests = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requests = request.getParameter("param");

        //before publicleague.jsp loads, this servlet will be called first to load all public league from the db
        if (requests != null && requests.equals("loadAll")) {
            //System.out.println("HEY IM HERE");
            privateLeagueList = PrivateLeagueDAO.getAllPrivateLeague();
            if (privateLeagueList != null) {
                request.setAttribute("privateleague", privateLeagueList);
                rd = request.getRequestDispatcher("privateLeague.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }

        }
        if (requests != null && requests.equals("details")) {
            String userIndex = request.getParameter("index");
            PrivateLeague pl = privateLeagueList.get(Integer.parseInt(userIndex));
            request.setAttribute("privateLeague", pl);
            rd = request.getRequestDispatcher("privateLeagueDetails.jsp");
            rd.forward(request, response);
        }

        if (requests != null && requests.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int points = Integer.parseInt(request.getParameter("points"));
            String prize = request.getParameter("prize");
            Boolean outcomePoints = PrivateLeagueDAO.updatePoints(id, points);
            Boolean outcomePrize = PrivateLeagueDAO.updatePrize(id, prize);
            if (outcomePoints && outcomePrize) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./PrivateLeagueServlet?param=loadAll");
                rd.forward(request, response);
            }
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
