package com.example.baby_tracker_fyp.model;

public class bottleFeeding {
    private String dateAndTime;
    private int AmtInOZ;
    private String bfNotes;


    public bottleFeeding(){
        //empty constructor
    }

    //rigth click on generate -> constructor
    public bottleFeeding(String dateAndTime, int AmtInOZ, String bfNotes) {
        this.dateAndTime = dateAndTime;
        this.AmtInOZ = AmtInOZ;
        this.bfNotes = bfNotes;
    }

    //rigth click on generate -> GETTERS AND SETTERS
    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getAmtInOZ() {
        return AmtInOZ;
    }

    public void setAmtInOZ(int amtInOZ) {
        this.AmtInOZ = AmtInOZ;
    }

    public String getBfNotes() {
        return bfNotes;
    }

    public void setBfNotes(String bfNotes) {
        this.bfNotes = bfNotes;
    }
}
