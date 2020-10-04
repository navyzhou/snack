package com.yc.snack.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.order.enums.ResultEnum;
import com.yc.snack.order.vo.ResultVO;
import com.yc.snack.product.client.ProductFeignClient;
import com.yc.snack.product.dto.ProductTypeInfoDTO;

@RestController
@RequestMapping("/product")
public class OrderProductInfoController {
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@GetMapping("/types")
	public ResultVO listForTypes() {
		List<ProductTypeInfoDTO> list = productFeignClient.findTypes();
		if (list == null || list.isEmpty()) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		return new ResultVO(ResultEnum.SUCCESS, list);
	}
}
