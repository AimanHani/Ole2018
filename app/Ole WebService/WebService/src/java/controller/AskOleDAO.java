/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import model.FAQ;

/**
 *
 * @author user
 */
public class AskOleDAO {

    public static String insertQuestion(String username, String question, String category) {
        int rs = 0;
        // I follow the php script to set the point as 0 here
        int points = 0;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("INSERT INTO askole (username,question, category) VALUES (?,?,?)");) {

            ps.setString(1, username);
            ps.setString(2, question);
            ps.setString(3, category);
            rs = ps.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
            c.close();
        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return "error";
    }
     public static HashMap<Integer, FAQ> retrieveFAQ(String username){
       HashMap<Integer, FAQ> askAndAnswerList = new HashMap<Integer, FAQ>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select askId,question,answer,category from askole where username = ?");) {
            
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int askId = rs.getInt(1);
                String question = rs.getString(2);
                String answer = rs.getString(3);
                String category = rs.getString(4);
               
               

                askAndAnswerList.put(askId, new FAQ(askId,question,answer,category));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return askAndAnswerList;
    }
}
