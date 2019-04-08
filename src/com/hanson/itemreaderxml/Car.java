/**
 * Project: spring-batch
 * File: Car.java
 * Package: com.hanson.itemreaderxml
 * Date: 2019年4月8日 下午9:24:35
 * Copyright (c) 2019, hanson.yan@qq.com All Rights Reserved.
 */
package com.hanson.itemreaderxml;

/**
 * <p>Description: TODO</p>
 * @author Hanson
 * @since  JDK 1.8
 * @time 2019年4月8日 下午9:24:35
 */
public class Car {
	
	private String brand;
	private String color;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Car() {
		super();
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + "]";
	}
	
}
