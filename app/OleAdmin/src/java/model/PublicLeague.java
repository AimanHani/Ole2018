package model;

public class PublicLeague {

    private int leagueID;
    private String tournamentId;
    private String tournamentName;
    private int pointsAllocated;
    private String leagueName;
    private String prize;

    public PublicLeague(int leagueID, String tournamentId, String tournamentName, int pointsAllocated, String leagueName, String prize) {
        this.leagueID = leagueID;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.pointsAllocated = pointsAllocated;
        this.leagueName = leagueName;
        this.prize = prize;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getPointsAllocated() {
        return pointsAllocated;
    }

    public void setPointsAllocated(int pointsAllocated) {
        this.pointsAllocated = pointsAllocated;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }
}
