package model;

public class Tournament {
    String tournamentId;
    String name;

    public Tournament(String tournamentId, String name) {
        this.tournamentId = tournamentId;
        this.name = name;
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


}
