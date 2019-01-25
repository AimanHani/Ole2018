/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.AskOleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.PrivateLeagueDAO;
import model.AllPublicLeague;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "PrivateLeagueJson", urlPatterns = {"/json/privateLeague"})
public class PrivateLeague extends HttpServlet {

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
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String leagueName = request.getParameter("leagueName");
        model.PrivateLeague pl;
        AllPublicLeague apl;
        JSONObject json = new JSONObject();
        HashMap<Integer, model.PrivateLeague> privateLeaguesByname;
        try {
            if (leagueName != null) {
                apl = PrivateLeagueDAO.retrieveLeague(leagueName);
                if (apl != null) {
                    json.put("LeagueId", apl.getLeagueID());
                    json.put("tournamentId", apl.getTournamentID());
                    json.put("pointsAllocated", apl.getPointsAllocated());
                    json.put("leagueName", apl.getLeagueName());
                    privateLeaguesByname = PrivateLeagueDAO.retrievePrivateLeagueByName(apl.getLeagueID());

                    if (privateLeaguesByname != null) {
                        if (privateLeaguesByname.size() != 0) {

                            Iterator it = privateLeaguesByname.entrySet().iterator();
                            while (it.hasNext()) {

                                Map.Entry pair = (Map.Entry) it.next();
                                //System.out.println(pair.getKey() + " = " + pair.getValue());
                                pl = (model.PrivateLeague) pair.getValue();
                                json.put("privateLeagueID", pl.getPrivateLeaugeId());
                                json.put("prize", pl.getPrize());
                                json.put("startDate", pl.getStartDate());
                                json.put("endDate", pl.getEndDate());
                                json.put("leagueKeyId", pl.getLeagueId());
                                json.put("userName", pl.getUsername());
                                json.put("password", pl.getPassword());

                                it.remove(); // avoids a ConcurrentModificationException
                                //list.put(json);

                            }
                            parentJson.put("status", "success");
                            parentJson.put("results", json);
                            out.print(parentJson);
                            out.flush();
                        } else {
                            json.put("private league", "no private league exists under this league");
                            list.put(json);
                            parentJson.put("results", list);
                            out.print(parentJson);
                            out.flush();
                        }
                    } else {

                        json.put("status", "error");
                        String invalidMsg = "No private league exists under this league" + "/" + "";
                        String[] invalidString = {invalidMsg};
                        json.put("messages", invalidString);
                        parentJson.put("results", list);
                        out.print(parentJson);
                        out.flush();
                    }

                } else {
                    json.put("status", "error");
                    String invalidMsg = "Cannot retrieve league" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
            JSONObject json = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String prize = request.getParameter("prize");
        String leagueName = request.getParameter("leagueName");
        //int leagueId = Integer.parseInt(request.getParameter("leagueId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pointsAllocated = request.getParameter("pointsAllocated");
        String tournamentId = request.getParameter("tournamentId");
        String status = "";
        try {
            
            System.out.println(username+password);
                status = PrivateLeagueDAO.createPrivateLeague(leagueName, prize, password, startDate, endDate, username, pointsAllocated, tournamentId);
                if (status.equals("successful")) {

                    json.put("status", "successful");
                }
                else if(status.equals("start date and end date is not in valid format")){
                    json.put("status", "start date or end date is not in valid format");
                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                }
                else {

                    json.put("status", "error");
                    String invalidMsg = "Something is wrong check checkS" + "/" + "";
                    String[] invalidString = {invalidMsg};
                    json.put("messages", invalidString);
                }
                out.print(json);
                out.flush();
           

        } catch (JSONException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
