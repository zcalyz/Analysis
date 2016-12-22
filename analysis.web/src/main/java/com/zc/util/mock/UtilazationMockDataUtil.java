package com.zc.util.mock;

import java.util.ArrayList;
import java.util.Random;

import org.joda.time.LocalDateTime;

import com.zc.constant.EchartConstants;
import com.zc.display.model.EchartLegend;
import com.zc.display.model.EchartSeries;
import com.zc.display.model.EchartTitle;
import com.zc.display.model.EchartXAxis;
import com.zc.display.model.EchartYAxis;
import com.zc.util.DoubleFormatUtil;
import com.zc.util.ThreadLocalDateUtil;

public class UtilazationMockDataUtil {
	
	/**
	 * 起始CPU
	 */
	private static final int START_CPU = 5;
	
	/**
	 * 最大cpu利用
	 */
	private static final int MAX_CPU = 60;
	
	/**
	 * 每组循环次数
	 */
	private static final int CPU_LOOP_TIMES_EACH_GROUP = 10;
	
	/**
	 * 每组CPU数据增长情况
	 */
	private static final int CPU_INC_STEP = 5;
	
	
	/**
	 * 起始I/O
	 */
	private static final int START_IO = 1;
	
	/**
	 * 最大IO利用
	 */
	private static final int MAX_IO = 12;
	
	/**
	 * 每组IO数据增长情况
	 */
	private static final int IO_INC_STEP = 1;
	
	/**
	 * 获取ECharts中的title
	 */
	public static EchartTitle getEchartTitle(){
		EchartTitle echartTitle = new EchartTitle();
		echartTitle.setText("192.168.30.128:7002");
		return echartTitle;
	}
	
	/**
	 * 获取Legend
	 * @return
	 */
	public static EchartLegend getEchartLegend(){
		EchartLegend legend = new EchartLegend();
		ArrayList<String> legendData = new ArrayList<String>();
		legendData.add(EchartConstants.LEGEND_CPU);
		legendData.add(EchartConstants.LEGEND_IO);
		
		legend.setData(legendData);
		
		return legend;
	}
	
	/**
	 * 获取CPU序列
	 * @return
	 */
	public static EchartSeries getCPUSeries(){
		EchartSeries echartSeries = new EchartSeries();
		echartSeries.setName(EchartConstants.LEGEND_CPU);
		
		Random random = new Random();
		for(int maxCPU = START_CPU; maxCPU < MAX_CPU; maxCPU = maxCPU + CPU_INC_STEP){
			
			for(int loopTime = 0; loopTime < CPU_LOOP_TIMES_EACH_GROUP; loopTime++){
				Double cpuUtilazation = random.nextDouble() * maxCPU;
				echartSeries.addSeriesData(DoubleFormatUtil.arrivateRateFormat.format(cpuUtilazation));
			}
		}
		
		return echartSeries;
	}
	
	/**
	 * 获取横坐标数据
	 * @param series
	 * @return
	 */
	public static EchartXAxis getXAxis(EchartSeries series){
		EchartXAxis echartXAxis = new EchartXAxis();
		echartXAxis.setName(EchartConstants.X_TIME);

		LocalDateTime startTime = new LocalDateTime(2016, 12, 22, 16, 10);
		echartXAxis.setMin(ThreadLocalDateUtil.formatDate(startTime.toDate()));
		
		LocalDateTime endTime = new LocalDateTime(2016, 12, 22, 17, 46);
		echartXAxis.setMax(ThreadLocalDateUtil.formatDate(endTime.toDate()));
		
		for(int i = 2 ; i < series.getData().size(); i++){
			LocalDateTime time = new LocalDateTime(2016, 12, 22, 16 + i/60, i%60);
			echartXAxis.addAxisData(ThreadLocalDateUtil.formatDate(time.toDate()));
		}
		return echartXAxis;
	}
	
	/*
	 * 获取IO数据
	 */
	public static EchartSeries getIOSeries(){
		EchartSeries echartSeries = new EchartSeries();
		echartSeries.setName(EchartConstants.LEGEND_IO);
		
		Random random = new Random();
		for(int maxIO = START_IO; maxIO < MAX_IO; maxIO = maxIO + IO_INC_STEP){
			
			for(int loopTime = 0; loopTime < CPU_LOOP_TIMES_EACH_GROUP; loopTime++){
				Double IOUtilazation = random.nextDouble() * maxIO;
				echartSeries.addSeriesData(DoubleFormatUtil.arrivateRateFormat.format(IOUtilazation));
			}
		}
		
		return echartSeries;
	}
	
	public static EchartYAxis getYAxis(){
		EchartYAxis yAxis = new EchartYAxis();
		yAxis.setName(EchartConstants.Y_UTILAZATION);
		yAxis.setMax(100);
		
		return yAxis;
	}
}
