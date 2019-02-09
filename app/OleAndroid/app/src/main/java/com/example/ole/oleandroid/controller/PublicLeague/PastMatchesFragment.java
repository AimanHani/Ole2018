package com.example.ole.oleandroid.controller.PublicLeague;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.MatchDAO;
import com.example.ole.oleandroid.model.Match;

import java.util.ArrayList;

public class PastMatchesFragment extends Fragment {

    View view;
    ArrayList<Match> matchList;

    PastMatchListAdapter pmListAdapter;
    ArrayList<Match> matches = new ArrayList<>();
    ListView matchListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.past_matches_main, container, false);

        matches = MatchDAO.getPastMatches();
        matchListView = view.findViewById(R.id.matchListView);

        pmListAdapter = new PastMatchListAdapter(getContext(), matches);
        matchListView.setAdapter(pmListAdapter);

        return view;
    }

}
