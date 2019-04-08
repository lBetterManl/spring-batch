/**
 * Project: spring-batch
 * File: DbJdbcWriter.java
 * Package: com.hanson.itemreaderdb
 * Date: 2019年4月8日 下午8:46:42
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderdb;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午8:46:42
 */
@Component("dbJdbcWriter")
public class DbJdbcWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		for (User item : items) {
			System.out.println(item);
		}
	}

}
