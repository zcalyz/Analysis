package com.zc.util;

import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.zc.analysis.model.Station;
import com.zc.analysis.model.TransactionModel;

public class PrintSearchHitsResultUtil {
	

	public static void printWholeLine(SearchHits searchHits){
		
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSource();
			System.out.println(source);
		}
	}
	
	public static void printForRelation(SearchHits searchHits){
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit hit : hits) {
			Map<String, Object> source = hit.getSource();
			System.out.print(source.get("dst") + "  ");
			System.out.print(source.get("url") + "  ");
			System.out.println(source.get("dstType") + " ");
			System.out.println(source.get("timestamp") + " ");
			System.out.println(source.get("interval") + " ");
		}
	}
	
	public static void printForTableFormat(List<Station> inputData){
		
		for(Station station  : inputData){
			List<TransactionModel> stationData = station.getTransactions();

			printColumn(station);
			
			for(TransactionModel model : stationData){
				System.out.print(model.getServiceTime() + "-" + model.getArriveRate() + "    ");
			}
			System.out.println("\n");
		}
	}
	
	public static void printColumn(Station station){
		System.out.print(station.getName() + "    ");
		
		List<TransactionModel> transactionModels = station.getTransactions();
		for(TransactionModel model : transactionModels){
			System.out.print(model.getName() + "  ");
		}
		System.out.println();
		
		System.out.print(station.getName() + "    ");
	}
	
	public static void printAnalysisResult(Station station){
		System.out.println("\n+++++++++++++++++++result+++++++++++++++++++++\n");
		List<TransactionModel> transactions = station.getTransactions();
		
		printColumn(station);
		for(TransactionModel model : transactions){
			System.out.print(model.getResidenceTime() + "-" + model.getUtilazation()+ "—" + model.getQueueLenth());
		}	
	}

}
