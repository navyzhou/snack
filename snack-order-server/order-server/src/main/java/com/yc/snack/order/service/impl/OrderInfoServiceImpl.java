package com.yc.snack.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.snack.order.bean.OrderInfo;
import com.yc.snack.order.bean.OrderItemInfo;
import com.yc.snack.order.dto.OrderListDTO;
import com.yc.snack.order.enums.ResultEnum;
import com.yc.snack.order.exception.AddrInfoException;
import com.yc.snack.order.exception.ProductException;
import com.yc.snack.order.mapper.IOrderInfoMapper;
import com.yc.snack.order.mapper.IOrderItemInfoMapper;
import com.yc.snack.order.service.IOrderInfoService;
import com.yc.snack.order.util.StringUtil;
import com.yc.snack.order.vo.ResultVO;
import com.yc.snack.product.client.ProductFeignClient;
import com.yc.snack.product.dto.CartInfoDTO;
import com.yc.snack.product.dto.ProductInfoDTO;
import com.yc.snack.user.client.UserFeignClient;
import com.yc.snack.user.dto.AddrInfoDTO;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService{
	@Autowired
	private IOrderInfoMapper orderInfoMapper;

	@Autowired
	private IOrderItemInfoMapper orderItemInfoMapper;

	@Autowired
	private ProductFeignClient productFeignClient;

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	@Transactional
	public ResultVO add(List<String> cnos, String ano, Integer mno) {
		// 根据购物车编号获取商品信息
		List<ProductInfoDTO> listGoods = productFeignClient.findByCno(cnos);

		// 判断数据完整性，下单的商品数量跟你查询出来是商品数量是否一致
		if (listGoods == null || listGoods.isEmpty() || listGoods.size() < cnos.size()) {
			throw new ProductException(ResultEnum.PRODUCT_NOE_EXIST);
		}

		AddrInfoDTO addrInfo = userFeignClient.findByAno(ano);
		if (addrInfo == null || StringUtil.checkNull(addrInfo.getAno())) {
			throw new AddrInfoException(ResultEnum.ADDR_NOE_EXIST);
		}

		// 生成存订单编号
		String ono = UUID.randomUUID().toString();

		// 计算总价，避免别人拦截修改
		double totalPrice = 0;
		List<CartInfoDTO> cartInfoList = new ArrayList<CartInfoDTO>(); // 购物车数据，用来发送消息和扣库存
		List<OrderItemInfo> orderItems = new ArrayList<OrderItemInfo>(); // 订单详细信息，到时候添加订单详细的时候要用
		OrderItemInfo orderItemInfo = null;

		// 判断商品的库存是否足够
		for (ProductInfoDTO product : listGoods) {
			if (product.getNum() > product.getBalance()) {
				throw new ProductException(ResultEnum.PRODUCT_INSUFFICINENT_STOCK);
			}

			totalPrice += product.getPrice() * product.getNum();
			cartInfoList.add(new CartInfoDTO(product.getGno(), product.getNum(), product.getPrice()));

			orderItemInfo = new OrderItemInfo();
			BeanUtils.copyProperties(product, orderItemInfo);
			orderItemInfo.setOno(ono);
			orderItems.add(orderItemInfo);
		}

		// 添加订单
		OrderInfo orderInfo = new OrderInfo();
		BeanUtils.copyProperties(addrInfo, orderInfo);
		orderInfo.setOno(ono); // 订单编号
		orderInfo.setPrice(totalPrice); // 订单总价

		if ( !(orderInfoMapper.add(orderInfo) > 0)) {
			throw new RuntimeException();
		}

		// 添加订单详细
		if ( !(orderItemInfoMapper.add(orderItems)> 0)) {
			throw new RuntimeException();
		}
		
		// 删除购物车数据
		if ( !(productFeignClient.delCart(cnos)> 0)) {
			throw new RuntimeException();
		}

		// 扣库存
		if( !(productFeignClient.buckleStock(cartInfoList) > 0)) {
			throw new RuntimeException();
		}

		// 返回订单编号和订单总额
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", ono);
		map.put("totalPrice", totalPrice);
		return new ResultVO(ResultEnum.SUCCESS, map);
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
