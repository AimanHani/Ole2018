package com.example.ole.oleandroid.dbConnection;

public class DBConnection {

    private String mainUrl = "http://192.168.1.7:3306"; //may vary depends on wifi connection
    //String insertUrl = mainUrl + "/tutorial/insertStudent.php";
    //String showUrl = mainUrl + "/tutorial/showStudents.php";


    public DBConnection() {
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public String getLoginUrl(){
        String url = mainUrl + "/ole/login.php";
        return url;
    }
    public String getSignupUrl(){
        String url = mainUrl+"/ole/signup.php";
        return url;
    }
}
