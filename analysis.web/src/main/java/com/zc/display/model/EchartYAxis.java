package com.zc.display.model;

import java.util.ArrayList;
import java.util.List;

/**
 * echart 纵坐标对应类
 * @author zhaichen
 *
 */
public class EchartYAxis {
	
	private String name;
	
	private String type = "value";
	
	private Integer max;
	
	List<Object> boundaryGap;

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
	
	
	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public void addBoundaryGap(Object data){
		if(boundaryGap == null){
			boundaryGap = new ArrayList<Object>();
		}
		boundaryGap.add(data);
	}
	
}
