package com.zc.display.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * echart横坐标对应类
 * @author zhaichen
 *
 */
public class EchartXAxis {
	
	private String name = "到达率/s";
	
	private String type = "category";
	
	private String min;
	
	private String max;
	
	/**
	 * 折线距坐标轴两侧是否有间距
	 */
	private boolean boundaryGap = false;
	
	/**
	 * 横坐标轴数据
	 */
	private List<Object> data;

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

	
	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * 增加数据
	 * @param inputData
	 */
	public void addAxisData(Object inputData){
		if(data == null){
			data = new ArrayList<Object>();
		}
		data.add(inputData);
	}
	
}
