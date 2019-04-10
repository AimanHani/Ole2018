/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import backGroudTask.Mailer;
import controller.AskDAO;
import controller.PrivateLeagueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PrivateLeague;
import org.json.JSONException;
import org.json.JSONObject;

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
        if (requests != null && requests.equals("leaderBoard")) {
            //System.out.println("HEY IM HERE");
            privateLeagueList = PrivateLeagueDAO.getAllPrivateLeague();
            if (privateLeagueList != null) {
                request.setAttribute("privateleague", privateLeagueList);
                rd = request.getRequestDispatcher("leaderBoardPrivate.jsp");
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
        if (requests != null && requests.equals("delete")) {
            int leagueId = Integer.parseInt(request.getParameter("leagueid"));
            String username = request.getParameter("username");
            String leaguename = request.getParameter("leaguename");
            ArrayList<Integer> logIdList = PrivateLeagueDAO.getListOfLogIds(leagueId);
            Boolean deleteSpecialLogs = PrivateLeagueDAO.deleteSpecialLogs(logIdList);
            Boolean deleteMatchesLogs = PrivateLeagueDAO.deleteMatchesLogs(logIdList);
            Boolean deleteLogs = PrivateLeagueDAO.deleteLogs(leagueId);
            Boolean deletePrivateLeagueTeams = PrivateLeagueDAO.deletePrivateLeagueTeams(leagueId);
            Boolean deletePrivateLeaugue = PrivateLeagueDAO.deletePrivateLeague(leagueId);
            Boolean deleteLeague = PrivateLeagueDAO.deleteLeague(leagueId);
            String userEmail = AskDAO.getUserEmail(username);
            Boolean emaiSentStatus = Mailer.send("olegroup18@gmail.com", "squadxole", userEmail, "System generated message from Ole team", "Your Private League Name: " + leaguename + " has been removed by the admin");
            if (deleteSpecialLogs && deleteMatchesLogs && deleteLogs && deletePrivateLeagueTeams && deletePrivateLeaugue && deleteLeague && emaiSentStatus) {
                System.out.println("SUCCESS");
                if (request.getParameter("origin").equals("app")) {
                    response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();
                    JSONObject json = new JSONObject();
                    try {
                        json.put("status", "successful");
                    } catch (JSONException ex) {
                        Logger.getLogger(PrivateLeagueServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.print(json);
                    out.flush();
                } else {
                    rd = request.getRequestDispatcher("./PrivateLeagueServlet?param=loadAll");
                    rd.forward(request, response);
                }
                
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
