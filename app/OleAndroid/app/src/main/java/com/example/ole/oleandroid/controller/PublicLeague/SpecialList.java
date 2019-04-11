package com.example.ole.oleandroid.controller.PublicLeague;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
import com.example.ole.oleandroid.controller.HomeLeague;
import com.example.ole.oleandroid.controller.Matches;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.PSpecials;
import com.example.ole.oleandroid.model.Specials;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpecialList extends SideMenuBar {

    SpecialListAdapter specialListAdapter;
    //ArrayList<Integer> pointsList;
    //ArrayList<String> specialNameList;
    ListView specialListView;
    Button confirmspecialsbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.speciallist); //contain item list view e.g. item1, item2

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.speciallist, null, false);
        super.mDrawerlayout.addView(contentView, 0);

//        pointsList = new ArrayList<>();
//        specialNameList = new ArrayList<>();

        specialListView = findViewById(R.id.specialListView);
        confirmspecialsbtn = findViewById(R.id.confirmspecialsbtn);

        //int logid = 2;

        Bundle bundle = getIntent().getExtras();
        final int logId = bundle.getInt("logId");
        final int leagueId = bundle.getInt("leagueId");

        final ArrayList<PSpecials> specialsList = PSpecialDAO.getSpecialsList(logId, leagueId);

        specialListAdapter = new SpecialListAdapter(SpecialList.this, specialsList);
        specialListView.setAdapter(specialListAdapter);

        //hani refer to this part for the pop up box
        confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<PSpecials> newList = specialListAdapter.getUpdatedSpecialList();

                int totalPoints = 0;

                for (PSpecials s : newList) {
                    System.out.println("Special: " + s.getSpecialsID() + ", " + s.getPrediction() + ", " + s.getDoubleIt() + ", " + s.getPoints());
                    if (s.getDoubleIt()) {
                        totalPoints += s.getPoints() * 2;
                    } else {
                        totalPoints += s.getPoints();
                    }
                }

                final Dialog dialog = new Dialog(SpecialList.this);
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
                        Intent intent = new Intent(SpecialList.this, PublicLeagueDetails.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra("FROM_ACTIVITY", "specials");
                        bundle.putInt("logId", logId);
                        bundle.putInt("leagueId", leagueId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

                dialog.show();
                //return true;
                //default:
                //return false;
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
        players.add("Lionel Messi");

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
                Intent intent = new Intent(SpecialList.this, FormGuideDetails.class);
                intent.putExtra("page", "specials");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
