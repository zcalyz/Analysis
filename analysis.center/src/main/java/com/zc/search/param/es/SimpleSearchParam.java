package com.zc.search.param.es;

import java.util.Date;

import com.zc.search.param.BaseESSearchParam;

public class SimpleSearchParam extends BaseESSearchParam{
	
	private Date startTime;
	
	private Date endTime;
	
	private String dstType;

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

	public String getDstType() {
		return dstType;
	}

	public void setDstType(String dstType) {
		this.dstType = dstType;
	}
	
}
