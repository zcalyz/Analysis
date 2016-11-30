package com.zc.search.dao.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.annotation.Resource;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zc.constant.AddressConstant;
import com.zc.constant.EsColumnConstant;
import com.zc.constant.EsDBInfo;
import com.zc.search.dao.DataReadDAO;
import com.zc.search.param.BaseESInsertParam;
import com.zc.search.param.BaseESSearchParam;
import com.zc.search.param.es.SimpleSearchParam;
import com.zc.search.service.es.impl.EsQueryService;
import com.zc.util.JsonUtil;
import com.zc.util.PropertyFileReadUtil;

/**
 * 根据条件在Es中读取数据
 * @author zhaichen
 *
 */
@Component(value="esDataReadDAO")
public class EsDataReadDAOImpl implements DataReadDAO {

	private static Logger logger = LoggerFactory.getLogger(EsDataReadDAOImpl.class);
	
	@Resource(name="esQueryService")
	private EsQueryService simpleQueryService;
	
	private static final int ES_MAX_COUNT = 100;

	@Override
	public SearchHits searchData(BaseESSearchParam searchParam) {

		TransportClient esClient = getESClient();
		SearchRequestBuilder searchRequest = initBaseSearchQuery(esClient, searchParam);
		
		SimpleSearchParam simpleSearchParam = (SimpleSearchParam) searchParam; 
		simpleQueryService.addRangeQueryForTimestamp(searchRequest, simpleSearchParam);
//		simpleQueryService.addTermQueryForDstType(searchRequest, simpleSearchParam);

		// setFrom，从哪一个Score开始查
		searchRequest.setSize(ES_MAX_COUNT).setExplain(true);
		SearchResponse response = executeSearchOperation(searchRequest);

		esClient.close();	
		return response.getHits();
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
		Settings settings = Settings.settingsBuilder().put(EsColumnConstant.CLUSTER_NAME, EsDBInfo.REMOTE_JEESHOP_RELATION.getClusterName()).build();
		
		TransportClient client = TransportClient.builder().settings(settings).build();
		Map<String, String> addressMap = PropertyFileReadUtil.getAddressMap();

		String[] addressArray = addressMap.get(AddressConstant.ES_REMOTE_ADDRESS).split(",");

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
	 * 拼装基本的查询语句，用于测试
	 * @param searchRequest
	 * @param simpleSearchParam
	 */
//	private void initTestQuery(SearchRequestBuilder searchRequest, SimpleSearchParamExample simpleSearchParam){	
////		 拼接查询语句
//		simpleQueryServiceExample.addTermQueryForName(searchRequest, simpleSearchParam);
//		simpleQueryServiceExample.addRangeQueryForCreateTime(searchRequest, simpleSearchParam);
//		simpleQueryServiceExample.addRangeQueryForAge(searchRequest, simpleSearchParam);
//		simpleQueryServiceExample.addSortField(searchRequest, "age", SortOrder.DESC);
//		simpleQueryServiceExample.addMatchQueryForName(searchRequest, simpleSearchParam);
//	}

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
