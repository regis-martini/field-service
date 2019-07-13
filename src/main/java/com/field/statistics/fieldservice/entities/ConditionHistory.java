package com.field.statistics.fieldservice.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_configuration")
public class ConditionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_id_generator")
    @SequenceGenerator(name = "condition_id_generator", sequenceName = "condition_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "vegetation", nullable = false)
    private double vegetation;
    @Column(name = "occurrence_at", nullable = false)
    private LocalDateTime occurrenceAt;

    public ConditionHistory(double vegetation, LocalDateTime occurrenceAt) {
        this.vegetation = vegetation;
        this.occurrenceAt = occurrenceAt;
    }
}
