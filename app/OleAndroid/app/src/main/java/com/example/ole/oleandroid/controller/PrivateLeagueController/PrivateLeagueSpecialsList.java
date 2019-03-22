package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PSpecialDAO;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.PSpecials;

import java.util.ArrayList;

public class PrivateLeagueSpecialsList extends SideMenuBar {

    PrivateLeagueSpecialsAdapter privateLeagueSpecialAdapter;
    ListView specialListView;
    Button confirmspecialsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_private_league_specials_list, null, false);
        super.mDrawerlayout.addView(contentView, 0);

        specialListView = findViewById(R.id.specialListView);
        confirmspecialsbtn = findViewById(R.id.confirmspecialsbtn);

        Bundle bundle = getIntent().getExtras();
        final int logId = bundle.getInt("logId");
        final int leagueId = bundle.getInt("leagueId");

        final ArrayList<PSpecials> specialsList = PSpecialDAO.getSpecialsList(logId, leagueId);
        privateLeagueSpecialAdapter = new PrivateLeagueSpecialsAdapter(PrivateLeagueSpecialsList.this, specialsList);
        specialListView.setAdapter(privateLeagueSpecialAdapter);
        confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<PSpecials> newList = privateLeagueSpecialAdapter.getUpdatedSpecialList();
                int totalPoints = 0;
                for (PSpecials s : newList) {
                    System.out.println("Special: " + s.getSpecialsID() + ", " + s.getPrediction() + ", " + s.getDoubleIt() + ", " + s.getPoints());
                    if (s.getDoubleIt()) {
                        totalPoints += s.getPoints() * 2;
                    } else {
                        totalPoints += s.getPoints();
                    }
                }
                final Dialog dialog = new Dialog(PrivateLeagueSpecialsList.this);
                dialog.setContentView(R.layout.confirmspecialspopout);
                TextView msg = dialog.findViewById(R.id.confirmMessage);
                msg.setText("You could earn up to " + totalPoints + " Ole points! Confirm specials?");
//                TextView cancel = dialog.findViewById(R.id.cancel);
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                ImageView closeWindow = dialog.findViewById(R.id.closeWindow);
//                closeWindow.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });

                TextView end = dialog.findViewById(R.id.end);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                TextView confirmspecialsbtn = dialog.findViewById(R.id.confirmspecialsbtn);
                confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            PSpecialDAO.updateSpecialsPrediction(newList, leagueId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


//                        Intent i = new Intent(PrivateLeagueSpecialsList.this, PrivateLeagueDetails.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("logId", logId);
//                        bundle.putInt("leagueId", leagueId);
//                        i.putExtras(bundle);
//                        startActivity(i);
                        Intent i = new Intent(PrivateLeagueSpecialsList.this, PrivateLeagueDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("leagueid", leagueId+"");
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
                dialog.show();
            }
        });
    }

    public static ArrayList<String> players() {
        ArrayList<String> players = new ArrayList<>();
        players.add("Lionel Messi");
        players.add("Sergio Ramos");
        players.add("Marco Reus");
        players.add("Zlatan Ibrahimovic");
        players.add("Andrea Pirlo");
        players.add("Lionel Messi");
        players.add("Paul Pogba");
        return players;
    }
}
