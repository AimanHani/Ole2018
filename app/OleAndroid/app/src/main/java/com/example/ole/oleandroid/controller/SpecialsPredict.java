package com.example.ole.oleandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SpecialsPredict extends AppCompatActivity {
    String logId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specials_predict);

        Bundle b = getIntent().getExtras();

        logId = b.getString("logId");

        JSONObject matches = getRecentMatches();

        try {
            JSONArray recentMatches = matches.getJSONArray("results");

            for (int i = 0; i < recentMatches.length(); i++) {
                JSONObject match = recentMatches.getJSONObject(i);
                //create text views of multiple matches and join buttons
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private JSONObject getRecentMatches() {
        final JSONObject[] results = new JSONObject[1];

        RequestQueue queue = Volley.newRequestQueue(this);

        final String url = new DBConnection().getPublicLeagueUrl();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        //listPublicLeague.append(response.toString());
                        results[0] = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(getRequest);
        return results[0];
    }
}
