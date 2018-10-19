package model;

public class Specials {

    private int specialsId;
    private String description;
    private String status;
    
    public Specials(int specialsId, String description, String status){
        this.specialsId = specialsId;
        this.description = description;
        this.status = status;

    }
    
    public int getSpecialsId() {
        return specialsId;
    }

    public void setSpecialsId(int specialsId) {
        this.specialsId = specialsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }  
}
