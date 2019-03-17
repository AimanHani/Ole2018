package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.TeamItemDAO;
import com.example.ole.oleandroid.controller.PublicLeague.UpcomingMatchListAdapter;
import com.example.ole.oleandroid.model.Match;
import com.example.ole.oleandroid.model.TeamItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrivateLeagueUpcomingMatchesAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Match> matchList;//data source of the list adapter
    private ViewHolder viewHolder;
    private ArrayList<View> views = new ArrayList<>();
    private HashMap<Integer, Match> editMatches = new HashMap<>();
    ImageView team1_photo, team2_photo;
    TextView team1_name, team2_name, match_date;
    EditText team1_scoreinput, team2_scoreinput;

    public static class ViewHolder {
        TextView team1_name, team2_name, match_date;
        EditText team1_scoreinput, team2_scoreinput;
        ImageView team1_photo, team2_photo;
    }

    public PrivateLeagueUpcomingMatchesAdapter(Context context, ArrayList<Match> matchList) {
        this.context = context;
        this.matchList = matchList;
    }

    @Override
    public int getCount() {
        return matchList.size(); //returns total of items in the list
    }

    @Override
    public Match getItem(int position) {
        return matchList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        //if (convertView == null) {
        viewHolder = new ViewHolder();
        convertView = LayoutInflater.from(context).
                inflate(R.layout.activity_private_league_upcoming_matches_adapter, parent, false);
        viewHolder.team1_name = convertView.findViewById(R.id.team1_name);
        viewHolder.team2_name = convertView.findViewById(R.id.team2_name);
        viewHolder.team1_scoreinput = convertView.findViewById(R.id.team1_scoreinput);
        viewHolder.team2_scoreinput = convertView.findViewById(R.id.team2_scoreinput);
        viewHolder.match_date = convertView.findViewById(R.id.match_date);
        viewHolder.team1_photo = convertView.findViewById(R.id.team1_photo);
        viewHolder.team2_photo = convertView.findViewById(R.id.team2_photo);

        final Match m = (Match) getItem(position);
        HashMap<String, TeamItems> teamItemsList = TeamItemDAO.initiateTeamList();
        //System.out.println(viewHolder.team1_name.getText().toString());
        viewHolder.team1_name.setText(m.getTeam1());
        viewHolder.team1_photo.setImageResource(teamItemsList.get(m.getTeam1()).getmTeamImage());

        if (m.getTeam1Score() != -1) {
            viewHolder.team1_scoreinput.setText(m.getTeam1Score() + "");
        }

        if (m.getTeam2Score() != -1) {
            viewHolder.team2_scoreinput.setText(m.getTeam2Score()+"");
        }

        //System.out.println(m.getTeam1()+" vs "+m.getTeam2());
        viewHolder.team2_name.setText(m.getTeam2());
        viewHolder.team2_photo.setImageResource(teamItemsList.get(m.getTeam2()).getmTeamImage());
        viewHolder.match_date.setTag(m.getMatchID());
        viewHolder.match_date.setText(m.getMatchDate() + " " + m.getMatchTime());

        viewHolder.team1_scoreinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (s.toString().length() == 1 && !s.toString().equals("")) {
                    int score = Integer.parseInt(s.toString());
                    int matchId = m.getMatchID();
                    //Match match = MatchDAO.getFutureMatchById(matchId);

                    if (editMatches.get(matchId) == null) {
                        m.setTeam1Score(score);
                        editMatches.put(matchId, m);
                    } else {
                        editMatches.get(matchId).setTeam1Score(score);
                    }
                }

            }
        });

        viewHolder.team2_scoreinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (s.toString().length() == 1 && !s.toString().equals("")) {
                    int score = Integer.parseInt(s.toString());
                    int matchId = m.getMatchID();
                    //Match match = MatchDAO.getFutureMatchById(matchId);

                    if (editMatches.get(matchId) == null) {
                        m.setTeam2Score(score);
                        editMatches.put(matchId, m);
                    } else {
                        editMatches.get(matchId).setTeam2Score(score);
                    }

//                    if (editMatches.get(matchId) != null) {
//                        m.setTeam2Score(score);
//                        editMatches.put(matchId, m);
//                    } else {
//                        editMatches.get(editMatches.get(matchId)).setTeam2Score(score);
//                    }
                }
            }
        });
        convertView.setTag(viewHolder);
        return convertView;// returns the view for the current row
    }

    public ArrayList<View> getViewHolders() {
        return views;
    }

    public ArrayList<Match> getEditMatches() {
        ArrayList<Match> matchList = new ArrayList<>();
        for(Map.Entry<Integer, Match> entry: editMatches.entrySet()) {
            matchList.add(entry.getValue());
        }

        return matchList;
    }
}
