package com.zc.search.service.es.impl;

import org.elasticsearch.action.search.SearchRequestBuilder;

import com.zc.search.param.es.SimpleSearchParam;
import com.zc.search.service.es.BaseEsQueryService;

public class SimpleEsQueryService extends BaseEsQueryService{
	
	/**
	 * 精确匹配，不支持通配符
	 * 
	 * @param searchRequest
	 * @param simpleSearchParam
	 */

	public void addTermQueryForDstType(SearchRequestBuilder searchRequest, SimpleSearchParam simpleSearchParam) {
		String dstType = simpleSearchParam.getDstType();
		super.addTermQuery(searchRequest, "dstType", new String[]{dstType});
	}
	
	/**
	 * 范围匹配[start,end) (dates, numbers, or strings)
	 * 
	 * @param searchRequest
	 * @param simpleSearchParam
	 */
	public void addRangeQueryForTimestamp(SearchRequestBuilder searchRequest, SimpleSearchParam simpleSearchParam) {
		super.addRangeQuery(searchRequest, "timestamp", simpleSearchParam.getStartTime().toLocaleString(), simpleSearchParam.getEndTime().toLocaleString());
	}
}
