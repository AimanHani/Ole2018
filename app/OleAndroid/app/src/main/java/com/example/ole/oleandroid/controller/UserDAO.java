package com.example.ole.oleandroid.controller;

import com.example.ole.oleandroid.model.User;

import java.util.ArrayList;

public class UserDAO {
    private ArrayList<User> allUsers = new ArrayList();
    private User loginUser;

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void addUsers(User user){
        allUsers.add(user);
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
