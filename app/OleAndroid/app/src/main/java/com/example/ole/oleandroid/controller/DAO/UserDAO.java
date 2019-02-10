package com.example.ole.oleandroid.controller.DAO;

import com.example.ole.oleandroid.model.User;

import java.util.ArrayList;

public class UserDAO {
    private static ArrayList<User> allUsers = new ArrayList();
    private static User loginUser;

    public void setAllUsers(ArrayList<User> allUsers) {
        UserDAO.allUsers = allUsers;
    }

    public static void addUsers(User user){
        allUsers.add(user);
    }

    public static User getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(User user) {
        loginUser = user;
    }
}
