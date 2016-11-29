package com.zc.analysis.model;

import java.util.List;

/**
 * 
 * 用于描述一个服务器节点
 * @author zhaichen
 *
 */
public class Station {
	
	/**
	 * 节点名称
	 */
	private String name;
	
	/**
	 * 节点所有事务
	 */
	private List<TransactionModel> transactions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TransactionModel> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionModel> transactions) {
		this.transactions = transactions;
	}
	
}
