package com.zc.analysis.controller.ajax;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.analysis.algorithm.service.QueueModelSolverService;
import com.zc.analysis.model.AnalysisResultAO;
import com.zc.analysis.model.Station;
import com.zc.common.service.RetriveModelInputDataSerivce;
import com.zc.constant.EchartConstants;
import com.zc.constant.EsDBInfo;
import com.zc.constant.VOParamNameConstant;
import com.zc.display.model.EchartSeries;
import com.zc.display.model.EchartXAxis;
import com.zc.display.model.EchartYAxis;
import com.zc.display.model.StationEchartVO;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.util.InputDataChangeUtil;
import com.zc.util.MockDataUtil;
import com.zc.util.ThreadLocalDateUtil;
import com.zc.util.ConvertToVODataUtil;

@Controller
@RequestMapping("/chart")
public class RetriveAnalysisController {
	
	private static Logger logger = LoggerFactory.getLogger(RetriveAnalysisController.class);
	
	@Resource(name = "retriveModelInputDataSerivce")
	private RetriveModelInputDataSerivce retriveModelInputDataSerivce;
	
	@Resource(name = "queueModelSolverService")
	private QueueModelSolverService queueModelSolverService;
	
	@RequestMapping("/simpleChart.do")
	@ResponseBody
	public StationEchartVO getAnalysisDate(HttpServletRequest request){
		
		SimpleSearchParam searchParam = initSimpleSearchParam();
		
		// 获取日期参数
		Date startTime = getStartTime(request);
		Date endTime = getEndTime(request);
		if(startTime != null && endTime != null){
			searchParam.setStartTime(startTime);
			searchParam.setEndTime(endTime);
		}
		
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
		echartSeries.setName(EchartConstants.LEGEND_PREDICT_TIME);
		for(Double arrivateRate = 0.10; ; arrivateRate+=0.70){
			InputDataChangeUtil.changeArriveRate(station, arrivateRate);
			Station resultStation = queueModelSolverService.getAnalysisResult(station);
			
			AnalysisResultAO stationAO = ConvertToVODataUtil.transformToTableResult(resultStation);
			if(stationAO.getTotalResidenceTime() <= 0){
				break;
			}
			xAxis.addAxisData(ConvertToVODataUtil.arrivateRateFormat(arrivateRate));
			echartSeries.addSeriesData(stationAO.getTotalResidenceTime());
		}
		
		EchartYAxis yAxis = new EchartYAxis();
		yAxis.setName(EchartConstants.Y_RESPONSE_TIME);
		stationEchartVO.setyAxis(yAxis);
		
		stationEchartVO.setxAxis(xAxis);
		stationEchartVO.addPeformanceDataSeries(echartSeries);
		stationEchartVO.setLegend(MockDataUtil.initSimpleLegend());
		
		return stationEchartVO;
	}
	
	/**
	 * 获取Date类型的开始时间
	 * @param request
	 * @return
	 */
	private Date getStartTime(HttpServletRequest request){
		String startTime = request.getParameter(VOParamNameConstant.START_TIME);
		return ThreadLocalDateUtil.parse(startTime);
	}
	
	/**
	 * 获取Date类型的结束时间
	 * @param request
	 * @return
	 */
	private Date getEndTime(HttpServletRequest request){
		String endTime = request.getParameter(VOParamNameConstant.END_TIME);
		return ThreadLocalDateUtil.parse(endTime);
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
