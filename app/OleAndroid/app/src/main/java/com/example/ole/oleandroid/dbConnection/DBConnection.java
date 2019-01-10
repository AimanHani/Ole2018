package com.example.ole.oleandroid.dbConnection;

public class DBConnection {

    private String mainUrl = "http://192.168.39.1:8084/WebService"; //may vary depends on wifi connection
    // Hazirah ip: 192.168.39.1:3306, 192.168.1.35, 172.20.10.3
    // PHP: http://192.168.1.7:3306/ole
    //WEB SERVICE: http://192.168.1.7:8084/WebService/
    //taiwan number ip: 192.168.1.7

    public DBConnection() {
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public String getLoginUrl(){
        //String url = mainUrl + "/login.php";
        String url = mainUrl + "/json/authenticate";
        return url;
    }
    public String getSignupUrl(){
        //String url = mainUrl+"/signup.php";
        String url = mainUrl+"/json/signUp";
        return url;
    }

    public String getPublicLeagueUrl(){
        //String url = mainUrl+"/allPublicLeagues.php";
        String url = mainUrl + "/json/allPublicLeagues";
        return url;
    }

    public String insertUserPublicLeagueUrl(){
        //String url = mainUrl+"/joinPublicLeague.php";
        String url = mainUrl+"/json/joinPublicLeagues";
        return url;
    }

    public String manageSpecialsUrl(){
        String url = mainUrl+"/manageSpecials.php";
        return url;
    }

    public String getMatchesUrl(){
        //String url = mainUrl+"/manageMatches.php";
        String url = mainUrl+"/json/getMatches";
        return url;
    }

    public String manageMatchesUrl(){
        //String url = mainUrl+"/manageMatches.php";
        String url = mainUrl+"/json/manageMatches";
        return url;
    }

    public String profileUrl(){
        //String url = mainUrl+"/profile.php";
        String url = mainUrl + "/json/publicLeagueProfile";
        return url;
    }
    
}
