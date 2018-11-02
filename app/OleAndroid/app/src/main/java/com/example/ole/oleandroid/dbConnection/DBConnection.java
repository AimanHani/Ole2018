package com.example.ole.oleandroid.dbConnection;

public class DBConnection {

    private String mainUrl = "http://10.58.125.73/ole"; //may vary depends on wifi connection
    // Hazirah ip: 192.168.39.1
    //String insertUrl = mainUrl + "/tutorial/insertStudent.php";
    //String showUrl = mainUrl + "/tutorial/showStudents.php";


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
}
