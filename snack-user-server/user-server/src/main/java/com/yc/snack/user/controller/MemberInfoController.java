package com.yc.snack.user.controller;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.user.bean.MemberInfo;
import com.yc.snack.user.dto.CookieConstant;
import com.yc.snack.user.dto.MemberLoginInfoDTO;
import com.yc.snack.user.dto.SessionKeysConstant;
import com.yc.snack.user.enums.ResultEnum;
import com.yc.snack.user.service.IMemberInfoService;
import com.yc.snack.user.util.CookieUtil;
import com.yc.snack.user.util.SendMailUtil;
import com.yc.snack.user.vo.ResultVO;

@RestController
@RequestMapping("/member")
public class MemberInfoController {
	@Autowired
	private IMemberInfoService memberInfoService;
	
	@Autowired
	private SendMailUtil sendMailUtil;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@PostMapping("login")
	public ResultVO login(MemberInfo mf, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// 先判断有没有登录
		Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID); 
		
		if (cookie != null && redisTemplate.opsForValue().get(cookie.getValue()) != null) { // 说明已经登录
			return new ResultVO(ResultEnum.LOGIN_INFO);
		}
		
		
		String vcode = String.valueOf(session.getAttribute("vcode"));
		if (!vcode.equalsIgnoreCase(mf.getRealName())) {
			return new ResultVO(ResultEnum.CODE_ERROR);
		}
		
		MemberInfo memberInfo = memberInfoService.login(mf);
		if (memberInfo == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		
		MemberLoginInfoDTO memberLoginInfoDTO = new MemberLoginInfoDTO();
		// 属性拷贝，将相同属性名的值拷贝过去
		BeanUtils.copyProperties(memberInfo, memberLoginInfoDTO);
		
		session.setAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT, memberLoginInfoDTO);
		
		// 先存redis
		String token = UUID.randomUUID().toString(); // 生成一个登录标识符
		redisTemplate.opsForValue().set(token, memberLoginInfoDTO, CookieConstant.EXPIRE, TimeUnit.SECONDS);
		
		// 然后将这个登录标识符存到cookie中
		CookieUtil.set(response, CookieConstant.OPENID, token, CookieConstant.EXPIRE);
		return new ResultVO(ResultEnum.LOGIN_SUCCESS);
	}
	
	@PostMapping("reg")
	public ResultVO reg(MemberInfo mf, HttpSession session) {
		Object obj = session.getAttribute("code");
		if (obj == null) {
			return new ResultVO(ResultEnum.CODE_TIMEOUT);
		}
		
		if (!mf.getRealName().equals(String.valueOf(obj))) {
			return new ResultVO(ResultEnum.CODE_ERROR);
		}
		
		int result = memberInfoService.reg(mf);
		if (result > 0) {
			return new ResultVO(ResultEnum.REG_SUCCESS);
		}
		return new ResultVO(ResultEnum.REG_ERROR);
	}
	
	@GetMapping("/check")
	public ResultVO check(HttpSession session) {
		Object obj = session.getAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.ERROR);
		}
		return new ResultVO(ResultEnum.SUCCESS, obj);
	}
	
	@PostMapping("/code")
	public ResultVO sendCode(String receive, String nickName, HttpSession session) {
		String code = "";
		Random rd = new Random();
		while (code.length() < 6) {
			code += rd.nextInt(10);
		}
		
		if (sendMailUtil.sendHtmlMail(receive, nickName, code)) { // 说明发送成功
			session.setAttribute("code", code);
			
			TimerTask task = new TimerTask() { // 创建一个定时器任务
				@Override
				public void run() {
					session.removeAttribute("code");
				}
			};
			
			Timer timer = new Timer(); // 创建一个定时器
			timer.schedule(task, 180000); // 3分钟之后执行这个任务一次
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}
	
	/**
	 * 退出登录的
	 * @param session
	 * @param openid
	 * @return
	 */
	@PostMapping("/loginout")
	public int loginout(HttpSession session, @RequestParam String openid) {
		try {
			session.removeAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
			redisTemplate.delete(openid);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
