package com.zc.search.service.impl;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.springframework.stereotype.Service;

import com.zc.search.param.es.SimpleSearchParam;
import com.zc.search.service.AbstractEsQueryService;

/**
 * 常用查询语句
 * 
 * @author zhaichen
 *
 */

@Service(value="simpleEsQueryServiceForTest")
public class SimpleEsQueryServiceForTest extends AbstractEsQueryService {

	/**
	 * 精确匹配，不支持通配符
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */

	public void addTermQueryForName(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		super.addTermQuery(searchRequest, "name", searchParam.getName());
	}

	/**
	 * 范围匹配[start,end) (dates, numbers, or strings)
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	public void addRangeQueryForAge(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		super.addRangeQuery(searchRequest, "age", searchParam.getStartAge(), searchParam.getEndAge());
	}

	/**
	 * 时间段范围查询 需要转化为long型
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	public void addRangeQueryForCreateTime(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		super.addRangeQuery(searchRequest, "creatTime", searchParam.getStartTime().getTime(),
				searchParam.getEndTime().getTime());
	}

	/**
	 * wildcard 支持通配符
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	public void addWildcardForAge(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		super.addWildcardForAge(searchRequest, "name", "ja*");
	}

	/**
	 * 查询匹配会进行分词所有包含jack 和 mike的字段都会被匹配
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	public void addMatchQueryForName(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		super.addMatchQueryForName(searchRequest, "name", "jack and mike");
	}

}
