package com.zc.constant;

/**
 * 使用的ES的index和对应type
 * @author zhaichen
 *
 */
public enum EsIndex {
	REMOTE_RELATION("apm2.0-10.82.81.180-relationship-1", "RelationShip"),
	LOCAL_DEFAULT("db_index_date","table_type");
	
	private String index;
	
	private String type;
	
	private EsIndex(String index, String type){
		this.index = index;
		this.type = type;
	}

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
