package com.yc.snacknet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.enums.ResultEnum;
import com.yc.snacknet.service.IAdminInfoService;
import com.yc.snacknet.util.SessionConstantKeys;
import com.yc.snacknet.vo.ResultVO;
import com.yc.snacknet.websocket.WebServerSocket;

@RestController
@RequestMapping("/admin")
public class AdminInfoController {
	@Autowired
	private IAdminInfoService adminInfoService;
	
	@PostMapping("/login")
	public ResultVO login(@RequestParam Map<String, String> map, HttpSession session) {
		AdminInfo admin = adminInfoService.login(map);
		if (admin == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		
		// 判断当前用户有没有在其它地方登录
		WebServerSocket wss = WebServerSocket.getWebSocket(String.valueOf(admin.getAid()));
		if (wss != null) {
			wss.sendMessage("101"); // 给客户端发送一个挤退信息
		}
		session.setAttribute(SessionConstantKeys.CURRENTBACKLOGINACCOUNT, admin);
		return new ResultVO(ResultEnum.LOGIN_SUCCESS);
	}
	
	@GetMapping("/check")
	public ResultVO checkLogin(HttpSession session) {
		Object obj = session.getAttribute(SessionConstantKeys.CURRENTBACKLOGINACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		return new ResultVO(ResultEnum.SUCCESS, obj);
	}
	
	@GetMapping("/finds")
	public List<AdminInfo> finds() {
		return adminInfoService.finds();
	}
	
	@PostMapping("/add")
	public ResultVO add(AdminInfo af) {
		return adminInfoService.add(af);
	}
	
	@PostMapping("/update")
	public ResultVO update(AdminInfo af) {
		return adminInfoService.update(af);
	}
	
	@PostMapping("/restPwd")
	public ResultVO restPwd(String aid) {
		return adminInfoService.restPwd(aid);
	}
	
	@PostMapping("/findByCondition")
	public List<AdminInfo> findByCondition(@RequestParam Map<String, Object> map) {
		return adminInfoService.findByCondition(map);
	}
}
