package com.example.ole.oleandroid.model;

public class Teams {
    private int teamId;
    private String teamName;
    private boolean checked;

    public Teams(int teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
        checked = false;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return checked;
    }
}
