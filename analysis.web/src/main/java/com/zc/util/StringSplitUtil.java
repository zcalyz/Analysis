package com.zc.util;

public class StringSplitUtil {
	
	/**
	 * 根据分隔符分割字符串，并返回最后的String
	 * @param targetString
	 * @param split
	 * @return
	 */
	public static String getLastSplitString(String targetString, String split){
		int lastIndex = targetString.lastIndexOf(split);
		if(lastIndex == -1){
			return targetString;
		}
		
		return targetString.substring(lastIndex + 1);
	}
}
