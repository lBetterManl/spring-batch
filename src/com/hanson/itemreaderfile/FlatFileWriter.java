/**
 * Project: spring-batch
 * File: FlatFileWriter.java
 * Package: com.hanson.itemreaderfile
 * Date: 2019年4月8日 下午9:17:05
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderfile;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午9:17:05
 */
@Component
public class FlatFileWriter implements ItemWriter<Student> {

	@Override
	public void write(List<? extends Student> items) throws Exception {
		for (Student student : items) {
			System.out.println(student);
		}
	}

}
