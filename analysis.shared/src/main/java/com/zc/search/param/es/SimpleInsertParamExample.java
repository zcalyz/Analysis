package com.zc.search.param.es;

import java.util.Date;

import com.zc.search.param.BaseESInsertParam;

public class SimpleInsertParamExample extends BaseESInsertParam{
	private String name;

	private String address;

	private int age;
	
	/**
	 * 创建时间
	 */
	private Date creatTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
}
