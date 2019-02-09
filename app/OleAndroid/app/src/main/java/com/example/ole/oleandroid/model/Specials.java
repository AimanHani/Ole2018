package com.example.ole.oleandroid.model;

public class Specials {

    private int specialsID;
    private String description;
    private int points;
    private boolean checked;

    public Specials(int specialsID, String description, int points) {
        this.specialsID = specialsID;
        this.description = description;
        this.points = points;
        checked = false;
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
}
