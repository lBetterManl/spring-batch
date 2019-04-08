/**
 * Project: spring-batch
 * File: SpringBatchApplication.java
 * Package: com.hanson.batch
 * Date: 2019年3月27日 下午7:12:27
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderdb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年3月27日 下午7:12:27
 */
@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}
}
