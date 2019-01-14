/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.AskDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ask;

@WebServlet(name = "AskServlet", urlPatterns = {"/AskServlet"})
public class AskServlet extends HttpServlet {
    
    ArrayList<Ask> askList = null;
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
        
                requests = request.getParameter("param");

        //before specials.jsp loads, this servlet will be called first to load all specials from the db
        if ((requests != null && requests.equals("loadAll")) || (request.getParameter("loadAll") != null)) {
            askList = AskDAO.getAll();
            if (askList != null) {
                request.setAttribute("ask", askList);
                rd = request.getRequestDispatcher("askOle.jsp");
                rd.forward(request, response);
            }
        }
        if ((requests != null && requests.equals("loadAll")) || (request.getParameter("loadUnread") != null)) {
            askList = AskDAO.getUnreadAsk();
            if (askList != null) {
                request.setAttribute("ask", askList);
                rd = request.getRequestDispatcher("askOle.jsp");
                rd.forward(request, response);
            }
        }
        
        if ((requests != null && requests.equals("loadAll")) || (request.getParameter("catPublic") != null)) {
            askList = AskDAO.getCategoryAsk("Public League");
            if (askList != null) {
                request.setAttribute("ask", askList);
                rd = request.getRequestDispatcher("askOle.jsp");
                rd.forward(request, response);
            }
        }
        
        if ((requests != null && requests.equals("loadAll")) || (request.getParameter("catPrivate") != null)) {
            askList = AskDAO.getCategoryAsk("Private League");
            if (askList != null) {
                request.setAttribute("ask", askList);
                rd = request.getRequestDispatcher("askOle.jsp");
                rd.forward(request, response);
            }
        }
        
        if ((requests != null && requests.equals("loadAll")) || (request.getParameter("catGeneral") != null)) {
            askList = AskDAO.getCategoryAsk("General");
            if (askList != null) {
                request.setAttribute("ask", askList);
                rd = request.getRequestDispatcher("askOle.jsp");
                rd.forward(request, response);
            }
        }
        
        
        if (requests != null && requests.equals("addQuestion")) {
            String question = request.getParameter("question");
            Boolean outcome = AskDAO.addQuesAsk(question);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./AskServlet?param=loadAll");
                rd.forward(request, response);
            }else{
                System.out.println("fail/duplicate");
                rd = request.getRequestDispatcher("./AskServlet?param=loadAll");
                rd.forward(request, response);
            }
        }
        
        if (requests != null && requests.equals("addAnswer")) {
            String answer = request.getParameter("answer");
            String askId = request.getParameter("askId");
            System.out.println("Hello " + askId);
            System.out.println("Hello " + answer);
            Boolean outcome = AskDAO.addAnsAsk(Integer.parseInt(askId),answer);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./AskServlet?param=loadAll");
                rd.forward(request, response);
            }
        }

        if (requests != null && requests.equals("delete")) {
            String askId = request.getParameter("askId");
            Boolean outcome = AskDAO.deleteAsk(Integer.parseInt(askId));
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./AskServlet?param=loadAll");
                rd.forward(request, response);
            }

        }
        
        if (requests != null && requests.equals("search")) {
            //System.out.println("HEY IM HERE TEEHEE");
            String keyword = request.getParameter("keyword");
            askList = AskDAO.searchKeyword(keyword);
            if (askList != null) {
                request.setAttribute("ask", askList);
                rd = request.getRequestDispatcher("askOle.jsp");
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
