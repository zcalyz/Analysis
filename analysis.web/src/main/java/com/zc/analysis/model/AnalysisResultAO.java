package com.zc.analysis.model;

import java.util.LinkedList;

/**
 * 分析结果模型，用于前端表格展示及简单统计
 * @author zhaichen
 *
 */
public class AnalysisResultAO {
	
	/**
	 * 节点名称
	 */
	private String stationName;
	
	/**
	 * 所有事物的名称
	 */
	private LinkedList<String> transactionNames;
	
	/**
	 * 驻留时间
	 */
	private LinkedList<String> residenceTimes;
	
	/**
	 * 队列长度
	 */
	private LinkedList<String> queueLengths;
	
	private LinkedList<String> utilizations;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public LinkedList<String> getTransactionNames() {
		if(transactionNames == null){
			transactionNames = new LinkedList<String>();
		}
		return transactionNames;
	}

	public void setTransactionNames(LinkedList<String> transactionNames) {
		this.transactionNames = transactionNames;
	}

	public LinkedList<String> getResidenceTimes() {
		if(residenceTimes == null){
			residenceTimes = new LinkedList<String>();
		}
		return residenceTimes;
	}

	public void setResidenceTimes(LinkedList<String> residenceTimes) {
		this.residenceTimes = residenceTimes;
	}

	public LinkedList<String> getQueueLengths() {
		if(queueLengths == null){
			queueLengths = new LinkedList<String>();
		}
		return queueLengths;
	}

	public void setQueueLengths(LinkedList<String> queueLengths) {
		this.queueLengths = queueLengths;
	}

	public LinkedList<String> getUtilizations() {
		if(utilizations == null){
			utilizations = new LinkedList<String>();
		}
		return utilizations;
	}

	public void setUtilizations(LinkedList<String> utilizations) {
		this.utilizations = utilizations;
	} 
	
//	下面是用于统计的方法
	
	/**
	 * 获取驻留时间的总和
	 * @return
	 */
	public Double getTotalResidenceTime(){
		Double totalTime = 0D;
		for(String residenceTime : residenceTimes){
			Double time = Double.valueOf(residenceTime);
			totalTime = totalTime + time;
		}
		return totalTime;
	}
	
}
