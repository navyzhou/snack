package com.yc.snack.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.product.dto.ProductInfoDTO;
import com.yc.snack.product.dto.CartInfoDTO;
import com.yc.snack.product.service.IGoodsInfoService;

@RestController
@RequestMapping("/product")
public class GoodsInfoForOrderController {
	@Autowired
	private IGoodsInfoService goodsInfoService;
	
	/**
	 * 根据商品编号获取商品信息
	 * @param gnos
	 * @return
	 */
	@PostMapping("/listForGno")
	public List<ProductInfoDTO> listForGno(@RequestParam List<String> gnos) {
		return goodsInfoService.listForGno(gnos);
	}
	
	/**
	 * 根据购物车编号获取商品信息
	 * @param cnos
	 * @return
	 */
	@PostMapping("/listForCno")
	public List<ProductInfoDTO> listForCno(@RequestParam List<String> cnos) {
		return goodsInfoService.listForCno(cnos);
	}
	
	/**
	 * 扣库存的方法
	 * @param list
	 * @return
	 */
	@PostMapping("/buckleStock")
	public int buckleStock(@RequestBody List<CartInfoDTO> list) {
		return goodsInfoService.buckleStock(list);
	}
}
