package com.zc.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于读取Property文件属性
 * 
 * @author zhaichen
 *
 */
public class PropertyFileReadUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyFileReadUtil.class);
	/**
	 * 储存address.properties 中的配置
	 */
	private static Map<String, String> addressMap;

	public static Map<String, String> getAddressMap() {
		if (addressMap == null) {
			addressMap = new HashMap<String, String>();
			Properties properties = loadFileInProperties("properties/address.properties");
			
			initMapByPropeties(properties, addressMap);
		}

		return addressMap;
	}

	/**
	 * 公用方法，将指定文件的内容读取到Properties类中
	 * @param fileName
	 * @return
	 */
	private static Properties loadFileInProperties(String fileName) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = PropertyFileReadUtil.class.getClassLoader().getResourceAsStream(fileName);

			properties.load(inputStream);

		} catch (Exception e) {
			logger.error("init properties error! ",e);
		}

		return properties;
	}
	
	/**
	 * 将propeties中的内容放到targetMap中
	 * @param properties
	 * @param targetMap
	 */
	private static void initMapByPropeties(Properties properties, Map<String, String> targetMap){
		Iterator<String> iterator = properties.stringPropertyNames().iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			targetMap.put(key, properties.getProperty(key));
		}
	}
	
}
