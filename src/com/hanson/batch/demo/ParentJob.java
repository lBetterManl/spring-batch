/**
 * Project: spring-batch
 * File: ParentJob.java
 * Package: com.hanson.batch.demo
 * Date: 2019年3月27日 下午10:51:02
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.batch.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年3月27日 下午10:51:02
 */
@Configuration
@EnableBatchProcessing
public class ParentJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private Job childJobOne;

	@Autowired
	private Job childJobTwo;// 启动对象

	@Autowired
	private JobLauncher launcher;

	@Bean
	public Job parentDemoJob(JobRepository repository,PlatformTransactionManager transactionManager) {
		return jobBuilderFactory.get("parentDemoJob")
				.start(childJob1(repository,transactionManager))
				.next(childJob2(repository,transactionManager))
				.build();
	}

	// 返回的是Job类型的Step，特殊的Step
	public Step childJob2(JobRepository repository, PlatformTransactionManager transactionManager) {
		return new JobStepBuilder(new StepBuilder("childJob2"))
				.job(childJobTwo)
				.launcher(launcher)// 使用父Job的启动对象
				.repository(repository)
				.transactionManager(transactionManager)
				.build();
	}

	// 返回的是Job类型的Step，特殊的Step
	public Step childJob1(JobRepository repository, PlatformTransactionManager transactionManager) {
		return new JobStepBuilder(new StepBuilder("childJob1"))
				.job(childJobOne)
				.launcher(launcher)// 使用父Job的启动对象
				.repository(repository)
				.transactionManager(transactionManager)
				.build();
	}

}
