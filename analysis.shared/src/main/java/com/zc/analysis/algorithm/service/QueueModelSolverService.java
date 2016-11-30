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
	 * @param station
	 * @return 返回填充完分析结果的Station
	 */
	Station getAnalysisResult(Station station);
	
}
