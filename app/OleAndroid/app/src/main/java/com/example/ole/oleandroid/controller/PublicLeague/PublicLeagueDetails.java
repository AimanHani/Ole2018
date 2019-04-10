package com.example.ole.oleandroid.controller.PublicLeague;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.R;

import com.example.ole.oleandroid.controller.DAO.PrivateLeagueDAO;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.DAO.TeamCountryItemDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.Leaderboard.LeaderboardPublicAdapter;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueDetails;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueMain;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueMatchesMain;
import com.example.ole.oleandroid.controller.PrivateLeagueController.PrivateLeagueSpecialsList;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.CountryItem;
import com.example.ole.oleandroid.model.PublicLeague;
import com.example.ole.oleandroid.model.PublicLeagueProfile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PublicLeagueDetails extends SideMenuBar implements View.OnClickListener, Serializable {
    View view;
    Button button, predict;
    PublicLeagueDetailsAdapter publicLeagueDetailsAdapter;
    ListView membersListView;
    TextView prizeInput, leagueNameInput, totalNoMembers, specialtext, matchtext, mainview, leaveText;
    LinearLayout blackoutimage;
    PublicLeague publicLeague;
    FloatingActionButton main, predictSpecial, predictMatch, leaveCompetiion;
    Animation FoodFabOpen, FoodFabClose, FabRClockwise, FabRAntiClockwise, Fadein, Fadeout;
    boolean isOpen = false;
    int logId, leagueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_public_league_details, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        Bundle bundle = getIntent().getExtras();
        logId = bundle.getInt("logId");
        leagueId = bundle.getInt("leagueId");

        publicLeague = PublicLeagueDAO.getOnePublicLeague(leagueId);
        ArrayList<PublicLeagueProfile> publicLeagueProfileList = ScoreBoardDAO.getPublicLeagueProfiles();

        prizeInput = findViewById(R.id.publicPrizeInput);
        leagueNameInput = findViewById(R.id.leagueNameInput);
        //publicPoints = findViewById(R.id.publicPoints);
        totalNoMembers = findViewById(R.id.totalNoMembers);
        membersListView = findViewById(R.id.membersListView);


        prizeInput.setText(publicLeague.getPrize());
        leagueNameInput.setText(publicLeague.getLeagueName());
        totalNoMembers.setText(publicLeagueProfileList.size() + "");

        LeaderboardPublicAdapter lbPubAdapter = new LeaderboardPublicAdapter(PublicLeagueDetails.this, publicLeagueProfileList);
        membersListView.setAdapter(lbPubAdapter);

        blackoutimage = findViewById(R.id.blackoutimage);
        main = findViewById(R.id.floatingActionButton);
        predictSpecial = findViewById(R.id.predictSpecial);
        predictMatch = findViewById(R.id.predictMatch);
        mainview = findViewById(R.id.mainview);
        leaveCompetiion = findViewById(R.id.leaveCompetiion);


        // enable animations for FloatingActionButton
        FoodFabOpen = AnimationUtils.loadAnimation(this, R.anim.foodfabopen);
        FoodFabClose = AnimationUtils.loadAnimation(this, R.anim.foodfabclose);
        FabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
        FabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        specialtext = findViewById(R.id.specialtext);
        matchtext = findViewById(R.id.matchtext);
        leaveText = findViewById(R.id.leaveText);
        predictMatch.setOnClickListener(this);
        predictSpecial.setOnClickListener(this);
        blackoutimage.setOnClickListener(this);
        leaveCompetiion.setOnClickListener(this);

        main.bringToFront();
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    closeBlackout();
                } else {
                    openBlackout();
                }
            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blackoutimage:
                closeBlackout();
                break;
            case R.id.specialtext:
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
                if (isOpen) {
                    closeBlackout();
                } else {
                    openBlackout();
                }
                break;
            case R.id.predictMatch:
                Intent intent2 = new Intent(PublicLeagueDetails.this, MatchesTabs.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("logId", logId);
                bundle2.putInt("leagueId", publicLeague.getLeagueId());
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.predictSpecial:
                Intent intent3 = new Intent(PublicLeagueDetails.this, SpecialList.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("logId", logId);
                bundle3.putInt("leagueId", publicLeague.getLeagueId());
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;
            case R.id.leaveCompetiion:
                leaveCompetition();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PublicLeagueDetails.this, PublicLeagueList.class);
        startActivity(intent);
    }

    private void closeBlackout() {
        blackoutimage.startAnimation(Fadeout);
        blackoutimage.setVisibility(View.GONE);
        predictSpecial.startAnimation(FoodFabClose);
        predictMatch.startAnimation(FoodFabClose);
        leaveCompetiion.startAnimation(FoodFabClose);
        main.startAnimation(FabRAntiClockwise);
        predictSpecial.setClickable(false);
        predictMatch.setClickable(false);
        leaveCompetiion.setClickable(false);
        specialtext.setVisibility(View.GONE);
        matchtext.setVisibility(View.GONE);
        leaveText.setVisibility(View.GONE);
        isOpen = false;
    }

    public void openBlackout() {
        blackoutimage.setVisibility(View.VISIBLE);
        blackoutimage.startAnimation(Fadein);
        predictSpecial.startAnimation(FoodFabOpen);
        predictMatch.startAnimation(FoodFabOpen);
        leaveCompetiion.startAnimation(FoodFabOpen);
        main.startAnimation(FabRClockwise);
        predictSpecial.setClickable(true);
        predictMatch.setClickable(true);
        leaveCompetiion.setClickable(true);
        specialtext.setVisibility(View.VISIBLE);
        matchtext.setVisibility(View.VISIBLE);
        leaveText.setVisibility(View.VISIBLE);
        specialtext.bringToFront();
        matchtext.bringToFront();
        leaveText.bringToFront();
        isOpen = true;
    }

    public Dialog loadingDialog() {
        System.out.println("loading pop");
        Dialog dialog = new Dialog(PublicLeagueDetails.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView text = dialog.findViewById(R.id.textStatus);
        text.setText("Leaving");
        dialog.show();
        return dialog;
    }

    private void leaveCompetition() {
        final Dialog load = loadingDialog();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        boolean deleteStatus = PrivateLeagueDAO.exitPrivateLeague(leagueId+"", UserDAO.getLoginUser().getUsername());
                        if (deleteStatus) {
                            Toast.makeText(getBaseContext(), "Successfully left" + publicLeague.getLeagueName(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(PublicLeagueDetails.this, PublicLeagueList.class);
                            intent.putExtra("FROM_ACTIVITY", "delete");
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), "Failed to leave" + publicLeague.getLeagueName() + " , please try again.", Toast.LENGTH_LONG).show();
                            load.dismiss();
                            closeBlackout();
                        }
                    }
                }, 3000);
    }
}
