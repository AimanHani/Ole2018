package com.example.ole.oleandroid.controller.DAO;

import android.content.Context;

import com.example.ole.oleandroid.dbConnection.DBConnection;
import com.example.ole.oleandroid.dbConnection.PostHttp;
import com.example.ole.oleandroid.model.User;

import org.json.JSONObject;

public class SignupDAO {
    private static Context context;

    public static Boolean validate(String send, String username, String pwd) {
        PostHttp connection = new PostHttp();
        String response = null;
        String url = DBConnection.getSignupUrl();
        String status = "error";
        Boolean valid = false;

        try {
            response = PostHttp.postForm(url, send);
            System.out.println(response);

            JSONObject result = new JSONObject(response);
            status = result.getString("status");

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
                valid = true;
                User userDetails = new User(usernameRetrieved, name, password, dob, country, contactNum, email, favoriteTeam);
                UserDAO.setLoginUser(userDetails);
            } else {
                //loadSamePage();
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (LoginDAO.validate("username=" + username + "&password=" + pwd)){
                return true;
            }

        }

        return valid;
    }

}
