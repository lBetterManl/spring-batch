/**
 * Project: spring-batch
 * File: User.java
 * Package: com.hanson.itemreaderfile
 * Date: 2019年4月8日 下午8:54:28
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderfile;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午8:54:28
 */
public class Student {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Student() {
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
}
