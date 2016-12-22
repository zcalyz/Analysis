package com.zc.simple.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.display.model.EchartLegend;
import com.zc.display.model.EchartSeries;
import com.zc.display.model.EchartXAxis;
import com.zc.display.model.StationEchartVO;
import com.zc.util.JsonUtil;
import com.zc.util.mock.BottleNeckMockDataUtil;


@Controller
@RequestMapping("/chart")
public class EchartAjaxTest {
	
	@RequestMapping("/simple.do")
	@ResponseBody
	public StationEchartVO getData(Model model){
		StationEchartVO stationEchartVO = new StationEchartVO();
		
		stationEchartVO.setLegend(BottleNeckMockDataUtil.initSimpleLegend());
		stationEchartVO.setxAxis(initXAxis());
		stationEchartVO.addPeformanceDataSeries(initSeries());
		
		return stationEchartVO;
	}
	
	
	public static EchartXAxis initXAxis(){
		EchartXAxis echartXAxis = new EchartXAxis();
		List<Object> xdata = new ArrayList<Object>();
		xdata.add("2");
		xdata.add("10");
		echartXAxis.setData(xdata);
		
		return echartXAxis;
	}
	
	public static EchartSeries initSeries(){
		EchartSeries echartSeries = new EchartSeries();
		echartSeries.setName("real");
		ArrayList<Object> data = new ArrayList<Object>();
		//TODO 改成init
		data.add("0.3");
		data.add("0.7");
		
		echartSeries.setData(data);
		return echartSeries;
	}
	
	public static void main(String[] args) {
		EchartAjaxTest echartSynTest = new EchartAjaxTest();
		StationEchartVO data = echartSynTest.getData(null);
		System.out.println(JsonUtil.ConvertBeanToJson(data));	
	}
}
