package com.field.statistics.fieldservice.model;

import lombok.Data;

@Data
public class Statistics {

    private double sum = 0.0;
    private double maximum = Double.MIN_VALUE;
    private double minimum = Double.MAX_VALUE;
    private int count = 0;

    public void addData(double amount) {
        count++;
        sum += amount;

        if (amount > maximum) {
            maximum = amount;
        }
        if (amount < minimum) {
            minimum = amount;
        }
    }

    public double calculateAverage() {
        return (count > 0) ? (sum / count) : 0.0;
    }
}
