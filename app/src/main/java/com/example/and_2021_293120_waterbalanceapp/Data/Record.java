package com.example.and_2021_293120_waterbalanceapp.Data;


public class Record {
    private double goal;
    private double progress;

    public Record() {
    }

    public Record(double goal, double progress){
        this.progress = progress;
        this.goal = goal;
    }

    public double getGoal()
    {
        return goal;
    }

    public double getProgress()
    {
        return progress;
    }

    public void setGoal(double goal){
        this.goal = goal;
    }

    public void setProgress(double progress){
        this.progress = progress;
    }
}
