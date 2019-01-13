package com.example.ole.oleandroid.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ole.oleandroid.R;

import java.util.ArrayList;

public class SpecialList extends AppCompatActivity{

    SpecialListAdapter specialListAdapter;
    ArrayList<Integer> pointsList;
    ListView specialListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speciallist);
        pointsList = new ArrayList<>();
        specialListView = findViewById(R.id.specialListView);

        //i put 5,2 to test whether thing works, in the future pass in from database
        pointsList.add(5);
        pointsList.add(2);

        specialListAdapter = new SpecialListAdapter(SpecialList.this, pointsList);
        specialListView.setAdapter(specialListAdapter);

    }

}
