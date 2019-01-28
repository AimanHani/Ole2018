package model;

public class Users {

    private String username;
    private String name;
    private String password;
    private String dob;
    private String country;
    private String contactNo;
    private String email;
    private String favoriteTeam;

    public Users(String username, String name, String password, String dob, String country, String contactNo, String email, String favoriteTeam) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.country = country;
        this.contactNo = contactNo;
        this.email = email;
        this.favoriteTeam = favoriteTeam;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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
