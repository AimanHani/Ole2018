package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


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


        //result = (TextView) findViewById(R.id.result);

        //setContentView(R.layout.activity_loading_page);

        /**
         * YT: for now i link it to check leagues (public and private)
         */
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this , PublicLeagueList.class);
                 startActivity(intent);            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, Home.class);
//                Bundle bundle = new Bundle();
//                                    bundle.putString("userID", "bob");
//                                    intent.putExtras(bundle);
//                                    startActivity(intent);
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    //loadSamePage();
                } else {
                    System.out.println("Signing In "+username.getText().toString());

                    //tries to login
                    //String status = LoginDAO.validate(username.getText().toString(), password.getText().toString(), getApplicationContext());
                    String url = DBConnection.getLoginUrl();
                    final String[] status = {"error"};

// comments starts here if want to bypass login webservice
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {
                                    System.out.println(ServerResponse);








                                       

     
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
                            params.put("username", username.getText().toString());
                            params.put("password", password.getText().toString());
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
//comments stop here

                    // codes to bypass login with webservice
//                    Intent intent = new Intent(Login.this, Home.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username", username.getText().toString());
//                    intent.putExtras(bundle);
//                    startActivity(intent);

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

