package com.zc.util;

public class TypeConvertUtil {
	
	private static final double NANO_TO_SENCOND_STEP = 1000000000d;
	
	/**
	 * 将纳秒转换为秒
	 * @param nanoTime
	 * @return
	 */
	public static double converNanotimeToSecond(long nanoTime){
		return nanoTime/NANO_TO_SENCOND_STEP;
	}
}
