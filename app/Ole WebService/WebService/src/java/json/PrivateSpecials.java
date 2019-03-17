/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.GetMatchesDAO;
import controller.ManageMatchesDAO;
import controller.ManageSpecialsDAO;
import controller.PrivateSpecialsDAO;
import controller.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.Match;
import model.PrivateSpecial;
import model.Special;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "PrivateSpecialsJson", urlPatterns = {"/json/privateSpecials"})
public class PrivateSpecials extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageSpecials</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageSpecials at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        int logId = Integer.parseInt(request.getParameter("logId"));
        String username = request.getParameter("username");
        System.out.println("LeagueID" + request.getParameter("leagueId"));
        int leagueId = Integer.parseInt(request.getParameter("leagueId"));
        
        PrivateSpecial s;
        HashMap<Integer, PrivateSpecial> specialsList = PrivateSpecialsDAO.getSpecials(logId, leagueId, username);
        try {
            Iterator it = specialsList.entrySet().iterator();
            while (it.hasNext()) {
                JSONObject json = new JSONObject();
                Map.Entry pair = (Map.Entry) it.next();
                //System.out.println(pair.getKey() + " = " + pair.getValue());
                s = (PrivateSpecial) pair.getValue();
                json.put("specialsId", s.getSpecialsID());
                json.put("description", s.getDescription());
                json.put("points", s.getPoints());
                json.put("doubleIt", s.getDoubleIt());
                json.put("prediction", s.getPrediction());
                it.remove(); // avoids a ConcurrentModificationException
                list.put(json);

            }
            parentJson.put("results", list);
            out.print(parentJson);
            out.flush();

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
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

//        System.out.println("params "+ request.getParameter("params"));

        String username = request.getParameter("username");
        int leagueId = Integer.parseInt(request.getParameter("leagueId"));

        int logId = UserDAO.getLogId(username, leagueId);

        JSONArray paramsValue;
        String results = "error";

        try {
            JSONObject params = new JSONObject(request.getParameter("params"));
            paramsValue = params.getJSONArray("params");

            if (paramsValue.length() > 0 && ManageSpecialsDAO.updateMultipleSpecialsPrediction(paramsValue, logId).equals("successful")) {
                results = "successful";
                json.put("status", results);

            } else {
                json.put("status", results);
            }
        } catch (JSONException ex) {
            Logger.getLogger(ManageMatches.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.print(json);
        out.flush();

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
