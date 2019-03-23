package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.model.Member;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrivateMembersDAO {
    private static String leagueId;

    private static ArrayList<Member> allPrivateMembers = new ArrayList<>();

    public static ArrayList<Member> getAllPrivateMembers() {
        return allPrivateMembers;
    }

    public static void setAllPrivateMembers(ArrayList<Member> members) {
        PrivateMembersDAO.allPrivateMembers = members;
    }

    public static void clearAllPrivateMembers(){
        PrivateMembersDAO.allPrivateMembers = new ArrayList<>();
    }

    public static void addPrivateMembers(Member members){
        allPrivateMembers.add(members);
    }

    public static void setId(String leagueId){
        PrivateMembersDAO.leagueId = leagueId;
    }

    public static String getId(){
        return PrivateMembersDAO.leagueId;
    }

    public static ArrayList<Member> getPrivateMembers(String leagueid){
        String url2 = DBConnection.privateLeagueUrl() + "?method=retrieveMembers&leagueid=" + leagueid;
        System.out.println("Getting private league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url2);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray members = result.getJSONArray("results");
            PrivateMembersDAO.clearAllPrivateMembers();
            int numMembers = members.length();
            if (members.length() > 0) {
                for (int i = 0; i < members.length(); i++) {
                    JSONObject membersObj = members.getJSONObject(i);
                    Member privateMembers = new Member(
                            membersObj.getInt("logid"),
                            membersObj.getString("username"),
                            membersObj.getInt("leagueid"),
                            membersObj.getInt("points"),
                            membersObj.getString("country")
                    );
                    PrivateMembersDAO.addPrivateMembers(privateMembers);
                }
            } else {
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return PrivateMembersDAO.allPrivateMembers;
    }
}
