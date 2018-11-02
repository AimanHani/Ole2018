package servlet;

import controller.PublicLeagueDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PublicLeague;


@WebServlet(name = "PublicLeagueServlet", urlPatterns = {"/PublicLeagueServlet"})
public class PublicLeagueServlet extends HttpServlet {
    
    ArrayList<PublicLeague> publicLeagueList = null;
    RequestDispatcher rd = null;
    String requests = null;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        requests = request.getParameter("param");
        
        //before publicleague.jsp loads, this servlet will be called first to load all public league from the db
        if (requests != null && requests.equals("loadAll")) {
            //System.out.println("HEY IM HERE");
            publicLeagueList = PublicLeagueDAO.getAllPublicLeague();
            if (publicLeagueList != null) {
                request.setAttribute("publicleague", publicLeagueList);
                rd = request.getRequestDispatcher("publicLeague.jsp");
                //System.out.println(usersList.size());
                rd.forward(request, response);
            }

        }
        if (requests != null && requests.equals("details")) {
            String userIndex = request.getParameter("index");
            PublicLeague pl = publicLeagueList.get(Integer.parseInt(userIndex));
            request.setAttribute("publicLeague", pl);
            rd = request.getRequestDispatcher("publicLeagueDetails.jsp");
            rd.forward(request, response);
        }
        
        
        if (requests != null && requests.equals("updatePoints")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int points = Integer.parseInt(request.getParameter("points"));
            Boolean outcome = PublicLeagueDAO.updatePoints(id, points);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./PublicLeagueServlet?param=loadAll");
                rd.forward(request, response);
            }
        }
        
        if (requests != null && requests.equals("updatePrize")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String prize = request.getParameter("prize");
            Boolean outcome = PublicLeagueDAO.updatePrize(id, prize);
            if (outcome) {
                System.out.println("SUCCESS");
                rd = request.getRequestDispatcher("./PublicLeagueServlet?param=loadAll");
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
