package com.example.ole.oleandroid.controller;

import android.content.Intent;
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
import com.example.ole.oleandroid.Matches;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.model.CountryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

    ArrayList<EditText> predictionsList = new ArrayList<>();
    int count = 0;

    private ArrayList<Integer> specialsIds = new ArrayList();

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
        predictionsList.add(input1);
        predictionsList.add(input2);
        predictionsList.add(input3);


        confirm = (Button) findViewById(R.id.confirm);

        final Bundle b = getIntent().getExtras();
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
                String url = new DBConnection().manageSpecialsUrl();

                for (int i = 0; i < specialsIds.size(); i++) {


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {
                                    System.out.println(ServerResponse);
                                    //results[0] = ServerResponse;
                                    //result.append(ServerResponse);
                                    Intent intent = new Intent(SpecialsPredict.this, Matches.class);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    System.out.println("error  :");
                                    volleyError.printStackTrace();
                                    //result.append("error");
                                    //results[0] = "error";
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            try {
                                params.put("prediction", predictionsList.get(count).getText().toString());
                                params.put("logId", logId);
                                params.put("specialsId", specialsIds.get(count).toString());
                                params.put("method", "post");
                            } catch (Exception e) {

                            }
                            count++;
                            return params;
                        }

                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        }));

    }

    public void showSpecials(String serverResponse) {
        try {
            JSONObject jsonObj = new JSONObject(serverResponse);
            JSONArray specialsList = jsonObj.getJSONArray("results");

            TextView[] allText = new TextView[]{specials1, specials2, specials3};

            for (int i = 0; i < specialsList.length(); i++) {
                JSONObject specials = specialsList.getJSONObject(i);
                String description = specials.getString("description");
                int specialsID = specials.getInt("specialsId");
                specialsIds.add(specialsID);
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

}
