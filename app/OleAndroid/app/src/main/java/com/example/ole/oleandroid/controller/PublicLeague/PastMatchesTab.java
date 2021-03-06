package com.example.ole.oleandroid.controller.PublicLeague;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.MatchDAO;
import com.example.ole.oleandroid.model.Match;

import java.util.ArrayList;

public class PastMatchesTab extends AppCompatActivity {


    PastMatchListAdapter pmListAdapter;
    ArrayList<Match> matches = new ArrayList<>();
    ListView matchListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_matches_main); //contain item list view e.g. item1, item2

        matches = MatchDAO.getPastMatches();
        matchListView = findViewById(R.id.matchListView);

        pmListAdapter = new PastMatchListAdapter(PastMatchesTab.this, matches);
        matchListView.setAdapter(pmListAdapter);
    }
}
