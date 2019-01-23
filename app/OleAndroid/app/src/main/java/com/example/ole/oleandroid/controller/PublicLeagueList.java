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
import com.example.ole.oleandroid.model.PublicLeague;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PublicLeagueList extends AppCompatActivity {

    PublicLeagueListAdapter publicLeagueListAdapter;
    ArrayList<String> leaguelist;
    ListView publicLeagueListView;
    int leagueID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicleaguelist); //contain item list view e.g. item1, item2
        leaguelist = new ArrayList<String>();
        publicLeagueListView = findViewById(R.id.publicLeagueListView);

//        PublicLeagueDAO.clearAllPublicLeague();
//        String url = DBConnection.getPublicLeagueUrl();
//        System.out.println("Getting public league list");
//
//        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            System.out.println(response.toString());
//                            JSONArray publicLeagueList = response.getJSONArray("results");
//
//                            for (int i = 0; i < publicLeagueList.length(); i++) {
//                                JSONObject publicLeagueObject = publicLeagueList.getJSONObject(i);
//                                PublicLeague publicLeague = new PublicLeague(
//                                        publicLeagueObject.getInt("leagueID"),
//                                        publicLeagueObject.getString("prize"),
//                                        publicLeagueObject.getInt("tournamentID"),
//                                        publicLeagueObject.getInt("pointsAllocated"),
//                                        publicLeagueObject.getString("leagueName"),
//                                        publicLeagueObject.getString("tournamentName")
//                                );
//                                PublicLeagueDAO.addPublicleague(publicLeague);
//                                leaguelist.add(publicLeague.getLeagueName());
//                            }
//                            publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, leaguelist);
//                            publicLeagueListView.setAdapter(publicLeagueListAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Log.d("Error.Response", response);
//                        System.out.println("Error");
//                        error.printStackTrace();
//                    }
//                }
//        );
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        VolleyRequest.setRequestQueue(requestQueue);
//        VolleyRequest.addRequestJson(getRequest);

//        ArrayList<PublicLeague> allPublicLeague = PublicLeagueDAO.getAllPublicLeague();
//        for (int i = 0; i < allPublicLeague.size(); i++) {
//            leaguelist.add(allPublicLeague.get(i).getLeagueName());
//        }


        //i put 5,2 to test whether thing works, in the future pass in from database
        leaguelist.add("English Premier League");
        leaguelist.add("League 2");
        leaguelist.add("League 3");
        leaguelist.add("League 4");

        publicLeagueListAdapter = new PublicLeagueListAdapter(PublicLeagueList.this, leaguelist);
        publicLeagueListView.setAdapter(publicLeagueListAdapter);
//        publicLeagueListView.setAdapter(publicLeagueListAdapter);

    }

    //league logo
    //league name
    //join btn

}
