package com.example.ole.oleandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ole.oleandroid.controller.SpecialsPredict;
import com.example.ole.oleandroid.dbConnection.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Matches extends AppCompatActivity {
    TextView match1;
    TextView match2;
    Button join1;
    Button join2;

    ArrayList<Integer> matchIdList = new ArrayList<>();
    ArrayList<TextView> matchTextView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        match1 = (TextView) findViewById(R.id.match1);
        match2 = (TextView) findViewById(R.id.match2);

        matchTextView.add(match1);
        matchTextView.add(match2);

        join1 = (Button) findViewById(R.id.join1);
        join2 = (Button) findViewById(R.id.join2);

        //JSONObject matches = getRecentMatches();

        final JSONObject[] results = new JSONObject[1];

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        final String url = new DBConnection().getMatchesUrl();

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setMatchesText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        queue.add(getRequest);


        final Bundle b = getIntent().getExtras();
        //String logId = b.getString("logId");

        join1.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Matches.this, Matches.class);
                b.putString("matchId", matchIdList.get(0).toString());
                intent.putExtras(b);
                startActivity(intent);


            }
        }));

        join2.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Matches.this, Matches.class);
                b.putString("matchId", matchIdList.get(1).toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        }));


    }

    public void setMatchesText(JSONObject matches) {
        try {
            JSONArray matchList = matches.getJSONArray("results");
            for (int i = 0; i < 2; i++) {
                JSONObject match = matchList.getJSONObject(i);
                System.out.println(match.toString());
                //int matchId = match.getInt("matchId");
                matchIdList.add(match.getInt("matchId"));
                matchTextView.get(i).setText(match.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
