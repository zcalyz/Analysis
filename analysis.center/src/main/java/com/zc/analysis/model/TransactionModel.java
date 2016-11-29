package com.zc.analysis.model;

/**
 * 
 * 用于代表一个事事务
 * @author zhaichen
 *
 */
public class TransactionModel {
	
	/**
	 * 事务的名称
	 */
	private String name;
	/**
	 * 事务的到达率
	 */
	private double arriveRate;
	
	/**
	 * 服务时间
	 */
	private Long serviceTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getArriveRate() {
		return arriveRate;
	}

	public void setArriveRate(double arriveRate) {
		this.arriveRate = arriveRate;
	}

	public Long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Long serviceTime) {
		this.serviceTime = serviceTime;
	}
	
}
