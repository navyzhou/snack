package com.yc.snack.order.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.order.enums.ResultEnum;
import com.yc.snack.order.vo.ResultVO;
import com.yc.snack.product.client.ProductFeignClient;
import com.yc.snack.product.dto.CartInfoDTO;
import com.yc.snack.product.dto.ProductInfoDTO;
import com.yc.snack.product.dto.ProductTypeInfoDTO;
import com.yc.snack.user.dto.MemberLoginInfoDTO;
import com.yc.snack.user.dto.SessionKeysConstant;

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
	
	@GetMapping("/cart")
	public ResultVO cartInfo(HttpSession session) {
		Object obj =  session.getAttribute(SessionKeysConstant.CURRENTMEMBERACCOUNT);
		if (obj == null) {
			return new ResultVO(ResultEnum.LOGIN_ERROR);
		}
		MemberLoginInfoDTO member = (MemberLoginInfoDTO) obj;
		List<CartInfoDTO> list = productFeignClient.findCartList(String.valueOf(member.getMno()));
		
		if (list == null || list.isEmpty()) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		return new ResultVO(ResultEnum.SUCCESS, list);
	}
	
	@PostMapping("/findByCnos")
	public ResultVO findByCnos(@RequestParam List<String> cnos) {
		List<ProductInfoDTO> list = productFeignClient.findByCno(cnos);
		if (list == null || list.isEmpty()) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		return new ResultVO(ResultEnum.SUCCESS, list);
	}
}
