package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;

import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.PublicMembersDAO;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PublicLeague;
import com.example.ole.oleandroid.model.PublicMembers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class PublicLeagueDetails extends SideMenuBar implements View.OnClickListener, Serializable {

    View view;
    Button button, predict;
    PublicLeagueDetailsAdapter publicLeagueDetailsAdapter;
    ListView LeagueListView;
    TextView prizeInput, leagueNameInput,publicPoints;
    PublicLeague publicLeague = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        publicLeague = PublicMembersDAO.getLeague();

        View contentView = inflater.inflate(R.layout.activity_public_league_details, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        PrivateLeagueDAO.clearAllPrivateLeague();
        String url2 = DBConnection.insertPrivateLeagueUrl()+"?method=retrieveMembers&leagueid="+publicLeague.getLeagueId();

        System.out.println("Getting public league members");
        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url2);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray members = result.getJSONArray("results");

            PublicMembersDAO.clearAllPublicMembers();
            if (members.length() > 0) {
                for (int i = 0; i < members.length(); i++) {
                    JSONObject membersObj = members.getJSONObject(i);
                    PublicMembers publicmembers = new PublicMembers(
                            membersObj.getInt("logid"),
                            membersObj.getString("username"),
                            membersObj.getInt("leagueid"),
                            membersObj.getInt("points")
                    );
                    PublicMembersDAO.addPublicMembers(publicmembers);
                }
            } else {            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        //System.out.println(PublicMembersDAO.getAllPublicMembers().size());
        prizeInput = findViewById(R.id.publicPrizeInput);
        leagueNameInput = findViewById(R.id.leagueNameInput);
        publicPoints = findViewById(R.id.publicPoints);

        if (publicLeague != null) {
            prizeInput.setText(publicLeague.getPrize());
            leagueNameInput.setText(publicLeague.getLeagueName());
            publicPoints.setText(publicLeague.getPointsAllocated()+"");
        }
        LeagueListView = findViewById(R.id.privateLeagueListView);
        publicLeagueDetailsAdapter = new PublicLeagueDetailsAdapter(PublicLeagueDetails.this, PublicMembersDAO.getAllPublicMembers());
        LeagueListView.setAdapter(publicLeagueDetailsAdapter);

        predict = findViewById(R.id.predict);
        predict.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(PublicLeagueDetails.this, MatchesTabs.class);
                Bundle bundle = new Bundle();
                bundle.putInt("logId", publicLeague.getLogId());
                bundle.putInt("leagueId", publicLeague.getLeagueId());
                intent.putExtras(bundle);
                PublicLeagueDetails.this.startActivity(intent);
            }

        });
    }

    @Override
    public void onClick(View v) {    }
}
