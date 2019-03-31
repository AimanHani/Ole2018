package com.example.ole.oleandroid.model;

import java.util.Comparator;

public class TeamItems {
    private String mTeamName;
    private int mTeamImage;
    public TeamItems(String teamName, int teamImage){
        mTeamName = teamName;
        mTeamImage = teamImage;
    }

    public String getmTeamName() {
        return mTeamName;
    }

    public int getmTeamImage() {
        return mTeamImage;
    }

    public static class SortTeamName implements Comparator<TeamItems>
    {
        public int compare(TeamItems o1, TeamItems o2) {
            return o1.mTeamName.compareTo(o2.mTeamName);
        }
    }

}
