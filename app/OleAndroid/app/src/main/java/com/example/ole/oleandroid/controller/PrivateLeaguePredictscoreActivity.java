package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.ole.oleandroid.R;

public class PrivateLeaguePredictscoreActivity extends Fragment implements View.OnClickListener{
    View view;
    Button confirmPrediction;
    /**
     *  Remember to put the no connection
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_private_league_predictscore, container, false);
        confirmPrediction = view.findViewById(R.id.confirmPrediction);
        confirmPrediction.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirmPrediction:
                Intent intent = new Intent(getActivity(), PrivateLeagueFinaltempActivity.class);
                this.startActivity(intent);
        }
    }
    //for backend:
    //use bundle to pass the data (list of specials)
}
