package com.zc.display.model;

/**
 * echart 纵坐标对应类
 * @author zhaichen
 *
 */
public class EchartYAxis {
	
	private String name;
	
	private String type = "value";

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
	
}
