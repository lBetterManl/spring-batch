/**
 * Project: spring-batch
 * File: SpringBatchApplication.java
 * Package: com.hanson.batch
 * Date: 2019年3月27日 下午7:12:27
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderxml;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Description: 从XML文件读取数据输出到控制台</p>
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
