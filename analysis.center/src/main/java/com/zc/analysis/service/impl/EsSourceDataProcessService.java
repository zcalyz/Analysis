package com.zc.analysis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.zc.constant.EsColumnConstant;

/**
 * 简单Es元数据预处理类
 * @author zhaichen
 *
 */
public class EsSourceDataProcessService {
	
	/**
	 * 访问第三方应用所发出的消息
	 */
	public static final String THIRD_PART_MESSAGE = "3";
	
	/**
	 * 根据服务器节点分割数据
	 * @return
	 */
	public Map<String, List<SearchHit>> separateDataByStations(SearchHits searchHits){
		
		SearchHit[] hits = searchHits.getHits();	
		if(hits == null){
			return null;
		}

		HashMap<String, List<SearchHit>> resultMap = new HashMap<String, List<SearchHit>>();
		
		for(SearchHit searchHit : hits){
			if(isNeedFilter(searchHit)){
				continue;
			}
			
			addSearchHitToResultMapByColumn(resultMap, searchHit, EsColumnConstant.STATION_NAME);
		}
		
		return resultMap;
	}
	
	/**
	 * 根据Url分割数据
	 */
	public Map<String, List<SearchHit>> sepatateDataByUrl(List<SearchHit> searchHits){
		
		if(CollectionUtils.isEmpty(searchHits)){
			return null;
		}
		
		HashMap<String, List<SearchHit>> resultMap = new HashMap<String, List<SearchHit>>();
		for(SearchHit searchHit : searchHits){
			addSearchHitToResultMapByColumn(resultMap, searchHit, EsColumnConstant.URL);
		}
		
		return resultMap;
	}
	
	/**
	 * 计算平均服务时间
	 * @param searchHits
	 * @return
	 */
	public Long calAverageServiceTime(List<SearchHit> searchHits){
		if(CollectionUtils.isEmpty(searchHits)){
			return null;
		}
		
		Long sumServiceTime = 0L;
		for(SearchHit searchHit : searchHits){
			Map<String, Object> data = searchHit.getSource();
			Long serviceTime = Long.valueOf(data.get(EsColumnConstant.SERVICE_TIME_NAME) + "");
			sumServiceTime += serviceTime;
		}
		
		Long averageTime = sumServiceTime / searchHits.size();
		return averageTime;
	}
	
	/**
	 * 将查询条目增加到其对应列的list中,根据列划分数据时使用
	 */
	public void addSearchHitToResultMapByColumn(HashMap<String, List<SearchHit>> resultMap, SearchHit searchHit, String columnName){
		Map<String, Object> data = searchHit.getSource();
		String stationName = (String) data.get(columnName);
		
		List<SearchHit> searchHits = resultMap.get(stationName);
		if(searchHits == null){
			searchHits = new ArrayList<SearchHit>();
			resultMap.put(stationName, searchHits);
		}
		
		searchHits.add(searchHit);
	}
	
	/**
	 * 判段是否需要过滤
	 * @param searchHit
	 * @return
	 */
	public boolean isNeedFilter(SearchHit searchHit){
		Map<String, Object> data = searchHit.getSource();
		
		if (StringUtils.isEmpty(data.get(EsColumnConstant.URL))
				|| data.get(EsColumnConstant.DST_MESSAGE_TYPE).equals(THIRD_PART_MESSAGE)) {
			return true;
		}
		
		return false;
	}
	
}
