package com.yc.snack.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.order.enums.OrderStatusEnum;
import com.yc.snack.order.enums.ResultEnum;
import com.yc.snack.order.service.IOrderInfoService;
import com.yc.snack.order.vo.ResultVO;
import com.yc.snack.user.dto.MemberLoginInfoDTO;
import com.yc.snack.user.dto.SessionKeysConstant;

@RestController
@RequestMapping("/order")
public class OrderInfoControlller {
	@Autowired
	private IOrderInfoService orderInfoService;
	
	/**
	 * 下单
	 * @param order
	 * @param session
	 * @return
	 */
	@PostMapping("/add")
	public ResultVO addOrder(@RequestParam("cnos[]") List<String> cnos, String ano, HttpSession session) {
		Object obj =  session.getAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.ERROR);
		}
		MemberLoginInfoDTO member = (MemberLoginInfoDTO) obj;
		return orderInfoService.add(cnos, ano, member.getMno());
	}
	
	/**
	 * 根据会员编号查询订单
	 */
	@GetMapping("/finds")
	public ResultVO listOrder(HttpSession session) {
		Object obj =  session.getAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.ERROR);
		}
		MemberLoginInfoDTO member = (MemberLoginInfoDTO) obj;
		return orderInfoService.listOrder(member.getMno());
	}
	
	/**
	 * 订单已完成
	 */
	@PostMapping("finish")
	public ResultVO finish(String orderId) {
		return orderInfoService.update(orderId, OrderStatusEnum.FINISH.getCode());
	}
	
	/**
	 * 已发货
	 * @param orderId
	 * @return
	 */
	@PostMapping("send")
	public ResultVO send(String orderId) {
		return orderInfoService.update(orderId, OrderStatusEnum.SEND.getCode());
	}
}
