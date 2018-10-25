package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    EditText username, password;
    Button signin, signup;
    RequestQueue requestQueue;
    TextView result;
    //= Volley.newRequestQueue(getApplicationContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        result = (TextView) findViewById(R.id.result);

        setContentView(R.layout.activity_home);



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("");
                System.out.println("here2");
//                String results = LoginDAO.validate(username.getText().toString(), password.getText().toString());
//                result.append(results);

                String url = new DBConnection().getLoginUrl();
                //final String[] results = new String[1];

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                System.out.println(ServerResponse);
                                //results[0] = ServerResponse;
                                result.append(ServerResponse);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                System.out.println("error  :");
                                volleyError.printStackTrace();
                                result.append("error");
                                //results[0] = "error";
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        // The firs argument should be same sa your MySQL database table columns.
                        params.put("username", username.getText().toString());

                        return params;
                    }

                };

                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

// Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);


            }
            //);

        });
        signup = (Button) findViewById(R.id.login);

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

    }

        //        signup.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//            }
//        });
    }

