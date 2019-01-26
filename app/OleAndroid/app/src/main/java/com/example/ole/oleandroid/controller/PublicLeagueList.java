package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PublicLeague;
import com.example.ole.oleandroid.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class PublicLeagueList extends AppCompatActivity {

    PublicLeagueListAdapter publicLeagueListAdapter;
    ArrayList<PublicLeague> leaguelist;
    ListView publicLeagueListView;
    int leagueID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicleaguelist); //contain item list view e.g. item1, item2
        leaguelist = new ArrayList<>();
        publicLeagueListView = findViewById(R.id.publicLeagueListView);

        PublicLeagueDAO.clearAllPublicLeague();
        String url = DBConnection.getPublicLeagueUrl();
        System.out.println("Getting public league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray publicLeagues = result.getJSONArray("results");

            if (publicLeagues.length() > 0) {
                for (int i = 0; i < publicLeagues.length(); i++) {
                    JSONObject publicLeagueObject = publicLeagues.getJSONObject(i);
                    PublicLeague publicLeague = new PublicLeague(
                            publicLeagueObject.getInt("leagueID"),
                            publicLeagueObject.getInt("tournamentID"),
                            publicLeagueObject.getString("prize"),
                            publicLeagueObject.getInt("pointsAllocated"),
                            publicLeagueObject.getString("leagueName"),
                            publicLeagueObject.getString("tournamentName"),
                            publicLeagueObject.getInt("logId")
                    );
                    PublicLeagueDAO.addPublicleague(publicLeague);
                    //leaguelist.add(publicLeague);
                }

                //i put 5,2 to test whether thing works, in the future pass in from database
                //leaguelist.add("English Premier League");
                PublicLeagueDAO.addPublicleague(new PublicLeague(1009,2,"Arsenal Jersey", 3, "Public League","Premier League", 5));
//                leaguelist.add("League 3");
//                leaguelist.add("League 4");

                publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, PublicLeagueDAO.getAllPublicLeague());
                publicLeagueListView.setAdapter(publicLeagueListAdapter);
//        publicLeagueListView.setAdapter(publicLeagueListAdapter);

            } else {
                //loadSamePage();
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }

    //league logo
    //league name
    //join btn

}
