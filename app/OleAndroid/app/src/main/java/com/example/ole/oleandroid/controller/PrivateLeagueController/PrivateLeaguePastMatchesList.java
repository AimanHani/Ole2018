package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.MatchDAO;
import com.example.ole.oleandroid.controller.DAO.PrivateMatchDAO;
import com.example.ole.oleandroid.controller.PublicLeague.PastMatchListAdapter;
import com.example.ole.oleandroid.model.Match;

import java.util.ArrayList;

public class PrivateLeaguePastMatchesList extends Fragment {
    View view;
    ArrayList<Match> matchList;
    PrivateLeaguePastMatchesAdapter pmListAdapter;
    ArrayList<Match> matches = new ArrayList<>();
    ListView matchListView;
    private static int logId;
    private static int leagueId;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_private_league_past_matches_list, container, false);

        matches = PrivateMatchDAO.getPastMatches(leagueId);
        matchListView = view.findViewById(R.id.matchListView);

        pmListAdapter = new PrivateLeaguePastMatchesAdapter(getContext(), matches);
        matchListView.setAdapter(pmListAdapter);

        return view;
    }
    public static void setLogId(int id) {
        logId = id;
    }
    public static void setLeagueId(int id) {
        leagueId = id;
    }
}
