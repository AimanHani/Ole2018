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
import model.PrivateSpecial;
import model.Special;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrivateSpecialsDAO {

    public static HashMap<Integer, PrivateSpecial> getSpecials(int logId, int leagueId, String username) {
        HashMap<Integer, PrivateSpecial> specialsList = new HashMap();

        //try to get user's existing specialslog  
        //SELECT logId FROM `log` where username = 'nilly' and leagueId = 66 
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT s.logid FROM `log` l inner join specialslog s on l.logId = s.logid where username = '" + username + "' and leagueId = " + leagueId + " ");) {
            ResultSet rs = stmt.executeQuery();
            System.out.println(stmt);
            if (rs.next()) {
                int id = rs.getInt(1);
                try (Connection conn2 = DBConnection.getConnection(); PreparedStatement stmt2 = conn2.prepareStatement("select s.specialsId, s.description, s.points, sl.doubleIt, sl.prediction from specialslog sl, specials s where sl.specialsId = s.specialsId and sl.logid =?");) {
                    stmt2.setInt(1, id);
                    rs = stmt2.executeQuery();
                    System.out.println(stmt2);
                    while (rs.next()) {
                        int specialsID = rs.getInt(1);
                        String description = rs.getString(2);
                        int points = rs.getInt(3);
                        int doubleIt = rs.getInt(4);
                        String prediction = rs.getString(5);
                        specialsList.put(specialsID, new PrivateSpecial(specialsID, description, points, doubleIt, prediction));
                    }
                    rs.close();
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                try (Connection conn3 = DBConnection.getConnection(); PreparedStatement stmt3 = conn3.prepareStatement("select s.specialsId, s.description, s.points, sl.doubleIt, sl.prediction from specialslog sl, specials s where sl.specialsId = s.specialsId and sl.logid =?");) {
                    stmt3.setInt(1, logId);
                    rs = stmt3.executeQuery();
                    System.out.println(stmt3);
                    while (rs.next()) {
                        int specialsID = rs.getInt(1);
                        String description = rs.getString(2);
                        int points = rs.getInt(3);
                        int doubleIt = rs.getInt(4);
                        String prediction = rs.getString(5);
                        specialsList.put(specialsID, new PrivateSpecial(specialsID, description, points, doubleIt, prediction));
                    }
                    rs.close();
                    conn.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
            System.out.println(ps);
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

//    public static HashMap<Integer, Special> getAllSpecials() {
//        HashMap<Integer, Special> specialsList = new HashMap();
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from specials where status = 'Y'");) {
//            System.out.println(stmt);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                int specialsID = rs.getInt(1);
//                String description = rs.getString(2);
//                int points = rs.getInt(4);
//                specialsList.put(specialsID, new Special(specialsID, description, points));
//            }
//            rs.close();
//            conn.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return specialsList;
//    }
    public static String updateMultipleSpecialsPrediction(JSONArray predictionList, int logId) {
        int[] rs;
        String result = "error";
        ArrayList<Integer> existingSpecialsPredictions = getExistingSpecialsPrediction(logId);
        try (Connection c = DBConnection.getConnection(); Statement cs = c.createStatement();) {
            System.out.println(cs);
            for (int i = 0; i < predictionList.length(); i++) {
                JSONObject prediction = predictionList.getJSONObject(i);
                int specialsId = prediction.getInt("specialsId");

                String statement = "";
                if (existingSpecialsPredictions.contains(specialsId)) {
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

    public static String setInsertSpecialPredictionStatement(int logID, int specialsId, String prediction, int doubleIt) {
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
