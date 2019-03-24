package com.example.ole.oleandroid.controller.PublicLeague;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.controller.Signup;
import com.example.ole.oleandroid.model.PublicLeague;

import java.util.ArrayList;
import java.util.Arrays;

public class PublicLeagueListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context; //context
    private ArrayList<PublicLeague> leaguelist;//data source of the list adapter
    private ViewHolder viewHolder;

    private static class ViewHolder {
        TextView leagueName;
        Button joinleaguebtn;
        LinearLayout publicLeagueRow;
    }

    //public constructor
    // passes in a PublicLeague Arraylist so will be able to retrieve the logId as well and not just the name
    public PublicLeagueListAdapter(Context context, ArrayList<PublicLeague> leaguelist) {
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
            viewHolder = new PublicLeagueListAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_public_league_list, parent, false);
        }

        /**
         * This will tell initialize the textview element in publicleaguelistlayout
         */
        viewHolder.leagueName = convertView.findViewById(R.id.leaguename);
        viewHolder.joinleaguebtn = convertView.findViewById(R.id.joinleaguebtn);
        viewHolder.publicLeagueRow = convertView.findViewById(R.id.publicLeagueRow);

        //this will get each point from the arraylist
        viewHolder.leagueName.setText(leaguelist.get(position).getLeagueName());
        viewHolder.publicLeagueRow.setTag(new ArrayList<>(Arrays.asList(position + "", leaguelist.get(position).getUserJoin().toString()))); //label the first item on the list
        //viewHolder.joinleaguebtn.setTag(2, leaguelist.get(position).getLogId());
        if (!leaguelist.get(position).getUserJoin()) {
            viewHolder.joinleaguebtn.setVisibility(View.VISIBLE);
            viewHolder.leagueName.setClickable(false);
            viewHolder.publicLeagueRow.setClickable(false);
        }

        viewHolder.joinleaguebtn.setOnClickListener(this);
        viewHolder.publicLeagueRow.setOnClickListener(this);

        return convertView;// returns the view for the current row
    }

    public void onClick(View view) {
        ArrayList<String> tags = (ArrayList<String>) view.getTag();
        int position = Integer.parseInt(tags.get(0));
        Boolean userJoin = Boolean.parseBoolean(tags.get(1));
        System.out.println(userJoin);
        PublicLeague pubLeague = leaguelist.get(position);
        int logId = pubLeague.getLogId();

        if (!userJoin && view.getId() == R.id.joinleaguebtn) {
            int joinLogId = PublicLeagueDAO.joinPublicLeague(pubLeague.getLeagueId(), UserDAO.getLoginUser().getUsername());
            System.out.println("Join Public League: " + joinLogId);
            Toast.makeText(context, "Joining " + pubLeague.getLeagueName(), Toast.LENGTH_SHORT).show();

            if (joinLogId == 0) {
                //loadNextPage(view, context, joinLogId, leaguelist.get(position).getLeagueId());
                //loadNextPage(view, context, leaguelist.get(position));
                Toast.makeText(context, "Failed to join " + pubLeague.getLeagueName(), Toast.LENGTH_SHORT).show();
            } else {
                loadNextPage(context, joinLogId, pubLeague.getLeagueId());
            }
        } else {
            loadNextPage(context, logId, pubLeague.getLeagueId());
        }
    }


    private void loadNextPage(Context ctx, int logId, int leagueId) {
        Intent intent = new Intent(ctx, PublicLeagueDetails.class);
        Bundle bundle = new Bundle();
        bundle.putInt("logId", logId); //pl.getLogId()
        bundle.putInt("leagueId", leagueId); //pl.getLeagueId()
        intent.putExtras(bundle);
        ctx.startActivity(intent);


//        switch (view.getId()) {
//            case R.id.joinleaguebtn:
////                Intent intent = new Intent(ctx, PublicLeagueDetails.class);
////                context.startActivity(intent);
//
//                Intent intent = new Intent(ctx, PublicLeagueDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("logId", pl.getLogId());
//                bundle.putInt("leagueId", pl.getLeagueId());
//                intent.putExtras(bundle);
//                ctx.startActivity(intent);
//
//        }
    }
}
