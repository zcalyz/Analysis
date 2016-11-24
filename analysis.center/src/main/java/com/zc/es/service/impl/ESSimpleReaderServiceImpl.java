package com.zc.es.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zc.constant.AddressConstant;
import com.zc.util.PropertyFileReadUtil;

public class ESSimpleReaderServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(ESSimpleReaderServiceImpl.class);

	private TransportClient getESClient() {
		TransportClient client = TransportClient.builder().build();
		Map<String, String> addressMap = PropertyFileReadUtil.getAddressMap();

		String[] addressArray = addressMap.get(AddressConstant.ES_ADDRESS_NAME).split(",");

		for (String address : addressArray) {
			try {
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), 9300));
			} catch (UnknownHostException e) {
				logger.error("address is invalid,address=" + address, e);
			}
		}

		return client;
	}
	
}
