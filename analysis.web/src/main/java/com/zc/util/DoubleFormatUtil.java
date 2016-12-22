package com.zc.util;

import java.text.DecimalFormat;

public class DoubleFormatUtil {
	/**
	 * double类型格式化
	 */
	public static DecimalFormat doubleFormat = new DecimalFormat("0.00");
	
	public static DecimalFormat arrivateRateFormat = new DecimalFormat("0.00");
	
	public static DecimalFormat deviationFormat = new DecimalFormat("0.000");
	
	/**
	 * 将数据格式化为百分比形式
	 * @param targetNum
	 * @return
	 */
	public static String formatToPercentage(Double targetNum){
		return doubleFormat.format(targetNum*100) + "%";
	}
}
