package com.example.ole.oleandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

public class PublicLeaguePage extends AppCompatActivity {

    TextView listPublicLeague;
    PublicLeague publicLeague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_league);

        listPublicLeague = (TextView) findViewById(R.id.listPublicLeague);

        RequestQueue queue = Volley.newRequestQueue(this);

        final String url = new DBConnection().getPublicLeagueUrl();

// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        //Log.d("Response", response.toString());


                        try {

                            JSONArray publicLeague = response.getJSONArray("results");
                            JSONObject firstLeague = publicLeague.getJSONObject(0);
                            int leagueID = firstLeague.getInt("leagueId");
                            String prize = firstLeague.getString("prize");
                            int tournamentID = firstLeague.getInt("tournamentId");
                            int pointsAllocated = firstLeague.getInt("tournamentId");
                            String leagueName = firstLeague.getString("leagueName");

                            listPublicLeague.append("League "+leagueName);
                            listPublicLeague.append("League ID: "+leagueID);
                            listPublicLeague.append("Tournament ID: "+tournamentID);
                            listPublicLeague.append("Point Allocated "+pointsAllocated);
                            listPublicLeague.append("Final Prize: "+prize);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.d("Error.Response", response);
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }
}
