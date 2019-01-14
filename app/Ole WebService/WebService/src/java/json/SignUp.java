/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import controller.SignUpDAO;
import controller.LoginDAO;
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
        
          JSONObject json = new JSONObject();
        response.setContentType("\"Content-Type\", \"application/x-www-form-urlencoded\"");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String name  = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthdate = request.getParameter("birthdate");
        String contactNo = request.getParameter("contactNo");
        String country = request.getParameter("country");
        String team = request.getParameter("team");
               
        
        String status = "";
        String token = "";
       
        try {
            
            status = SignUpDAO.signUp(username,  name, password,  email,  birthdate,  contactNo, country,team);

            if (status.equals("success")) {

                json.put("Status", "new record has been created");
               
            } else if(status.equals("username has been taken")) {
    
                json.put("status","error");
                String invalidMsg = "Your username has been taken"+"/"+"";
                String[] invalidString = {invalidMsg};
                json.put("messages",invalidString);
            }
            else if(status.equals("birthdate error")){
                json.put("status","error");
                String invalidMsg = "birthdate error"+"/"+"";
                String[] invalidString = {invalidMsg};
                json.put("messages",invalidString);
            }
            else if(status.equals("email has been taken")){
                json.put("status","error");
                String invalidMsg = "email has been taken"+"/"+"";
                String[] invalidString = {invalidMsg};
                json.put("messages",invalidString);
            }
            else if(status.equals("username and email have been taken")){
                json.put("status","error");
                String invalidMsg = "username and email have been taken"+"/"+"";
                String[] invalidString = {invalidMsg};
                json.put("messages",invalidString);
            }
            else{
                json.put("status","error");
                String invalidMsg = "check db connection class "+"/"+"";
                String[] invalidString = {invalidMsg};
                json.put("messages",invalidString);
            }
            out.print(json);
            out.flush();

        } catch (SQLException e) {
            e.printStackTrace();
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
