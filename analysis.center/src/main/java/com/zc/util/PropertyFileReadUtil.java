package com.zc.util;

import java.io.FileInputStream;
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
			Properties properties = initPropertiesByFile("properties/address.properties");
			Iterator<String> iterator = properties.stringPropertyNames().iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();
				addressMap.put(key, properties.getProperty(key));
			}
		}

		return addressMap;
	}

	/**
	 * 公用方法，根据文件名获取初始化的Properties类
	 * @param fileName
	 * @return
	 */
	private static Properties initPropertiesByFile(String fileName) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = PropertyFileReadUtil.class.getClassLoader().getResourceAsStream(fileName);

			properties.load(inputStream);

		} catch (Exception e) {
			logger.error("init properties error! ",e);
		}

		return properties;
	}
}
