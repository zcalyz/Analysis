package com.zc.search.param.es;

import java.util.Date;

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
	
	/**
	 *  查询起始时间
	 */
	private Date startTime;
	
	/**
	 * 查询结束时间
	 */
	private Date endTime;


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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
