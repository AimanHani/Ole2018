package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextView details;
    Button home;

    String matchId;
    String logId;
    String username;
    String finalResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        details = findViewById(R.id.details);
        home = findViewById(R.id.home);

        Bundle b = getIntent().getExtras();
        matchId = b.getString("matchId");
        logId = b.getString("logId");
        username = b.getString("username");

//        finalResults = "Username: " + username +System.getProperty("line.separator");
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//        String url = new DBConnection().profileUrl();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String serverResponse) {
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        System.out.println("error  :");
//                        //volleyError.printStackTrace();
//                        //results[0] = "error";
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                try {
//                    params.put("matchId", matchId);
//
//                } catch (Exception e) {
//
//                }
//                return params;
//            }
//
//        };
//
//
//        requestQueue.add(stringRequest);

    }
}
