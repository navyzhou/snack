package com.yc.snack.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.user.bean.EmailInfo;

@RestController
@RequestMapping("/test")
public class ReadEmailInfoController {
	@Autowired
	private EmailInfo emailInfo;
	
	// 不要返回对象，返回字符串，不然前端获取有问题
	@GetMapping("/info")
	public String getInfo() {
		return emailInfo.toString();
	}
}
