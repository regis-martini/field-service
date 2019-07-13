package com.field.statistics.fieldservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.field.statistics.fieldservice.entities.ConditionHistory;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ConditionHistoryRepositoryTest {

    @Autowired
    private ConditionHistoryRepository repository;

    @Test
    public void saveCondition() {
        ConditionHistory saved = repository.save(new ConditionHistory(0.68, LocalDateTime.now()));

        assertThat(repository.getOne(saved.getId())).isEqualTo(saved);
    }
}