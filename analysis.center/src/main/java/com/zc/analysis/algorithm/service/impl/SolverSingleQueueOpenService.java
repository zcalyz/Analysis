package com.zc.analysis.algorithm.service.impl;

import java.util.List;

import com.zc.analysis.algorithm.service.BaseSolverService;
import com.zc.analysis.model.Station;
import com.zc.analysis.model.TransactionModel;
import com.zc.util.TypeConvertUtil;

/**
 * 单队列
 * @author zhaichen
 *
 */
public class SolverSingleQueueOpenService implements BaseSolverService{
	
	@Override
	public void analysisAndSetResult(Station station) {
		List<TransactionModel> transactions = station.getTransactions();	
		
		setThroughputAndUtilazation(transactions);
		
		double totalUtilization = getTotalUtilization(transactions);
		setResidenceTime(transactions, totalUtilization);
		
		setQueueLength(transactions);
		
	}
		
	/**
	 * 计算并设置每个事务的吞吐量和利用率
	 * @param transactions
	 */
	private void setThroughputAndUtilazation(List<TransactionModel> transactions){
		
		for(TransactionModel transaction : transactions){
			calculateThroughput(transaction);
			calculateUtilazation(transaction);
		}
	}
	
	/**
	 * 获取该节点的总利用率
	 * @param transactions
	 * @return
	 */
	private double getTotalUtilization(List<TransactionModel> transactions){
		double totalUtilization = 0d;
		for(TransactionModel transaction : transactions){
			totalUtilization = totalUtilization + transaction.getUtilazation();
		}
		
		return totalUtilization;
	}
	
	/**
	 * 计算事务的吞吐量
	 * @param transaction
	 */
	private void calculateThroughput(TransactionModel transaction){
		double throughtput = transaction.getArriveRate();
		transaction.setThroughput(throughtput);
	}
	
	/**
	 * 计算资源利用率
	 * @param transaction
	 */
	private void calculateUtilazation(TransactionModel transaction){
		double serviceTimeInSecond = TypeConvertUtil.converNanotimeToSecond(transaction.getServiceTime());
		
		double utilazation = transaction.getArriveRate() * serviceTimeInSecond;
		transaction.setUtilazation(utilazation);
	}
	
	/**
	 * 计算驻留时间
	 * @param transaction
	 */
	private void setResidenceTime(List<TransactionModel> transactions, double totalUtilization){
		
		for(TransactionModel transaction : transactions){
			double serviceTimeInSecond = TypeConvertUtil.converNanotimeToSecond(transaction.getServiceTime());
			
			double residenceTime = serviceTimeInSecond/(1 - totalUtilization);
			transaction.setResidenceTime(residenceTime);
		}
	}
	
	/**
	 * 计算并设置队列长度
	 * @param transactions
	 */
	private void setQueueLength(List<TransactionModel> transactions){
		
		for(TransactionModel transaction : transactions){
			double queueLength = transaction.getResidenceTime() * transaction.getArriveRate();
			transaction.setQueueLenth(queueLength);
		}
	}

}
