package com.zc.analysis.model;

import java.util.List;

public class AnalysisResult {
	private List<Double> responseTimes;
	
	private List<Double> throughouts;
	
	private List<Double> arriveRates;
	
	private double[] utilizations;
	
	private String stationName;
	
	/**
	 * 获取总的响应时间
	 * @return
	 */
	public double getTotalRespTime(){
		
		return 0;
	}
	
	/**
	 * 获取总的吞吐量
	 * @return
	 */
	public double getTotalThrougthout(){
		
		return 0;
	}
	
	/**
	 * 获取总的利用率
	 */
	public double getTotalUtilization(){
		
		return 0;
	}
	
	/**
	 * 获取总的到达率
	 */
	public double getTotalArriveRates(){
		
		return 0;
	}

	public List<Double> getResponseTimes() {
		return responseTimes;
	}

	public void setResponseTimes(List<Double> responseTimes) {
		this.responseTimes = responseTimes;
	}

	public List<Double> getThroughouts() {
		return throughouts;
	}

	public void setThroughouts(List<Double> throughouts) {
		this.throughouts = throughouts;
	}

	public List<Double> getArriveRates() {
		return arriveRates;
	}

	public void setArriveRates(List<Double> arriveRates) {
		this.arriveRates = arriveRates;
	}

	public double[] getUtilizations() {
		return utilizations;
	}

	public void setUtilizations(double[] utilizations) {
		this.utilizations = utilizations;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
}
