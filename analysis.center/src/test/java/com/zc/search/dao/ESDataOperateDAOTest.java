package com.zc.search.dao;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.zc.search.dao.impl.SimpleESDataOperateDAOImpl;
import com.zc.search.param.es.SimpleInsertParam;
import com.zc.search.param.es.SimpleSearchParam;

public class ESDataOperateDAOTest {

	public static final String DEFAULT_INDXE = "db_index_date";

	public static final String DEFAULT_TYPE = "table_type";
	
	@Test
	public void insertTest(){
		SimpleESDataOperateDAOImpl esReader = new SimpleESDataOperateDAOImpl();
		SimpleInsertParam insertParam = new SimpleInsertParam();
		// insert test
		for(int i = 2; i < 30; i++){
			insertParam.setName("ming");
			insertParam.setAge(i);
			// 创建指定日期的Date
			LocalDateTime dateTime = new LocalDateTime(2017, 11, i, 0, 0);
			insertParam.setCreatTime(dateTime.toDate());
			
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
		searchParam.setName(new String[]{"ming"});
		searchParam.setStartAge(10);
		searchParam.setEndAge(20);
		
		LocalDateTime startDate = new LocalDateTime(2017, 11, 18, 0, 0);
		LocalDateTime endDate = new LocalDateTime(2017, 11, 23, 0, 0);
		
		searchParam.setStartTime(startDate.toDate());
		searchParam.setEndTime(endDate.toDate());
	}
	
}
