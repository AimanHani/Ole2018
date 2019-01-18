package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginDAO {

    public static String validate (final String username, final String password, Context context){
        String url = new DBConnection().getLoginUrl();
        final boolean[] process = {true};
        final String[] status = {"error"};
        RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String ServerResponse) {
                            System.out.println(ServerResponse);
                            while (!process[0]) {
                                try {
                                    JSONObject result = new JSONObject(ServerResponse);
                                    status[0] = result.getString("status");

                                    JSONObject user = result.getJSONObject("user");
                                    String username = user.getString("username");
                                    String name = user.getString("name");
                                    String password = user.getString("password");
                                    String dob = user.getString("dob");
                                    String country = user.getString("country");
                                    String contactNum = user.getString("contactNum");
                                    String email = user.getString("email");
                                    String favoriteTeam = user.getString("favoriteTeam");

                                    User userDetails = new User(username, name, password, dob, country, contactNum, email, favoriteTeam);
                                    UserDAO.setLoginUser(userDetails);

                                    process[0] = false;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
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
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);


        return status[0];
    }

    public static String returnResult (String results){
        return results;
    }
}
