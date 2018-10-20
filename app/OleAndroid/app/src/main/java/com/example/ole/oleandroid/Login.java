package com.example.ole.oleandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    EditText username, password;
    Button signin, signup;
    RequestQueue requestQueue ;
            //= Volley.newRequestQueue(getApplicationContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);


//        signin.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                String url = new DBConnection().getMainUrl()+ "/ole/login.php";
//                System.out.println("url :"+url);
//
//                HashMap<String, String> params = new HashMap<String, String>();
//                params.put("username", username.getText().toString());
//
//
//                requestQueue = Volley.newRequestQueue(getApplicationContext());
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                        Request.Method.POST,
//                        url,
//                        new JSONObject(params),
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                System.out.println("here1");
//                                try {
//                                    JSONArray students = response.getJSONArray("user");
//                                    for (int i = 0; i < students.length(); i++) {
//                                        JSONObject student = students.getJSONObject(i);
//                                        String username = student.getString("username");
//                                        String name = student.getString("name");
//                                        System.out.println(username);
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                System.out.println("error");
//                            }
//                        }
//                        );
//                requestQueue.add(jsonObjectRequest);
//
//            }
//        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("here2");
                String url = new DBConnection().getMainUrl()+"/ole/login.php";
                //requestQueue = Volley.newRequestQueue(getApplicationContext());

//                StringRequest request = new StringRequest(Request.Method.POST,
//                        url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                System.out.println(response);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                System.out.println("error");
//                            }
//                        }) {
//
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> parameters = new HashMap<String, String>();
//                        parameters.put("username", username.getText().toString());
//
//
//                        return parameters;
//                    }
//                };
//
//
//                requestQueue.add(request);


                // Creating string request with post method.
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
                                System.out.println("error  :");
                                volleyError.printStackTrace();
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


//        signup.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//            }
//        });
        });
    }
}
