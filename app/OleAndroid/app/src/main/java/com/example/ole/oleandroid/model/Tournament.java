package com.example.ole.oleandroid.model;

public class Tournament {
    String tournamentId;
    String name;
    boolean checked;

    public Tournament(String tournamentId, String name) {
        this.tournamentId = tournamentId;
        this.name = name;
        checked = false;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return checked;
    }
}
