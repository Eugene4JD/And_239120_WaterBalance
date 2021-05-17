package com.example.and_2021_293120_waterbalanceapp.Data;

public class CurrentData {
    private double goal;
    private double progress;

    public CurrentData() { }

    public CurrentData(double goal, double progress) {
        this.goal = goal;
        this.progress = progress;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getGoal() {
        return goal;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String toString()
    {
        return this.getProgress() + " / " + this.getGoal();
    }
}
