package com.zc.util;

import java.util.ArrayList;

import com.zc.constant.EchartConstants;
import com.zc.display.model.EchartLegend;

public class MockDataUtil {
	
	public static EchartLegend initSimpleLegend(){
		EchartLegend legend = new EchartLegend();
		ArrayList<String> legendData = new ArrayList<String>();
		legendData.add(EchartConstants.LEGEND_PREDICT_TIME);
		
		legend.setData(legendData);
		
		return legend;
	}
}
