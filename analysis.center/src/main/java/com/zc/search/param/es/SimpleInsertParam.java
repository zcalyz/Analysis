package com.zc.search.param.es;

import com.zc.search.param.BaseESInsertParam;

public class SimpleInsertParam extends BaseESInsertParam{
	private String name;

	private String address;

	private int age;

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
	
	
}
