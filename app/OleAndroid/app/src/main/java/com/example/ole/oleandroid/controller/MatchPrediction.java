package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MatchPrediction extends AppCompatActivity {
    CheckBox doubleItValues;
    Button confirm;
    EditText team1Prediction;
    EditText team2Prediction;
    TextView details;
    TextView team1PredictionLabel;
    TextView team2PredictionLabel;

    String matchId;
    String logId;
    String matchDetails;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_prediction);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        details = findViewById(R.id.details);
        confirm = findViewById(R.id.confirm);
        doubleItValues = findViewById(R.id.doubleItCheck);
        team1Prediction = findViewById(R.id.team1Prediction);
        team2Prediction = findViewById(R.id.team2Prediction);
        team1PredictionLabel = findViewById(R.id.team1PredictionLabel);
        team2PredictionLabel = findViewById(R.id.team2PredictionLabel);
        System.out.println("match prediction");

        final Bundle b = getIntent().getExtras();
        matchId = b.getString("matchId");
        logId = b.getString("logId");

        matchDetails = b.getString("matchDetails");
        JSONObject detailsMatch = null;
        try {
            detailsMatch = new JSONObject(matchDetails);

            String date = detailsMatch.getString("date");
            String time = detailsMatch.getString("time");
            String team1 = detailsMatch.getString("team1");
            String team2 = detailsMatch.getString("team2");

            details.setText("Date " + date + System.getProperty("line.separator"));
            details.append("Time Scheduled: " + time + System.getProperty("line.separator") + System.getProperty("line.separator"));
            details.append("Teams 1: " + team1 + System.getProperty("line.separator"));
            details.append("Teams 2: " + team2 + System.getProperty("line.separator"));
            team1PredictionLabel.setText(team1 + " score: ");
            team2PredictionLabel.setText(team2 + " score: ");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("matchDetails : " + matchDetails);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String doubleIt = "false";

                if (doubleItValues.isChecked()){
                    doubleIt = "true";
                }

                String url = new DBConnection().manageMatchesUrl();

                final String finalDoubleIt = doubleIt;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String serverResponse) {
                                System.out.print(serverResponse);
                                Intent intent = new Intent(MatchPrediction.this, HomeTile.class);
                                Bundle b = getIntent().getExtras();
                                intent.putExtras(b);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("error  :");
                                //volleyError.printStackTrace();
                                //results[0] = "error";
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        try {
                            params.put("matchId", matchId);
                            params.put("logId", logId);
                            params.put("team1Prediction", team1Prediction.getText().toString());
                            params.put("team2Prediction", team2Prediction.getText().toString());
                            params.put("doubleItValues", finalDoubleIt);
                            params.put("method", "insert");

                        } catch (Exception e) {

                        }
                        return params;
                    }

                };


                requestQueue.add(stringRequest);
            }
        });
    }

}
