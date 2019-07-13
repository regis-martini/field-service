package com.field.statistics.fieldservice.repositories;

import com.field.statistics.fieldservice.entities.ConditionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionHistoryRepository extends JpaRepository<ConditionHistory, Integer> {
}
