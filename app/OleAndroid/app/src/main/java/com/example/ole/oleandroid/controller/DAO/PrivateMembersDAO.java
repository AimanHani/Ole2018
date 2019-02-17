package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.PrivateLeague;
import com.example.ole.oleandroid.model.PrivateMembers;

import org.json.JSONObject;

import java.util.ArrayList;

public class PrivateMembersDAO {
    private static String leagueId;

    private static ArrayList<PrivateMembers> allPrivateMembers = new ArrayList<>();

    public static ArrayList<PrivateMembers> getAllPrivateMembers() {
        return allPrivateMembers;
    }

    public static void setAllPrivateMembers(ArrayList<PrivateMembers> members) {
        PrivateMembersDAO.allPrivateMembers = members;
    }

    public static void clearAllPrivateMembers(){
        PrivateMembersDAO.allPrivateMembers = new ArrayList<>();
    }

    public static void addPrivateMembers(PrivateMembers members){
        allPrivateMembers.add(members);
    }

    public static void setId(String leagueId){
        PrivateMembersDAO.leagueId = leagueId;
    }

    public static String getId(){
        return PrivateMembersDAO.leagueId;
    }
}
