package com.zc.search.param;

public abstract class BaseESInsertParam {
	
	protected String index;
	
	protected String type;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
