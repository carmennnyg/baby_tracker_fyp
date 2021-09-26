package com.example.baby_tracker_fyp.model;


public class mealFeeding {
    private String dateAndTime;
    private String mealSpinner;
    private String supplementSpinner;
    private String bfNotes;


    public mealFeeding(){
        //empty constructor
    }

    //rigth click on generate -> constructor
    public mealFeeding(String dateAndTime, String mealSpinner, String supplementSpinner,
                       String bfNotes) {
        this.dateAndTime = dateAndTime;
        this.mealSpinner = mealSpinner;
        this.supplementSpinner = supplementSpinner;
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

    public String getMealSpinner() {
        return mealSpinner;
    }

    public void setMealSpinner(String mealSpinner) {
        this.mealSpinner = mealSpinner;
    }

    public String getSupplementSpinner() {
        return supplementSpinner;
    }

    public void setSupplementSpinner(String supplementSpinner) {
        this.supplementSpinner = supplementSpinner;
    }
}
