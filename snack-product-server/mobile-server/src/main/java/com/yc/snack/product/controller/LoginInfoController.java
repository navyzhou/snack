package com.yc.snack.product.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.product.enums.ResultEnum;
import com.yc.snack.product.util.CookieUtil;
import com.yc.snack.product.vo.ResultVO;
import com.yc.snack.user.client.UserFeignClient;
import com.yc.snack.user.dto.CookieConstant;
import com.yc.snack.user.dto.SessionKeysConstant;

@RestController
@RequestMapping("/login")
public class LoginInfoController {
	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/check")
	public ResultVO checkLogin(HttpSession session) {
		Object obj =  session.getAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		return new ResultVO(ResultEnum.LOGIN_SUCCESS, obj);
	}
	
	@PostMapping("/loginout")
	public ResultVO loginout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
		if (cookie == null) {
			return new ResultVO(ResultEnum.ERROR);
		}
		int result = userFeignClient.loginout(cookie.getValue());
		if (result > 0) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			session.removeAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}
}
