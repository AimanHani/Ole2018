package servlet;

import controller.APIDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "APIServlet", urlPatterns = {"/APIServlet"})
public class APIServlet extends HttpServlet {
    boolean result;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("team") != null) {
            // team button pressed.
            //System.out.println("team");
            result = APIDAO.loadTeam();
            response.sendRedirect("api.jsp?status=team "+result);
        }
        if (request.getParameter("tournament") != null) {
            // tournament button pressed.
            //System.out.println("tournament");
            String result1 = APIDAO.loadTournament();
            if (!result1.equals("false")){
                 //RequestDispatcher rd = request.getRequestDispatcher("./SpecialsServlet?param=loadAll");
                 //rd.forward(request, response);
                 response.sendRedirect("./SpecialsServlet?param=loadAll&id="+result1);
            }else{
            response.sendRedirect("api.jsp?status=tournament "+result);
            }
        }
        if (request.getParameter("match") != null) {
            // match button pressed.
            //System.out.println("match");
            result = APIDAO.loadMatch();
            response.sendRedirect("api.jsp?status=match "+result);
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
