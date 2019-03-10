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

import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.PublicLeague;

import java.io.Serializable;

public class PublicLeagueDetails extends SideMenuBar implements View.OnClickListener, Serializable {

    View view;
    Button button, predict;
    PublicLeagueDetailsAdapter publicLeagueDetailsAdapter;
    ListView membersListView;
    TextView prizeInput, leagueNameInput, publicPoints;
    PublicLeague publicLeague = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_public_league_details, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        Bundle bundle = getIntent().getExtras();
        int logId = bundle.getInt("logId");
        int leagueId = bundle.getInt("leagueId");
        publicLeague = PublicLeagueDAO.getOnePublicLeague(leagueId);

        prizeInput = findViewById(R.id.publicPrizeInput);
        leagueNameInput = findViewById(R.id.leagueNameInput);
        publicPoints = findViewById(R.id.publicPoints);

        if (publicLeague != null) {
            prizeInput.setText(publicLeague.getPrize());
            leagueNameInput.setText(publicLeague.getLeagueName());
            publicPoints.setText(publicLeague.getPointsAllocated() + "");
        }

        membersListView = findViewById(R.id.membersListView);
        publicLeagueDetailsAdapter = new PublicLeagueDetailsAdapter(PublicLeagueDetails.this, PublicLeagueDAO.loadPublicMembers(leagueId));
        membersListView.setAdapter(publicLeagueDetailsAdapter);

        predict = findViewById(R.id.predict);
        predict.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
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
    public void onClick(View v) {
    }
}
