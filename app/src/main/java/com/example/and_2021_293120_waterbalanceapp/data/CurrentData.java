package com.example.and_2021_293120_waterbalanceapp.data;

public class CurrentData {
    private double goal;
    private double progress;

    public CurrentData() {
    }

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

    public String toString() {
        return fmt(this.getProgress()) + " / " + fmt(this.getGoal());
    }

    private static String fmt(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}
