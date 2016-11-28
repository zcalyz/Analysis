package com.zc.search.dao;

import org.elasticsearch.search.SearchHits;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.zc.constant.EsIndex;
import com.zc.search.dao.impl.SimpleESDataOperateDAOImpl;
import com.zc.search.param.es.SimpleInsertParamExample;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.search.param.es.SimpleSearchParamExample;
import com.zc.util.PrintSearchHitsResultUtil;

public class ESDataOperateDAOTest {

	public static final String DEFAULT_INDXE = "db_index_date";

	public static final String DEFAULT_TYPE = "table_type";
	
	@Test
	public void insertTest(){
		SimpleESDataOperateDAOImpl esReader = new SimpleESDataOperateDAOImpl();
		SimpleInsertParamExample insertParam = new SimpleInsertParamExample();
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

	public static void initInsertParam(SimpleInsertParamExample insertParam) {
		insertParam.setIndex(EsIndex.LOCAL_DEFAULT.getIndex());
		insertParam.setType(EsIndex.LOCAL_DEFAULT.getType());
		insertParam.setAddress("street NO.2");
	}
	
	@Test
	public void searchTest(){
		SimpleESDataOperateDAOImpl esReader = new SimpleESDataOperateDAOImpl();
		
		SimpleSearchParam simpleSearchParam = new SimpleSearchParam();
//		SimpleSearchParamExample searchParam = new SimpleSearchParamExample();
		// search test
//		initSearchParamForExample(searchParam);
		initSimpleSearchParam(simpleSearchParam);
		
		SearchHits searchData = esReader.searchData(simpleSearchParam);
		PrintSearchHitsResultUtil.printWholeLine(searchData);
	}
	
	public static void initSimpleSearchParam(SimpleSearchParam searchParam) {
		searchParam.setIndexs(new String[] { EsIndex.REMOTE_RELATION.getIndex() });
		searchParam.setTypes(new String[] {EsIndex.REMOTE_RELATION.getType()});
		
		searchParam.setDstType("3");
		
		LocalDateTime startTime = new LocalDateTime(2016, 7, 22	, 15, 0);
		LocalDateTime endTime = new LocalDateTime(2016, 7, 23, 0, 0);
		searchParam.setStartTime(startTime.toDate());
		searchParam.setEndTime(endTime.toDate());
	}
	
	/**
	 * 语法熟悉时使用的查询参数
	 * @param searchParam
	 */
	public static void initSearchParamForExample(SimpleSearchParamExample searchParam) {
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
