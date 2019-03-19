/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class PrivateSpecial {

    private int specialsID;
    private String description;
    private int points;
    private int doubleIt;
    private String prediction;

    public PrivateSpecial(int specialsID, String description, int points, int doubleIt, String prediction) {
        this.specialsID = specialsID;
        this.description = description;
        this.points = points;
        this.doubleIt = doubleIt;
        this.prediction = prediction;
    }

    public int getPoints() {    
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSpecialsID() {
        return specialsID;
    }

    public void setSpecialsID(int specialsID) {
        this.specialsID = specialsID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDoubleIt() {
        return doubleIt;
    }

    public void setDoubleIt(int doubleIt) {
        this.doubleIt = doubleIt;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

}
