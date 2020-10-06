package com.yc.snack.order.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class MqReceiver {
	private final static Logger logger = LoggerFactory.getLogger(MqReceiver.class);
	
	//@RabbitListener(queues = "myQueue") // 要监听的队列名称
	// @RabbitListener(queuesToDeclare = @Queue("myQueue")) // 如果这个消息队列没有，则会自动创建
	// @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue"), exchange = @Exchange("myExchange")))
	public void process(String msg) {
		logger.info("MqReceiver {}", msg);
	}
	
	// 接收数码的消息
	/*@RabbitListener(bindings = @QueueBinding(
			value = @Queue("myQueue"), // 要监听的消息队列
			exchange = @Exchange("myOrder"), // 消息转换器
			key = "digital" // 这个是处理数码的
			))*/
	public void processDigital(String msg) {
		logger.info("Digital MqReceiver {}", msg);
	}
	
	// 接收零食的消息
	/*@RabbitListener(bindings = @QueueBinding(
			value = @Queue("myQueue"), // 要监听的消息队列
			exchange = @Exchange("myOrder"), // 消息转换器
			key = "snack" // 这个是处理数码的
			))*/
	public void processSnack(String msg) {
		logger.info("Snack MqReceiver {}", msg);
	}
}
