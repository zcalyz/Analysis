package com.zc.common.service;

import java.util.List;
import java.util.Map;

import com.zc.analysis.model.AnalysisResult;
import com.zc.analysis.model.TransactionModel;
import com.zc.search.param.BaseESSearchParam;

public interface RetriveInputDataSerivce {
	
	/**
	 * 获取模型所需的输入数据
	 * @return
	 */
	Map<String, List<TransactionModel>> retriveInputData(BaseESSearchParam searchParam);
}
