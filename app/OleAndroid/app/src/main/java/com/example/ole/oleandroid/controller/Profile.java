package com.example.ole.oleandroid.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.TeamCountryItemDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.model.User;

public class Profile extends SideMenuBar {
//    TextView details;

    TextView pointsGotten;
    TextView userName;
    TextView userCountry;
    TextView userFavTeam;
    TextView wonQty;
    TextView lostQty;
    TextView playQty;
    TextView accQty;
    ImageView teamImage;

    /*String matchId;
    String logId;
    String username;
    String finalResults;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        //get user object
        Intent i = getIntent();
        //final User u = (User)i.getSerializableExtra("User");
        User loginUser = UserDAO.getLoginUser();
        String numLeaguesJoined = UserDAO.getProfileStatistics();

//        details = findViewById(R.id.details);
        pointsGotten = findViewById(R.id.pointsGotten);
        userName = findViewById(R.id.userName);
        userCountry = findViewById(R.id.userCountry);
        userFavTeam = findViewById(R.id.userFavTeam);
        userFavTeam = findViewById(R.id.userFavTeam);
        wonQty = findViewById(R.id.wonQty);
        lostQty = findViewById(R.id.lostQty);
        playQty = findViewById(R.id.playQty);
        accQty = findViewById(R.id.accQty);
        teamImage = findViewById(R.id.teamImage);

        userName.setText(loginUser.getUsername());
        userCountry.setText(loginUser.getCountry());
        userFavTeam.setText(loginUser.getFavoriteTeam());
        if (numLeaguesJoined != null){
            playQty.setText(numLeaguesJoined);
        }
        teamImage.setImageResource(TeamCountryItemDAO.teamItemsList.get(loginUser.getFavoriteTeam()).getmTeamImage());


    }
}
