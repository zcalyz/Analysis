package com.zc.util;

import java.util.List;

import com.zc.display.model.StationEchartVO;

public class DataChangeUtil {
	
	/**
	 * 将到达率扩大指定倍数
	 * @param resultAO
	 * @param times
	 */
	public static void arrivateRateMultiple(StationEchartVO stationEchartVO, int times){
		List<Object> xAxisData = stationEchartVO.getxAxis().getData();
		//将到达率扩大指定倍数
		for(int i = 0; i < xAxisData.size(); i++){
			Object rateObj = xAxisData.get(i);
			Double arrivateRate = Double.valueOf((String) rateObj);
			arrivateRate = arrivateRate * times;
			xAxisData.set(i, arrivateRate.toString());
		}
	}
}
