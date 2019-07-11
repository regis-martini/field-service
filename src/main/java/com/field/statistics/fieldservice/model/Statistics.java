package com.field.statistics.fieldservice.model;

import lombok.Data;

@Data
public class Statistics {

    private double sum = 0.0;
    private double maximum = 0.0;
    private double minimum = 0.0;
    private int count = 0;

    public void addData(double amount) {
        count++;
        sum += amount;

        if (amount > maximum) {
            maximum = amount;
        } else if (amount < minimum) {
            minimum = amount;
        }
    }

    public double calculateAverage() {
        return (count > 0) ? (sum / count) : 0.0;
    }
}
