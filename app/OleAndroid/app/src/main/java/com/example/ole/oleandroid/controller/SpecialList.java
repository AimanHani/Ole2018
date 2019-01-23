package com.example.ole.oleandroid.controller;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;

import java.util.ArrayList;

public class SpecialList extends AppCompatActivity{

    SpecialListAdapter specialListAdapter;
    ArrayList<Integer> pointsList;
    ListView specialListView;
    Button confirmspecialsbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speciallist); //contain item list view e.g. item1, item2
        pointsList = new ArrayList<>();
        specialListView = findViewById(R.id.specialListView);
        confirmspecialsbtn = findViewById(R.id.confirmspecialsbtn);

        //i put 5,2 to test whether thing works, in the future pass in from database
        pointsList.add(5);
        pointsList.add(2);

        specialListAdapter = new SpecialListAdapter(SpecialList.this, pointsList);
        specialListView.setAdapter(specialListAdapter);

        confirmspecialsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SpecialList.this);
                dialog.setContentView(R.layout.confirmspecialspopout);
                TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ImageView closeWindow = (ImageView) dialog.findViewById(R.id.closeWindow);
                closeWindow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                TextView end = (TextView) dialog.findViewById(R.id.end);
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(SpecialList.this, Leagues.class);
                        startActivity(i);
                        finish();
                    }
                });

                dialog.show();
                //return true;
                //default:
                //return false;
            }
        });

    }

    //image of the prediction item e.g. best player
    //dropdown list of the player name
    //points that could be earned from this prediction item
    //double it button
    //if click on the button, points x2

}
