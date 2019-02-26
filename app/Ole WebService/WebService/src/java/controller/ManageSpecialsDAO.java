/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ManageMatchesDAO.setInsertMatchStatement;
import dbConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AllPublicLeague;
import model.Special;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class ManageSpecialsDAO {

    public static HashMap<Integer, Special> getSpecials(int logId) {
        HashMap<Integer, Special> specialsList = new HashMap();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select s.specialsId, s.description, s.points from specialslog sl, specials s where sl.specialsId = s.specialsId and sl.logid =?");) {
            stmt.setInt(1, logId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int specialsID = rs.getInt(1);
                String description = rs.getString(2);
                int points = rs.getInt(3);

                specialsList.put(specialsID, new Special(specialsID, description, points));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return specialsList;
    }

    public static String updateSpecialsPrediction(int logID, int specialsID, String prediction) {

        int rs = 0;
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement("update specialslog set prediction = ? where logid = ? and specialsid =?");) {

            ps.setString(1, prediction);
            ps.setInt(2, logID);
            ps.setInt(3, specialsID);

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

    public static HashMap<Integer, Special> getAllSpecials() {
        HashMap<Integer, Special> specialsList = new HashMap();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from specials where status = 'Y'");) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int specialsID = rs.getInt(1);
                String description = rs.getString(2);
                int points = rs.getInt(4);

                specialsList.put(specialsID, new Special(specialsID, description, points));

            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return specialsList;
    }

    public static String updateMultipleSpecialsPrediction(JSONArray predictionList, int logId) {

        int[] rs;
        String result = "error";
        
        ArrayList<Integer> existingSpecialsPredictions = getExistingSpecialsPrediction(logId);

        try (Connection c = DBConnection.getConnection(); Statement cs = c.createStatement();) {
//{"specialsId":4,"logId":2,"prediction":"Lionel Messi","doubleIt":0}
            for (int i = 0; i < predictionList.length(); i++) {
                JSONObject prediction = predictionList.getJSONObject(i);
                int specialsId = prediction.getInt("specialsId");
                
                String statement = "";
                if (existingSpecialsPredictions.contains(specialsId)){
                    statement = setUpdateSpecialPredictionStatement(logId, prediction.getInt("specialsId"), prediction.getString("prediction"), prediction.getInt("doubleIt"));
                } else {
                    statement = setInsertSpecialPredictionStatement(logId, prediction.getInt("specialsId"), prediction.getString("prediction"), prediction.getInt("doubleIt"));
                }
                cs.addBatch(statement);
            }

            rs = cs.executeBatch();
            System.out.println("The number of rows updated: " + rs.length);
            if (rs.length == predictionList.length()) {
                result = "successful";
            }
            c.close();
        } catch (Exception e) {
            System.out.println("check db connection class" + e.getMessage());
        }

        return result;

    }

    public static String setUpdateSpecialPredictionStatement(int logID, int specialsId, String prediction, int doubleIt) {
        return "UPDATE specialslog SET prediction=\'" + prediction + "\', doubleIt = " + doubleIt
                + " WHERE logid = " + logID
                + " AND specialsId = " + specialsId;
    }
    
    public static String setInsertSpecialPredictionStatement(int logID, int specialsId, String prediction, int doubleIt)  {
        return "Insert into specialslog (logid, specialsId, prediction, doubleIt) VALUES ("
                + logID
                + "," + specialsId
                + ",\'" + prediction
                + "\'," + doubleIt + ");";
    }
    
    
    public static ArrayList<Integer> getExistingSpecialsPrediction(int logId) {
        ArrayList<Integer> specialsIdPredictions = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select specialsId from specialslog where logId = ?");) {
            stmt.setInt(1, logId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                specialsIdPredictions.add(rs.getInt(1));
            }
            rs.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return specialsIdPredictions;
    }

}

//select * from specials where status = 'Y'
