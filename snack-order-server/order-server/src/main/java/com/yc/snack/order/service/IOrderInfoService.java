package com.yc.snack.order.service;

import java.util.List;

import com.yc.snack.order.vo.ResultVO;

public interface IOrderInfoService {
	/**
	 * 下单
	 * @param cnos
	 * @param ano
	 * @param mno
	 * @return
	 */
	public ResultVO add(List<String> cnos, String ano, Integer mno);
	
	/**
	 * 修改订单状态
	 * @param ono
	 * @param status
	 * @return
	 */
	public ResultVO update(String ono, Integer status);
	
	public ResultVO listOrder(Integer mno);
}
