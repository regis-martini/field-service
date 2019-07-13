package com.field.statistics.fieldservice.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition {

    @NotNull
    private double vegetation;
    @NotNull
    private LocalDateTime occurrenceAt;
}
