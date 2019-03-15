package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ole.oleandroid.R;

public class PredictMainPage extends AppCompatActivity implements View.OnClickListener {

    LinearLayout blackoutimage;
    FloatingActionButton main, predictSpecial, predictMatch;
    Animation FoodFabOpen, FoodFabClose, FabRClockwise, FabRAntiClockwise, Fadein, Fadeout;
    TextView specialtext, matchtext, mainview;
    boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predict_main_page);

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

                    predictMatch.setOnClickListener(this);
                    predictSpecial.setOnClickListener(this);
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
                //Intent toAddMeal = new Intent(this.getActivity(), xxx.class);
                //startActivity(toAddMeal);
                break;

            case R.id.matchtext:
                //Intent toDiaryActivity = new Intent(this.getActivity(), xxx.class);
                //startActivity(toDiaryActivity);
                break;

            case R.id.floatingActionButton:
                mainview.setText("BYEBYE");
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
