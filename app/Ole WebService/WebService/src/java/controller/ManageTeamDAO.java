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
import model.Teams;

/**
 *
 * @author user
 */
public class ManageTeamDAO {
    public static HashMap<Integer, Teams>getAllTeams(){
        HashMap<Integer, Teams>specialsList = new HashMap();
         try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("select * from team");) {
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int teamID = rs.getInt(1);
                String name = rs.getString(2);
                
                specialsList.put(teamID,new Teams(teamID,name));
                
            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return specialsList;
    }
}

//select * from specials where status = 'Y'