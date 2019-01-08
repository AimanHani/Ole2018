/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.FaqDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Faq;

@WebServlet(name = "FaqServlet", urlPatterns = {"/FaqServlet"})
public class FaqServlet extends HttpServlet {
    
    ArrayList<Faq> faqList = null;
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
            faqList = FaqDAO.getAllFaq();
            if (faqList != null) {
                request.setAttribute("faq", faqList);
                rd = request.getRequestDispatcher("faq.jsp");
                rd.forward(request, response);
            }
        }
        if ((requests != null && requests.equals("loadAll")) || (request.getParameter("loadUnread") != null)) {
            faqList = FaqDAO.getUnreadFaq();
            if (faqList != null) {
                request.setAttribute("faq", faqList);
                rd = request.getRequestDispatcher("faq.jsp");
                rd.forward(request, response);
            }
        }
        if (requests != null && requests.equals("addQuestion")) {
            String question = request.getParameter("question");
            Boolean outcome = FaqDAO.addQuesFaq(question);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./FaqServlet?param=loadAll");
                rd.forward(request, response);
            }else{
                System.out.println("fail/duplicate");
                rd = request.getRequestDispatcher("./FaqServlet?param=loadAll");
                rd.forward(request, response);
            }
        }
        
        if (requests != null && requests.equals("addAnswer")) {
            String answer = request.getParameter("answer");
            String faqId = request.getParameter("faqId");
            System.out.println("Hello " + faqId);
            System.out.println("Hello " + answer);
            Boolean outcome = FaqDAO.addAnsFaq(Integer.parseInt(faqId),answer);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./FaqServlet?param=loadAll");
                rd.forward(request, response);
            }
        }

        if (requests != null && requests.equals("delete")) {
            String faqId = request.getParameter("faqId");
            Boolean outcome = FaqDAO.deleteFaq(Integer.parseInt(faqId));
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./FaqServlet?param=loadAll");
                rd.forward(request, response);
            }

        }
        
        if (requests != null && requests.equals("search")) {
            //System.out.println("HEY IM HERE TEEHEE");
            String keyword = request.getParameter("keyword");
            faqList = FaqDAO.searchKeyword(keyword);
            if (faqList != null) {
                request.setAttribute("faq", faqList);
                rd = request.getRequestDispatcher("faq.jsp");
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
