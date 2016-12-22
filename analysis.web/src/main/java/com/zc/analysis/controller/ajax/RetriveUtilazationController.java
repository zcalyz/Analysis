package com.zc.analysis.controller.ajax;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import com.zc.display.model.EchartSeries;
import com.zc.display.model.EchartTitle;
import com.zc.display.model.EchartXAxis;
import com.zc.display.model.EchartYAxis;
import com.zc.display.model.StationEchartVO;
import com.zc.util.InputDataChangeUtil;
import com.zc.util.mock.BottleNeckMockDataUtil;
import com.zc.util.mock.UtilazationMockDataUtil;
import com.zc.util.ConvertToVODataUtil;
import com.zc.util.DataChangeUtil;

/**
 * 获取利用率相关数据
 * @author zhaichen
 *
 */
@Controller
@RequestMapping("/chart")
public class RetriveUtilazationController {
	
	private static Logger logger = LoggerFactory.getLogger(RetriveUtilazationController.class);
	
	@Resource(name = "retriveModelInputDataSerivce")
	private RetriveModelInputDataSerivce retriveModelInputDataSerivce;
	
	@Resource(name = "queueModelSolverService")
	private QueueModelSolverService queueModelSolverService;
	
	@RequestMapping("/utilazation.do")
	@ResponseBody
	public StationEchartVO getAnalysisDate(HttpServletRequest request){
		
		StationEchartVO stationEchartVO = new StationEchartVO();
		stationEchartVO.setTitle(UtilazationMockDataUtil.getEchartTitle());
		stationEchartVO.setLegend(UtilazationMockDataUtil.getEchartLegend());
		
		stationEchartVO.addPeformanceDataSeries(UtilazationMockDataUtil.getCPUSeries());
		stationEchartVO.addPeformanceDataSeries(UtilazationMockDataUtil.getIOSeries());
		
		stationEchartVO.setyAxis(UtilazationMockDataUtil.getYAxis());
		stationEchartVO.setxAxis(UtilazationMockDataUtil.getXAxis(stationEchartVO.getPeformanceDataSeries().get(0)));
		
		
		return stationEchartVO;
	}
	
	/**
	 * 根据结果数据Station,获取echart展示数据
	 * @param station
	 * @return
	 */
	public StationEchartVO getEchartDate(Station station){
		StationEchartVO stationEchartVO = new StationEchartVO();
		
		EchartXAxis xAxis = new EchartXAxis();
		EchartSeries echartSeries = new EchartSeries();
		echartSeries.setName(EchartConstants.LEGEND_PREDICT_TIME);
		for(Double arrivateRate = 0.10; ; arrivateRate+=0.30){
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
		// 设置横轴数据
		stationEchartVO.setxAxis(xAxis);
		// 设置预测数据
		stationEchartVO.addPeformanceDataSeries(echartSeries);
		// 设置模拟产生的真实数据
		EchartSeries realData = BottleNeckMockDataUtil.mockRealBottlneckData(echartSeries);
		stationEchartVO.addPeformanceDataSeries(realData);
		
		stationEchartVO.setLegend(BottleNeckMockDataUtil.initSimpleLegend());
		
		// 设置标题
		EchartTitle echartTitle = ConvertToVODataUtil.getEchartTile(station.getName());
		// 计算并计算结果误差，放在副标题处
//		setDeviation(echartTitle, stationEchartVO.getPeformanceDataSeries());
		stationEchartVO.setTitle(echartTitle);
		
		// 扩大到达率，用于展示效果
		DataChangeUtil.arrivateRateMultiple(stationEchartVO, 4);
		
		return stationEchartVO;
	}
	


}
