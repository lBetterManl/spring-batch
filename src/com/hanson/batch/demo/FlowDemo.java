/**
 * Project: spring-batch
 * File: FlowDemo.java
 * Package: com.hanson.job
 * Date: 2019年3月27日 下午8:23:16
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.batch.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
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
 * @time 2019年3月27日 下午8:23:16
 */
@Configuration
@EnableBatchProcessing
public class FlowDemo {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	// 创建Job对象
	@Bean
	public Job flowDemoJob() {
		return jobBuilderFactory.get("flowDemoJob")
				.start(demoFlow())
				.next(flowStep3())
				.end()
				.build();
	}
	
	// 创建Flow对象，指明Flow对象包含哪些step
	@Bean
	public Flow demoFlow() {
		return new FlowBuilder<Flow>("demoFlow")
				.start(flowStep1())
				.next(flowStep2())
				.build();				
	}
	
	/**
	 * TODO 
	 * @author Hanson
	 * @return
	 */
	private Step flowStep3() {
		return stepBuilderFactory.get("flowStep3").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("flowStep3");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
	public Step flowStep2() {
		return stepBuilderFactory.get("flowStep2").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("flowStep2");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step flowStep1() {
		return stepBuilderFactory.get("flowStep1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("flowStep1");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

}
