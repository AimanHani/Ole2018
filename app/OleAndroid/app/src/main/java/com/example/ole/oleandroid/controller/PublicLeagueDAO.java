package com.example.ole.oleandroid.controller;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PublicLeague;
import com.example.ole.oleandroid.model.User;

import org.json.JSONObject;

import java.util.ArrayList;

public class PublicLeagueDAO {
    private static ArrayList<PublicLeague> allPublicLeague = new ArrayList<>();

    public static ArrayList<PublicLeague> getAllPublicLeague() {
        return allPublicLeague;
    }

    public static void setAllPublicLeague(ArrayList<PublicLeague> allPublicLeague) {
        PublicLeagueDAO.allPublicLeague = allPublicLeague;
    }

    public static void clearAllPublicLeague(){
        PublicLeagueDAO.allPublicLeague = new ArrayList<>();
    }

    public static void addPublicleague(PublicLeague publicLeague){
        allPublicLeague.add(publicLeague);
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
