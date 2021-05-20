package com.example.and_2021_293120_waterbalanceapp.data;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    private double goal;
    private double progress;
    private String timeStamp;

    public Record() {
    }

    public Record(double goal, double progress) {
        this.progress = progress;
        this.goal = goal;
        this.timeStamp = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());
    }

    public double getGoal() {
        return goal;
    }

    public double getProgress() {
        return progress;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
