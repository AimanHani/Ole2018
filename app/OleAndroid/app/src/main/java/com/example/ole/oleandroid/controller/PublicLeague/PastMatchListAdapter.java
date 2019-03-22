package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.TeamItemDAO;
import com.example.ole.oleandroid.model.Match;
import com.example.ole.oleandroid.model.TeamItems;

import java.util.ArrayList;
import java.util.HashMap;

public class PastMatchListAdapter extends BaseAdapter{
        private Context context; //context
        private ArrayList<Match> matchList;//data source of the list adapter
        private ViewHolder viewHolder;
        ImageView team1_photo, team2_photo;
        TextView team1_name, team2_name, match_date, team1_score, team2_score;

        private static class ViewHolder {
            TextView team1_name, team2_name, match_date, team1_score, team2_score, userteam1_score, userteam2_score, points;
            ImageView team1_photo, team2_photo;
        }

        //public constructor
        // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
        public PastMatchListAdapter(Context context, ArrayList<Match> matchList) {
            this.context = context;
            this.matchList = matchList;
        }

        @Override
        public int getCount() {
            return matchList.size(); //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return matchList.get(position); //returns list item at the specified position
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // inflate the layout for each list row
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).
                        //inflate(R.layout.matches_with_predicted_score, parent, false);
                        inflate(R.layout.activity_past_matches, parent, false);
            }

            /**
             * This will tell initialize the textview element in past matches main
             */

            //TextView team1_name, team2_name, match_date, team1_score, team2_score;
            //ImageView team1_photo, team2_photo;
            viewHolder.team1_name = convertView.findViewById(R.id.team1_name);
            viewHolder.team2_name = convertView.findViewById(R.id.team2_name);
            viewHolder.team1_score = convertView.findViewById(R.id.team1_score);
            viewHolder.team2_score = convertView.findViewById(R.id.team2_score);
            viewHolder.match_date = convertView.findViewById(R.id.match_date);
            viewHolder.team1_photo = convertView.findViewById(R.id.team1_photo);
            viewHolder.team2_photo = convertView.findViewById(R.id.team2_photo);

            viewHolder.userteam1_score = convertView.findViewById(R.id.userteam1_score);
            viewHolder.userteam2_score = convertView.findViewById(R.id.userteam2_score);
            viewHolder.points = convertView.findViewById(R.id.points);

            HashMap<String, TeamItems> teamItemsList = TeamItemDAO.teamItemsList;
            //System.out.println(viewHolder.team1_name.getText().toString());
            viewHolder.team1_name.setText(matchList.get(position).getTeam1());
            viewHolder.team1_score.setText(matchList.get(position).getTeam1Score()+"");
            viewHolder.team1_photo.setImageResource(teamItemsList.get(matchList.get(position).getTeam1()).getmTeamImage());

            viewHolder.team2_name.setText(matchList.get(position).getTeam2());
            viewHolder.team2_score.setText(matchList.get(position).getTeam2Score()+"");
            viewHolder.team2_photo.setImageResource(teamItemsList.get(matchList.get(position).getTeam2()).getmTeamImage());

            viewHolder.match_date.setText(matchList.get(position).getMatchDate()+" "+matchList.get(position).getMatchTime());
        /*
        // get current item to be displayed
        Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView points = (TextView)
                convertView.findViewById(R.id.text_view_item_name);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getItemName());
        textViewItemDescription.setText(currentItem.getItemDescription());
        */

            //get users prediction
            if(matchList.get(position).getTeam1Prediction()==-1 || matchList.get(position).getTeam1Prediction()==-1){
                viewHolder.userteam1_score.setText("-");
                viewHolder.userteam2_score.setText("-");
                viewHolder.points.setText("-");
            }else{
                viewHolder.userteam1_score.setText(matchList.get(position).getTeam1Prediction()+"");
                viewHolder.userteam2_score.setText(matchList.get(position).getTeam2Prediction()+"");
                viewHolder.points.setText("0");
                Match m = matchList.get(position);
                //setting the proper points

                //team 1 wins
                if(m.getTeam1Score()>m.getTeam2Score()){
                    if(m.getTeam1Prediction()>m.getTeam2Prediction()){
                        viewHolder.points.setText(m.getPoints()+"");
                        if(m.getTeam1Score()==m.getTeam1Prediction() && m.getTeam2Score()==m.getTeam2Prediction()){
                            viewHolder.points.setText(m.getPoints()*2 + "");
                        }
                    }
                }
                //team 2 wins
                else if(m.getTeam2Score()>m.getTeam1Score()){
                    if(m.getTeam2Prediction()>m.getTeam1Prediction()){
                        viewHolder.points.setText(m.getPoints()+"");
                        if(m.getTeam1Score()==m.getTeam1Prediction() && m.getTeam2Score()==m.getTeam2Prediction()){
                            viewHolder.points.setText(m.getPoints()*2 + "");
                        }
                    }
                }
                //draw
                else if(m.getTeam1Score() == m.getTeam2Score()){
                    if(m.getTeam1Prediction() == m.getTeam2Prediction()){
                        viewHolder.points.setText(m.getPoints()+"");
                        if(m.getTeam1Score()==m.getTeam1Prediction() && m.getTeam2Score()==m.getTeam2Prediction()){
                            viewHolder.points.setText(m.getPoints()*2 + "");
                        }
                    }
                }else{
                    viewHolder.points.setText("0");
                }

            }

            return convertView;// returns the view for the current row
        }


}
