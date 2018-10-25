package com.example.ole.oleandroid.controller;

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
}
