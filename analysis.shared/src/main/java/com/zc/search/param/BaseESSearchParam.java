package com.zc.search.param;

import java.io.Serializable;

public abstract class BaseESSearchParam implements Serializable{
	
	private static final long serialVersionUID = -822666494853562714L;

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
