package com.example.baby_tracker_fyp.model;

public class medication {
    private String dateAndTime;
    private String medDetails;
    private String medNotes;


    public medication() {
        //empty constructor
    }

    //right click on generate -> constructor
    public medication(String dateAndTime, String medDetails, String medNotes) {
        this.dateAndTime = dateAndTime;
        this.medDetails = medDetails;
        this.medNotes = medNotes;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getMedDetails() {
        return medDetails;
    }

    public void setMedDetails(String medDetails) {
        this.medDetails = medDetails;
    }

    public String getMedNotes() {
        return medNotes;
    }

    public void setMedNotes(String medNotes) {
        this.medNotes = medNotes;
    }
}
