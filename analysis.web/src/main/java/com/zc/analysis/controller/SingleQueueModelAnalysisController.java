package com.zc.analysis.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zc.analysis.algorithm.service.QueueModelSolverService;
import com.zc.analysis.model.Station;
import com.zc.analysis.model.AnalysisResultAO;
import com.zc.common.service.RetriveModelInputDataSerivce;
import com.zc.constant.EsDBInfo;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.util.InputDataChangeUtil;
import com.zc.util.ConvertToVODataUtil;


/**
 * 获取单队列模型分析数据
 * @author zhaichen
 *
 */

@Controller
@RequestMapping("/singleQueueModel")
public class SingleQueueModelAnalysisController {
	
	@Resource(name = "retriveModelInputDataSerivce")
	private RetriveModelInputDataSerivce retriveModelInputDataSerivce;
	
	@Resource(name = "queueModelSolverService")
	private QueueModelSolverService queueModelSolverService;
	
	private static Logger logger = LoggerFactory.getLogger(SingleQueueModelAnalysisController.class);
	
	@RequestMapping("/display")
	public String displayAnalysisResult(HttpServletRequest request, Model model){
		SimpleSearchParam searchParam = initSimpleSearchParam();
		List<Station> stations = retriveModelInputDataSerivce.retriveInputData(searchParam);
		
		AnalysisResultAO resultAO = null;
		for(Station station : stations){
			InputDataChangeUtil.changeArriveRate(station, 0.1);
			Station resultStation = queueModelSolverService.getAnalysisResult(station);
			// 获取一个前端模型
			if(resultStation.getTransactions().size() > 1){
				resultAO = ConvertToVODataUtil.transformToTableResult(resultStation);
			}
		}
		
		model.addAttribute("resultList", resultAO);
		return "analysis/simpleAnalysis";
	}
	
	
	public SimpleSearchParam initSimpleSearchParam() {
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
