package com.field.statistics.fieldservice.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.field.statistics.fieldservice.controllers.dto.StatisticsDto;
import com.field.statistics.fieldservice.model.Condition;
import com.field.statistics.fieldservice.services.StatisticsService;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldControllerTest {

    @Autowired
    private FieldController fieldController;
    @Autowired
    private StatisticsService statisticsService;

    @After
    public void tearDown() throws Exception {
        statisticsService.clearStatistics();
    }

    @Test
    public void addFieldConditions() {
        fieldController.addFieldConditions(new Condition(0.68, LocalDateTime.now()));
        StatisticsDto fieldStatistics = fieldController.getFieldStatistics();
        assertThat(fieldStatistics.getMin()).isEqualTo(0.68);
        assertThat(fieldStatistics.getMax()).isEqualTo(0.68);
        assertThat(fieldStatistics.getAvg()).isEqualTo(0.68);
    }

    @Test
    public void addFieldMultipleConditionsSameDay() {
        fieldController.addFieldConditions(new Condition(0.68, LocalDateTime.now()));
        fieldController.addFieldConditions(new Condition(0.01, LocalDateTime.now()));
        fieldController.addFieldConditions(new Condition(1.0, LocalDateTime.now()));
        StatisticsDto fieldStatistics = fieldController.getFieldStatistics();
        assertThat(fieldStatistics.getMin()).isEqualTo(0.01);
        assertThat(fieldStatistics.getMax()).isEqualTo(1.0);
        assertThat(fieldStatistics.getAvg()).isEqualTo(0.5633333333333334);
    }

    @Test
    public void addFieldNoneConditionsDay() {
        StatisticsDto fieldStatistics = fieldController.getFieldStatistics();
        assertThat(fieldStatistics.getMin()).isEqualTo(0.0);
        assertThat(fieldStatistics.getMax()).isEqualTo(0.0);
        assertThat(fieldStatistics.getAvg()).isEqualTo(0.0);
    }

//    @Test
//    public void addFieldMultipleConditionsFor30Day() {
//        IntStream.range(1, 30).forEach(i -> {
//            fieldController
//                .addFieldConditions(new Condition(new Random().nextDouble(), LocalDateTime.now().minusDays(i)));
//            fieldController
//                .addFieldConditions(new Condition(new Random().nextDouble(), LocalDateTime.now().minusDays(i)));
//            fieldController
//                .addFieldConditions(new Condition(new Random().nextDouble(), LocalDateTime.now().minusDays(i)));
//        });
//        StatisticsDto fieldStatistics = fieldController.getFieldStatistics();
//        assertThat(fieldStatistics.getMin()).isCloseTo(0.01, Percentage.withPercentage(100));
//        assertThat(fieldStatistics.getMax()).isCloseTo(1, Percentage.withPercentage(100));
//        assertThat(fieldStatistics.getAvg()).isCloseTo(1, Percentage.withPercentage(100));
//    }
}