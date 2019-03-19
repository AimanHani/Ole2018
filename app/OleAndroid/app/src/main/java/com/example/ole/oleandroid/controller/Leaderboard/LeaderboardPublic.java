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
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.Leaderboard.LeaderboardPublicAdapter;
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
    ArrayList<PublicLeagueProfile> publicLeagueProfileList = new ArrayList<>();
    ListView lbPubList;
    TextView userPosition, userScore;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //userCard = view.findViewById(R.id.userCard);
        //userPosition = view.findViewById(R.id.userPosition);
        //userScore = view.findViewById(R.id.userScore);

        view = inflater.inflate(R.layout.activity_leaderboard_public, container, false);

        publicLeagueProfileList = ScoreBoardDAO.getPublicLeagueProfiles();
        lbPubList = view.findViewById(R.id.lbPubList);
        userPosition = view.findViewById(R.id.userPosition);
        userScore = view.findViewById(R.id.userScore);
        int userPos = ScoreBoardDAO.getUserPositionPublic(UserDAO.getLoginUser().getUsername(), publicLeagueProfileList);
        int getUserPoints = ScoreBoardDAO.getUserPointsPublic(UserDAO.getLoginUser().getUsername(), publicLeagueProfileList);

        if (userPos == 0) {
            userPosition.setText("-");
        } else {
            userPosition.setText("#" + userPos);
        }

        if (getUserPoints != -1){
            userScore.setText(getUserPoints+"");
        } else {
            userScore.setText("");
        }

        lbPubAdapter = new LeaderboardPublicAdapter(getContext(), publicLeagueProfileList);
        lbPubList.setAdapter(lbPubAdapter);

        return view;
    }
}
