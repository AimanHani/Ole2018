package com.example.ole.oleandroid.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.SignupDAO;
import com.example.ole.oleandroid.controller.DAO.TeamCountryItemDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.model.TeamItems;
import com.example.ole.oleandroid.model.User;

public class Profile extends SideMenuBar {
    TextView userName, userCountry, userFavTeam, matchAccValue, specialsAccValue, playQty, accQty, changeTeam;
    ImageView teamImage, countryImage;
    User loginUser;

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
        loginUser = UserDAO.getLoginUser();
        String numLeaguesJoined = UserDAO.getProfileStatistics();

        userName = findViewById(R.id.userName);
        userCountry = findViewById(R.id.userCountry);
        userFavTeam = findViewById(R.id.userFavTeam);
        teamImage = findViewById(R.id.teamImage);
        countryImage = findViewById(R.id.countryImage);
        changeTeam = findViewById(R.id.changeTeam);

        userName.setText(loginUser.getUsername());
        userCountry.setText(loginUser.getCountry());
        countryImage.setImageResource(TeamCountryItemDAO.getCountryImageResource(loginUser.getCountry()));
        userFavTeam.setText(loginUser.getFavoriteTeam());
        teamImage.setImageResource(TeamCountryItemDAO.getTeamImageResource(loginUser.getFavoriteTeam()));
        changeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //something
                changeTeamDialog();
            }
        });

        matchAccValue = findViewById(R.id.matchAccValue);
        specialsAccValue = findViewById(R.id.specialsAccValue);
        playQty = findViewById(R.id.playQty);
        accQty = findViewById(R.id.accQty);
        if (numLeaguesJoined != null) {
            playQty.setText(numLeaguesJoined);
        }
    }


    public Dialog changeTeamDialog() {
        final Dialog dialog = new Dialog(Profile.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.change_team_popup);
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        final Spinner favouriteTeamSpinner = dialog.findViewById(R.id.favouriteTeamSpinner);
        TeamAdapter mAdapter2 = new TeamAdapter(this, TeamCountryItemDAO.initiateTeamArrayList());
        favouriteTeamSpinner.setAdapter(mAdapter2);
        final String[] clickedTeamName = {null};

        favouriteTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TeamItems clickedItem = (TeamItems) parent.getItemAtPosition(position);
                clickedTeamName[0] = clickedItem.getmTeamName();
                //Toast.makeText(Profile.this, clickedTeamName[0] + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView confirm = dialog.findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //something
                if (clickedTeamName[0] != null) {
                    //Toast.makeText(Profile.this, clickedTeamName[0] + " update DB", Toast.LENGTH_SHORT).show();
                    String status = UserDAO.updateFavoriteTeam(clickedTeamName[0]);
                    if (status.equals("successful")){
                        dialog.dismiss();
                    }
                    Toast.makeText(Profile.this, clickedTeamName[0] + " changed "+ status, Toast.LENGTH_SHORT).show();
                    loginUser.setFavoriteTeam(clickedTeamName[0]);
                    userFavTeam.setText(loginUser.getFavoriteTeam());
                    teamImage.setImageResource(TeamCountryItemDAO.getTeamImageResource(loginUser.getFavoriteTeam()));

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
