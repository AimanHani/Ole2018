package com.example.ole.oleandroid.dbConnection;

public class DBConnection {

    private String mainUrl = "http://10.124.144.7:3306/ole"; //may vary depends on wifi connection
    // Hazirah ip: 192.168.39.1:3306, 192.168.1.35
    //String insertUrl = mainUrl + "/tutorial/insertStudent.php";
    //String showUrl = mainUrl + "/tutorial/showStudents.php";
    //taiwan number ip: 10.124.133.134

    public DBConnection() {
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public String getLoginUrl(){
        String url = mainUrl + "/login.php";
        return url;
    }
    public String getSignupUrl(){
        String url = mainUrl+"/signup.php";
        return url;
    }

    public String getPublicLeagueUrl(){
        String url = mainUrl+"/allPublicLeagues.php";
        return url;
    }

    public String insertUserPublicLeagueUrl(){
        String url = mainUrl+"/joinPublicLeague.php";
        return url;
    }

    public String manageSpecialsUrl(){
        String url = mainUrl+"/manageSpecials.php";
        return url;
    }

    public String manageMatchesUrl(){
        String url = mainUrl+"/manageMatches.php";
        return url;
    }
    
}
