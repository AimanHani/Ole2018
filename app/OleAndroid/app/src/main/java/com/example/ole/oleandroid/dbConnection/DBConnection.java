package com.example.ole.oleandroid.dbConnection;

public class DBConnection {

    private String mainUrl = "http://10.124.133.134:8084/WebService"; //may vary depends on wifi connection
    private static String mainUrl = "http://192.168.39.1:8084/WebService"; //may vary depends on wifi connection

    // Hazirah ip: 192.168.39.1:3306, 192.168.1.35, 172.20.10.3
    // PHP: http://192.168.1.7:3306/ole
    //WEB SERVICE: http://192.168.1.7:8084/WebService/
    //taiwan number ip: 192.168.1.7

    public DBConnection() {
    }

    public String getMainUrl() {
    public static String getMainUrl() {
        return mainUrl;
    }

    public String getLoginUrl(){
        //String url = mainUrl + "/login.php";
    public static String getLoginUrl(){
        String url = mainUrl + "/json/authenticate";
        return url;
    }
    public String getSignupUrl(){
        //String url = mainUrl+"/signup.php";
    public static String getSignupUrl(){
        String url = mainUrl+"/json/signUp";
        return url;
    }

    public String getPublicLeagueUrl(){
        //String url = mainUrl+"/allPublicLeagues.php";
    public static String getPublicLeagueUrl(){
        String url = mainUrl + "/json/allPublicLeagues";
        return url;
    }

    public String insertUserPublicLeagueUrl(){
        //String url = mainUrl+"/joinPublicLeague.php";
    public static String insertUserPublicLeagueUrl(){
        String url = mainUrl+"/json/joinPublicLeagues";
        return url;
    }

    public String manageSpecialsUrl(){
    public static String manageSpecialsUrl(){
        String url = mainUrl+"/manageSpecials.php";
        return url;
    }

    public String getMatchesUrl(){
        //String url = mainUrl+"/manageMatches.php";
    public static String getMatchesUrl(){
        String url = mainUrl+"/json/getMatches";
        return url;
    }

    public String manageMatchesUrl(){
        //String url = mainUrl+"/manageMatches.php";
    public static String manageMatchesUrl(){
        String url = mainUrl+"/json/manageMatches";
        return url;
    }

    public String profileUrl(){
        //String url = mainUrl+"/profile.php";
    public static String profileUrl(){
        String url = mainUrl + "/json/publicLeagueProfile";
        return url;
    }
    
}

