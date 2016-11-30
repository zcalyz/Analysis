package com.zc.analysis.model;

import java.io.Serializable;

/**
 * 
 * 用于代表一个事事务
 * @author zhaichen
 *
 */
public class TransactionModel implements Serializable{
	
	private static final long serialVersionUID = -5725189651250905742L;
	/**
	 * 事务的名称
	 */
	private String name;
	/**
	 * 事务的到达率
	 */
	private Double arriveRate;
	
	/**
	 * 服务时间，单位为纳秒，不包含排队
	 */
	private Long serviceTime;
	
	/**
	 * 驻留时间,单位为秒：服务+排队时间
	 * @return
	 */
	private Double residenceTime;
	
	/**
	 * 事务吞吐量
	 * @return
	 */
	private Double throughput;
	
	/**
	 * 该事务占有的资源利用率
	 */
	private Double utilazation;
	
	/**
	 * 队列长度
	 */
	private Double queueLenth;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getArriveRate() {
		return arriveRate;
	}

	public void setArriveRate(Double arriveRate) {
		this.arriveRate = arriveRate;
	}

	public Long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Double getResidenceTime() {
		return residenceTime;
	}

	public void setResidenceTime(Double residenceTime) {
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

	public Double getQueueLenth() {
		return queueLenth;
	}

	public void setQueueLenth(Double queueLenth) {
		this.queueLenth = queueLenth;
	}
	
}
