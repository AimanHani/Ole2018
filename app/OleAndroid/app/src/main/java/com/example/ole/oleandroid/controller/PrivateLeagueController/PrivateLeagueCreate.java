package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.controller.Signup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class PrivateLeagueCreate extends SideMenuBar {
    EditText leaguename, prize, password, pointsAllocated;
    Button selectleague;
    private Timer timer;
    Drawable tickDone;
    ArrayList<String> allLeague = null;
    boolean nameOk = false;
    boolean prizeOk = false;
    boolean passwordOk = false;
    boolean pointsAllocatedOk = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_create, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        tickDone = getResources().getDrawable(R.drawable.ic_done_black_24dp);
        tickDone.setBounds(0, 0, tickDone.getIntrinsicWidth(), tickDone.getIntrinsicHeight());


        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            allLeague = extras.getStringArrayList("allLeague");
            // and get whatever type user account id is
        }
        //System.out.println(allLeague.size() + "in here");

        leaguename = (EditText) findViewById(R.id.leaguename);
        prize = (EditText) findViewById(R.id.prize);
        password = (EditText) findViewById(R.id.password);
        pointsAllocated = (EditText) findViewById(R.id.points);

        validationOnTextView();

        selectleague = findViewById(R.id.selectleague);
        selectleague.setOnClickListener(new View.OnClickListener( ) {
            
            @Override
            public void onClick(View view){
                if (allLeague.contains(leaguename.getEditableText().toString())){
                    leaguename.setError("name is taken");
                    Toast.makeText(PrivateLeagueCreate.this,
                            "League name is already taken ", Toast.LENGTH_LONG).show();

                }else{

                if (!leaguename.getEditableText().toString().isEmpty() && !prize.getEditableText().toString().isEmpty() && !password.getEditableText().toString().isEmpty() && !pointsAllocated.getEditableText().toString().isEmpty() && nameOk && prizeOk && passwordOk && pointsAllocatedOk) {
            //if (leaguename!=null && prize!=null && password!=null) {
                Intent intent = new Intent(PrivateLeagueCreate.this, PrivateLeagueTournamentList.class);
                intent.putExtra("leaguename", leaguename.getText().toString());
                intent.putExtra("prize", prize.getText().toString());
                intent.putExtra("password", password.getText().toString());
                intent.putExtra("pointsAllocated", pointsAllocated.getText().toString());
                startActivity(intent);
            } else {
                    Toast.makeText(PrivateLeagueCreate.this,
                            "Please Complete the form", Toast.LENGTH_LONG).show();

                }
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
public void validationOnTextView() {
    leaguename.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (timer != null) {
                timer.cancel();
            }
        }

        @Override
        public void afterTextChanged(final Editable s) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (s.toString().isEmpty() || s.toString().length() < 3) {
                                leaguename.setError("minimum 3 characters");
                                nameOk = false;

                            }
                            else {
                                leaguename.setError("Good", tickDone);
                                nameOk = true;
                            }

                            /*
                            * if (lista.contains(conta1)) {
    System.out.println("Account found");
} else {
    System.out.println("Account not found");
}
                            * */


                        }
                    });
                }
            }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
        }
    });

    password.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (timer != null) {
                timer.cancel();
            }
        }

        @Override
        public void afterTextChanged(final Editable s) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (s.toString().isEmpty() || s.toString().length() < 3) {
                                password.setError("minimum 3 characters");
                                passwordOk = false;

                            } else {
                                password.setError("Good", tickDone);
                                passwordOk = true;
                            }
                        }
                    });
                }
            }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
        }
    });

    pointsAllocated.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (timer != null) {
                timer.cancel();
            }
        }

        @Override
        public void afterTextChanged(final Editable s) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                Integer.parseInt(s.toString());
                                pointsAllocated.setError("Good", tickDone);
                                pointsAllocatedOk = true;
                            }catch (NumberFormatException ex) {
                                pointsAllocated.setError("numbers only");
                                pointsAllocatedOk = false;
                            }
                        }
                    });
                }
            }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
        }
    });

    prize.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (timer != null) {
                timer.cancel();
            }
        }

        @Override
        public void afterTextChanged(final Editable s) {

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    PrivateLeagueCreate.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (s.toString().isEmpty() || s.toString().length() < 3 || s.toString().contains("$")|| s.toString().contains("money")) {
                                prize.setError("invalid input");
                                prizeOk = false;
                            } else {
                                prize.setError("Good", tickDone);
                                prizeOk = true;
                            }
                        }
                    });
                }
            }, 100); // 600ms delay before the timer executes the „run“ method from TimerTask
        }
    });
}
}
