package com.example.ole.oleandroid.controller.PublicLeague;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.MatchDAO;
import com.example.ole.oleandroid.controller.Login;
import com.example.ole.oleandroid.controller.SideMenuBar;
import com.example.ole.oleandroid.model.Match;

import java.util.ArrayList;

public class UpcomingMatchesFragment extends Fragment implements View.OnClickListener {
    View view;
    Button predictspecials;
    Button saveMatchPrediction;
    private static int logId;
    UpcomingMatchListAdapter pmListAdapter;
    ArrayList<Match> matches = new ArrayList<>();
    ListView matchListView;

    /**
     * Remember to put the no connection
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.upcoming_matches_main, container, false);

        predictspecials = view.findViewById(R.id.predictspecials);
        predictspecials.setOnClickListener(this);

        saveMatchPrediction = view.findViewById(R.id.saveMatchPrediction);
        saveMatchPrediction.setOnClickListener(this);

        matches = MatchDAO.getFutureMatches();
        matchListView = view.findViewById(R.id.matchListView);

        pmListAdapter = new UpcomingMatchListAdapter(getActivity(), matches);
        matchListView.setAdapter(pmListAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.predictspecials:
                Intent intent = new Intent(getActivity(), SpecialList.class);
                Bundle bundle = new Bundle();
                bundle.putInt("logId", logId);
                intent.putExtras(bundle);
                this.startActivity(intent);

            case R.id.saveMatchPrediction:
                matchPredictionPopup();
                //call api to save prediction
                // show a pop up prediction saved successfully
        }
    }

    public static void setLogId(int id) {
        logId = id;
    }
    //for backend:
    //use bundle to pass the data (list of specials)

    public void matchPredictionPopup() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.successful_pop_up);

        Button close = dialog.findViewById(R.id.close);
        close.setText("Ok");
        close.setEnabled(true);

        TextView text = dialog.findViewById(R.id.textStatus);
        text.setText("Match Prediction Saved!");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
