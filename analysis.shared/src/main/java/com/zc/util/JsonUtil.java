package com.zc.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	
	public static String ConvertBeanToJson(Object targetObject){
		return JSON.toJSONString(targetObject);
	}
}
