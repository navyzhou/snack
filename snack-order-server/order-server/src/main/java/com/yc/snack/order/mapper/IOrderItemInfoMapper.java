package com.yc.snack.order.mapper;

import java.util.List;

import com.yc.snack.order.bean.OrderItemInfo;

public interface IOrderItemInfoMapper {
	/**
	 * 添加订单详细
	 * @param ofs
	 * @return
	 */
	public int add(List<OrderItemInfo> ofs);
}
