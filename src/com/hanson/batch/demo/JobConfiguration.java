/**
 * Project: spring-batch
 * File: JobConfiguration.java
 * Package: com.hanson.job
 * Date: 2019年3月27日 下午7:19:52
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
 * @time 2019年3月27日 下午7:19:52
 */
@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	// 注入创建任务对象的对象
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	// 任务的执行有Sep决定
	// 注入创建Step对象的对象
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// 创建任务对象
	@Bean
	public Job helloJob() {
		return jobBuilderFactory.get("helloJob").start(setp1()).build();
	}

	@Bean
	public Step setp1() {
		return stepBuilderFactory.get("setp1").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("Hello World!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

}
