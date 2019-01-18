/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author user
 */
public class User {
    private String username;
    private String name;
    private String password;
    private Date dob;
    private String country;
    private String contactNumber;
    private String email;
    private String favoriteTeam;

    public User(String username, String name, String password, Date dob, String country, String contactNumber, String email, String favoriteTeam) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.country = country;
        this.contactNumber = contactNumber;
        this.email = email;
        this.favoriteTeam = favoriteTeam;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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
