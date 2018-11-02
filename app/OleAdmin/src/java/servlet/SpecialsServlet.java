/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.APIDAO;
import controller.SpecialsDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Specials;

@WebServlet(name = "SpecialsServlet", urlPatterns = {"/SpecialsServlet"})
public class SpecialsServlet extends HttpServlet {

    ArrayList<Specials> specialsList = null;
    RequestDispatcher rd = null;
    String requests = null;
    String id = "";

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

        requests = request.getParameter("param");
        id = request.getParameter("id");

        //before specials.jsp loads, this servlet will be called first to load all specials from the db
        if (requests != null && requests.equals("loadAll")) {
            //System.out.println("HEY IM HERE");
            specialsList = SpecialsDAO.getAllSpecials();
            if (specialsList != null) {
                request.setAttribute("specials", specialsList);
                if (id != null && !id.equals("")) {
                    rd = request.getRequestDispatcher("leagueSpecials.jsp?id="+id);
                    //System.out.println(usersList.size());
                    rd.forward(request, response);
                }
                rd = request.getRequestDispatcher("specials.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }

        }

        if (requests != null && requests.equals("add")) {
            //System.out.println("HEY IM HERE");
            String description = request.getParameter("description");
            Boolean outcome = SpecialsDAO.addSpecials(description);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./SpecialsServlet?param=loadAll");
                rd.forward(request, response);
            }

        }

        if (requests != null && requests.equals("delete")) {
            //System.out.println("HEY IM HERE");
            String specialsId = request.getParameter("id");
            Boolean outcome = SpecialsDAO.deleteSpecials(Integer.parseInt(specialsId));
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./SpecialsServlet?param=loadAll");
                rd.forward(request, response);
            }

        }

        if (requests != null && requests.equals("update")) {
            String[] specialsList = request.getParameterValues("specials");
            Boolean outcome = SpecialsDAO.updateSpecials(specialsList);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./SpecialsServlet?param=loadAll");
                rd.forward(request, response);
            }

        }

        if (requests != null && requests.equals("updateLeague")) {
            String[] specialsList = request.getParameterValues("specials");
            String leagueId = request.getParameter("id");
            Boolean outcome = APIDAO.addSpecials(specialsList, leagueId);
            if (outcome) {
                System.out.println("SUCCESS");
                response.sendRedirect("api.jsp?status=tournament " + outcome);
                //rd.forward(request, response);
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
