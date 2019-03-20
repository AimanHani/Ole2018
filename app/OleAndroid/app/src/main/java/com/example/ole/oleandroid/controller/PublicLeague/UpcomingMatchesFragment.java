package com.example.ole.oleandroid.controller.PublicLeague;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UpcomingMatchesFragment extends Fragment implements View.OnClickListener {
    View view;
    Button predictspecials;
    Button saveMatchPrediction;
    private static int logId;
    private static int leagueId;
    UpcomingMatchListAdapter pmListAdapter;
    ArrayList<Match> matches = new ArrayList<>();
    ListView matchListView;

    /**
     * Remember to put the no connection
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.upcoming_matches_main, container, false);

        predictspecials = view.findViewById(R.id.predictspecials);
        predictspecials.setTag("predict");
        predictspecials.setOnClickListener(this);

        saveMatchPrediction = view.findViewById(R.id.saveMatchPrediction);
        saveMatchPrediction.setTag("save");
        saveMatchPrediction.setOnClickListener(this);

        matches = MatchDAO.getFutureMatches(leagueId);
        matchListView = view.findViewById(R.id.matchListView);

        pmListAdapter = new UpcomingMatchListAdapter(getActivity(), matches);
        matchListView.setAdapter(pmListAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        String status = v.getTag().toString();
        System.out.println("click " + status);

        if (status.equals("predict")) {
            Intent intent = new Intent(getActivity(), SpecialList.class);
            Bundle bundle = new Bundle();
            bundle.putInt("logId", logId);
            bundle.putInt("leagueId", leagueId);
            intent.putExtras(bundle);
            this.startActivity(intent);

        } else {
            try {
                matchPredictionPopup();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //call api to save prediction
            // show a pop up prediction saved successfully
        }
    }


    public static void setLogId(int id) {
        logId = id;
    }
    public static void setLeagueId(int id) {
        leagueId = id;
    }
    //for backend:
    //use bundle to pass the data (list of specials)

    public void matchPredictionPopup() throws IOException, JSONException {
        getAllPrediction();
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.successful_pop_up);

        TextView close = dialog.findViewById(R.id.confirm);
        close.setText("Close");
        close.setEnabled(true);

//        TextView text = dialog.findViewById(R.id.textStatus);
//        text.setText("Match Prediction Saved!");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    public void getAllPrediction() throws IOException, JSONException {
        ArrayList<Match> newMatchList = pmListAdapter.getEditMatches();

//        for (Match m: newMatchList){
//            System.out.println(m.getMatchID()+ " space "+ m.getTeam1Score() + " space " + m.getTeam2Score());
//        }

        if (newMatchList.size() > 0) {
            boolean insert = MatchDAO.updateMatchPredictions(newMatchList, leagueId);
            System.out.println("insert status " + insert);
        }

    }
}
