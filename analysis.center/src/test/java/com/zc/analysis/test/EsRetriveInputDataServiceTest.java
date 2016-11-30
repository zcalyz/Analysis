package com.zc.analysis.test;

import java.util.List;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.zc.analysis.model.Station;
import com.zc.analysis.service.impl.EsRetriveInputDataService;
import com.zc.constant.EsInfo;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.util.PrintSearchHitsResultUtil;

public class EsRetriveInputDataServiceTest {
	
	@Test
	public void analysisTest(){
		EsRetriveInputDataService inputDataService = new EsRetriveInputDataService();
		
		SimpleSearchParam searchParam = initSimpleSearchParam();
		List<Station> inputData = inputDataService.retriveInputData(searchParam);
		
		PrintSearchHitsResultUtil.printForTableFormat(inputData);
	}
	
	public static SimpleSearchParam initSimpleSearchParam() {
		SimpleSearchParam searchParam = new SimpleSearchParam();
		searchParam.setIndexs(new String[] { EsInfo.REMOTE_SERVICE_RELATION.getIndex() });
		searchParam.setTypes(new String[] {EsInfo.REMOTE_SERVICE_RELATION.getType()});;
		
		LocalDateTime startTime = new LocalDateTime(2016, 11, 29, 8, 2);
		LocalDateTime endTime = new LocalDateTime(2016, 11, 29, 10, 5);
		searchParam.setStartTime(startTime.toDate());
		searchParam.setEndTime(endTime.toDate());
		
		return searchParam;
	}
}
