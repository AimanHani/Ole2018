package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;

import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.Leaderboard.LeaderboardPublicAdapter;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueDetails;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueMatchesMain;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueSpecialsList;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.PublicLeague;
import com.example.ole.oleandroid.model.PublicLeagueProfile;

import java.io.Serializable;
import java.util.ArrayList;

public class PublicLeagueDetails extends SideMenuBar implements View.OnClickListener, Serializable {


    View view;
    Button button, predict;
    PublicLeagueDetailsAdapter publicLeagueDetailsAdapter;
    ListView membersListView;
    TextView prizeInput, leagueNameInput, publicPoints, totalNoMembers, specialtext, matchtext, mainview;
    PublicLeague publicLeague = null;
    int logId = 0;
    LinearLayout blackoutimage;
    FloatingActionButton main, predictSpecial, predictMatch;
    Animation FoodFabOpen, FoodFabClose, FabRClockwise, FabRAntiClockwise, Fadein, Fadeout;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_public_league_details, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        Bundle bundle = getIntent().getExtras();
        logId = bundle.getInt("logId");
        int leagueId = bundle.getInt("leagueId");
        publicLeague = PublicLeagueDAO.getOnePublicLeague(leagueId);

        //PublicLeagueDAO.loadPublicMembers(leagueId);

        membersListView = findViewById(R.id.membersListView);
        publicLeagueDetailsAdapter = new PublicLeagueDetailsAdapter(PublicLeagueDetails.this, PublicLeagueDAO.loadPublicMembers(leagueId));
        membersListView.setAdapter(publicLeagueDetailsAdapter);

        prizeInput = findViewById(R.id.publicPrizeInput);
        leagueNameInput = findViewById(R.id.leagueNameInput);
        publicPoints = findViewById(R.id.publicPoints);
        totalNoMembers = findViewById(R.id.totalNoMembers);


        if (publicLeague != null) {
            prizeInput.setText(publicLeague.getPrize());
            leagueNameInput.setText(publicLeague.getLeagueName());
            publicPoints.setText(publicLeague.getPointsAllocated() + "");
            totalNoMembers.setText(PublicLeagueDAO.getNumMembers()+"");
        }

        membersListView = findViewById(R.id.membersListView);
        ArrayList<PublicLeagueProfile> publicLeagueProfileList = ScoreBoardDAO.getPublicLeagueProfiles();
        LeaderboardPublicAdapter lbPubAdapter = new LeaderboardPublicAdapter(PublicLeagueDetails.this, publicLeagueProfileList);
        membersListView.setAdapter(lbPubAdapter);

//        publicLeagueDetailsAdapter = new PublicLeagueDetailsAdapter(PublicLeagueDetails.this, PublicLeagueDAO.loadPublicMembers(leagueId));
//        membersListView.setAdapter(publicLeagueDetailsAdapter);

//        predict = findViewById(R.id.predict);
//        predict.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PublicLeagueDetails.this, MatchesTabs.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("logId", publicLeague.getLogId());
//                bundle.putInt("leagueId", publicLeague.getLeagueId());
//                intent.putExtras(bundle);
//                PublicLeagueDetails.this.startActivity(intent);
//            }
//
//        });

        blackoutimage = findViewById(R.id.blackoutimage);
        main = findViewById(R.id.floatingActionButton);
        predictSpecial = findViewById(R.id.predictSpecial);
        predictMatch = findViewById(R.id.predictMatch);
        mainview = findViewById(R.id.mainview);

        // enable animations for FloatingActionButton
        FoodFabOpen = AnimationUtils.loadAnimation(this, R.anim.foodfabopen);
        FoodFabClose = AnimationUtils.loadAnimation(this, R.anim.foodfabclose);
        FabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
        FabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        specialtext = findViewById(R.id.specialtext);
        matchtext = findViewById(R.id.matchtext);

        main.bringToFront();
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    blackoutimage.startAnimation(Fadeout);
                    blackoutimage.setVisibility(View.GONE);
                    predictSpecial.startAnimation(FoodFabClose);
                    predictMatch.startAnimation(FoodFabClose);
                    main.startAnimation(FabRAntiClockwise);
                    predictSpecial.setClickable(false);
                    predictMatch.setClickable(false);
                    specialtext.setVisibility(View.GONE);
                    matchtext.setVisibility(View.GONE);
                    isOpen = false;


                } else {
                    blackoutimage.setVisibility(View.VISIBLE);
                    blackoutimage.startAnimation(Fadein);
                    predictSpecial.startAnimation(FoodFabOpen);
                    predictMatch.startAnimation(FoodFabOpen);
                    main.startAnimation(FabRClockwise);
                    predictSpecial.setClickable(true);
                    predictMatch.setClickable(true);
                    specialtext.setVisibility(View.VISIBLE);
                    matchtext.setVisibility(View.VISIBLE);
                    isOpen = true;

                    predictMatch.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(PublicLeagueDetails.this, MatchesTabs.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("logId", logId);
                            bundle.putInt("leagueId", publicLeague.getLeagueId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    predictSpecial.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent(PublicLeagueDetails.this, SpecialList.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("logId", logId);
                            bundle.putInt("leagueId", publicLeague.getLeagueId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    blackoutimage.setOnClickListener(this);
                }
            }
        });

    }



    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.blackoutimage:
                blackoutimage.startAnimation(Fadeout);
                blackoutimage.setVisibility(View.GONE);
                predictSpecial.startAnimation(FoodFabClose);
                predictMatch.startAnimation(FoodFabClose);
                main.startAnimation(FabRAntiClockwise);
                predictSpecial.setClickable(false);
                predictMatch.setClickable(false);
                specialtext.setVisibility(View.GONE);
                matchtext.setVisibility(View.GONE);
                isOpen = false;
                break;

            case R.id.specialtext:
                //Intent = new Intent(this.getActivity(), xxx.class);
                //startActivity();
                break;

            case R.id.matchtext:
                Intent intent = new Intent(PublicLeagueDetails.this, MatchesTabs.class);
                Bundle bundle = new Bundle();
                bundle.putInt("logId", publicLeague.getLogId());
                bundle.putInt("leagueId", publicLeague.getLeagueId());
                intent.putExtras(bundle);
                PublicLeagueDetails.this.startActivity(intent);
                break;

            case R.id.floatingActionButton:
                //mainview.setText("BYEBYE");
                if (isOpen) {
                    blackoutimage.startAnimation(Fadeout);
                    blackoutimage.setVisibility(View.GONE);
                    predictSpecial.startAnimation(FoodFabClose);
                    predictMatch.startAnimation(FoodFabClose);
                    main.startAnimation(FabRAntiClockwise);
                    predictSpecial.setClickable(false);
                    predictMatch.setClickable(false);
                    specialtext.setVisibility(View.GONE);
                    matchtext.setVisibility(View.GONE);
                    isOpen = false;


                } else {
                    blackoutimage.setVisibility(View.VISIBLE);
                    blackoutimage.startAnimation(Fadein);
                    predictSpecial.startAnimation(FoodFabOpen);
                    predictMatch.startAnimation(FoodFabOpen);
                    main.startAnimation(FabRClockwise);
                    predictSpecial.setClickable(true);
                    predictMatch.setClickable(true);
                    specialtext.setVisibility(View.VISIBLE);
                    matchtext.setVisibility(View.VISIBLE);
                    isOpen = true;

                    predictMatch.setOnClickListener(this);
                    predictSpecial.setOnClickListener(this);
                    blackoutimage.setOnClickListener(this);
                }

                break;
        }
    }
}
