package com.zc.analysis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.zc.analysis.model.Station;
import com.zc.analysis.model.TransactionModel;
import com.zc.common.service.RetriveModelInputDataSerivce;
import com.zc.search.dao.DataReadDAO;
import com.zc.search.param.BaseESSearchParam;

/**
 * 获取模型所需输入数据，
 * @author zhaichen
 *
 */
@Component(value="esRetriveModelInputDataService")
public class EsRetriveModelInputDataService implements RetriveModelInputDataSerivce{
	
	@Resource(name="esDataReadDAO")
	private DataReadDAO  dataOperateDAO;
	
	@Resource(name="esSourceDataProcessService")
	private EsSourceDataProcessService dataAnalysisService;

	@Override
	public List<Station> retriveInputData(BaseESSearchParam searchParam) {
		
		SearchHits searchData = dataOperateDAO.searchData(searchParam);
		System.out.println(searchData.getHits().length);
		
		// TODO Util判空
		
		// 获取队列模型所需输入数据
		List<Station> stations = new ArrayList<Station>();
		
		// 以节点为维度对检索数据进行拆分
		Map<String, List<SearchHit>> separatedDataByStation = dataAnalysisService.separateDataByStations(searchData);
		
		for(Map.Entry<String, List<SearchHit>> stationDataEntry : separatedDataByStation.entrySet()){
			Station station = new Station();
			station.setName(stationDataEntry.getKey());
			// 以节点为维度获取查询原始数据
			List<SearchHit> searchHitsInStation = stationDataEntry.getValue();
			
			List<TransactionModel> transtionModels = getTransactionInStationDimension(searchHitsInStation);
			
			station.setTransactions(transtionModels);
			stations.add(station);
		}
		
		return stations;
	}
	
	/**
	 * 获取指定station的所有TransactionModel
	 * @return
	 */
	private List<TransactionModel> getTransactionInStationDimension(List<SearchHit> searchHitInStation){
		List<TransactionModel> transactionModels = new ArrayList<TransactionModel>();
		
		Map<String, List<SearchHit>> separatedDataByUrl = dataAnalysisService.sepatateDataByUrl(searchHitInStation);
		
		for(Map.Entry<String, List<SearchHit>> searchHitsInUrlEntry : separatedDataByUrl.entrySet()){
			
			TransactionModel transactionModel = initTransactionBySeperatedData(searchHitsInUrlEntry);

			if(transactionModel != null){
				transactionModels.add(transactionModel);
			}
		}
		
		return transactionModels;
	}
	
	/**
	 * 根据以Url为维度划分的数据初始化TransactionModel
	 * @param searchHits
	 * @return
	 */
	private TransactionModel initTransactionBySeperatedData(Map.Entry<String, List<SearchHit>> searchHitsInUrlEntry){
		
		List<SearchHit> searchHitsInUrl = searchHitsInUrlEntry.getValue();
		
		if(CollectionUtils.isEmpty(searchHitsInUrl)){
			return null;
		}
		
		TransactionModel transactionModel = new TransactionModel();
		transactionModel.setName(searchHitsInUrlEntry.getKey());
		
		// 设置这段时间改请求出现的次数
		Integer arrtiveRate = new Integer(searchHitsInUrl.size());
		transactionModel.setArriveRate(arrtiveRate.doubleValue());
		// 获取平均服务时间
		Long averageServiceTime = dataAnalysisService.calAverageServiceTime(searchHitsInUrl);
		if(averageServiceTime != null){
			transactionModel.setServiceTime(averageServiceTime);
		}
		return transactionModel;
	}

}
