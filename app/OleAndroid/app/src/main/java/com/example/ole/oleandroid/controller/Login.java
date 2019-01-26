package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.User;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    EditText username, password;
    Button signin, signup;
    RequestQueue requestQueue;
    TextView result;
    Button facebookButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);

        facebookButton = findViewById(R.id.facebookButton);


        //result = (TextView) findViewById(R.id.result);

        //setContentView(R.layout.activity_loading_page);

        /**
         * YT: for now i link it to check leagues (public and private)
         */
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, PublicLeagueList.class);
                startActivity(intent);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// comments starts here if want to bypass login webservice
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    //loadSamePage();
                } else {
                    System.out.println("Signing In " + username.getText().toString());
                    final String[] status = {"error"};

                    JSONObject json = new JSONObject();
                    try {
                        json.put("username", username.getText().toString());
                        json.put("password", password.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = DBConnection.getLoginUrl();
/*
                    PostHttp connection = new PostHttp();
                    String response = null;
                    try {
                        response = connection.post(url, json.toString());
                        System.out.println(response);

                        JSONObject result = new JSONObject(response);
                        status[0] = result.getString("status");

                        if (status[0].equals("success")) {
                            JSONObject user = result.getJSONObject("user");
                            String usernameRetrieved = user.getString("username");
                            System.out.println(usernameRetrieved);
                            String name = user.getString("name");
                            String password = user.getString("password");
                            String dob = user.getString("dob");
                            String country = user.getString("country");
                            String contactNum = user.getString("contactNum");
                            String email = user.getString("email");
                            String favoriteTeam = user.getString("favoriteTeam");

                            User userDetails = new User(usernameRetrieved, name, password, dob, country, contactNum, email, favoriteTeam);
                            UserDAO.setLoginUser(userDetails);

                            Intent intent = new Intent(Login.this, Home.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username", usernameRetrieved);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            //loadSamePage();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
*/


//comments stop here

                    // codes to bypass login with webservice
                    Intent intent = new Intent(Login.this, Home.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

    }

    public void loadSamePage() {
        Intent intent = new Intent(Login.this, Login.class);
        startActivity(intent);
    }
}

