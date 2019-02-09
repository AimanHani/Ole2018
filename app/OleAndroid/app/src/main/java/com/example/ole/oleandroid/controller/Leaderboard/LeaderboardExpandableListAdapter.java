package com.example.ole.oleandroid.controller.Leaderboard;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ole.oleandroid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LeaderboardExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<Object>> listHashMap;
    private ViewHolder viewHolder;

    private static class ViewHolder {
        CardView userCard;
        ImageView throphy;
        TextView userPosition, userScore, lbItemRank, lbItemUser,lbItemScore;
    }

    public LeaderboardExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Object>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int j) {
        return listHashMap.get(listDataHeader.get(i)).get(j);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int j) {
        return j;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String)getGroup(i);
        if(view == null) {
            LayoutInflater inflater =(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.leaderboard_privatelistgroup,null);
        }
        TextView lbListGrp = (TextView)view.findViewById(R.id.lbListGrp);
        lbListGrp.setTypeface(null, Typeface.BOLD);
        lbListGrp.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int j, boolean b, View view, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).
                    inflate(R.layout.leaderboard_privatelistitem, viewGroup, false);
        }

        viewHolder.userCard = view.findViewById(R.id.userCard);
        viewHolder.throphy = view.findViewById(R.id.throphy);
        viewHolder.userPosition = view.findViewById(R.id.userPosition);
        viewHolder.userScore = view.findViewById(R.id.userScore);
        viewHolder.lbItemRank = view.findViewById(R.id.lbItemRank);
        viewHolder.lbItemUser = view.findViewById(R.id.lbItemUser);
        viewHolder.lbItemScore = view.findViewById(R.id.lbItemScore);


        return view;// returns the view for the current row
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
