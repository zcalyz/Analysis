package com.zc.constant;

/**
 * 使用的ES的index和对应type
 * @author zhaichen
 *
 */
public enum EsInfo {
	REMOTE_JEESHOP_RELATION("Apm", "apm2.0-10.82.81.180-relationship-1", "RelationShip"),
	LOCAL_DEFAULT("local-elasticsearch", "db_index_date","table_type"),
	REMOTE_SERVICE_RELATION("Apm", "apm2.0-115.236.12.194-relationship-1", "RelationShip");
	
	private String clusterName;
	
	private String index;
	
	private String type;
	
	private EsInfo(String clusterName, String index, String type){
		this.index = index;
		this.type = type;
		this.clusterName = clusterName;
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

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	
}
