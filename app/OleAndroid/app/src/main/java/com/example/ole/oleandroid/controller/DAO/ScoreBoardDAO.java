package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;

import java.util.ArrayList;

import com.example.ole.oleandroid.model.PrivateLeagueProfile;
import com.example.ole.oleandroid.model.PublicLeagueProfile;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScoreBoardDAO {

    public static ArrayList<PublicLeagueProfile> publicLeagueProfiles = new ArrayList<>();
    public static ArrayList<PrivateLeagueProfile> privateLeagueProfiles = new ArrayList<>();

    public static ArrayList<PublicLeagueProfile> getPublicLeagueProfiles() {
        ArrayList<PublicLeagueProfile> publicLeagueProfileList = new ArrayList<>();
        String url = DBConnection.getScoreBoardUrl() + "?league=public";
        System.out.println("Getting pub league scoreboard");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray publicLeagueProfile = result.getJSONArray("results");

            if (publicLeagueProfile.length() > 0) {
                for (int i = 0; i < publicLeagueProfile.length(); i++) {
                    JSONObject matchObject = publicLeagueProfile.getJSONObject(i);

                    int leagueID = matchObject.getInt("leagueId");
                    int totalPoints = matchObject.getInt("totalPoints");
                    String username = matchObject.getString("username");
                    int rank = matchObject.getInt("rank");
                    PublicLeagueProfile plp = new PublicLeagueProfile(username, leagueID, totalPoints, rank);
                    publicLeagueProfileList.add(plp);
                }

            } else {
                //loadSamePage();
                publicLeagueProfileList = null;

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            publicLeagueProfileList = null;
        }
        publicLeagueProfiles = publicLeagueProfileList;
        return publicLeagueProfileList;
    }

    public static ArrayList<PrivateLeagueProfile> getPrivateLeagueProfiles() {
        ArrayList<PrivateLeagueProfile> privateLeagueProfileList = new ArrayList<>();
        String url = DBConnection.getScoreBoardUrl() + "?league=private&username="+UserDAO.getLoginUser().getUsername();
        System.out.println("Getting private league scoreboard");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray privateLeagueProfile = result.getJSONArray("results");

            if (privateLeagueProfile.length() > 0) {
                for (int i = 0; i < privateLeagueProfile.length(); i++) {
                    JSONObject matchObject = privateLeagueProfile.getJSONObject(i);

                    int leagueID = matchObject.getInt("leagueId");
                    int totalPoints = matchObject.getInt("totalPoints");
                    String username = matchObject.getString("username");
                    PrivateLeagueProfile plp = new PrivateLeagueProfile(username, leagueID, totalPoints);
                    privateLeagueProfileList.add(plp);
                }

            } else {
                //loadSamePage();
                privateLeagueProfileList = null;

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            privateLeagueProfileList = null;
        }
        privateLeagueProfiles = privateLeagueProfileList;
        return privateLeagueProfileList;
    }

    public static int getUserPosition(String username, ArrayList<PublicLeagueProfile> list) {
        for (PublicLeagueProfile p : list) {
            if (p.getUsername().equals(username)) {
                return p.getRank();
            }
        }
        return 0;
    }

    public static int getUserPoints(String username, ArrayList<PublicLeagueProfile> list) {
        for (PublicLeagueProfile p : list) {
            if (p.getUsername().equals(username)) {
                return p.getTotalPoints();
            }
        }
        return -1;
    }
}
