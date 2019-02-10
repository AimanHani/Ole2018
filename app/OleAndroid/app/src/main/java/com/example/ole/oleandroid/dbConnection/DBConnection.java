package com.example.ole.oleandroid.dbConnection;

public class DBConnection {

    private static String mainUrl = "http://192.168.1.35:8084/WebService"; //may vary depends on wifi connection
    // Hazirah ip: 192.168.39.1:3306, 192.168.1.35, 172.20.10.3
    // PHP: http://192.168.1.7:3306/ole
    //WEB SERVICE: http://192.168.1.7:8084/WebService/
    //taiwan number ip: 192.168.1.7

    public static String getMainUrl() {
        return mainUrl;
    }

    public static String getLoginUrl(){
        String url = mainUrl + "/json/authenticate";
        return url;
    }

    public static String getSignupUrl(){
        String url = mainUrl+"/json/signUp";
        return url;
    }
    
    public static String getPublicLeagueUrl(){
        String url = mainUrl + "/json/PublicLeagueJson";
        return url;
    }

    public static String insertUserPublicLeagueUrl(){
        String url = mainUrl+"/json/joinPublicLeague";
        return url;
    }

    public static String manageSpecialsUrl(){
        String url = mainUrl+"/manageSpecials.php";
        return url;
    }

    public static String getMatchesUrl(){
        String url = mainUrl+"/json/getMatches";
        return url;
    }

    public static String manageMatchesUrl(){
        String url = mainUrl+"/json/manageMatches";
        return url;
    }

    public static String profileUrl(){
        String url = mainUrl + "/json/publicLeagueProfile";
        return url;
    }

    public static String insertPrivateLeagueUrl(){
        String url = mainUrl+"/json/privateLeague";
        return url;
    }

    public static String manageSpecials(){
        String url = mainUrl+"/json/manageSpecials";
        return url;
    }

    public static String getSpecials(){
        String url = mainUrl+"/json/getSpecials";
        return url;
    }

    public static String getTeams(){
        String url = mainUrl+"/json/getTeam";
        return url;
    }

    public static String futureMatchesUrl(){
        String url = mainUrl+"/json/getMatches?matchStatus=future";
        return url;
    }

    public static String pastMatchesUrl(){
        String url = mainUrl+"/json/getMatches?matchStatus=past";
        return url;
    }

    public static String getScoreBoardUrl(){
        String url = mainUrl+"/json/ScoreBoard";
        return url;
    }
}

