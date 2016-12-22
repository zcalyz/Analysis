package com.zc.util;

import java.util.List;

import com.zc.display.model.EchartSeries;

public class DataCalculateUtil {

	/**
	 * 预测值和真实值的误差计算
	 * 
	 * @return
	 */
	public static Double calculateDeviation(List<EchartSeries> series) {
		// 数据错误直接返回
		if (series.size() < 2) {
			return null;
		}

		List<Object> predictDatas = series.get(0).getData();
		List<Object> realDatas = series.get(1).getData();
		int totalNum = predictDatas.size();
		Double totalDeviation = 0.0d;

		for (int i = 0; i < totalNum; i++) {
			Double realData = (Double) realDatas.get(i);
			Double predictData = (Double) predictDatas.get(i);

			Double deviation = Math.abs(predictData - realData) / realData;
			totalDeviation += deviation;
		}

		return totalDeviation / (totalNum * 2.7);
	}
}
