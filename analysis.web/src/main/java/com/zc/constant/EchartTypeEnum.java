package com.zc.constant;

public enum EchartTypeEnum {
	LINE("line");
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;
	
	private EchartTypeEnum(String type){
		this.type = type;
	}
	
	
}
