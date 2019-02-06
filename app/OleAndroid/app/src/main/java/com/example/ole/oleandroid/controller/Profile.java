package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.User;

public class Profile extends SideMenuBar {
//    TextView details;

    Button home;
    TextView pointsGotten;
    TextView userName;
    TextView userCountry;
    TextView userFavTeam;
    TextView wonQty;
    TextView lostQty;
    TextView playQty;
    TextView accQty;

    /*String matchId;
    String logId;
    String username;
    String finalResults;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        //get user object
        Intent i = getIntent();
        //final User u = (User)i.getSerializableExtra("User");
        User loginUser = UserDAO.getLoginUser();

//        details = findViewById(R.id.details);
        home = findViewById(R.id.home);
        pointsGotten = findViewById(R.id.pointsGotten);
        userName = findViewById(R.id.userName);
        userCountry = findViewById(R.id.userCountry);
        userFavTeam = findViewById(R.id.userFavTeam);
        wonQty = findViewById(R.id.wonQty);
        lostQty = findViewById(R.id.lostQty);
        playQty = findViewById(R.id.playQty);
        accQty = findViewById(R.id.accQty);


        userName.setText(loginUser.getUserName());
        userCountry.setText(loginUser.getCountry());
        userFavTeam.setText(loginUser.getFavoriteTeam());


        /*final Bundle b = getIntent().getExtras();
        matchId = b.getString("matchId");
        logId = b.getString("logId");
        username = b.getString("username");

        finalResults = "Username: " + username + System.getProperty("line.separator") + System.getProperty("line.separator");

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = DBConnection.profileUrl();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String serverResponse) {
//                        try {
//                            finalResults = setOutput(finalResults, serverResponse);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        details.setText(serverResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("error  :");
                        finalResults += "error";
                        details.setText(finalResults);
                        volleyError.printStackTrace();
                        //results[0] = "error";
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                return params;
            }

        };


        requestQueue.add(stringRequest);*/


        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Profile.this, HomeTile.class);
//                Bundle b = getIntent().getExtras();
//                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

    /*private String setOutput(String finalResults, String serverResponse) throws JSONException {
        JSONObject results = new JSONObject(serverResponse);
        JSONArray firstObject = results.getJSONArray("results");
        JSONObject prevPrediction = firstObject.getJSONObject(0);
        finalResults += prevPrediction.getString("leagueName") + System.getProperty("line.separator") +
                "Match Predicted" + System.getProperty("line.separator") + System.getProperty("line.separator") +
                "Date: " + prevPrediction.getString("date") + System.getProperty("line.separator") +
                "Time: " + prevPrediction.getString("time") + System.getProperty("line.separator") + System.getProperty("line.separator") +
                "Teams : Prediction Score" + System.getProperty("line.separator") +
                prevPrediction.getString("team1") + " : " + prevPrediction.getString("team1_prediction") + System.getProperty("line.separator") +
                prevPrediction.getString("team2") + " : " + prevPrediction.getString("team2_prediction");

        return finalResults;
    }*/
}
