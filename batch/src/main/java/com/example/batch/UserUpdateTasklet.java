package com.example.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateTasklet implements Tasklet {

    private final JdbcTemplate jdbcTemplate;

    public UserUpdateTasklet (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String updateQuery = "UPDATE users SET age = age + 1";
        int updateCount = jdbcTemplate.update(updateQuery);

        System.out.println("updateCount = " + updateCount);
        return RepeatStatus.FINISHED;
    }
}
