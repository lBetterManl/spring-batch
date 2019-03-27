/**
 * Project: spring-batch
 * File: MyDecider.java
 * Package: com.hanson.batch.demo
 * Date: 2019年3月27日 下午9:45:35
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.batch.demo;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * <p>Description: 决策器</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年3月27日 下午9:45:35
 */
public class MyDecider implements JobExecutionDecider {
	
	private int count;

	@Override
	public FlowExecutionStatus decide(JobExecution arg0, StepExecution arg1) {
		count++;
		if (count%2==0) {
			return new FlowExecutionStatus("even");
		}else {
			return new FlowExecutionStatus("odd");
		}
	}

}
