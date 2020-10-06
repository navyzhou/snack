package com.yc.snack.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.order.message.IStreamClient;
import com.yc.snack.product.dto.CartInfoDTO;

//@RestController
public class SendMessageController {
	//@Autowired
	private IStreamClient streamClient;
	
	//@GetMapping("/sendMsg")
	public void process() {
		CartInfoDTO cart = new CartInfoDTO();
		cart.setGno("1001");
		cart.setNums(10);
		cart.setPrice(12.68);
		
		streamClient.input().send(MessageBuilder.withPayload(cart).build());
	}
}
