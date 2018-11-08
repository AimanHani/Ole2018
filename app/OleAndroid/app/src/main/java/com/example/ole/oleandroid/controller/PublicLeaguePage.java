package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.PublicLeague;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PublicLeaguePage extends AppCompatActivity {

    TextView listPublicLeague;
    PublicLeague publicLeague;
    Button joinPublicLeague;
    String userName;
    String results = "";
    int leagueID;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_league);
        final Bundle b = getIntent().getExtras();

        userName = b.getString("username");
        listPublicLeague = (TextView) findViewById(R.id.listPublicLeague);
        joinPublicLeague = (Button) findViewById(R.id.join_btn);

        System.out.println(b.get("leagueId"));

        if (b.get("leagueId") != null) {
            joinPublicLeague.setEnabled(false);
        }
        queue = Volley.newRequestQueue(this);

        String url = new DBConnection().getPublicLeagueUrl();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        //listPublicLeague.append(response.toString());
                        try {

                            JSONArray publicLeague = response.getJSONArray("results");
                            JSONObject firstLeague = publicLeague.getJSONObject(0);
                            leagueID = firstLeague.getInt("leagueId");
                            String prize = firstLeague.getString("prize");
                            int tournamentID = firstLeague.getInt("tournamentId");
                            int pointsAllocated = firstLeague.getInt("tournamentId");
                            String leagueName = firstLeague.getString("leagueName");
                            //int numberOfParticipants = firstLeague.getInt("numberParticipants");
                            //listPublicLeague.append("Username:"+userName);

                            JSONArray participants = response.getJSONArray("participants");
                            JSONObject participantsOne = participants.getJSONObject(0);
                            int numberOfParticipants = participantsOne.getInt("num_participants");

                            listPublicLeague.setText("League " + leagueName + System.getProperty("line.separator"));
                            listPublicLeague.append("League ID: " + leagueID + System.getProperty("line.separator"));
                            listPublicLeague.append("Tournament ID: " + tournamentID + System.getProperty("line.separator"));
                            listPublicLeague.append("Point Allocated " + pointsAllocated + System.getProperty("line.separator"));
                            listPublicLeague.append("Final Prize: " + prize + System.getProperty("line.separator"));
                            listPublicLeague.append("Number of Participants:" + numberOfParticipants);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.d("Error.Response", response);
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);

        joinPublicLeague.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String[] result = new String[0];
                        //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                        //String result = publicLeagueInsertUser(userName, leagueID, queue);

                        String url = new DBConnection().insertUserPublicLeagueUrl();


                        // Creating string request with post method.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String ServerResponse) {
                                        System.out.println("ServerResponse " + ServerResponse);
                                        if (!ServerResponse.equals("error")) {
                                            //result[0] = ServerResponse;
                                            Intent intent = new Intent(PublicLeaguePage.this, SpecialsPredict.class);
                                            //Bundle b = getIntent().getExtras();
                                            b.putString("logId", ServerResponse);
                                            b.putString("leagueId", leagueID + "");
                                            System.out.println(b.getString("logId") + " " + b.getString("userID"));
                                            intent.putExtras(b);
                                            startActivity(intent);
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        System.out.println("error  :");
                                        //volleyError.printStackTrace();
                                        result[0] = "error";
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                try {
                                    params.put("username", userName);
                                    params.put("leagueId", leagueID + "");

                                } catch (Exception e) {

                                }
                                return params;
                            }

                        };
                        queue.add(stringRequest);


                    }
                })
        );
    }


}
