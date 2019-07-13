package com.field.statistics.fieldservice.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.field.statistics.fieldservice.controllers.dto.StatisticsDto;
import com.field.statistics.fieldservice.model.Condition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void addFieldConditionsOldStatisticsWontAffectResult() {
        //old statistic
        statisticsService.addFieldConditions(new Condition(0.98, LocalDateTime.of(2019, 5, 1, 0, 0, 0)));

        //valid statistic
        statisticsService.addFieldConditions(
            new Condition(0.68, LocalDateTime.of(2019, LocalDate.now().getMonth(), 01, 0, 0, 0)));

        StatisticsDto statistics = statisticsService.getStatistics();
        assertThat(statistics.getMin()).isEqualTo(0.68);
        assertThat(statistics.getMax()).isEqualTo(0.68);
        assertThat(statistics.getAvg()).isEqualTo(0.68);
    }

    @Test
    public void getStatistics() {
        statisticsService.addFieldConditions(
            new Condition(0.68, LocalDateTime.of(2019, LocalDate.now().getMonth(), 01, 0, 0, 0)));

        StatisticsDto statistics = statisticsService.getStatistics();
        assertThat(statistics.getMin()).isEqualTo(0.68);
        assertThat(statistics.getMax()).isEqualTo(0.68);
        assertThat(statistics.getAvg()).isEqualTo(0.68);
    }
}