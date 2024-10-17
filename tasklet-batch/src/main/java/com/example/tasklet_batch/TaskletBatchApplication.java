package com.example.tasklet_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class TaskletBatchApplication implements CommandLineRunner {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job updateUserJob;

	public static void main(String[] args) {
		SpringApplication.run(TaskletBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// jobLauncher.run(updateUserJob, new JobParameters());
		jobLauncher.run(updateUserJob, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
	}
}
