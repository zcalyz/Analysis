package com.zc.analysis.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.analysis.algorithm.service.QueueModelSolverService;
import com.zc.analysis.model.AnalysisResultAO;
import com.zc.analysis.model.Station;
import com.zc.common.service.RetriveModelInputDataSerivce;
import com.zc.constant.EsDBInfo;
import com.zc.display.model.EchartSeries;
import com.zc.display.model.EchartXAxis;
import com.zc.display.model.StationEchartVO;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.util.InputDataChangeUtil;
import com.zc.util.MockDataUtil;
import com.zc.util.TypeConvertUtil;

@Controller
@RequestMapping("/chart")
public class RetriveAnalysisController {
	
	@Resource(name = "retriveModelInputDataSerivce")
	private RetriveModelInputDataSerivce retriveModelInputDataSerivce;
	
	@Resource(name = "queueModelSolverService")
	private QueueModelSolverService queueModelSolverService;
	
	@RequestMapping("/simpleChart.do")
	@ResponseBody
	public StationEchartVO getAnalysis(Model model){
		
		SimpleSearchParam searchParam = initSimpleSearchParam();
		List<Station> stations = retriveModelInputDataSerivce.retriveInputData(searchParam);
		
		StationEchartVO echartDate = null;
		for(Station station : stations){
			if(station.getTransactions().size() > 1){
				echartDate = getEchartDate(station);
				break;
			}

		}
		
		return echartDate;
	}
	
	public StationEchartVO getEchartDate(Station station){
		StationEchartVO stationEchartVO = new StationEchartVO();
		
		EchartXAxis xAxis = new EchartXAxis();
		EchartSeries echartSeries = new EchartSeries();
		for(Double arrivate = 0.1; ; arrivate+=0.7){
			InputDataChangeUtil.changeArriveRate(station, arrivate);
			Station resultStation = queueModelSolverService.getAnalysisResult(station);
			
			AnalysisResultAO stationAO = TypeConvertUtil.transformToTableResult(resultStation);
			if(stationAO.getTotalResidenceTime() <= 0){
				break;
			}
			xAxis.addAxisData(arrivate);
			echartSeries.addSeriesData(stationAO.getTotalResidenceTime());
		}
		
		stationEchartVO.setxAxis(xAxis);
		stationEchartVO.addPeformanceDataSeries(echartSeries);
		stationEchartVO.setLegend(MockDataUtil.initSimpleLegend());
		
		return stationEchartVO;
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
