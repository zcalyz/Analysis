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
	
	/**
	 * 到达率是否递增
	 * @return
	 */
	private boolean whatIf = false;
	
	/**
	 * 要更改的目标到达率，whatIf为true时才使用
	 */
	private int targetArrivateRate;
	

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

	public boolean isWhatIf() {
		return whatIf;
	}

	public void setWhatIf(boolean whatIf) {
		this.whatIf = whatIf;
	}

	public int getTargetArrivateRate() {
		return targetArrivateRate;
	}

	public void setTargetArrivateRate(int targetArrivateRate) {
		this.targetArrivateRate = targetArrivateRate;
	}
	
}
