package com.zc.util;

import java.util.ArrayList;
import java.util.Random;

import com.zc.constant.EchartConstants;
import com.zc.display.model.EchartLegend;
import com.zc.display.model.EchartSeries;

public class MockDataUtil {
	
	/**
	 * 最大误差
	 */
	private static final double DEVIATION = 0.6;
	
	private static final double DIVIDE_VALUE = 0.35;

	
	public static EchartLegend initSimpleLegend(){
		EchartLegend legend = new EchartLegend();
		ArrayList<String> legendData = new ArrayList<String>();
		legendData.add(EchartConstants.LEGEND_PREDICT_TIME);
		legendData.add(EchartConstants.LEGEND_REAL_TIME);
		
		legend.setData(legendData);
		
		return legend;
	}
	
	public static EchartSeries mockRealBottlneckData(EchartSeries sourceData){
		
		EchartSeries echartSeries = new EchartSeries();
		echartSeries.setName(EchartConstants.LEGEND_REAL_TIME);
		
		for(Object data : sourceData.getData()){
			Double targetData = (Double) data;
			Double similarData = createSimilarData(targetData);
			echartSeries.addSeriesData(similarData);
		}
		
		return echartSeries;
	}
	
	/**
	 * 生成指定误差范围内的数据
	 * @param data
	 * @return
	 */
	public static Double createSimilarData(Double data){
		Random random = new Random();
		double gapData = random.nextDouble() * data * DEVIATION;
		
		Double similarData;
		// 随机确定加减符号
		boolean isPlus = (random.nextDouble() < DIVIDE_VALUE);
		if(isPlus){
			similarData = data + gapData;
		}else{
			similarData = data - gapData;
		}
		
		return similarData;
	}
}
