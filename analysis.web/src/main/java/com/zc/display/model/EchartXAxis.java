package com.zc.display.model;

import java.util.List;

/**
 * echart横坐标对应类
 * @author zhaichen
 *
 */
public class EchartXAxis {
	
	private String type = "category";
	
	/**
	 * 折线距坐标轴两侧是否有间距
	 */
	private boolean boundaryGap = false;
	
	/**
	 * 横坐标轴数据
	 */
	private List<String> data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBoundaryGap() {
		return boundaryGap;
	}

	public void setBoundaryGap(boolean boundaryGap) {
		this.boundaryGap = boundaryGap;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
	
}
