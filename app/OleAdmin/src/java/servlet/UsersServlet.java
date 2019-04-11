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
import model.Users;
import controller.UsersDAO;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "UsersServlet", urlPatterns = {"/UsersServlet"})
public class UsersServlet extends HttpServlet {

    ArrayList<Users> usersList = null;
    RequestDispatcher rd = null;
    String requests = null;

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

        //before users.jsp loads, this servlet will be called first to load all users from the db
        requests = request.getParameter("param");
        if (requests != null && requests.equals("loadAll")) {
            //System.out.println("HEY IM HERE");
            usersList = UsersDAO.getAllUsers();
            if (usersList != null) {
                request.setAttribute("users", usersList);
                rd = request.getRequestDispatcher("users.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }
        }

        //before userDetail.jsp loads, this servlet will be called first
        //to pick the index of arraylist<user> the user detail will extract info from
        //requests = request.getParameter("param");
        if (requests != null && requests.equals("details")) {
            String userIndex = request.getParameter("index");
            Users userObj = usersList.get(Integer.parseInt(userIndex));
            request.setAttribute("userObj", userObj);
            rd = request.getRequestDispatcher("userDetail.jsp");
            rd.forward(request, response);
        }

        //search for users with matching usernames
        //requests = request.getParameter("param");
        if (requests != null && requests.equals("search")) {
            //System.out.println("HEY IM HERE TEEHEE");
            String username = request.getParameter("username");
            usersList = UsersDAO.searchUsername(username);
            if (usersList != null) {
                request.setAttribute("users", usersList);
                rd = request.getRequestDispatcher("users.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }
        }
        
        //delete user from db based on usernames
        //requests = request.getParameter("param");
        if (requests != null && requests.equals("delete")) {
            String username = request.getParameter("username");
            System.out.println(username);
            Boolean outcome = UsersDAO.deleteUser(username);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./UsersServlet?param=loadAll");
                rd.forward(request, response);
            }
        }
        
        if (requests != null && requests.equals("adminChangePassword")) {
            String userIndex = request.getParameter("password");
            Users userObj = usersList.get(Integer.parseInt(userIndex));
            request.setAttribute("userObj", userObj);
            rd = request.getRequestDispatcher("userDetail.jsp");
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
