package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.controller.TeamItemDAO;
import com.example.ole.oleandroid.model.TeamItems;
import com.example.ole.oleandroid.model.Teams;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.ole.oleandroid.R;

public class TeamListPrivateAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Integer> pointsList; //data source of the list adapter
    private ArrayList<String> teamNameList;
    private ArrayList<Teams> teamList;
    private TeamListPrivateAdapter.ViewHolder viewHolder;
    private Button confirmspecialsbtn;
    private CheckBox checkPrivateTeam;
    private ImageView profile_image;
    HashMap<String, TeamItems> teamItemsList = TeamItemDAO.teamItemsList;

    private static class ViewHolder {
        ImageView profile_image;
        TextView teamid;
        TextView itemname;
        CheckBox checkPrivateTeam;
    }

    //uses list of specials Object so can get both points and name
    public TeamListPrivateAdapter(Context context, ArrayList<Teams> teamList) {
        this.context = context;
        this.teamList = teamList;

    }

    //public constructor
    public TeamListPrivateAdapter(Context context, ArrayList<Integer> pointsList, ArrayList<String> teamNameList, ArrayList<Teams> teamList) {
        this.context = context;
        this.pointsList = pointsList;
        this.teamNameList = teamNameList;
        this.teamList = teamList;
    }

    @Override
    public int getCount() {
        return teamList.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return teamList.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new TeamListPrivateAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_team_list_private_adapter, parent, false);



        /**
         * This will tell initialize the textview element in speciallistlayout
         */
        viewHolder.teamid = convertView.findViewById(R.id.teamid);
        viewHolder.itemname = convertView.findViewById(R.id.itemname);
        viewHolder.profile_image = convertView.findViewById(R.id.profile_image);
        viewHolder.checkPrivateTeam = (CheckBox)convertView.findViewById(R.id.checkPrivateTeam);

        //this will get each point from the arraylist
        //viewHolder.teamid.setText(teamList.get(position).getTeamId()+"");
        viewHolder.itemname.setText(teamList.get(position).getTeamName());
        viewHolder.profile_image.setImageResource(teamItemsList.get(teamList.get(position).getTeamName()).getmTeamImage());


        convertView.setTag(viewHolder);
            viewHolder.checkPrivateTeam.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Teams t = (Teams) cb.getTag();
                    /*Toast.makeText(context.getApplicationContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();*/
                    t.setChecked(cb.isChecked());
                    System.out.println(t.getTeamId());
                }
            });
        }else
        {
            // the getTag returns the viewHolder object set as a tag to the view
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Teams t = teamList.get(position);
        viewHolder.itemname.setText("" +  t.getTeamName() + "");
//        viewHolder.checkPrivateTeam.setText(t.getTeamId());
        viewHolder.checkPrivateTeam.setChecked(t.getChecked());
        viewHolder.checkPrivateTeam.setTag(t);

        return convertView;
    }
}


