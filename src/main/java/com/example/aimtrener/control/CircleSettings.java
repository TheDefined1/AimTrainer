package com.example.aimtrener.control;

public class CircleSettings {
    private static double size = 50; // Default size

    public CircleSettings(double size) {
        CircleSettings.size = size; // Update static size
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        CircleSettings.size = size;
    }
}
