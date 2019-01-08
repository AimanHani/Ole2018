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

/**
 *
 * @author user
 */
public class RetrieveParticipantsDAO {
    public static HashMap<AllPublicLeague,Integer> retrieveParticipants(){
        HashMap<AllPublicLeague, Integer> participantsInLeague = new HashMap<AllPublicLeague, Integer>();
         try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT count(username), l.leagueId from log l inner join publicleague p where l.leagueId = p.leagueId group by l.leagueId");) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int numOfParticipants = rs.getInt(1);
                int leagueID = rs.getInt(2);
                

                participantsInLeague.put(new AllPublicLeague(leagueID),numOfParticipants);

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return participantsInLeague;
    }
}
