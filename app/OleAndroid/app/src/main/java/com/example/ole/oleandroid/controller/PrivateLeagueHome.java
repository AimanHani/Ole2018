package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.User;
import com.example.ole.oleandroid.pageController.FAQ;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class PrivateLeagueHome extends AppCompatActivity {

    FloatingActionButton addPrivateLeague;
    FloatingActionButton searchLeagueName;
    EditText leaguename;
    PrivateLeague privateleague;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_home);

        //get user object
        //Intent i = getIntent();
        //final User u = (User)i.getSerializableExtra("User");
        //User loginUser = UserDAO.getLoginUser();

        addPrivateLeague = findViewById(R.id.addPrivateLeague);
        addPrivateLeague.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PrivateLeagueHome.this, private_league_create.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                //intent.putExtra("User", u);
                startActivity(intent);
            }
        });

        leaguename = (EditText) findViewById(R.id.leaguename);
        searchLeagueName = findViewById(R.id.searchLeagueName);
        searchLeagueName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (leaguename.getText().toString().equals("")) {
                    //loadSamePage();
                } else {
                    System.out.println("Retrieving... " + leaguename.getText().toString());
                    final String[] status = {"error"};

                    JSONObject json = new JSONObject();
                    try {
                        json.put("method", "retrieveLeagueName");
                        json.put("leagueName", leaguename.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = DBConnection.insertPrivateLeagueUrl();

                    PostHttp connection = new PostHttp();
                    String response = null;
                    try {
                        response = connection.post(url, json.toString());
                        System.out.println(response);

                        JSONObject result = new JSONObject(response);
                        status[0] = result.getString("status");

                        if (status[0].equals("success")) {
                            JSONObject league = result.getJSONObject("league");
                            int leagueId = league.getInt("LeagueId");
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

                            //System.out.println("HEY MISTA" + java.sql.Date.valueOf(endDate) + password);

                            try {
                                privateleague = new PrivateLeague(leagueId, leagueName, prize, password, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), username, pointsAllocated, tournamentId, leagueKeyId);
                                //pl = new PrivateLeague(privateLeagueId, prize,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                                //pl = new PrivateLeague(privateLeaugeId, leagueName,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                                //(int privateLeaugeId, String prize, Date startDate, Date endDate, int leagueId, String username) {
                                //(int privateLeaugeId, String leagueName, String prize, Date startDate, Date endDate, int leagueId) {
                                PrivateLeague.setPrivateLeague(privateleague);
                            }catch(Exception e){}

                            Intent intent = new Intent(PrivateLeagueHome.this, private_league_details.class);
                            Bundle b = getIntent().getExtras();
                            intent.putExtras(b);
                            //intent.putExtra("PL", pl);
                            startActivity(intent);
                        } else {
                            //loadSamePage();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }



//comments stop here
/*
                    // codes to bypass login with webservice
                    Intent intent = new Intent(Login.this, Home.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
*/

                }

                /*
                if (leaguename.getText().toString().equals("")) {
                    //loadSamePage();
                } else {
                    System.out.println("Retrieving..."+ leaguename.getText().toString());
                    String url = DBConnection.insertPrivateLeagueUrl()+"?leagueName="+leaguename.getText().toString()
                            ;
                    final String[] status = {"error"};

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {
                                    System.out.println("HELLOOOO" + ServerResponse);
                                    try {

                                        JSONObject result = new JSONObject(ServerResponse);
                                        status[0] = result.getString("status");

                                        if (status[0].equals("success")) {

                                            JSONObject league = result.getJSONObject("results");
                                            int LeagueId = league.getInt("LeagueId");
                                            int privateLeagueId = league.getInt("tournamentId");
                                            String pointsAllocated = league.getString("pointsAllocated");
                                            String leagueName = league.getString("leagueName");
                                            String privateLeagueID = league.getString("privateLeagueID");
                                            String prize = league.getString("prize");
                                            String startDate = league.getString("startDate");
                                            String endDate = league.getString("endDate");
                                            int leagueKeyId = league.getInt("leagueKeyId");
                                            String username = league.getString("userName");
                                            try {
                                                pl = new PrivateLeague(privateLeagueId, prize,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                                                //pl = new PrivateLeague(privateLeaugeId, leagueName,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                                                //(int privateLeaugeId, String prize, Date startDate, Date endDate, int leagueId, String username) {
                                                //(int privateLeaugeId, String leagueName, String prize, Date startDate, Date endDate, int leagueId) {
                                            }catch(Exception e){}

                                            Intent intent = new Intent(PrivateLeagueHome.this, private_league_details.class);
                                            Bundle b = getIntent().getExtras();
                                            intent.putExtras(b);
                                            intent.putExtra("PL", pl);
                                            startActivity(intent);
                                        } else {
                                            //loadSamePage();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    System.out.println("error");
                                    volleyError.printStackTrace();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("leagueName", leaguename.getText().toString());
                            //params.put("password", password.getText().toString());
                            return params;
                        }
                    };

                    //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    //VolleyRequest.setRequestQueue(requestQueue);
                    VolleyRequest.addRequestString(stringRequest);

                }

*/

                Intent intent = new Intent(PrivateLeagueHome.this, private_league_details.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                //intent.putExtra("User", u);
                startActivity(intent);

            }
        });
    }
}
