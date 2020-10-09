package com.yc.snacknet.service;

import java.util.List;

import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.vo.ResultVO;

public interface IGoodsTypeService {
	public ResultVO add(GoodsType type);
	
	public ResultVO update(GoodsType type);
	
	public List<GoodsType> findAll();
}
