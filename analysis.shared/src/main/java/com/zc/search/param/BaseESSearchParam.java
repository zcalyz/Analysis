package com.zc.search.param;

import java.io.Serializable;

public abstract class BaseESSearchParam implements Serializable{
	
	private static final long serialVersionUID = -822666494853562714L;

	protected String[] indexs;
	
	protected String[] types;
	
	/**
	 * 查询的数据量
	 */
	protected int size;

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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
