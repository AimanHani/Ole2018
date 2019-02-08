package com.example.ole.oleandroid.controller;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PublicLeague;

import org.json.JSONObject;

import java.util.ArrayList;

public class PrivateLeagueDAO {
    private static ArrayList<PrivateLeague> allPrivateLeague = new ArrayList<>();

    public static ArrayList<PrivateLeague> getAllPrivateLeague() {
        return allPrivateLeague;
    }

    public static void setAllPrivateLeague(ArrayList<PrivateLeague> allPrivateLeague) {
        PrivateLeagueDAO.allPrivateLeague = allPrivateLeague;
    }

    public static void clearAllPrivateLeague(){
        PrivateLeagueDAO.allPrivateLeague = new ArrayList<>();
    }

    public static void addPrivateleague(PrivateLeague privateLeague){
        allPrivateLeague.add(privateLeague);
    }

    public static boolean joinPublicLeague(int leagueId, String username){
        String url = DBConnection.insertUserPublicLeagueUrl();

        String params = concatParams(username, leagueId);
        PostHttp connection = new PostHttp();
        String response = null;
        Boolean resultFinal = false;

        try {
            response = connection.postForm(url, params);
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            String status = result.getString("status");

            if (status.equals("success")) {
                resultFinal = true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultFinal;
    }

    public static String concatParams(String username, int leagueId) {
        return "username=" + username + "&leagueId=" + leagueId;
    }
}
