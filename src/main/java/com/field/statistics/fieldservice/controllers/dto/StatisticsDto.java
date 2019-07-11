package com.field.statistics.fieldservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {

    private double max = 0.0;
    private double min = 0.0;
    private double avg = 0.0;

}
