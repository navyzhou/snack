package com.yc.snack.order.mapper;

import java.util.List;
import java.util.Map;

import com.yc.snack.order.bean.OrderInfo;
import com.yc.snack.order.dto.OrderListDTO;

public interface IOrderInfoMapper {
	/**
	 * 添加订单
	 * @param of
	 * @return
	 */
	public int add(OrderInfo of);

	public int update(Map<String, Object> map);

	public List<OrderListDTO> listOrder(Integer mno);
}
