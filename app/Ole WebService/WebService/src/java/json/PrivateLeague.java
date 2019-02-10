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
import controller.PublicLeagueDAO;
import java.util.ArrayList;
import model.AllPublicLeague;
import model.PublicLeague;
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
        //String leagueName = request.getParameter("leagueName");
        //model.PrivateLeague pl;
        //AllPublicLeague apl;
        JSONObject json = new JSONObject();
        //HashMap<Integer, model.PrivateLeague> privateLeaguesByname;
        //System.out.println(request.getHeader("params"));
        JSONObject headers = null;
        String head = "";
        String username = "";
        try {
            //headers = new JSONObject(request.getHeader("params"));

            head = request.getParameter("method");
            username = request.getParameter("username");
            //head = headers.getString("method");
            //username = headers.getString("username");
        } catch (Exception e) {
        }

        switch (head) {
            case "retrievePrivateLeague":
                //System.out.println("OLALALA");
                list = new JSONArray();
                parentJson = new JSONObject();

                ArrayList<model.PrivateLeague> privateLeagueList = PrivateLeagueDAO.getPrivateLeagues(username);
                //ArrayList<Integer> userPublicLeaguesID = PrivateLeagueDAO.getUserPublicLeaguesID(username);

                System.out.println(privateLeagueList.size());
                try {
                    for (int i = 0; i < privateLeagueList.size(); i++) {
//(leagueID, leagueName, prize, password, startDate,endDate,leagueID, userName, pointsAllocated, tournamentID));

                        json = new JSONObject();
                        model.PrivateLeague privateLeague = privateLeagueList.get(i);
                        json.put("leagueID", privateLeague.getPrivateLeaugeId());
                        json.put("leagueName", privateLeague.getLeagueName());
                        json.put("prize", privateLeague.getPrize());
                        json.put("password", privateLeague.getPassword());
                        json.put("startDate", privateLeague.getStartDate());
                        json.put("endDate", privateLeague.getEndDate());
                        json.put("userName", privateLeague.getUsername());
                        json.put("tournamentID", privateLeague.getTournamentId());
                        json.put("pointsAllocated", privateLeague.getPointsAllocated());
                        list.put(json);
                    }
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(PublicLeagueJson.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
                
                case "retrieveAllPrivateLeague":
                //System.out.println("OLALALA");
                list = new JSONArray();
                parentJson = new JSONObject();

                privateLeagueList = PrivateLeagueDAO.getAllPrivateLeagues();
                //ArrayList<Integer> userPublicLeaguesID = PrivateLeagueDAO.getUserPublicLeaguesID(username);

                System.out.println(privateLeagueList.size());
                try {
                    for (int i = 0; i < privateLeagueList.size(); i++) {
//(leagueID, leagueName, prize, password, startDate,endDate,leagueID, userName, pointsAllocated, tournamentID));

                        json = new JSONObject();
                        model.PrivateLeague privateLeague = privateLeagueList.get(i);
                        json.put("leagueID", privateLeague.getPrivateLeaugeId());
                        json.put("leagueName", privateLeague.getLeagueName());
                        json.put("prize", privateLeague.getPrize());
                        json.put("password", privateLeague.getPassword());
                        json.put("startDate", privateLeague.getStartDate());
                        json.put("endDate", privateLeague.getEndDate());
                        json.put("userName", privateLeague.getUsername());
                        json.put("tournamentID", privateLeague.getTournamentId());
                        json.put("pointsAllocated", privateLeague.getPointsAllocated());
                        list.put(json);
                    }
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(PublicLeagueJson.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            //select l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl on l.leagueId = pl.leagueKeyId where pl.username='billy'
            //select l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl on l.leagueId = pl.leagueKeyId where pl.username='billy'
case "retrieveTournament":
                System.out.println("HAY");
                list = new JSONArray();
                parentJson = new JSONObject();

                ArrayList<model.Tournament> tournamentList = PrivateLeagueDAO.getTournament();
                System.out.println("tournament: " + tournamentList.size());
                try {
                    for (int i = 0; i < tournamentList.size(); i++) {
                        json = new JSONObject();
                        model.Tournament tournament = tournamentList.get(i);
                        json.put("tournamentId", tournament.getTournamentId());
                        json.put("name", tournament.getName());
                        list.put(json);
                    }
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(PublicLeagueJson.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                break;
        }

        /*
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
         */
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
        System.out.println(request.getHeader("params"));
        JSONObject headers = null;
        String head = "";
        try {
            headers = new JSONObject(request.getHeader("params"));
            head = headers.getString("method");
        } catch (Exception e) {
        }

        switch (head) {
            case "insertNew":
                try {
                    headers = new JSONObject(request.getHeader("params"));
                    String username = headers.getString("username");
                    String password = headers.getString("password");
                    String prize = headers.getString("prize");
                    String leagueName = headers.getString("leagueName");
                    //int leagueId = Integer.parseInt(request.getParameter("leagueId"));
                    String startDate = headers.getString("startDate");
                    String endDate = headers.getString("endDate");
                    String pointsAllocated = headers.getString("pointsAllocated");
                    String tournamentId = headers.getString("tournamentId");
                    String specials = headers.getString("specials");
                    String teams = headers.getString("teams");
                    String status = "";

                    //System.out.println(username + password);
                    //System.out.println(tournamentId + leagueName);
                    status = PrivateLeagueDAO.createPrivateLeague(leagueName, prize, password, startDate, endDate, username, pointsAllocated, tournamentId, specials, teams);
                    if (status.equals("successful")) {

                        json.put("status", "successful");
                    } else if (status.equals("start date and end date is not in valid format")) {
                        json.put("status", "start date or end date is not in valid format");
                        String invalidMsg = "Something is wrong check checkS" + "/" + "";
                        String[] invalidString = {invalidMsg};
                        json.put("messages", invalidString);
                    } else {

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
                break;
            case "retrieveLeagueName":
                System.out.println(request.getHeader("params"));
                headers = null;
                JSONArray list = new JSONArray();
                JSONObject parentJson = new JSONObject();
                model.PrivateLeague pl;
                AllPublicLeague apl;
                json = new JSONObject();
                JSONObject league = new JSONObject();
                HashMap<Integer, model.PrivateLeague> privateLeaguesByname;
                try {
                    headers = new JSONObject(request.getHeader("params"));
                    String leagueName = headers.getString("leagueName");
                    if (leagueName != null) {
                        apl = PrivateLeagueDAO.retrieveLeague(leagueName);

                        if (apl != null) {
                            league.put("LeagueId", apl.getLeagueID());
                            league.put("tournamentId", apl.getTournamentID());
                            league.put("pointsAllocated", apl.getPointsAllocated());
                            league.put("leagueName", apl.getLeagueName());
                            privateLeaguesByname = PrivateLeagueDAO.retrievePrivateLeagueByName(apl.getLeagueID());

                            if (privateLeaguesByname != null) {
                                if (!privateLeaguesByname.isEmpty()) {

                                    Iterator it = privateLeaguesByname.entrySet().iterator();
                                    while (it.hasNext()) {

                                        Map.Entry pair = (Map.Entry) it.next();
                                        //System.out.println(pair.getKey() + " = " + pair.getValue());
                                        pl = (model.PrivateLeague) pair.getValue();
                                        league.put("privateLeagueID", pl.getPrivateLeaugeId());
                                        league.put("prize", pl.getPrize());
                                        league.put("startDate", pl.getStartDate());
                                        league.put("endDate", pl.getEndDate());
                                        league.put("leagueKeyId", pl.getLeagueId());
                                        league.put("userName", pl.getUsername());
                                        league.put("password", pl.getPassword());

                                        it.remove(); // avoids a ConcurrentModificationException
                                        //list.put(json);

                                    }
                                    System.out.println("im here" + json);
                                    json.put("status", "success");
                                    json.put("league", league);
                                    out.print(json);
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
                break;
            case "retrievePrivateLeague":
                //System.out.println("OLALALA");
                list = new JSONArray();
                parentJson = new JSONObject();
                String username = request.getParameter("username");
                ArrayList<model.PrivateLeague> privateLeagueList = PrivateLeagueDAO.getPrivateLeagues(username);
                //ArrayList<Integer> userPublicLeaguesID = PrivateLeagueDAO.getUserPublicLeaguesID(username);

                System.out.println(privateLeagueList.size());
                try {
                    for (int i = 0; i < privateLeagueList.size(); i++) {
//(leagueID, leagueName, prize, password, startDate,endDate,leagueID, userName, pointsAllocated, tournamentID));

                        json = new JSONObject();
                        model.PrivateLeague privateLeague = privateLeagueList.get(i);
                        json.put("leagueID", privateLeague.getPrivateLeaugeId());
                        json.put("leagueName", privateLeague.getLeagueName());
                        json.put("prize", privateLeague.getPrize());
                        json.put("password", privateLeague.getPassword());
                        json.put("startDate", privateLeague.getStartDate());
                        json.put("endDate", privateLeague.getEndDate());
                        json.put("userName", privateLeague.getUsername());
                        json.put("tournamentID", privateLeague.getTournamentId());
                        json.put("pointsAllocated", privateLeague.getPointsAllocated());
                        list.put(json);
                    }
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(PublicLeagueJson.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            //select l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl on l.leagueId = pl.leagueKeyId where pl.username='billy'
            //select l.leagueId, l.tournamentId, l.pointsAllocated, l.leagueName, pl.prize, pl.password, pl.startDate, pl.endDate, pl.username from league l inner join privateleague pl on l.leagueId = pl.leagueKeyId where pl.username='billy'
            case "retrieveTournament":
                System.out.println("HAY");
                list = new JSONArray();
                parentJson = new JSONObject();

                ArrayList<model.Tournament> tournamentList = PrivateLeagueDAO.getTournament();
                System.out.println("tournament: " + tournamentList.size());
                try {
                    for (int i = 0; i < tournamentList.size(); i++) {
                        json = new JSONObject();
                        model.Tournament tournament = tournamentList.get(i);
                        json.put("tournamentId", tournament.getTournamentId());
                        json.put("name", tournament.getName());
                        list.put(json);
                    }
                    parentJson.put("results", list);
                    out.print(parentJson);
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(PublicLeagueJson.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                break;
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
