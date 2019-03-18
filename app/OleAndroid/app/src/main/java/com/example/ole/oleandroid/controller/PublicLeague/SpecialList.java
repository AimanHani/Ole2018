package com.example.ole.oleandroid.controller.PublicLeague;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.SpecialDAO;
import com.example.ole.oleandroid.controller.HomeLeague;
import com.example.ole.oleandroid.controller.Matches;
import com.example.ole.oleandroid.controller.MatchesTabs;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
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

        final ArrayList<Specials> specialsList = SpecialDAO.getSpecialsList(logId);

        specialListAdapter = new SpecialListAdapter(SpecialList.this, specialsList);
        specialListView.setAdapter(specialListAdapter);

        //hani refer to this part for the pop up box
        confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<Specials> newList = specialListAdapter.getUpdatedSpecialList();

                int totalPoints = 0;

                for (Specials s : newList) {
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
                msg.setText("Confirm Specials? You could earn up to " + totalPoints + " Ole points");
//                TextView cancel = dialog.findViewById(R.id.cancel);
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });

                ImageView closeWindow = dialog.findViewById(R.id.closeWindow);
                closeWindow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

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
                            SpecialDAO.updateSpecialsPrediction(newList, leagueId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent i = new Intent(SpecialList.this, SpecialList.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("logId", logId);
                        bundle.putInt("leagueId", leagueId);
                        i.putExtras(bundle);
                        startActivity(i);
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

    //image of the prediction item e.g. best player
    //dropdown list of the player name
    //points that could be earned from this prediction item
    //double it button
    //if click on the button, points x2

}
