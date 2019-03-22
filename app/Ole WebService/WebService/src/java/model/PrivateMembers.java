package model;


public class PrivateMembers {
    
    private int logid;
    private String username;
    private int leagueId;
    private int points;
    private String country;

//    public PrivateMembers(int logid, String username, int leagueId, int points) {
//        this.logid = logid;
//        this.username = username;
//        this.leagueId = leagueId;
//        this.points = points;
//    }
//    
    public PrivateMembers(int logid, String username, int leagueId, int points, String country) {
        this.logid = logid;
        this.username = username;
        this.leagueId = leagueId;
        this.points = points;
        this.country = country;
    }

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }   
    
}
