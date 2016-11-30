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
	 * 服务时间，不包含排队
	 */
	private Long serviceTime;
	
	/**
	 * 驻留时间：服务+排队时间
	 * @return
	 */
	private Long residenceTime;
	
	/**
	 * 事务吞吐量
	 * @return
	 */
	private Double throughput;
	
	/**
	 * 该事务占有的资源利用率
	 */
	private Double utilazation;
	
	

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

	public Long getResidenceTime() {
		return residenceTime;
	}

	public void setResidenceTime(Long residenceTime) {
		this.residenceTime = residenceTime;
	}

	public Double getThroughput() {
		return throughput;
	}

	public void setThroughput(Double throughput) {
		this.throughput = throughput;
	}

	public Double getUtilazation() {
		return utilazation;
	}

	public void setUtilazation(Double utilazation) {
		this.utilazation = utilazation;
	}
	
}
