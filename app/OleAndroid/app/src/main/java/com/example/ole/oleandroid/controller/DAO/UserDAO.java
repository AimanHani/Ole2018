package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.GetHttp;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.Match;
import com.example.ole.oleandroid.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDAO {
    private static ArrayList<User> allUsers = new ArrayList();
    private static ArrayList<String> usernames = new ArrayList();
    private static User loginUser;

    public void setAllUsers(ArrayList<User> allUsers) {
        UserDAO.allUsers = allUsers;
    }

    public static void addUsers(User user) {
        allUsers.add(user);
    }

    public static User getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(User user) {
        loginUser = user;
    }

    public static void getUsernames() {
        String url = DBConnection.getUsers();

        System.out.println("Getting usernames");

        GetHttp getConnection = new GetHttp();
        String response = null;
        try {
            response = getConnection.run(url);
            System.out.println(response);
            JSONObject result = new JSONObject(response);
            JSONArray usernameArray = result.getJSONArray("results");

            if (usernameArray.length() > 0) {
                for (int i = 0; i < usernameArray.length(); i++) {
                    String username = usernameArray.getString(i);
                    usernames.add(username);
                }
            } else {
                usernames = null;
            }
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            usernames = null;
        }
    }

    public static boolean checkUsernameExist(String user) {
        if (usernames.contains(user)) {
            return true;
        }
        return false;
    }

    public static HashMap<String, Integer>  getProfileStatistics() {
        GetHttp connection = new GetHttp();
        String response = null;
        String url = DBConnection.getProfileStats();
        String username = "error";
        Boolean valid = false;
        HashMap<String, Integer> leagueStats = new HashMap<>();
        try {
            response = connection.run(url+ "?username=" + loginUser.getUsername());
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            username = result.getString("username");

            if (username.equals(loginUser.getUsername())) {

                //return result.getInt("numLeagues") + "";
                leagueStats.put("specialsPredictionTotal", result.getInt("specialsPredictionTotal"));
                leagueStats.put("numLeagues", result.getInt("numLeagues"));
                leagueStats.put("matchPredictionCorrect", result.getInt("matchPredictionCorrect"));
                leagueStats.put("pastMatchPrediction", result.getInt("pastMatchPrediction"));
                leagueStats.put("matchAccuracy", result.getInt("matchAccuracy"));
                return leagueStats;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String updateFavoriteTeam(String teamName) {
        PostHttp connection = new PostHttp();
        String response = null;
        String url = DBConnection.getMainUrl() + "/json/ChangeFavoriteTeam";
        String username = "error";
        Boolean valid = false;

        try {
            response = connection.postForm(url, "username=" + loginUser.getUsername() + "&teamName=" + teamName);
            System.out.println(response);

            JSONObject result = new JSONObject(response);

            return result.getString("status");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void getMatchAccuracy(){

    }

    public static void getSpecialsAccuracy(){

    }
}
