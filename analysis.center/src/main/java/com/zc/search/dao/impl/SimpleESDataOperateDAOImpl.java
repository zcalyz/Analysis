package com.zc.search.dao.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zc.constant.AddressConstant;
import com.zc.search.dao.ESDataOperateDAO;
import com.zc.search.param.BaseESInsertParam;
import com.zc.search.param.BaseESSearchParam;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.util.JsonUtil;
import com.zc.util.PropertyFileReadUtil;
import com.zc.util.SimpleQueryUtil;

public class SimpleESDataOperateDAOImpl implements ESDataOperateDAO {

	private static Logger logger = LoggerFactory.getLogger(SimpleESDataOperateDAOImpl.class);

	@Override
	public void searchData(BaseESSearchParam searchParam) {
		SimpleSearchParam simpleSearchParam = (SimpleSearchParam) searchParam;

		TransportClient esClient = getESClient();
		SearchRequestBuilder searchRequest = initBaseSearchQuery(esClient, searchParam);

		// 拼接查询语句
		SimpleQueryUtil.addTermQueryForName(searchRequest, simpleSearchParam);
		SimpleQueryUtil.addRangeQueryForAge(searchRequest, simpleSearchParam);
		SimpleQueryUtil.addSortField(searchRequest, "age", SortOrder.DESC);
		// SimpleQueryUtil.addMatchQueryForName(searchRequest, simpleSearchParam);

		// setFrom，从哪一个Score开始查
		searchRequest.setSize(20).setExplain(true);
		SearchResponse response = executeSearchOperation(searchRequest);

		SearchHit[] hits = response.getHits().getHits();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSource();
			System.out.println(source);
		}

		System.out.println(response.getHits().getTotalHits());

		esClient.close();
	}

	@Override
	public void insert(BaseESInsertParam insertParam) {
		TransportClient esClient = getESClient();
		IndexResponse response = esClient.prepareIndex(insertParam.getIndex(), insertParam.getType())
				.setSource(JsonUtil.ConvertBeanToJson(insertParam)).get();
		
		System.out.println(response.getId());
		esClient.close();
	}

	/**
	 * 获取elasticsearch链接客户端
	 * 
	 * @return
	 */
	private TransportClient getESClient() {
		TransportClient client = TransportClient.builder().build();
		Map<String, String> addressMap = PropertyFileReadUtil.getAddressMap();

		String[] addressArray = addressMap.get(AddressConstant.ES_ADDRESS_NAME).split(",");

		for (String address : addressArray) {
			try {
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), 9300));
			} catch (UnknownHostException e) {
				logger.error("address is invalid,address=" + address, e);
			}
		}

		return client;
	}

	/**
	 * 初始化基本查询语句
	 * 
	 * @param esClient
	 * @param searchParam
	 */
	private SearchRequestBuilder initBaseSearchQuery(TransportClient esClient, BaseESSearchParam searchParam) {
		return initBaseSearchQuery(esClient, searchParam, SearchType.DFS_QUERY_THEN_FETCH);
	}

	/**
	 * 
	 * @param esClient
	 * @param searchParam
	 * @param searchType
	 *            COUNT：只计算结果的数量 QUERY_THEN_FETCH:查询是针对所有的块执行的，但返回的是足够的信息
	 * @return
	 */
	private SearchRequestBuilder initBaseSearchQuery(TransportClient esClient, BaseESSearchParam searchParam,
			SearchType searchType) {
		return esClient.prepareSearch(searchParam.getIndexs()).setTypes(searchParam.getTypes())
				.setSearchType(searchType);
	}

	/**
	 * 执行查询语句
	 * 
	 * @param request
	 * @return
	 */
	private SearchResponse executeSearchOperation(SearchRequestBuilder request) {
		return request.execute().actionGet();
	}

}
