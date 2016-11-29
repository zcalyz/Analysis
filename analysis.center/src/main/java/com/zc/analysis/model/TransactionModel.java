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
	private double lambda;
	
	/**
	 * 服务时间
	 */
	private double serviceTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}
	
}
