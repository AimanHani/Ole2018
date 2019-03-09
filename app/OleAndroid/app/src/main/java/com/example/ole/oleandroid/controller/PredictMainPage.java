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
    FloatingActionButton main, camera, diary, addmeal;
    Animation FoodFabOpen, FoodFabClose, FabRClockwise, FabRAntiClockwise, Fadein, Fadeout;
    TextView addmealtext, diarytext, cameratext;
    boolean isOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predict_main_page);

        blackoutimage = findViewById(R.id.blackoutimage);

        // enable animations for FloatingActionButton
        FoodFabOpen = AnimationUtils.loadAnimation(this, R.anim.foodfabopen);
        FoodFabClose = AnimationUtils.loadAnimation(this, R.anim.foodfabclose);
        FabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise);
        FabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        Fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);

        addmealtext = findViewById(R.id.addmealtext);
        diarytext = findViewById(R.id.diarytext);
        cameratext = findViewById(R.id.cameratext);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.blackoutimage:
                blackoutimage.startAnimation(Fadeout);
                blackoutimage.setVisibility(View.GONE);
                camera.startAnimation(FoodFabClose);
                diary.startAnimation(FoodFabClose);
                addmeal.startAnimation(FoodFabClose);
                main.startAnimation(FabRAntiClockwise);
                camera.setClickable(false);
                diary.setClickable(false);
                addmeal.setClickable(false);
                addmealtext.setVisibility(View.GONE);
                cameratext.setVisibility(View.GONE);
                diarytext.setVisibility(View.GONE);
                isOpen = false;
                break;

            case R.id.addmeal:
                //Intent toAddMeal = new Intent(FoodPage.this.getActivity(), FoodSearchResult.class);
                //startActivity(toAddMeal);
                break;

            case R.id.diary:
                //Intent toDiaryActivity = new Intent(FoodPage.this.getActivity(), Diary.class);
                //startActivity(toDiaryActivity);
                break;

            case R.id.camera:
                //dispatchTakePictureIntent();
                break;

            case R.id.floatingActionButton:
                if (isOpen) {
                    blackoutimage.startAnimation(Fadeout);
                    blackoutimage.setVisibility(View.GONE);
                    camera.startAnimation(FoodFabClose);
                    diary.startAnimation(FoodFabClose);
                    addmeal.startAnimation(FoodFabClose);
                    main.startAnimation(FabRAntiClockwise);
                    camera.setClickable(false);
                    diary.setClickable(false);
                    addmeal.setClickable(false);
                    addmealtext.setVisibility(View.GONE);
                    cameratext.setVisibility(View.GONE);
                    diarytext.setVisibility(View.GONE);
                    isOpen = false;


                } else {
                    blackoutimage.setVisibility(View.VISIBLE);
                    blackoutimage.startAnimation(Fadein);
                    camera.startAnimation(FoodFabOpen);
                    diary.startAnimation(FoodFabOpen);
                    addmeal.startAnimation(FoodFabOpen);
                    main.startAnimation(FabRClockwise);
                    camera.setClickable(true);
                    diary.setClickable(true);
                    addmeal.setClickable(true);
                    addmealtext.setVisibility(View.VISIBLE);
                    cameratext.setVisibility(View.VISIBLE);
                    diarytext.setVisibility(View.VISIBLE);
                    isOpen = true;

                    diary.setOnClickListener(this);
                    camera.setOnClickListener(this);
                    addmeal.setOnClickListener(this);
                    blackoutimage.setOnClickListener(this);
                }

                break;
        }
    }

}
