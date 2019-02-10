package com.example.ole.oleandroid.controller.Leaderboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.PublicLeague.LeaderboardPublicAdapter;
import com.example.ole.oleandroid.model.PublicLeagueProfile;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardPublic extends Fragment {

    View view;
//    LeaderboardPublicAdapter lbPubAdapter;
//    ArrayList<PublicLeagueProfile> publicLeagueProfiles = new ArrayList<>();
//    ListView publicLBList;
    //CardView userCard;
    //ListView userPosition, userScore;

    LeaderboardPublicAdapter lbPubAdapter;
    ArrayList<PublicLeagueProfile> matches = new ArrayList<>();
    ListView lbPubList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //userCard = view.findViewById(R.id.userCard);
        //userPosition = view.findViewById(R.id.userPosition);
        //userScore = view.findViewById(R.id.userScore);

        view = inflater.inflate(R.layout.activity_leaderboard_public, container, false);

        //matches = ScoreBoardDAO.getPublicLeagueProfiles();
        //lbPubList = view.findViewById(R.id.matchListView);

        //lbPubAdapter = new LeaderboardPublicAdapter(getContext(), matches);
        //lbPubList.setAdapter(lbPubAdapter);

        return view;
    }
}
