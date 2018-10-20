package com.example.ole.oleandroid;

public class DBConnection {

    private String mainUrl = "http://192.168.99.1:3306"; //may vary depends on wifi connection
    //String insertUrl = mainUrl + "/tutorial/insertStudent.php";
    //String showUrl = mainUrl + "/tutorial/showStudents.php";


    public DBConnection() {
    }

    public String getMainUrl() {
        return mainUrl;
    }
}
