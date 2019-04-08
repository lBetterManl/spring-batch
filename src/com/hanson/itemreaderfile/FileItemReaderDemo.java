/**
 * Project: spring-batch
 * File: FileItemReaderDemo.java
 * Package: com.hanson.itemreaderfile
 * Date: 2019年4月8日 下午8:59:00
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderfile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午8:59:00
 */
@Configuration
public class FileItemReaderDemo {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private FlatFileWriter flatFileWriter;
	
	@Bean
	public Job fileItemReaderJob() {
		return jobBuilderFactory.get("fileItemReaderJob")
				.start(fileItemReaderStep())
				.build();
	}

	@Bean
	public Step fileItemReaderStep() {
		return stepBuilderFactory.get("fileItemReaderStep")
				.<Student,Student>chunk(2)
				.reader(flatFileReader())
				.writer(flatFileWriter)
				.build();
	}

	@Bean
	public FlatFileItemReader<Student> flatFileReader() {
		FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
		// 制定文件位置
		reader.setResource(new ClassPathResource("FlatFile.txt"));
		// 跳过第1行
		reader.setLinesToSkip(1);
	
		// 解析数据
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		// 指明字段
		tokenizer.setNames(new String[] {"name","age"});
		// 解析出的一行数据映射为对象
		DefaultLineMapper<Student> mapper = new DefaultLineMapper<Student>();
		mapper.setLineTokenizer(tokenizer);
		mapper.setFieldSetMapper(new FieldSetMapper<Student>() {
			@Override
			public Student mapFieldSet(FieldSet fieldSet) throws BindException {
				Student student = new Student();
				student.setName(fieldSet.readString("name"));
				student.setAge(fieldSet.readInt("age"));
				return student;
			}
		});
		mapper.afterPropertiesSet();
		reader.setLineMapper(mapper);
		return reader;
	}

}
