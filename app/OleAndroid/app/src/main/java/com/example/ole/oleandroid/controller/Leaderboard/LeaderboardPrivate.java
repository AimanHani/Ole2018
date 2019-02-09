package com.example.ole.oleandroid.controller.Leaderboard;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.FAQExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaderboardPrivate extends Fragment {
    private ExpandableListView listView;
    FAQExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    View view;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_leaderboard_private);
//
//        listView = (ExpandableListView) findViewById(R.id.output);
//        initData();
//        listAdapter = new FAQExpandableListAdapter(this,listDataHeader,listHash);
//        listView.setAdapter(listAdapter);
//
//    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("How do I earn more points?");
        listDataHeader.add("Can I not be on the Leaderboard?");

        List<String> questOne = new ArrayList<>();
        questOne.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        List<String> questTwo = new ArrayList<>();
        questTwo.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Diam vel quam elementum pulvinar. At tellus at urna condimentum mattis pellentesque id. Enim facilisis gravida neque convallis a. Eget magna fermentum iaculis eu non diam phasellus.");

        listHash.put(listDataHeader.get(0), questOne); // Header, Child data
        listHash.put(listDataHeader.get(1), questOne);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_leaderboard_public, container, false);
        return view;
    }
}
