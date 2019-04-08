/**
 * Project: spring-batch
 * File: XmlItemReaderDemo.java
 * Package: com.hanson.itemreaderxml
 * Date: 2019年4月8日 下午9:26:02
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderxml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午9:26:02
 */
@Configuration
public class XmlItemReaderDemo {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job xmlItemReaderJob() {
		return jobBuilderFactory.get("xmlItemReaderJob")
				.start(xmlItemReaderStep())
				.build();
	}

	@Bean
	public Step xmlItemReaderStep() {
		return stepBuilderFactory.get("xmlItemReaderStep")
				.<Car,Car>chunk(2)
				.reader(xmlFileReader())
				.writer(xmlFileWriter())
				.build();
	}

	@Bean
	public ItemWriter<Car> xmlFileWriter() {
		return new ItemWriter<Car>() {
			@Override
			public void write(List<? extends Car> items) throws Exception {
				for (Car car : items) {
					System.out.println(car);
				}
			}
		};
	}

	@Bean
	public ItemReader<Car> xmlFileReader() {
		StaxEventItemReader<Car> reader = new StaxEventItemReader<Car>();
		// 指定xml文件位置
		reader.setResource(new ClassPathResource("XmlFile.xml"));
		
		// 指定要处理的根节点
		reader.setFragmentRootElementName("car");
		// xml转为对象
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<String,Class> map = new HashMap<>();
		map.put("car", Car.class);
		marshaller.setAliases(map);
		reader.setUnmarshaller(marshaller);
		return reader;
	}
	
}
