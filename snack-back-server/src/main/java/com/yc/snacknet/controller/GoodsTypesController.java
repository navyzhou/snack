package com.yc.snacknet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.service.IGoodsTypeService;
import com.yc.snacknet.vo.ResultVO;

@RestController
@RequestMapping("/types")
public class GoodsTypesController {
	@Autowired
	private IGoodsTypeService goodsTypeSerice;
	
	@GetMapping("/findAll")
	public List<GoodsType> findAll() {
		return goodsTypeSerice.findAll();
	}
	
	@PostMapping("/update")
	public ResultVO update(GoodsType type){
		return goodsTypeSerice.update(type);
	}
	
	@PostMapping("/add")
	public ResultVO add(GoodsType type){
		return goodsTypeSerice.add(type);
	}
}
