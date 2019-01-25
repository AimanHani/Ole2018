package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.widget.Spinner;
import android.widget.Toast;

public class private_league_create extends AppCompatActivity {
    EditText leaguename, prize, startdate, enddate, password, pointsAllocated;
    Button createLeague;
    Spinner leagueid;
    String username ;
    String tournamentId = "2";
    //String pointsAllocated = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_league_create);

        //get user object
        //Intent i = getIntent();
        //final User u = (User)i.getSerializableExtra("User");
        //User u = UserDAO.getLoginUser();

        //username = u.getUserName();

        Spinner leagueid =  findViewById(R.id.leagueid);
        //leagueid = (EditText) findViewById(R.id.leagueid);
        leaguename = (EditText) findViewById(R.id.leaguename);
        prize = (EditText) findViewById(R.id.prize);
        startdate = (EditText) findViewById(R.id.startdate);
        enddate = (EditText) findViewById(R.id.enddate);
        password = (EditText) findViewById(R.id.password);
        pointsAllocated = (EditText) findViewById(R.id.pointsAllocated);
        createLeague = (Button) findViewById(R.id.createLeague);




        createLeague.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {

//link to epl page, currently with only English Premier League
                Intent intent = new Intent(private_league_create.this, PrivateLeagueChoosematchActivity.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
                System.out.println("Creating Private League");
/*
                String url = new DBConnection( ).insertPrivateLeagueUrl( );

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>( ) {
                            @Override
                            public void onResponse(String ServerResponse) {
                                System.out.println(ServerResponse);
                                Intent intent = new Intent(private_league_create.this, PrivateLeagueChoosematchActivity.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener( ) {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("error  :");
                                volleyError.printStackTrace( );
                                Intent intent = new Intent(private_league_create.this, private_league_create.class);
                                startActivity(intent);
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>( );
                        try {
                            params.put("username", username);
                            params.put("password", password.getText( ).toString( ));
                            params.put("prize", prize.getText( ).toString( ));
                            params.put("leagueName", leaguename.getText( ).toString( ));
                            params.put("tournamentId", tournamentId);
                            params.put("pointsAllocated", pointsAllocated.getText( ).toString( ));
                            params.put("startDate", startdate.getText( ).toString( ));
                            params.put("endDate", enddate.getText( ).toString( ));
                            validate( );
                        } catch (Exception e) {

                        }
                        return params;
                    }
                };
                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext( ));
                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);
                */
            }
        });
    }
    public boolean validate(){
        return true;
    }
}
