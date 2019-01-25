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
import model.AllPublicLeague;
import model.Special;

/**
 *
 * @author user
 */
public class ManageSpecialsDAO {
    public static HashMap<Integer, Special>getSpecials(int logID){
        HashMap<Integer, Special>specialsList = new HashMap();
         try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select s.specialsId, s.description, s.points from specialslog sl, specials s where sl.specialsId = s.specialsId and sl.logid =?");) {
            stmt.setInt(1, logID);
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int specialsID = rs.getInt(1);
                String description = rs.getString(2);
                int points = rs.getInt(3);
                
                specialsList.put(specialsID,new Special(specialsID,description, points));
                
            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return specialsList;
    }
    public static String updateSpecialsPrediction(int logID, int specialsID, String prediction){
         
        int rs = 0;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("update specialslog set prediction = ? where logid = ? and specialsid =?");) {

            ps.setString(1, prediction);
            ps.setInt(2, logID);
            ps.setInt(3, specialsID);
            
            rs = ps.executeUpdate();
            if (rs > 0) {
                return "successful";
            }
        } catch (Exception e) {
            System.out.println("check db connection class");
        }
        return "error";
        
    }
}
