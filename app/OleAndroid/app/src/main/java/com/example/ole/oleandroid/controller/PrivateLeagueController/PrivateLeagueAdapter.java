package com.example.ole.oleandroid.controller.PrivateLeagueController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
//import com.example.ole.oleandroid.controller.PublicLeague.MatchesTabs;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.Specials;

import java.util.ArrayList;

public class PrivateLeagueAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context; //context
    private ArrayList<PrivateLeague> leaguelist;//data source of the list adapter
    private PrivateLeagueAdapter.ViewHolder viewHolder;
    private Activity view;
    FloatingActionButton createPrivateLeagueBtn;
    Button joinleaguebtn;
    String leaguid;

    private static class ViewHolder {
        ImageView leaguelogo;
        TextView league;
        Button joinleaguebtn;
        LinearLayout rowLayout;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PrivateLeagueAdapter(Context context, ArrayList<PrivateLeague> leaguelist) {
        this.context = context;
        this.leaguelist = leaguelist;
    }

    @Override
    public int getCount() {
        return leaguelist.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return leaguelist.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            viewHolder = new PrivateLeagueAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_private_league_adapter, parent, false);


            /**
             * This will tell initialize the textview element in publicleaguelistlayout
             */

            if (leaguelist.size() > 0 && leaguelist.get(0).getLeagueName() != null) {
                viewHolder.league = convertView.findViewById(R.id.leaguename);
                viewHolder.leaguelogo = convertView.findViewById(R.id.leaguelogo);
                viewHolder.joinleaguebtn = convertView.findViewById(R.id.joinleaguebtn);
                viewHolder.rowLayout = convertView.findViewById(R.id.rowLayout);
                viewHolder.league.setText(leaguelist.get(position).getLeagueName());
                viewHolder.joinleaguebtn.setOnClickListener(this);
                viewHolder.leaguelogo.setOnClickListener(this);
                viewHolder.league.setOnClickListener(this);
                viewHolder.rowLayout.setOnClickListener(this);
                convertView.setTag(viewHolder);

//                viewHolder.joinleaguebtn.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        Button cb = (Button) v;
//                        PrivateLeague s = (PrivateLeague) cb.getTag();
//                        loadNextPage(v, context, s.getLeagueId() + "");
//
//                    }
//                });
            }
        } else {
            viewHolder = (PrivateLeagueAdapter.ViewHolder) convertView.getTag();
        }

        PrivateLeague s = leaguelist.get(position);
        viewHolder.league.setText("" + s.getLeagueName() + "");
        viewHolder.joinleaguebtn.setTag(s);
        viewHolder.league.setTag(s);
        viewHolder.leaguelogo.setTag(s);
        viewHolder.rowLayout.setTag(s);
        return convertView;// returns the view for the current row
    }


    public void onClick(View view) {
        PrivateLeague s = (PrivateLeague) view.getTag();
        loadNextPage(view, context, s.getLeagueId() + "");
    }

    private void loadNextPage(View view, Context ctx, String leagueid) {
        Intent intent = new Intent(ctx, PrivateLeagueDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("leagueid", leagueid);
        intent.putExtras(bundle);
        context.startActivity(intent);


    }

}
