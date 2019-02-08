package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.Match;
import com.example.ole.oleandroid.model.PublicLeague;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PastMatchesTab extends AppCompatActivity {


    PastMatchListAdapter pmListAdapter;
    ArrayList<Match> matches;
    ListView matchListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_matches_main); //contain item list view e.g. item1, item2


        matches = new ArrayList<>();
        matchListView = findViewById(R.id.matchListView);

        PublicLeagueDAO.clearAllPublicLeague();
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
//public Match(int matchID, int tournamentID, String matchDate, String matchTime, String team1, String team2, int team1Score, int team2Score) {
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

                //"results":[{"team1Score":-1,"matchDate":"2019-02-02","team1":"Brighton","tournamentId":2,"team2":"Watford","matchTime":"23:00:00","matchId":305,"team2Score":-1

                //i put 5,2 to test whether thing works, in the future pass in from database
                //leaguelist.add("English Premier League");
                //PublicLeagueDAO.addPublicleague(new PublicLeague(1009,2,"Arsenal Jersey", 3, "Public League","Premier League", 5, false));

                pmListAdapter = new PastMatchListAdapter(PastMatchesTab.this, matches);
                matchListView.setAdapter(pmListAdapter);

            } else {
                //loadSamePage();
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
