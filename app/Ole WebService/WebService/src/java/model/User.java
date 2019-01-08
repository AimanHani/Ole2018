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
    private String userName;
    private String name;
    private String password;
    private Date dateOfBirth;
    private String country;
    private String contactNumber;
    private String email;
    private String favoriteTeam;

    public User(String userName, String name, String password, Date dateOfBirth, String country, String contactNumber, String email, String favoriteTeam) {
        this.userName = userName;
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
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
