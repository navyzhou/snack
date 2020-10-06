package com.yc.snack.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface IStreamClient {
	String QUEUEINPUTNAME = "myMessage";
	String QUEUEOUTPUTNAME = "myMessage2";
	
	//@Input(IStreamClient.QUEUEINPUTNAME)
	SubscribableChannel input();  // Subscribable: 可订阅
	
	//@Output(IStreamClient.QUEUEOUTPUTNAME)
	MessageChannel output();
}
