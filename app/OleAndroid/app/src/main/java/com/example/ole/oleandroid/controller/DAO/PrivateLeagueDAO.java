package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateLeagueProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PrivateLeagueDAO {
    private static ArrayList<PrivateLeague> allPrivateLeague = new ArrayList<>();

    public static ArrayList<PrivateLeague> getAllPrivateLeague() {
        return allPrivateLeague;
    }

    public static void setAllPrivateLeague(ArrayList<PrivateLeague> allPrivateLeague) {
        PrivateLeagueDAO.allPrivateLeague = allPrivateLeague;
    }

    public static void clearAllPrivateLeague() {
        PrivateLeagueDAO.allPrivateLeague = new ArrayList<>();
    }

    public static void addPrivateleague(PrivateLeague privateLeague) {
        allPrivateLeague.add(privateLeague);
    }

    public static boolean joinPublicLeague(int leagueId, String username) {
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

    public static int getAdminLogId(String leagueid) {
        String url3 = DBConnection.privateLeagueUrl() + "?method=retrieveAdminLog&leagueid=" + leagueid;

        System.out.println("Getting admin log");
        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url3);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray arr = result.getJSONArray("results");
            if (arr.length() > 0) {
                JSONObject obj = arr.getJSONObject(0);
                return obj.getInt("logid");
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return -1;
    }

    public static PrivateLeague getPrivateLeague(String leagueid) {
        JSONObject json = new JSONObject();
        try {
            json.put("method", "retrieveLeagueName");
            json.put("leagueId", leagueid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = DBConnection.privateLeagueUrl();

        PostHttp connection = new PostHttp();
        String response = null;
        try {
            response = connection.post(url, json.toString());
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            String status = result.getString("status");

            if (status.equals("success")) {
                JSONObject league = result.getJSONObject("league");
                int leagueID = league.getInt("LeagueId");
                int tournamentId = league.getInt("tournamentId");
                int pointsAllocated = league.getInt("pointsAllocated");
                String leagueName = league.getString("leagueName");
                String privateLeagueID = league.getString("privateLeagueID");
                String prize = league.getString("prize");
                String startDate = league.getString("startDate");
                String endDate = league.getString("endDate");
                int leagueKeyId = league.getInt("leagueKeyId");
                String username = league.getString("userName");
                String password = league.getString("password");

                try {
                    PrivateLeague privateLeague = new PrivateLeague(leagueID, leagueName, prize, password, startDate, endDate, username, pointsAllocated, tournamentId, leagueKeyId);
                    PrivateLeague.setPrivateLeague(privateLeague);
                    return privateLeague;
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
