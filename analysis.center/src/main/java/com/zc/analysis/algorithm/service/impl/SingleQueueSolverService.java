package com.zc.analysis.algorithm.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zc.analysis.algorithm.service.QueueModelSolverService;
import com.zc.analysis.model.Station;
import com.zc.analysis.model.TransactionModel;
import com.zc.util.TypeConvertUtil;

/**
 * 单队列算法模型
 * @author zhaichen
 *
 */

@Component(value="singleQueueSolverService")
public class SingleQueueSolverService implements QueueModelSolverService{
	
	@Override
	public Station getAnalysisResult(Station station) {
		List<TransactionModel> transactions = station.getTransactions();	
		// 计算所有请求的吞吐量及利用率
		setThroughputAndUtilazation(transactions);
		
		double totalUtilization = getTotalUtilization(transactions);
		setResidenceTime(transactions, totalUtilization);
		
		setQueueLength(transactions);
		
		return station;
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
