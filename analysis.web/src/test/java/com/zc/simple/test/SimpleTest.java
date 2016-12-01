package com.zc.simple.test;

import com.zc.util.StringSplitUtil;

public class SimpleTest {
	
	public static void main(String[] args) {
		String sourceStr = "application@192.168.30.128:7002";
		String lastStr = StringSplitUtil.getLastSplitString(sourceStr, "@");

		System.out.println(lastStr);
		
	}
}
