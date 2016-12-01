package com.zc.util;

import java.util.ArrayList;

import com.zc.display.model.EchartLegend;

public class MockDataUtil {
	
	public static EchartLegend initSimpleLegend(){
		EchartLegend legend = new EchartLegend();
		ArrayList<String> legendData = new ArrayList<String>();
		legendData.add("real");
		
		legend.setData(legendData);
		
		return legend;
	}
}
