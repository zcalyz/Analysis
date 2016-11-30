package com.zc.common.service;

import java.util.List;
import com.zc.analysis.model.Station;
import com.zc.search.param.BaseESSearchParam;

/**
 * 模型输入数据获取服务
 * @author zhaichen
 *
 */
public interface RetriveModelInputDataSerivce {
	
	/**
	 * 获取模型所需的输入数据
	 * @return
	 */
	List<Station> retriveInputData(BaseESSearchParam searchParam);
}
