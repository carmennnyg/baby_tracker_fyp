package com.example.baby_tracker_fyp.model;

public class temperature {
    private String dateAndTime;
    private String degreeCelsius;
    private String temperatureNotes;


    public temperature() {
        //empty constructor
    }

    //right click on generate -> constructor
    public temperature(String dateAndTime, String degreeCelsius, String temperatureNotes) {
        this.dateAndTime = dateAndTime;
        this.degreeCelsius = degreeCelsius;
        this.temperatureNotes = temperatureNotes;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getDegreeCelsius() {
        return degreeCelsius;
    }

    public void setDegreeCelsius(String degreeCelsius) {
        this.degreeCelsius = degreeCelsius;
    }

    public String getTemperatureNotes() {
        return temperatureNotes;
    }

    public void setTemperatureNotes(String temperatureNotes) {
        this.temperatureNotes = temperatureNotes;
    }
}
