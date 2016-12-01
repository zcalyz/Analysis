package com.zc.util;

import java.util.List;

import com.zc.analysis.model.Station;
import com.zc.analysis.model.TransactionModel;

public class InputDataChangeUtil {
	
	public static void changeArriveRate(Station station, double targetArriveRate){
		List<TransactionModel> transactions = station.getTransactions();
		
		for(TransactionModel transaction : transactions){
			transaction.setArriveRate(targetArriveRate);
		}
	}
}
