/**
 * Project: spring-batch
 * File: DbJobDemo.java
 * Package: com.hanson.itemreaderdb
 * Date: 2019年4月8日 下午8:18:14
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午8:18:14
 */
@Configuration
public class DbJobDemo {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("dbJdbcWriter")
	private ItemWriter<User> dbJdbcWriter;
	
	@Bean
	public Job itemReaderDbJob() {
		return jobBuilderFactory.get("itemReaderDbJob")
				.start(itemReaderDbStep())
				.build();
	}

	@Bean
	public Step itemReaderDbStep() {
		return stepBuilderFactory.get("itemReaderDbStep")
				.<User,User>chunk(2)	//每2条提交一次处理
				.reader(dbJdbcReader())
				.writer(dbJdbcWriter)
				.build();
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<User> dbJdbcReader() {
		// 分页读取数据
		JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<User>();
		// 注入数据源
		reader.setDataSource(dataSource);
		// 分页读取每次2条
		reader.setFetchSize(2);
		// 把读取出来的数据转换为对象
		reader.setRowMapper(new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setNickName(rs.getString("nickName"));
				user.setSex(rs.getString("sex"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		});
		// 指定sql语句
		MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
		provider.setSelectClause("userName,password,nickName,sex,email");
		provider.setFromClause(" from tb_user ");
		// 指定排序
		Map<String, Order> sort = new HashMap<>(1);
		sort.put("userName", Order.ASCENDING);
		provider.setSortKeys(sort);
		// 赋予provider
		reader.setQueryProvider(provider);
		return reader;
	}
	
	

}
