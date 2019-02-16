package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.PrivateLeagueTabActivity;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;


public class PrivateLeagueDetails extends AppCompatActivity {
    //TextView leagueNameInput;
    Button predictButton;
    EditText privatePrizeInput, leagueNameInput, pldusername;
    TextView creator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_details);

        Intent intent = getIntent();
        String leaguename = intent.getStringExtra("leaguename");

        if (leaguename.equals("")) {
            //loadSamePage();
        } else {
            System.out.println("Retrieving... " + leaguename);
            final String[] status = {"error"};

            JSONObject json = new JSONObject();
            try {
                json.put("method", "retrieveLeagueName");
                json.put("leagueName", leaguename);
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
                    PrivateLeague privateleague=null;
                    try {
                        privateleague = new PrivateLeague(leagueId, leagueName, prize, password, startDate, endDate, username, pointsAllocated, tournamentId, leagueKeyId);
                        //pl = new PrivateLeague(privateLeagueId, prize,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                        //pl = new PrivateLeague(privateLeaugeId, leagueName,java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), leagueKeyId, username);
                        //(int privateLeaugeId, String prize, Date startDate, Date endDate, int leagueId, String username) {
                        //(int privateLeaugeId, String leagueName, String prize, Date startDate, Date endDate, int leagueId) {
                        PrivateLeague.setPrivateLeague(privateleague);
                    }catch(Exception e){}


                    predictButton = findViewById(R.id.predictButton);
                    privatePrizeInput = findViewById(R.id.privatePrizeInput);
                    pldusername = findViewById(R.id.pldusername);
                    leagueNameInput = findViewById(R.id.leagueNameInput);
                    creator = findViewById(R.id.creator);
                    //System.out.println("hehhehee" + privateLeague.getPrize());

                    if(privateleague!=null){
                        privatePrizeInput.setInputType(InputType.TYPE_NULL);
                        leagueNameInput.setInputType(InputType.TYPE_NULL);
                        creator.setInputType(InputType.TYPE_NULL);
                        pldusername.setInputType(InputType.TYPE_NULL);


                        privatePrizeInput.setText(privateleague.getPrize());
                        leagueNameInput.setText(privateleague.getLeagueName());
                        creator.setText(privateleague.getUsername());
                    }



                    predictButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {

                            Intent intent = new Intent(PrivateLeagueDetails.this, PrivateLeagueTabActivity.class);
                            Bundle b = getIntent().getExtras();
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });


                } else {
                    //loadSamePage();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }



//comments stop here
/*
                    // codes to bypass login with webservice
                    Intent intent = new Intent(Login.this, HomeTile.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
*/

        }


    }




}

