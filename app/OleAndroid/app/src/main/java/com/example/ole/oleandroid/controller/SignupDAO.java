package com.example.ole.oleandroid.controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.dbConnection.DBConnection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignupDAO {
    private static Context context;
    public static String validate (final String username, final String name, final String password, final Date birthday, final String contactNo,final String email,final String country, final String team){
        String url = new DBConnection().getLoginUrl();
        final String[] results = new String[1];

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        System.out.println(ServerResponse);
                        results[0] = ServerResponse;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("error  :");
                        volleyError.printStackTrace();

                        results[0] = "error";
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("username", username);
                params.put("name", name);
                params.put("password", password);
                params.put("dob", birthday.toString());
                params.put("country", country);
                params.put("contactNo", contactNo);
                params.put("email", email);
                params.put("favoriteTeam", team);


                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

// Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
        return results[0];

    }

}
