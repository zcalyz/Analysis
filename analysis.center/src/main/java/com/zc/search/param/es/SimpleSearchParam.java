package com.zc.search.param.es;

import com.zc.search.param.BaseESSearchParam;

public class SimpleSearchParam extends BaseESSearchParam{
	private String[] name;

	private String address;
	
	/**
	 * 用于分词查询
	 */
	private String text;

	private int startAge;
	
	private int endAge;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
}
