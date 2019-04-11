package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.PSpecialDAO;
import com.example.ole.oleandroid.controller.DAO.SpecialDAO;
import com.example.ole.oleandroid.controller.FormGuideDetails;
import com.example.ole.oleandroid.controller.PublicLeague.SpecialList;
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

                TextView end = dialog.findViewById(R.id.end);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                TextView confirmspecialsbtn = dialog.findViewById(R.id.confirmspecialsbtn);
                final boolean[] saveSpecials = {false};
                confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            saveSpecials[0] = PSpecialDAO.updateSpecialsPrediction(newList, leagueId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (saveSpecials[0]){
                            Intent i = new Intent(PrivateLeagueSpecialsList.this, PrivateLeagueDetails.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("leagueid", leagueId+"");
                            i.putExtra("FROM_ACTIVITY", "specials");
                            i.putExtras(bundle);
                            startActivity(i);
                        }

                    }
                });
                dialog.show();
            }
        });
    }

    public static ArrayList<String> players() {
        ArrayList<String> players = SpecialDAO.loadPlayers();
//        players.add("Lionel Messi");
//        players.add("Sergio Ramos");
//        players.add("Marco Reus");
//        players.add("Zlatan Ibrahimovic");
//        players.add("Andrea Pirlo");
//        players.add("Lionel Messi");
//        players.add("Paul Pogba");
        return players;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.infotoolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                Intent intent = new Intent(PrivateLeagueSpecialsList.this, FormGuideDetails.class);
                intent.putExtra("page", "specials");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
