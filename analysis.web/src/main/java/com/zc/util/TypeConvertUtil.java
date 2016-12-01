package com.zc.util;

import java.text.DecimalFormat;
import java.util.List;

import com.zc.analysis.model.Station;
import com.zc.analysis.model.TableAnalysisResultAO;
import com.zc.analysis.model.TransactionModel;

/**
 * 数据类型转换工具类
 * @author zhaichen
 *
 */
public class TypeConvertUtil {
	
	/**
	 * double类型格式化
	 */
	private static DecimalFormat doubleFormat = new DecimalFormat("0.00000");
	
	private static final String APP_SPLIT_SIMBOL = "@";
	
	private static final String URL_SPLIT_SIMBOL = "/";
	
	public static TableAnalysisResultAO transformToTableResult(Station station){
		TableAnalysisResultAO resultAO = new TableAnalysisResultAO();
		
		String stationName = StringSplitUtil.getLastSplitString(station.getName(), APP_SPLIT_SIMBOL);
		resultAO.setStationName(stationName);
		
		List<TransactionModel> transactions = station.getTransactions();
		for(TransactionModel transaction : transactions){
			resultAO.getTransactionNames().add(getFormatTransactionName(transaction.getName()));			
			resultAO.getResidenceTimes().add(doubleFormat.format(transaction.getResidenceTime()));
			resultAO.getQueueLengths().add(doubleFormat.format(transaction.getQueueLenth()));
			resultAO.getUtilizations().add(getFormatUtilization(transaction.getUtilazation()));
		}
		
		return resultAO;
	}
	
	/**
	 * 返回格式化的利用率，如3.2%
	 * @param utilization
	 * @return
	 */
	private static String getFormatUtilization(Double utilization){
		String formatUtilization = doubleFormat.format(utilization*100) + "%";
		
		return formatUtilization;
	}
	
	private static String getFormatTransactionName(String transactionName){
		return StringSplitUtil.getLastSplitString(transactionName, URL_SPLIT_SIMBOL);
	}
}
