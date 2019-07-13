package com.field.statistics.fieldservice.services;

import com.field.statistics.fieldservice.controllers.dto.StatisticsDto;
import com.field.statistics.fieldservice.model.Condition;
import com.field.statistics.fieldservice.model.Statistics;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    //Bucket of statistics per day
    private Map<LocalDate, Statistics> statisticBuckets = new ConcurrentHashMap<>();

    /**
     * Adding to statistics bucket vegetation amount
     *
     * @param condition Condition
     */
    public void addFieldConditions(@NonNull Condition condition) {
        int day = condition.getOccurrenceAt().getDayOfMonth();
        int year = condition.getOccurrenceAt().getYear();
        int month = condition.getOccurrenceAt().getMonthValue();

        LocalDate date = LocalDate.of(year, month, day);
        Statistics statistics = statisticBuckets.get(date);
        if (statistics == null) {
            statistics = new Statistics();
            statistics.addData(condition.getVegetation());
        } else {
            statistics.addData(condition.getVegetation());
        }
        statisticBuckets.put(date, statistics);
    }

    /**
     * Retrieves statistics for the last 30 days
     *
     * @return statistics StatisticsDto
     */
    public StatisticsDto getStatistics() {
        LocalDate localDate = LocalDate.now();
        List<Double> minList = new ArrayList<>();
        List<Double> maxList = new ArrayList<>();
        List<Double> avgList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Statistics stats = statisticBuckets.get(localDate);
            if (stats != null) {
                minList.add(stats.getMinimum());
                maxList.add(stats.getMaximum());
                avgList.add(stats.calculateAverage());
            }
            localDate = localDate.minusDays(1);
        }

        double minimal = minList.stream().min(Comparator.comparing(Double::valueOf)).orElse(0.0);
        double maximal = maxList.stream().max(Comparator.comparing(Double::valueOf)).orElse(0.0);
        double average = avgList.stream().mapToDouble(Double::valueOf).average().orElse(0.0);

        return new StatisticsDto(maximal, minimal, average);
    }

    /**
     * Clear the caches,used to make tests execution consistent
     */
    public void clearStatistics() {
        statisticBuckets.clear();
    }
}
