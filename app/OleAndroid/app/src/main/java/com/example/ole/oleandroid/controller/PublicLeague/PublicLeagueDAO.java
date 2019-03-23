package com.example.ole.oleandroid.controller.PublicLeague;

import com.example.ole.oleandroid.controller.DAO.UserDAO;
import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.Member;
import com.example.ole.oleandroid.model.PublicLeague;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PublicLeagueDAO {
    private static int numMembers = 0;

    public static int getNumMembers() {  return numMembers;    }

    private static ArrayList<PublicLeague> allPublicLeague = new ArrayList<>();

    public static ArrayList<PublicLeague> getAllPublicLeagueList() {
        return allPublicLeague;
    }

    public static void setAllPublicLeague(ArrayList<PublicLeague> allPublicLeague) {
        PublicLeagueDAO.allPublicLeague = allPublicLeague;
    }

    public static void clearAllPublicLeague() {
        PublicLeagueDAO.allPublicLeague = new ArrayList<>();
    }

    public static PublicLeague getOnePublicLeague(int leagueId) {
        for (PublicLeague pl : allPublicLeague) {
            if (pl.getLeagueId() == leagueId) {
                return pl;
            }
        }
        return null;
    }

    public static void addPublicleague(PublicLeague publicLeague) {
        allPublicLeague.add(publicLeague);
    }

    public static int joinPublicLeague(int leagueId, String username) {
        System.out.println("Join Public League DAO");
        String url = DBConnection.insertUserPublicLeagueUrl();

        String params = concatJoinParams(username, leagueId);
        PostHttp connection = new PostHttp();
        String response = null;
        int resultFinal = 0;

        try {
            response = connection.postForm(url, params);
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            String status = result.getString("status");

            if (status.equals("successful")) {
                resultFinal = result.getInt("logId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            int logid = checkJoin(leagueId, username);
            //System.out.println(logid);
            return logid;
        }
        return resultFinal;
    }

    public static int checkJoin(int leagueId, String username) {
        System.out.println("check Join");

        String params = concatJoinParams(username, leagueId);
        String url = DBConnection.insertUserPublicLeagueUrl() + "?" + params;
        GetHttp connection = new GetHttp();
        String response = null;
        Boolean resultFinal = false;
        int logId = 0;

        try {
            response = connection.run(url);
            //System.out.println(response);

            JSONObject result = new JSONObject(response);
            String status = result.getString("status");

            if (status.equals("successful")) {
                return result.getInt("logId");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return logId;

    }

    public static String concatJoinParams(String username, int leagueId) {
        return "username=" + username + "&leagueId=" + leagueId;
    }

    public static void getPublicLeague() {
        clearAllPublicLeague();
        String url = DBConnection.getPublicLeagueUrl() + "?username=" + UserDAO.getLoginUser().getUsername();
        System.out.println("Getting public league list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray publicLeagues = result.getJSONArray("results");

            if (publicLeagues.length() > 0) {
                for (int i = 0; i < publicLeagues.length(); i++) {
                    JSONObject publicLeagueObject = publicLeagues.getJSONObject(i);

                    Boolean userJoin = false;
                    if (publicLeagueObject.getString("userJoin").equals("true")) {
                        userJoin = true;
                    }

                    PublicLeague publicLeague = new PublicLeague(
                            publicLeagueObject.getInt("leagueID"),
                            publicLeagueObject.getInt("tournamentID"),
                            publicLeagueObject.getString("prize"),
                            publicLeagueObject.getInt("pointsAllocated"),
                            publicLeagueObject.getString("leagueName"),
                            publicLeagueObject.getString("tournamentName"),
                            publicLeagueObject.getInt("logId"),
                            userJoin
                    );
                    addPublicleague(publicLeague);
                }

            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public static void getPublicLeagueStats() {
        String url = DBConnection.getPublicLeagueStatsUrl();
        System.out.println("Getting public league stats list");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public static ArrayList<Member> loadPublicMembers(int leagueId) {
        PublicLeague pl = getOnePublicLeague(leagueId);
        ArrayList<Member> allMembers = new ArrayList<>();

        String url2 = DBConnection.privateLeagueUrl() + "?method=retrieveMembers&leagueid=" + leagueId;

        System.out.println("Getting public league members");
        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url2);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray members = result.getJSONArray("results");
            numMembers = members.length();
            if (members.length() > 0) {
                for (int i = 0; i < members.length(); i++) {
                    JSONObject membersObj = members.getJSONObject(i);
                    Member member = new Member(
                            membersObj.getInt("logid"),
                            membersObj.getString("username"),
                            membersObj.getInt("leagueid"),
                            membersObj.getInt("points"),
                            membersObj.getString("country")
                            );
                    allMembers.add(member);
                }
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

        pl.setAllMembers(allMembers); // set the list of members in the public league
        return pl.getAllMembers();
    }
}
