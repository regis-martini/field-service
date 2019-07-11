package com.field.statistics.fieldservice.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Condition {

    @NotNull
    private double vegetation;
    @NotNull
    private LocalDateTime occurrenceAt;
}
