package com.zc.display.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 以节点为单位的分析结果，用于echart显示
 * @author zhaichen
 *
 */
public class StationEchartVO {
	
	/**
	 * 节点名称
	 */
	private String statioName;
	
	private EchartLegend legend;
	
	private EchartXAxis xAxis;
	
	private EchartYAxis yAxis;
	
	private EchartTitle title;
	
	/**
	 * 存储要展示的性能数据
	 */
	private List<EchartSeries> peformanceDataSeries;

	public String getStatioName() {
		return statioName;
	}

	public void setStatioName(String statioName) {
		this.statioName = statioName;
	}

	public EchartLegend getLegend() {
		return legend;
	}

	public void setLegend(EchartLegend legend) {
		this.legend = legend;
	}

	public EchartXAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(EchartXAxis xAxis) {
		this.xAxis = xAxis;
	}

	public List<EchartSeries> getPeformanceDataSeries() {
		return peformanceDataSeries;
	}

	public void setPeformanceDataSeries(List<EchartSeries> peformanceDataSeries) {
		this.peformanceDataSeries = peformanceDataSeries;
	}
	
	public EchartYAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(EchartYAxis yAxis) {
		this.yAxis = yAxis;
	}

	public EchartTitle getTitle() {
		return title;
	}

	public void setTitle(EchartTitle title) {
		this.title = title;
	}

	public void addPeformanceDataSeries(EchartSeries series){
		if(peformanceDataSeries == null){
			peformanceDataSeries = new ArrayList<EchartSeries>();
		}
		peformanceDataSeries.add(series);
	}
}
