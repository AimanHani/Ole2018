/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.SignUpDAO;
import controller.LoginDAO;
import controller.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
@WebServlet(name = "SignUpJson", urlPatterns = {"/json/signUp"})
public class SignUp extends HttpServlet {

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
            out.println("<title>Servlet SignUp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUp at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String status = SignUpDAO.checkEmailUsername(email, username);

        JSONObject json = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        try {
            json.put("status", status);
        } catch (JSONException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.print(json);
        out.flush();

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
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        String contactNo = request.getParameter("contactNo");
        String country = request.getParameter("country");
        String team = request.getParameter("team");

        System.out.println(username + " " + birthdate);

        String status = "";
        String token = "";
        contactNo = contactNo.replaceAll("\\s", "");
        if (!contactNo.substring(0, 0).equals("+")) {
            contactNo = "+" + contactNo;
        }

        try {

            status = SignUpDAO.signUp(username, name, password, email, birthdate, contactNo, country, team);

            if (status.equals("success")) {

                json.put("status", "success");

                Boolean statusLogin = LoginDAO.validate(username, password);

                if (statusLogin) {
                    User u = UserDAO.getUserInfo(username);

                    JSONObject userDetails = new JSONObject();
                    userDetails.put("username", u.getUsername());
                    userDetails.put("password", u.getPassword());
                    userDetails.put("name", u.getName());
                    userDetails.put("dob", u.getDob());
                    userDetails.put("country", u.getCountry());
                    userDetails.put("contactNum", u.getContactNumber());
                    userDetails.put("email", u.getEmail());
                    userDetails.put("favoriteTeam", u.getFavoriteTeam());

                    json.put("status", "success");
                    json.put("user", userDetails);

                } else {
                    json.put("status", "error");
                    String invalidMsg = "invalid username" + "/" + "password";
                    json.put("messages", invalidMsg);
                }

            } else if (status.equals("username has been taken")) {
                json.put("status", "error");
                String invalidMsg = "Your username has been taken" + "/" + "";
                String[] invalidString = {invalidMsg};
                json.put("messages", invalidString);
            } else if (status.equals("birthdate error")) {
                json.put("status", "error");
                String invalidMsg = "birthdate error" + "/" + "";
                String[] invalidString = {invalidMsg};
                json.put("messages", invalidString);
            } else if (status.equals("email has been taken")) {
                json.put("status", "error");
                String invalidMsg = "email has been taken" + "/" + "";
                String[] invalidString = {invalidMsg};
                json.put("messages", invalidString);
            } else if (status.equals("username and email have been taken")) {
                json.put("status", "error");
                String invalidMsg = "username and email have been taken" + "/" + "";
                String[] invalidString = {invalidMsg};
                json.put("messages", invalidString);
            } else {
                json.put("status", "error");
                String invalidMsg = "check db connection class " + "/" + "";
                String[] invalidString = {invalidMsg};
                json.put("messages", invalidString);
            }

            out.print(json);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
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
