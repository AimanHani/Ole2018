package com.example.ole.oleandroid.controller.Leaderboard;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.ScoreBoardDAO;
import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.model.PrivateLeagueProfile;

import java.util.ArrayList;
import java.util.HashMap;

public class LeaderboardExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> listDataHeader;
    private HashMap<String, ArrayList<PrivateLeagueProfile>> listHashMap;
    private ViewHolder viewHolder;
    private RowViewHolder rowViewHolder;
    private int countChild = 0;

    private static class ViewHolder {
        CardView userCard;
        TextView userPosition, userScore;
        //ListView lbList;
        TextView lbItemRank, lbItemUser, lbItemScore;
    }

    private static class RowViewHolder {
        TextView userposition, username, score;
    }

    public LeaderboardExpandableListAdapter(Context context, ArrayList<String> listDataHeader, HashMap<String, ArrayList<PrivateLeagueProfile>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
        this.countChild = 0;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
//        System.out.println(listDataHeader.get(i));
        return listHashMap.get(listDataHeader.get(i)).size()+1;
//        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public ArrayList<PrivateLeagueProfile> getChild(int i, int j) {
        String leagueName = listDataHeader.get(i);
        return listHashMap.get(leagueName);
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
        String headerTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.leaderboard_privatelistgroup, null);
        }
        TextView lbListGrp = (TextView) view.findViewById(R.id.lbListGrp);
        lbListGrp.setTypeface(null, Typeface.BOLD);
        lbListGrp.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int j, boolean b, View view, ViewGroup viewGroup) {
        // inflate the layout for each private league

//        View childView1, childView2;
//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        childView1 = inflater.inflate(R.layout.leaderboard_privateusercard, null);
//        childView2 = inflater.inflate(R.layout.leaderboard_publiclist, null);
//        viewGroup.addView(childView1);
//        viewGroup.addView(childView2);
        ArrayList<PrivateLeagueProfile> listUsersInLeagues = getChild(i, j);

        //System.out.println(j + " " + listUsersInLeagues.get(j));
        //if (j > 0) {

        if (j == 0) {

            if (view == null || view.getTag() instanceof RowViewHolder) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(context).
                        inflate(R.layout.leaderboard_privateusercard, viewGroup, false);
                view.setTag(viewHolder);
            }

            viewHolder = (ViewHolder) view.getTag();
            viewHolder.userCard = view.findViewById(R.id.userCard);
            viewHolder.userPosition = view.findViewById(R.id.userPosition);
            viewHolder.userScore = view.findViewById(R.id.userScore);

//            viewHolder.lbItemRank = view.findViewById(R.id.lbItemRank);
//            viewHolder.lbItemUser = view.findViewById(R.id.lbItemUser);
//            viewHolder.lbItemScore = view.findViewById(R.id.lbItemScore);
//
//            viewHolder.lbItemRank.setVisibility(View.GONE);
//            viewHolder.lbItemUser.setVisibility(View.GONE);
//            viewHolder.lbItemScore.setVisibility(View.GONE);

//            viewHolder.lbItemRank.setText(listUsersInLeagues.get(j).getRank() + "");
//            viewHolder.lbItemUser.setText(listUsersInLeagues.get(j).getUsername());
//            viewHolder.lbItemScore.setText(listUsersInLeagues.get(j).getTotalPoints() + "");
            //viewHolder.lbList = view.findViewById(R.id.lbList);

            int userPos = ScoreBoardDAO.getUserPositionPrivate(UserDAO.getLoginUser().getUsername(), listUsersInLeagues);
            int getUserPoints = ScoreBoardDAO.getUserPointsPrivate(UserDAO.getLoginUser().getUsername(), listUsersInLeagues);

            if (userPos == 0) {
                viewHolder.userPosition.setText("-");
            } else {
                viewHolder.userPosition.setText("#" + userPos);
            }

            if (getUserPoints != -1) {
                viewHolder.userScore.setText(getUserPoints + "");
            } else {
                viewHolder.userScore.setText("");
            }
        } else {
            j = j-1;
            System.out.println("here1");
            if (view == null || view.getTag() instanceof ViewHolder) {
                rowViewHolder = new RowViewHolder();
                view = LayoutInflater.from(context).
                        inflate(R.layout.leaderboard_publiclist, viewGroup, false);
                view.setTag(rowViewHolder);
            }

            rowViewHolder = (RowViewHolder) view.getTag();

            rowViewHolder = (RowViewHolder) view.getTag();
            rowViewHolder.userposition = view.findViewById(R.id.userposition);
            rowViewHolder.username = view.findViewById(R.id.username);
            rowViewHolder.score = view.findViewById(R.id.score);

            rowViewHolder.userposition.setText(listUsersInLeagues.get(j).getRank() + "");
            rowViewHolder.username.setText(listUsersInLeagues.get(j).getUsername());
            if (listUsersInLeagues.get(j).getTotalPoints() < 1) {
                rowViewHolder.score.setText("0");
            } else {
                rowViewHolder.score.setText(listUsersInLeagues.get(j).getTotalPoints() + "");
            }


//        } else {
//            System.out.println("here2");
//            System.out.println(listUsersInLeagues.get(0).getUsername());
//            if (view == null) {
//                viewHolder = new ViewHolder();
//                view = LayoutInflater.from(context).
//                        inflate(R.layout.leaderboard_privateusercard, viewGroup, false);
//            }
//
//            viewHolder.userCard = view.findViewById(R.id.userCard);
//            viewHolder.userPosition = view.findViewById(R.id.userPosition);
//            viewHolder.userScore = view.findViewById(R.id.userScore);
//
//            viewHolder.lbItemRank = view.findViewById(R.id.lbItemRank);
//            viewHolder.lbItemUser = view.findViewById(R.id.lbItemUser);
//            viewHolder.lbItemScore = view.findViewById(R.id.lbItemScore);
//
//            viewHolder.lbItemRank.setText(listUsersInLeagues.get(j).getRank() + "");
//            viewHolder.lbItemUser.setText(listUsersInLeagues.get(j).getUsername());
//            viewHolder.lbItemScore.setText(listUsersInLeagues.get(j).getTotalPoints() + "");
//            //viewHolder.lbList = view.findViewById(R.id.lbList);
//
//            int userPos = ScoreBoardDAO.getUserPositionPrivate(UserDAO.getLoginUser().getUsername(), listUsersInLeagues);
//            int getUserPoints = ScoreBoardDAO.getUserPointsPrivate(UserDAO.getLoginUser().getUsername(), listUsersInLeagues);
//
//            if (userPos == 0) {
//                viewHolder.userPosition.setText("-");
//            } else {
//                viewHolder.userPosition.setText("#" + userPos);
//            }
//
//            if (getUserPoints != -1) {
//                viewHolder.userScore.setText(getUserPoints + "");
//            } else {
//                viewHolder.userScore.setText("");
//            }

//            System.out.println(listUsersInLeagues.toString());
//            LeaderboardPrivateAdapter privateAdapter = new LeaderboardPrivateAdapter(context, listUsersInLeagues);
//            viewHolder.lbList.setAdapter(privateAdapter);
//        }
//        countChild++;
        }
        return view;// returns the view for the current row
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
