package com.zc.util;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import com.zc.search.param.es.SimpleSearchParam;

public class SimpleQueryUtil {

	/**
	 * 精确匹配，不支持通配符
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	
	public static void addTermQueryForName(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		searchRequest.setQuery(QueryBuilders.termsQuery("name", searchParam.getName()));
	}

	/**
	 * 范围匹配[start,end) (dates, numbers, or strings)
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	public static void addRangeQueryForAge(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		searchRequest.setPostFilter(QueryBuilders.rangeQuery("age").from(searchParam.getStartAge())
				.to(searchParam.getEndAge()));
	}
	
	/**
	 * wildcard 支持通配符
	 * @param searchRequest
	 * @param searchParam
	 */
	public static void addWildcardForAge(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		searchRequest.setPostFilter(QueryBuilders.wildcardQuery("name","ja*"));
	}
	
	/**
	 * 查询匹配会进行分词所有包含jack 和 mike的字段都会被匹配
	 * @param searchRequest
	 * @param searchParam
	 */
	public static void addMatchQueryForName(SearchRequestBuilder searchRequest, SimpleSearchParam searchParam) {
		searchRequest.setPostFilter(QueryBuilders.matchQuery("name","jack and mike"));
	}
	
	/**
	 * 根据指定字段排序呢
	 * @param searchRequest
	 * @param filedName
	 * @param sortOrder
	 */
	public static void addSortField(SearchRequestBuilder searchRequest, String filedName, SortOrder sortOrder){
		searchRequest.addSort(filedName, sortOrder);
	}
}
