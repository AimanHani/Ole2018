package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.User;

import org.json.JSONObject;

public class LoginDAO {

    public static Boolean validate (String send){
        String url = DBConnection.getLoginUrl();
        PostHttp connection = new PostHttp();
        String response = null;
        Boolean valid = false;

        try {
            response = connection.postForm(url, send);
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            String status = result.getString("status");

            if (status.equals("success")) {
                JSONObject user = result.getJSONObject("user");
                String usernameRetrieved = user.getString("username");
                System.out.println(usernameRetrieved);
                String name = user.getString("name");
                String password = user.getString("password");
                String dob = user.getString("dob");
                String country = user.getString("country");
                String contactNum = user.getString("contactNum");
                String email = user.getString("email");
                String favoriteTeam = user.getString("favoriteTeam");

                User userDetails = new User(usernameRetrieved, name, password, dob, country, contactNum, email, favoriteTeam);
                UserDAO.setLoginUser(userDetails);
                valid = true;

            } else {
                //loadSamePage();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return valid;
    }

    public static String returnResult (String results){
        return results;
    }


    public static boolean changePassword(String email, String password){
        String url = DBConnection.changePasswordUrl();
        PostHttp connection = new PostHttp();
        String response = null;
        Boolean valid = false;

        try {
            response = connection.postForm(url, "email=" + email + "&password=" + password);
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            String status = result.getString("status");

            if (status.equals("success")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
