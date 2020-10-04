package com.yc.snack.order;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.order.enums.ResultEnum;
import com.yc.snack.order.vo.ResultVO;
import com.yc.snack.user.dto.SessionKeysConstant;

@RestController
@RequestMapping("/login")
public class LoginInfoController {
	
	@GetMapping("/check")
	public ResultVO checkLogin(HttpSession session) {
		Object obj =  session.getAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		return new ResultVO(ResultEnum.LOGIN_SUCCESS, obj);
	}
}
