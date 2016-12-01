package com.zc.display.model;

import java.util.List;

import com.zc.constant.EchartTypeEnum;

/**
 * echart 折线图纵坐标对应类
 * @author zhaichen
 *
 */
public class EchartSeries {
	
	/**
	 * 曲线名称
	 */
	private String name;
	
	/**
	 * 图形类型，默认为折现图
	 */
	private String type = EchartTypeEnum.LINE.getType();
	
	/**
	 * 图形是否平滑
	 */
	private boolean smooth = true;
	
	/**
	 * 要显示的数据
	 */
	private List<Object> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSmooth() {
		return smooth;
	}

	public void setSmooth(boolean smooth) {
		this.smooth = smooth;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}
	
}
