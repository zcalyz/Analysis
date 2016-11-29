package com.zc.search.dao;

import org.elasticsearch.search.SearchHits;

import com.zc.search.param.BaseESInsertParam;
import com.zc.search.param.BaseESSearchParam;

public interface EsDataOperateDAO {

	/**
	 * 根据查询条件查询查询数据
	 * @param searchParam
	 * @return
	 */
	public SearchHits searchData(BaseESSearchParam searchParam);

	/**
	 * 向指定index及type中插入数据
	 * 
	 * @param insertParam
	 */
	public void insert(BaseESInsertParam insertParam);
}
