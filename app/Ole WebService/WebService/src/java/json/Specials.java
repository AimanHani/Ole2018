/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.GetMatchesDAO;
import controller.ManageMatchesDAO;
import controller.ManageSpecialsDAO;
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
import model.Special;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "GetSpecialsJson", urlPatterns = {"/json/getSpecials"})
public class Specials extends HttpServlet {

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
        
        Special s;
        HashMap<Integer, Special> specialsList = ManageSpecialsDAO.getAllSpecials();
        try {
            Iterator it = specialsList.entrySet().iterator();
            while (it.hasNext()) {
                JSONObject json = new JSONObject();
                Map.Entry pair = (Map.Entry) it.next();
                //int numberOfParticipants = 0;
                //System.out.println(pair.getKey() + " = " + pair.getValue());
                s = (Special) pair.getValue();
                json.put("specialsId", s.getSpecialsID());
                json.put("description", s.getDescription());
                json.put("points", s.getPoints());
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
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        JSONObject json = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        int logID = Integer.parseInt(request.getParameter("logid"));
        String specialsID = request.getParameter("specialsId");
        String prediction = request.getParameter("prediction");
        
        if (prediction!=null&&specialsID!=null) {
            String status = "";

            try {
                int sID = Integer.parseInt(specialsID);

                status = ManageSpecialsDAO.updateSpecialsPrediction(logID, sID, prediction);
                if (!status.equals("error")) {

                    json.put("Status", "Update specials successful");

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
        }
        else{
            Special s;
        HashMap<Integer, Special> specialsList = ManageSpecialsDAO.getSpecials(logID);
        try {
            Iterator it = specialsList.entrySet().iterator();
            while (it.hasNext()) {
                json = new JSONObject();
                Map.Entry pair = (Map.Entry) it.next();
                int numberOfParticipants = 0;
                //System.out.println(pair.getKey() + " = " + pair.getValue());
                s = (Special) pair.getValue();
                json.put("specialsId", s.getSpecialsID());
                json.put("description", s.getDescription());
                json.put("points", s.getPoints());
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
