package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.MatchDAO;
import com.example.ole.oleandroid.model.Match;

import java.util.ArrayList;

public class UpcomingMatchesFragment extends Fragment implements View.OnClickListener {
    View view;
    Button predictspecials;
    private static int logId;
    UpcomingMatchListAdapter pmListAdapter;
    ArrayList<Match> matches = new ArrayList<>();
    ListView matchListView;

    /**
     * Remember to put the no connection
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.upcoming_matches_main, container, false);

        predictspecials = (Button) view.findViewById(R.id.predictspecials);
        predictspecials.setOnClickListener(this);

        matches = MatchDAO.getFutureMatches();
        matchListView = view.findViewById(R.id.matchListView);

        pmListAdapter = new UpcomingMatchListAdapter(getContext(), matches);
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
        }
    }

    public static void setLogId(int id) {
        logId = id;
    }
    //for backend:
    //use bundle to pass the data (list of specials)
}
