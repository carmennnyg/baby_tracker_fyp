package com.example.baby_tracker_fyp.model;

public class sleeping {
    private String dateAndTime;
    private String sleepMin;


    public sleeping(){
        //empty constructor
    }

    //right click on generate -> constructor
    public sleeping(String dateAndTime, String sleepMin) {
        this.dateAndTime = dateAndTime;
        this.sleepMin = sleepMin;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getSleepMin() {
        return sleepMin;
    }

    public void setSleepMin(String sleepMin) {
        this.sleepMin = sleepMin;
    }
}
