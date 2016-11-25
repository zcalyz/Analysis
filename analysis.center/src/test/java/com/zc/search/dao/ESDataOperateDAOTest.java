package com.zc.search.dao;

import org.junit.Test;

import com.zc.search.dao.impl.SimpleESDataOperateDAOImpl;
import com.zc.search.param.es.SimpleInsertParam;
import com.zc.search.param.es.SimpleSearchParam;

public class ESDataOperateDAOTest {

	public static final String DEFAULT_INDXE = "db_index";

	public static final String DEFAULT_TYPE = "table_type";
	
	@Test
	public void insertTest(){
		SimpleESDataOperateDAOImpl esReader = new SimpleESDataOperateDAOImpl();
		SimpleInsertParam insertParam = new SimpleInsertParam();
		// insert test
		for(int i = 5; i < 50; i++){
			insertParam.setName("kate");
			insertParam.setAge(i);
			initInsertParam(insertParam);
			esReader.insert(insertParam);
		}
	}

	public static void initInsertParam(SimpleInsertParam insertParam) {
		insertParam.setIndex(DEFAULT_INDXE);
		insertParam.setType(DEFAULT_TYPE);
		insertParam.setAddress("street NO.2");
	}
	
	@Test
	public void searchTest(){
		SimpleESDataOperateDAOImpl esReader = new SimpleESDataOperateDAOImpl();
		SimpleSearchParam searchParam = new SimpleSearchParam();
		// search test
		initSearchParam(searchParam);
		esReader.searchData(searchParam);
	}

	public static void initSearchParam(SimpleSearchParam searchParam) {
		searchParam.setIndexs(new String[] {DEFAULT_INDXE});
		searchParam.setTypes(new String[]{DEFAULT_TYPE});
		searchParam.setName(new String[]{"kate"});
		searchParam.setStartAge(10);
		searchParam.setEndAge(20);
	}
}
