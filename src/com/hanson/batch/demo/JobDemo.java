/**
 * Project: spring-batch
 * File: JobDemo.java
 * Package: com.hanson.job
 * Date: 2019年3月27日 下午8:04:53
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
 * @time 2019年3月27日 下午8:04:53
 */
@Configuration
@EnableBatchProcessing
public class JobDemo {

	// 注入创建任务对象的对象
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	// 任务的执行有Sep决定
	// 注入创建Step对象的对象
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	// 创建任务对象
	@Bean
	public Job demoJob() {
		return jobBuilderFactory.get("demoJob")
//				.start(setp1())
//				.next(step2())
//				.next(step3())
//				.end()
//				.build();
				.start(step1())
				.on("COMPLETED").to(step2())
				.from(step2()).on("COMPLETED").to(step3())//fail() stopAndRestart()
				.from(step3()).end()
				.build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("step3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("step2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("step1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
}
