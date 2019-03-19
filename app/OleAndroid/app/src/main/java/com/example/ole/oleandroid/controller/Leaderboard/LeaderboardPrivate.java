package com.example.ole.oleandroid.controller.Leaderboard;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.FAQ.FAQExpandableListAdapter;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateLeagueProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaderboardPrivate extends Fragment {
    private ExpandableListView listView;
    LeaderboardExpandableListAdapter listAdapter;
    private ArrayList<String> listDataHeader;
    private HashMap<String, ArrayList<PrivateLeagueProfile>> listHash;
    View view;

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();
        ArrayList<PrivateLeagueProfile> list = ScoreBoardDAO.privateLeagueProfiles;

        for (PrivateLeagueProfile p : list){
            String leagueName = p.getLeagueName();
            if (!listDataHeader.contains(leagueName)){
                listDataHeader.add(leagueName);
            }

            //int leagueId = p.getLeagueID();
            if (listHash.get(leagueName) == null){
                ArrayList<PrivateLeagueProfile> listProfiles = new ArrayList<>();
                listProfiles.add(p);
                listHash.put(leagueName, listProfiles);
            } else {
                ArrayList<PrivateLeagueProfile> listProfiles  = listHash.get(leagueName);
                listProfiles.add(p);
                listHash.put(leagueName, listProfiles);
            }
        }

        //listHash = ScoreBoardDAO.privateLeagueProfiles;
    }


//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.activity_leaderboard_private, container, false);
//        return view;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        System.out.println(listDataHeader.toString());
        System.out.println(listHash.toString());
        view = inflater.inflate(R.layout.activity_leaderboard_private, container, false);
        listView = view.findViewById(R.id.output);
        listAdapter = new LeaderboardExpandableListAdapter(getContext(), listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        return view;
    }


}
