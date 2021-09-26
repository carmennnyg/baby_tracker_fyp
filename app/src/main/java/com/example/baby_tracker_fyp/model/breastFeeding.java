package com.example.baby_tracker_fyp.model;

public class breastFeeding {
    private String dateAndTime;
    private String leftBreastFeedTime;
    private String rightBreastFeedTime;
    private String bfNotes;


    public breastFeeding(){
        //empty constructor
    }

    //rigth click on generate -> constructor
    public breastFeeding(String dateAndTime, String leftBreastFeedTime,
                         String rightBreastFeedTime,String bfNotes) {
        this.dateAndTime = dateAndTime;
        this.leftBreastFeedTime = leftBreastFeedTime;
        this.rightBreastFeedTime = rightBreastFeedTime;
        this.bfNotes = bfNotes;
    }

    //rigth click on generate -> GETTERS AND SETTERS
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

    public String getLeftBreastFeedTime() {
        return leftBreastFeedTime;
    }

    public void setLeftBreastFeedTime(String leftBreastFeedTime) {
        this.leftBreastFeedTime = leftBreastFeedTime;
    }

    public String getRightBreastFeedTime() {
        return rightBreastFeedTime;
    }

    public void setRightBreastFeedTime(String rightBreastFeedTime) {
        this.rightBreastFeedTime = rightBreastFeedTime;
    }


}
