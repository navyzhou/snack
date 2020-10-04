package com.yc.snack.product.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.product.enums.ResultEnum;
import com.yc.snack.product.util.SessionConstantKeys;
import com.yc.snack.product.vo.ResultVO;

@RestController
@RequestMapping("/login")
public class LoginInfoController {
	
	@GetMapping("/check")
	public ResultVO checkLogin(HttpSession session) {
		Object obj =  session.getAttribute(SessionConstantKeys.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		return new ResultVO(ResultEnum.LOGIN_SUCCESS, obj);
	}
}
