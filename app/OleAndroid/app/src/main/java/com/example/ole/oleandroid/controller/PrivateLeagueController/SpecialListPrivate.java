package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.Specials;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.model.Teams;

public class SpecialListPrivate extends SideMenuBar {
    SpecialListPrivateAdapter specialListPrivateAdapter;
    ListView specialListView;
    Button confirmspecialsbtn;
    ArrayList<Specials> specialsList = new ArrayList<>();
    String specials = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.speciallistprivate, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        specialListView = findViewById(R.id.specialListView);
        confirmspecialsbtn = findViewById(R.id.confirmspecialsbtn);


        int logId = 2;

        Bundle bundle = getIntent().getExtras();
        //int logId = bundle.getInt("logId");

        String url = DBConnection.getSpecials();
        System.out.println("Getting specials list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            if (response != null) {
                JSONObject result = new JSONObject(response);
                JSONArray publicLeagues = result.getJSONArray("results");
                System.out.println(publicLeagues.length());
                if (publicLeagues.length() > 0) {
                    for (int i = 0; i < publicLeagues.length(); i++) {
                        JSONObject specialsObject = publicLeagues.getJSONObject(i);
                        Specials special = new Specials(
                                specialsObject.getInt("specialsId"),
                                specialsObject.getString("description"),
                                specialsObject.getInt("points")
                        );
                        specialsList.add(special);
                    }

                } else {
                    //loadSamePage();
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }


        specialListPrivateAdapter = new SpecialListPrivateAdapter(SpecialListPrivate.this, specialsList);
        specialListView.setAdapter(specialListPrivateAdapter);

        confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                specials="";
                for(Specials s: specialsList){
                    if(s.getChecked()){
                        specials+=s.getSpecialsID()+",";
                    }
                }
                //System.out.println("HI"+specials.substring(0, specials.length() - 1));
                try {
                    specials = specials.substring(0, specials.length() - 1);
                }catch(Exception e){

                }
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    Intent intent = new Intent(SpecialListPrivate.this, TeamListPrivate.class);
                    intent.putExtra("leaguename", extras.getString("leaguename"));
                    intent.putExtra("prize", extras.getString("prize"));
                    intent.putExtra("password", extras.getString("password"));
                    intent.putExtra("pointsAllocated", extras.getString("pointsAllocated"));
                    intent.putExtra("leagueid", extras.getString("leagueid"));
                    intent.putExtra("startdate", extras.getString("startdate"));
                    intent.putExtra("enddate", extras.getString("enddate"));
                    intent.putExtra("specials", specials);
                    startActivity(intent);
                }
            }
        });

    }
}
