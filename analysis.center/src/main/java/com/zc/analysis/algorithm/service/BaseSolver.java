package com.zc.analysis.algorithm.service;


import com.zc.analysis.model.Station;

public interface BaseSolver {
	
	/**
	 * 分析并设置分析结果
	 * @param stations
	 */
	void analysisAndSetResult(Station stations);
	
}
