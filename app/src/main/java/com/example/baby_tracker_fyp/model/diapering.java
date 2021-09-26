package com.example.baby_tracker_fyp.model;

public class diapering {
    private String dateAndTime;
    private String diaperStatus;
    private String bfNotes;


    public diapering(){
        //empty constructor
    }

    //right click on generate -> constructor
    public diapering(String dateAndTime, String diaperStatus, String bfNotes) {
        this.dateAndTime = dateAndTime;
        this.diaperStatus = diaperStatus;
        this.bfNotes = bfNotes;
    }

    //right click on generate -> GETTERS AND SETTERS
    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getBfNotes() {
        return bfNotes;
    }

    public void setBfNotes(String bfNotes) {
        this.bfNotes = bfNotes;
    }

    public String getDiaperStatus() {
        return diaperStatus;
    }

    public void setDiaperStatus(String diaperStatus) {
        this.diaperStatus = diaperStatus;
    }
}

