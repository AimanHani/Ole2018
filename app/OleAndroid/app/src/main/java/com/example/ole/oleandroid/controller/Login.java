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
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueMain;


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
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);

        facebookButton = findViewById(R.id.facebookButton);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //result = (TextView) findViewById(R.id.result);
        //setContentView(R.layout.activity_loading_page);

        /**
         * YT: for now i link it to check leagues (public and private)
         */
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, PrivateLeagueMain.class);
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
                    String status = "error";

                    String send = concatUsernamePwd(username.getText().toString(), password.getText().toString());

                    Boolean valid = LoginDAO.validate(send);

                    if (valid){
                        Intent intent = new Intent(Login.this, SideMenuBar.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {

                    }

//comments stop here

                    // codes to bypass login with webservice
//                    Intent intent = new Intent(Login.this, Home.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username", username.getText().toString());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//
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

    public String concatUsernamePwd(String username, String pwd) {
        return "username=" + username + "&password=" + pwd;
    }
}

