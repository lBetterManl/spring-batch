/**
 * Project: spring-batch
 * File: User.java
 * Package: com.hanson.itemreaderdb
 * Date: 2019年4月8日 下午8:23:50
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderdb;

import org.apache.logging.log4j.util.StringBuilderFormattable;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午8:23:50
 */
public class User {
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	private String sex;
	
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
		
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", nickName=" + nickName + ", sex=" + sex
				+ ", email=" + email + "]";
	}
	
}
