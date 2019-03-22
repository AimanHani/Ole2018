package com.example.ole.oleandroid.model;

public class PSpecials {

    private int specialsID;
    private String description;
    private int points;
    private boolean checked;
    private boolean doubleIt;
    private String prediction;

    public PSpecials(int specialsID, String description, int points, int doubleIt, String prediction) {
        this.specialsID = specialsID;
        this.description = description;
        this.points = points;
        checked = false;
        if(doubleIt==1){
            this.doubleIt = true;
        }else{
            this.doubleIt = false;
        }
        this.prediction = prediction;
    }

    public PSpecials(int specialsID, String description, int points, int doubleIt) {
        this.specialsID = specialsID;
        this.description = description;
        this.points = points;
        checked = false;
        if(doubleIt==1){
            this.doubleIt = true;
        }else{
            this.doubleIt = false;
        }
        this.prediction = null;
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

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setDoubleIt(boolean b) {
        doubleIt = b;
    }

    public boolean getDoubleIt() {
        return doubleIt;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
