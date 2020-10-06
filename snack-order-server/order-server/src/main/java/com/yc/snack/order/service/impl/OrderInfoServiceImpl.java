package com.yc.snack.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snack.order.dto.OrderListDTO;
import com.yc.snack.order.enums.ResultEnum;
import com.yc.snack.order.mapper.IOrderInfoMapper;
import com.yc.snack.order.mapper.IOrderItemInfoMapper;
import com.yc.snack.order.service.IOrderInfoService;
import com.yc.snack.order.vo.ResultVO;
import com.yc.snack.product.client.ProductFeignClient;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService{
	@Autowired
	private IOrderInfoMapper orderInfoMapper;
	
	@Autowired
	private IOrderItemInfoMapper orderItemInfoMapper;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	
	@Override
	public ResultVO add(List<String> cnos, String ano, Integer mno) {
		// 根据购物车编号获取商品信息
		
		// 判断数据完整性，下单的商品数量跟你查询出来是商品数量是否一致
		
		// 计算总价，避免别人拦截修改
		
		// 判断商品的库存是否足够
		
		// 扣存款
		
		// 生成存订单编号
		
		// 添加订单
		
		// 添加订单详细
		
		// 删除购物车数据
		
		// 返回订单编号和订单总额
		
		return null;
	}

	@Override
	public ResultVO update(String ono, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ono", ono);
		map.put("status", status);
		if (orderInfoMapper.update(map) > 0) {
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}

	@Override
	public ResultVO listOrder(Integer mno) {
		List<OrderListDTO> list = orderInfoMapper.listOrder(mno);
		if (list == null || list.isEmpty()) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		return new ResultVO(ResultEnum.SUCCESS, list);
	}
}
