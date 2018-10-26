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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                Map<String, String> params = new HashMap<String, String>( );
                try {


                    // Creating Map String Params.


                    // Adding All values to Params.
                    // The firs argument should be same sa your MySQL database table columns.
                    params.put("username", username);
                    params.put("name", name);
                    params.put("password", SHA1(password));
                    params.put("dob", birthday.toString( ));
                    params.put("country", country);
                    params.put("contactNo", contactNo);
                    params.put("email", email);
                    params.put("favoriteTeam", team);


                }
                catch(Exception e){

                }
                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

// Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
        return results[0];

    }
    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length( ));
        sha1hash = md.digest( );
        return convertToHex(sha1hash);
    }

}
