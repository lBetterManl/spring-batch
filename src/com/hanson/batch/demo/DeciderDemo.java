/**
 * Project: spring-batch
 * File: Decider.java
 * Package: com.hanson.batch.demo
 * Date: 2019年3月27日 下午9:04:34
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.batch.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
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
 * @time 2019年3月27日 下午9:04:34
 */
@Configuration
@EnableBatchProcessing
public class DeciderDemo {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	// 创建任务(由决策其决定step执行)
	@Bean
	public Job deciderDemoJob() {
		return jobBuilderFactory.get("deciderDemoJob")
				.start(deciderDemoStep1())
				.next(demoDecider())
				.from(demoDecider()).on("even").to(deciderDemoStep2())
				.from(demoDecider()).on("odd").to(deciderDemoStep3())
				.from(deciderDemoStep3()).on("*").to(demoDecider())//无论step3返回什么，都返回决策器
				.end()
				.build();
	}
	
	// 决策器
	@Bean
	public JobExecutionDecider demoDecider() {
		return new MyDecider();
	}
	
	@Bean
	public Step deciderDemoStep1() {
		return stepBuilderFactory.get("deciderDemoStep1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
						System.out.println("deciderDemoStep1");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	
	
	@Bean
	public Step deciderDemoStep2() {
		return stepBuilderFactory.get("deciderDemoStep2")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
						System.out.println("even");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	
	@Bean
	public Step deciderDemoStep3() {
		return stepBuilderFactory.get("deciderDemoStep3")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
						System.out.println("odd");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}
	

}
