package com.example.ole.oleandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    TextView specials1;
    TextView specials2;
    TextView specials3;

    EditText input1;
    EditText input2;
    EditText input3;

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specials_predict);

        //specialsListContainer = (LinearLayout) findViewById(R.id.specialsListContainer);
        //specialsDisplay = (TextView) findViewById(R.id.specialsDisplay);

        specials1 = (TextView) findViewById(R.id.specials1);
        specials2 = (TextView) findViewById(R.id.specials2);
        specials3 = (TextView) findViewById(R.id.specials3);

        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        input3 = (EditText) findViewById(R.id.input3);

        confirm = (Button) findViewById(R.id.confirm);

        Bundle b = getIntent().getExtras();
        logId = b.getString("logId");
        System.out.println("logId : " + logId);


        String url = new DBConnection().manageSpecialsUrl();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String serverResponse) {
                        System.out.println("ServerResponse" + serverResponse);
                        showSpecials(serverResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("error  :");
                        //volleyError.printStackTrace();
                        //results[0] = "error";
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

        confirm.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        }));

    }

    public void showSpecials(String serverResponse){
        try {
            JSONObject jsonObj = new JSONObject(serverResponse);
            JSONArray specialsList = jsonObj.getJSONArray("results");

            TextView[] allText = new TextView[]{specials1,specials2,specials3};

            for (int i = 0; i < specialsList.length(); i++) {
                JSONObject specials = specialsList.getJSONObject(i);
                String description = specials.getString("description");
                System.out.println("line 50: " + description);
                allText[i].setText(description);
//                specialsDisplay.append(description);
//                TextView specialText = new TextView(this);
//                specialText.setText(description);
//                specialText.setId(i);
//                specialsListContainer.addView(specialText);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
