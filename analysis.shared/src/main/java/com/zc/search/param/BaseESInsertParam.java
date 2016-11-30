package com.zc.search.param;

import java.io.Serializable;

public abstract class BaseESInsertParam implements Serializable{
	
	private static final long serialVersionUID = -3523899168539662620L;

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
