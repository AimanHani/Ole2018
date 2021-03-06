/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Matches;
import controller.MatchesDAO;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "MatchesServlet", urlPatterns = {"/MatchesServlet"})
public class MatchesServlet extends HttpServlet {

    ArrayList<Matches> matchesList = null;
    RequestDispatcher rd = null;

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

        if (request.getParameter("loadAll") != null) {
            matchesList = MatchesDAO.getAllMatches();
            if (matchesList != null) {
                request.setAttribute("matches", matchesList);
                rd = request.getRequestDispatcher("matches.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }
        } else if (request.getParameter("loadPast") != null) {
            matchesList = MatchesDAO.getPastMatches();
            if (matchesList != null) {
                request.setAttribute("matches", matchesList);
                rd = request.getRequestDispatcher("matches.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }
        } else if (request.getParameter("loadUpcoming") != null) {
            matchesList = MatchesDAO.getUpcomingMatches();
            if (matchesList != null) {
                request.setAttribute("matches", matchesList);
                rd = request.getRequestDispatcher("matches.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }
        } else {
            matchesList = MatchesDAO.getAllMatches();
            if (matchesList != null) {
                request.setAttribute("matches", matchesList);
                rd = request.getRequestDispatcher("matches.jsp");
                //System.out.println(usersList.size());
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
