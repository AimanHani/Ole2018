package com.example.ole.oleandroid.controller;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.Match;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchDAO {

    public static ArrayList<Match> pastMatches = new ArrayList<>();
    public static ArrayList<Match> futureMatches = new ArrayList<>();

    public static ArrayList<Match> getPastMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        String url = DBConnection.getMatchesUrl()+"?matchStatus=past";
        System.out.println("Getting past matches list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray publicLeagues = result.getJSONArray("results");

            if (publicLeagues.length() > 0) {
                for (int i = 0; i < publicLeagues.length(); i++) {
                    JSONObject matchObject = publicLeagues.getJSONObject(i);

                    int matchId = matchObject.getInt("matchId");
                    int tournamentID = matchObject.getInt("tournamentId");
                    String matchDate = matchObject.getString("matchDate");
                    String matchTime = matchObject.getString("matchTime");
                    String team1 = matchObject.getString("team1");
                    String team2 = matchObject.getString("team2");
                    int team1Score = matchObject.getInt("team1Score");
                    int team2Score = matchObject.getInt("team2Score");

                    Match match = new Match(matchId, tournamentID, matchDate, matchTime, team1, team2, team1Score, team2Score);
                    matches.add(match);
                }

            } else {
                //loadSamePage();
                matches = null;

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            matches = null;
        }
        pastMatches = matches;
        return matches;
    }

    public static ArrayList<Match> getFutureMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        String url = DBConnection.getMatchesUrl()+"?matchStatus=future";
        System.out.println("Getting past matches list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray publicLeagues = result.getJSONArray("results");

            if (publicLeagues.length() > 0) {
                for (int i = 0; i < publicLeagues.length(); i++) {
                    JSONObject matchObject = publicLeagues.getJSONObject(i);

                    int matchId = matchObject.getInt("matchId");
                    int tournamentID = matchObject.getInt("tournamentId");
                    String matchDate = matchObject.getString("matchDate");
                    String matchTime = matchObject.getString("matchTime");
                    String team1 = matchObject.getString("team1");
                    String team2 = matchObject.getString("team2");
                    int team1Score = matchObject.getInt("team1Score");
                    int team2Score = matchObject.getInt("team2Score");

                    Match match = new Match(matchId, tournamentID, matchDate, matchTime, team1, team2, team1Score, team2Score);
                    matches.add(match);
                }

            } else {
                //loadSamePage();
                matches = null;

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            matches = null;
        }

        futureMatches = matches;
        return matches;
    }
}
