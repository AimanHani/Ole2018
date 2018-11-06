package com.example.ole.oleandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SpecialsPredict extends AppCompatActivity {
    String logId;
    LinearLayout specialsListContainer;
    TextView specialsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specials_predict);

        specialsListContainer = (LinearLayout) findViewById(R.id.specialsListContainer);
        specialsDisplay = (TextView) findViewById(R.id.specialsDisplay);

        Bundle b = getIntent().getExtras();
        logId = b.getString("logId");
        System.out.println("logId : " + logId);
//        String listSpecials = getSpecials(logId);
//
//        try {
//            JSONObject jsonObj = new JSONObject(listSpecials);
//            JSONArray specialsList = jsonObj.getJSONArray("results");
//            for (int i = 0; i < specialsList.length(); i++) {
//                JSONObject specials = specialsList.getJSONObject(i);
//                String description = specials.getString("description");
//                System.out.println("line 50: " + description);
//                //specialsDisplay.append(description);
////                TextView specialText = new TextView(this);
////                specialText.setText(description);
////                specialText.setId(i);
////                specialsListContainer.addView(specialText);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        //JSONObject firstLeague = publicLeague.getJSONObject(0);

//        try {
//            JSONArray recentMatches = matches.getJSONArray("results");
//
//            for (int i = 0; i < recentMatches.length(); i++) {
//                JSONObject match = recentMatches.getJSONObject(i);
//                //create text views of multiple matches and join buttons
//                Button myButton = new Button(this);
//                myButton.setText("Predict");
//                matchListContainer.addView(myButton);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }

    public String getSpecials(final String logId) {
        final String[] results = new String[1];

        String url = new DBConnection().manageSpecialsUrl();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        System.out.println("ServerResponse" + ServerResponse);
                        results[0] = ServerResponse;
                        return;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("error  :");
                        //volleyError.printStackTrace();
                        results[0] = "error";
                        return;
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                try {
                    params.put("logId", logId);
                    params.put("method", "get");
                } catch (Exception e) {

                }
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        return results[0];
    }

    public JSONObject getRecentMatches() {
        final JSONObject[] results = new JSONObject[1];

        RequestQueue queue = Volley.newRequestQueue(this);

        final String url = new DBConnection().getPublicLeagueUrl();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        //listPublicLeague.append(response.toString());
                        results[0] = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(getRequest);
        return results[0];
    }
}
