package com.yc.snack.product.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snack.product.dto.ProductInfoDTO;

@RestController
@RequestMapping("/product")
public class GoodsInfoForOrderController {
	
	@PostMapping("/listForGno")
	public List<ProductInfoDTO> listForGno(@RequestParam List<String> gnos) {
		return Collections.emptyList();
	}
}
