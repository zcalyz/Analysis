package com.zc.search.dao;

import org.elasticsearch.search.SearchHits;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.zc.constant.EsInfo;
import com.zc.search.dao.impl.EsDataReadDAOImpl;
import com.zc.search.param.es.SimpleInsertParamExample;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.search.param.es.SimpleSearchParamExample;
import com.zc.util.PrintSearchHitsResultUtil;

public class ESDataOperateDAOTest {

	public static final String DEFAULT_INDXE = "db_index_date";

	public static final String DEFAULT_TYPE = "table_type";
	
	@Test
	public void insertTest(){
		EsDataReadDAOImpl esReader = new EsDataReadDAOImpl();
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
		insertParam.setIndex(EsInfo.LOCAL_DEFAULT.getIndex());
		insertParam.setType(EsInfo.LOCAL_DEFAULT.getType());
		insertParam.setAddress("street NO.2");
	}
	
	@Test
	public void searchTest(){
		EsDataReadDAOImpl esReader = new EsDataReadDAOImpl();
		
		SimpleSearchParam simpleSearchParam = new SimpleSearchParam();
//		SimpleSearchParamExample searchParam = new SimpleSearchParamExample();
		// search test
//		initSearchParamForExample(searchParam);
		initSimpleSearchParam(simpleSearchParam);
		
		SearchHits searchData = esReader.searchData(simpleSearchParam);
		PrintSearchHitsResultUtil.printForRelation(searchData);
	}
	
	public static void initSimpleSearchParam(SimpleSearchParam searchParam) {
		searchParam.setIndexs(new String[] { EsInfo.REMOTE_SERVICE_RELATION.getIndex() });
		searchParam.setTypes(new String[] {EsInfo.REMOTE_SERVICE_RELATION.getType()});
		
		searchParam.setDstType("2");
		
		LocalDateTime startTime = new LocalDateTime(2016, 11, 29, 10, 02);
		LocalDateTime endTime = new LocalDateTime(2016, 11, 29, 10, 05);
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
