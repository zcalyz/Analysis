package com.zc.util;

import java.util.Map;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

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
			System.out.println(source.get("interval") + " ");
		}
	}
}
