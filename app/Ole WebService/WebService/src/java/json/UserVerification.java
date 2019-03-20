/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import backgroundTask.Mailer;
import backgroundTask.SMSVerification;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "UserVerification", urlPatterns = {"/json/userVerification"})
public class UserVerification extends HttpServlet {

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
            out.println("<title>Servlet UserVerfication</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserVerfication at " + request.getContextPath() + "</h1>");
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
        JSONArray list = new JSONArray();
        JSONObject parentJson = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if (request.getParameter("email") != null) {
            String email = request.getParameter("email");
            
            String verificationNumber = (int) (Math.random() * 9000) + 1000 + "";
            //Boolean smsStatus = SMSVerification.send(userNumber, verificationNumber);
            Boolean emailSentStatus = Mailer.send("olegroup18@gmail.com", "squadxole", email, "Confirm Your Sign Up", "Your Ole Verification Code: " + verificationNumber);
            
            //System.out.println("sms: " + smsStatus);
            System.out.println("email: " + emailSentStatus);
            try {

                JSONObject json = new JSONObject();
                
                if (emailSentStatus){
                    json.put("status", "successful");
                } else {
                    json.put("status", "failed");
                }
                
                json.put("verificationNumber", verificationNumber);
                //list.put(json);

                //parentJson.put("results", json);
//                userNumber = userNumber.replaceAll("\\s", "");
//            if (!userNumber.substring(0, 0).equals("+")) {
//                userNumber = "+" + userNumber;
//            }
                out.print(json);
                out.flush();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {

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
