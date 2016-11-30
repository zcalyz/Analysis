package com.zc.analysis.algorithm.service;


import com.zc.analysis.model.Station;

/**
 * 队列模型模型解析服务
 * @author zhaichen
 *
 */
public interface QueueModelSolverService {
	
	/**
	 * 分析并设置分析结果
	 * @param stations
	 */
	void analysisAndSetResult(Station station);
	
}
