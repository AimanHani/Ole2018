package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.model.PublicLeague;
import com.example.ole.oleandroid.model.PublicMembers;
import com.example.ole.oleandroid.model.PublicMembers;

import java.util.ArrayList;

public class PublicMembersDAO {
    private static String leagueId;
    private static PublicLeague pl = null;

    private static ArrayList<PublicMembers> allPublicMembers = new ArrayList<>();

    public static ArrayList<PublicMembers> getAllPublicMembers() {
        return allPublicMembers;
    }

    public static void setAllPublicMembers(ArrayList<PublicMembers> members) {
        PublicMembersDAO.allPublicMembers = members;
    }

    public static void clearAllPublicMembers(){
        PublicMembersDAO.allPublicMembers = new ArrayList<>();
    }

    public static void addPublicMembers(PublicMembers members){
        allPublicMembers.add(members);
    }

    public static void setId(String leagueId){
        PublicMembersDAO.leagueId = leagueId;
    }

    public static String getId(){
        return PublicMembersDAO.leagueId;
    }

    public static void setLeague(PublicLeague publicleague){
        pl = publicleague;
    }

    public static PublicLeague getLeague(){
        return pl;
    }
}
