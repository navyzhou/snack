package com.yc.snack.order.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.snack.order.OrderServerStartApplication;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=OrderServerStartApplication.class)
public class MySenderTest {
	/*@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Test
	public void send() {
		// 第一个参数是消息队列的名称，第二个参数是要发送的消息
		amqpTemplate.convertAndSend("myQueue", "你好！");
	}
	
	@Test
	public void send1() {
		// 第一个参数是Exchange的名称，第二个参数是routingkey
		amqpTemplate.convertAndSend("myOrder", "snack", "零食供应商收...");
	}
	
	
	@Test
	public void send2() {
		// 第一个参数是消息队列的名称，第二个参数是要发送的消息
		amqpTemplate.convertAndSend("myOrder", "digital", "数码供应商收...");
	}*/
}
