package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;

public class PrivateLeagueCreate extends SideMenuBar {
    EditText leaguename, prize, password, pointsAllocated;
    Button selectleague;
    Spinner leagueid;
    //String username ;
    String tournamentId = "2";
    //String pointsAllocated = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_create, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        //get user object
        //User u = UserDAO.getLoginUser();
        //username = u.getUserName();
        leaguename = (EditText) findViewById(R.id.leaguename);
        prize = (EditText) findViewById(R.id.prize);
        password = (EditText) findViewById(R.id.password);
        pointsAllocated = (EditText) findViewById(R.id.points);


        // Spinner leagueid =  findViewById(R.id.leagueid);
        //leagueid = (EditText) findViewById(R.id.leagueid);


        selectleague = findViewById(R.id.selectleague);
        selectleague.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view){

            if (!leaguename.getText().equals("") && !prize.getText().equals("") && !password.getText().equals("")) {
            //if (leaguename!=null && prize!=null && password!=null) {
                Intent intent = new Intent(PrivateLeagueCreate.this, PrivateLeagueSelectLeaguesActivity.class);
                intent.putExtra("leaguename", leaguename.getText().toString());
                intent.putExtra("prize", prize.getText().toString());
                intent.putExtra("password", password.getText().toString());
                intent.putExtra("pointsAllocated", pointsAllocated.getText().toString());
                startActivity(intent);
            } else {
                Intent intent = new Intent(PrivateLeagueCreate.this, PrivateLeagueCreate.class);
                Bundle b = getIntent().getExtras();
                intent.putExtras(b);
                startActivity(intent);
            }
        }
        });
}
/*
                String url = new DBConnection( ).insertPrivateLeagueUrl( );

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>( ) {
                            @Override
                            public void onResponse(String ServerResponse) {
                                System.out.println(ServerResponse);
                                Intent intent = new Intent(PrivateLeagueCreate.this, PrivateLeagueChoosematchActivity.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener( ) {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("error  :");
                                volleyError.printStackTrace( );
                                Intent intent = new Intent(PrivateLeagueCreate.this, PrivateLeagueCreate.class);
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
