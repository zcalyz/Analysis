package com.zc.util;

import java.text.DecimalFormat;
import java.util.List;

import com.zc.analysis.model.Station;
import com.zc.analysis.model.AnalysisResultAO;
import com.zc.analysis.model.TransactionModel;
import com.zc.constant.SplitConstant;
import com.zc.display.model.EchartTitle;
import com.zc.display.model.StationEchartVO;

/**
 * 将数据转换为前端展示数据
 * @author zhaichen
 *
 */
public class ConvertToVODataUtil {
	
	
	/**
	 * 将station转换为table格式的数据
	 * @param station
	 * @return
	 */
	public static AnalysisResultAO transformToTableResult(Station station){
		AnalysisResultAO resultAO = new AnalysisResultAO();
		
		String stationName = StringSplitUtil.getLastSplitString(station.getName(), SplitConstant.APP_SPLIT_SIMBOL);
		resultAO.setStationName(stationName);
		
		List<TransactionModel> transactions = station.getTransactions();
		for(TransactionModel transaction : transactions){
			resultAO.getTransactionNames().add(getFormatTransactionName(transaction.getName()));			
			resultAO.getResidenceTimes().add(DoubleFormatUtil.doubleFormat.format(transaction.getResidenceTime()));
			resultAO.getQueueLengths().add(DoubleFormatUtil.doubleFormat.format(transaction.getQueueLenth()));
			resultAO.getUtilizations().add(getFormatUtilization(transaction.getUtilazation()));
		}
		
		return resultAO;
	}
	
	public static String arrivateRateFormat(double arrivateRate){
		return DoubleFormatUtil.arrivateRateFormat.format(arrivateRate);
	}
	
	public static EchartTitle getEchartTile(String titleName){
		String text = StringSplitUtil.getLastSplitString(titleName, SplitConstant.APP_SPLIT_SIMBOL);
		EchartTitle echartTitle = new EchartTitle();
		echartTitle.setText(text);
		return echartTitle;
	}
	
	/**
	 * 转换为echart类型的展示数据
	 * @param staton
	 * @return
	 */
	public static StationEchartVO transformToEchartResult(Station staton){
		
		
		return null;
	}
	
	/**
	 * 返回格式化的利用率，如3.2%
	 * @param utilization
	 * @return
	 */
	private static String getFormatUtilization(Double utilization){
		String formatUtilization = DoubleFormatUtil.formatToPercentage(utilization);
		
		return formatUtilization;
	}
	
	private static String getFormatTransactionName(String transactionName){
		return StringSplitUtil.getLastSplitString(transactionName, SplitConstant.URL_SPLIT_SIMBOL);
	}
	
}
