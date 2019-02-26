/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import model.FAQ;
import model.Match;

/**
 *
 * @author user
 */
public class FAQ_DAO {
    public static HashMap<Integer, FAQ> retrieveFAQ(String category){
       HashMap<Integer, FAQ> faqList = new HashMap<Integer, FAQ>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select faqId,question,answer from faq where category = ?");) {
            
            stmt.setString(1,category);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int faqId = rs.getInt(1);
                String question = rs.getString(2);
                String answer = rs.getString(3);
               
               

                faqList.put(faqId, new FAQ(faqId,question,answer));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return faqList;
    }
}
