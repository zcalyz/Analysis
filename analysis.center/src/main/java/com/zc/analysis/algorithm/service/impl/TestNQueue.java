package com.zc.analysis.algorithm.service.impl;

import com.zc.analysis.math.Erlang;

public class TestNQueue {
	public static void main(String[] args) {
		double residenceTime = residenceTime(1.8, 3.42);
		System.out.println(residenceTime);
	}
	
	public static double residenceTime(double serviceTime, double scutilization){
		// Multiple server (uses Erlang-C)
		 
		 double residenceTime = serviceTime
				* (1 + (Erlang.erlangC(scutilization, 4) / (4 - scutilization)));
	
	return residenceTime;
	}
}
