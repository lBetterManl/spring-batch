/**
 * Project: spring-batch
 * File: ChildJob1.java
 * Package: com.hanson.batch.demo
 * Date: 2019年3月27日 下午10:46:38
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.batch.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年3月27日 下午10:46:38
 */
@Configuration
@EnableBatchProcessing
public class ChildJob1 {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step childJob1Step1() {
		return stepBuilderFactory.get("childJob1Step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
						System.out.println("childJob1Step1");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	
	@Bean
	public Job childJobOne() {
		return jobBuilderFactory.get("childJobOne")
				.start(childJob1Step1())
				.build();
	}

}
