package com.example.ole.oleandroid.model;

import java.util.Date;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
//public class User implements Serializable {
public class User {

    private String username;
    private String name;
    private String password;
    private String dateOfBirth;
    private String country;
    private String contactNumber;
    private String email;
    private String favoriteTeam;

    public User(String username, String name, String password, String dateOfBirth, String country, String contactNumber, String email, String favoriteTeam) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.contactNumber = contactNumber;
        this.email = email;
        this.favoriteTeam = favoriteTeam;
    }

    public User() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(String favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

}
