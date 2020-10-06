package com.yc.snack.order.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.yc.snack.product.dto.CartInfoDTO;

//@Component
//@EnableBinding(IStreamClient.class)
public class StreamReceiver {
	private final static Logger logger = LoggerFactory.getLogger(StreamReceiver.class);
	
	//@StreamListener(value = IStreamClient.QUEUEINPUTNAME)
	//@SendTo(IStreamClient.QUEUEOUTPUTNAME)
	public String processObj(CartInfoDTO cartInfoDTO) {
		logger.info("StreamRecevier: {}", cartInfoDTO);
		return "收到了...";
	}
	
	//@StreamListener(value = IStreamClient.QUEUEOUTPUTNAME)
	public void process(String msg) {
		logger.info("Return StreamRecevier: {}", msg);
	}
}
