package com.example.aimtrener.control;

public class TimerSettings {
    private static double time = 1.0; // Default time

    public TimerSettings(double time) {
        TimerSettings.time = time; // Update static time
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        TimerSettings.time = time;
    }
}
