package com.zc.util;

public class StringUtils {
	
	/**
	 * str == null || str == ""
	 * @param targetStr
	 * @return
	 */
	public static boolean isEmpty(String targetStr){
		if(targetStr == null || targetStr.trim().equals("")){
			return true;
		}	
		return false;
	}
}
