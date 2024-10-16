package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;


import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final UserUpdateTasklet userUpdateTasklet;
    private final PlatformTransactionManager platformTransactionManager;

    public BatchConfig(UserUpdateTasklet userUpdateTasklet, PlatformTransactionManager platformTransactionManager) {
        this.userUpdateTasklet = userUpdateTasklet;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Job updateUserJob(JobRepository jobRepository) {
        return new JobBuilder("updateUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(updateUserStep(jobRepository, platformTransactionManager))
                .build();
    }

    @Bean
    public Step updateUserStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("updateUserStep", jobRepository)
                .tasklet(userUpdateTasklet, platformTransactionManager)
                .build();
    }

}
