package com.zc.search.service.es;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;


/**
 * elasticsearch 查询语句服务类
 * 
 * @author zhaichen
 *
 */
public abstract class BaseEsQueryService {

	/**
	 * 增加精确匹配,value为多个值（不支持通配符)
	 * 
	 * @param searchRequest
	 * @param key
	 * @param value
	 */
	protected void addTermQuery(SearchRequestBuilder searchRequest, String key, String[] value) {
		searchRequest.setQuery(QueryBuilders.termsQuery(key, value));
	}

	/**
	 * 增加范围匹配[start,end) (dates, numbers, or strings)
	 * 
	 * @param searchRequest
	 * @param key
	 * @param start
	 * @param end
	 */
	protected static void addRangeQuery(SearchRequestBuilder searchRequest, String key, Object start, Object end) {
		searchRequest.setPostFilter(QueryBuilders.rangeQuery(key).from(start).to(end));
	}

	/**
	 * 增加wildcard(支持通配符)
	 * 
	 * @param searchRequest
	 * @param searchParam
	 */
	protected static void addWildcardForAge(SearchRequestBuilder searchRequest, String key, String value) {
		searchRequest.setPostFilter(QueryBuilders.wildcardQuery(key, value));
	}

	/**
	 * 增加MatchQuery，其会进行分词,所有包含分词的行都会被匹配
	 * 
	 * @param searchRequest
	 * @param key
	 * @param value
	 */
	protected static void addMatchQueryForName(SearchRequestBuilder searchRequest, String key, String value) {
		searchRequest.setPostFilter(QueryBuilders.matchQuery(key, value));
	}
	
	/**
	 * 根据指定字段排序
	 * @param searchRequest
	 * @param filedName
	 * @param sortOrder
	 */
	public void addSortField(SearchRequestBuilder searchRequest, String filedName, SortOrder sortOrder){
		searchRequest.addSort(filedName, sortOrder);
	}
}
