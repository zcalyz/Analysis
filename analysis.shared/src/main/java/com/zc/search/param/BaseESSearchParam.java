package com.zc.search.param;

public abstract class BaseESSearchParam {
	
	protected String[] indexs;
	
	protected String[] types;

	public String[] getIndexs() {
		return indexs;
	}

	public void setIndexs(String[] indexs) {
		this.indexs = indexs;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}
	
}
