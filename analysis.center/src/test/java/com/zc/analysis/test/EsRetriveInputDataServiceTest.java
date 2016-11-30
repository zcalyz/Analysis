package com.zc.analysis.test;

import java.util.List;

import org.elasticsearch.search.aggregations.support.ValuesSourceParser.Input;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.zc.analysis.algorithm.service.impl.SingleQueueSolverService;
import com.zc.analysis.model.Station;
import com.zc.analysis.service.impl.EsRetriveModelInputDataService;
import com.zc.constant.EsDBInfo;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.util.InputDataChangeUtil;
import com.zc.util.PrintSearchHitsResultUtil;

public class EsRetriveInputDataServiceTest {
	
	@Test
	public void analysisTest(){
		EsRetriveModelInputDataService inputDataService = new EsRetriveModelInputDataService();
		// 获取输入数据
		SimpleSearchParam searchParam = initSimpleSearchParam();
		List<Station> inputData = inputDataService.retriveInputData(searchParam);
		
		SingleQueueSolverService solverService = new SingleQueueSolverService();
		// 输出输入数据
		PrintSearchHitsResultUtil.printForTableFormat(inputData);
		// 对数据进行分析
		for(Station station : inputData){
			if(station.getTransactions().size() > 1){
				InputDataChangeUtil.changeArriveRate(station, 1);
				solverService.getAnalysisResult(station);
				PrintSearchHitsResultUtil.printAnalysisResult(station);
			}
		}
		
	}
	
	public static SimpleSearchParam initSimpleSearchParam() {
		SimpleSearchParam searchParam = new SimpleSearchParam();
		searchParam.setIndexs(new String[] { EsDBInfo.REMOTE_SERVICE_RELATION.getIndex() });
		searchParam.setTypes(new String[] {EsDBInfo.REMOTE_SERVICE_RELATION.getType()});;
		
		LocalDateTime startTime = new LocalDateTime(2016, 11, 29, 8, 2);
		LocalDateTime endTime = new LocalDateTime(2016, 11, 29, 10, 5);
		searchParam.setStartTime(startTime.toDate());
		searchParam.setEndTime(endTime.toDate());
		
		return searchParam;
	}
	
}
