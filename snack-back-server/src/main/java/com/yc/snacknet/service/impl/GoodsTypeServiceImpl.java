package com.yc.snacknet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.annotation.DataSourceTypeAnnotation;
import com.yc.snacknet.bean.GoodsType;
import com.yc.snacknet.enums.DataSourceTypeEnum;
import com.yc.snacknet.enums.ResultEnum;
import com.yc.snacknet.mapper.IGoodsTypeMapper;
import com.yc.snacknet.service.IGoodsTypeService;
import com.yc.snacknet.util.StringUtil;
import com.yc.snacknet.vo.ResultVO;

@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService{
	@Autowired
	private IGoodsTypeMapper goodsTypeMapper;
	
	@Override
	@DataSourceTypeAnnotation(DataSourceTypeEnum.PRODUCT)
	public ResultVO add(GoodsType type) {
		if (StringUtil.checkNull(type.getTname())) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		if (goodsTypeMapper.add(type) > 0) {
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}

	@Override
	@DataSourceTypeAnnotation(DataSourceTypeEnum.PRODUCT)
	public ResultVO update(GoodsType type) {
		if (StringUtil.checkNull(type.getTname())) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		if (goodsTypeMapper.update(type) > 0) {
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}

	@Override
	@DataSourceTypeAnnotation(DataSourceTypeEnum.PRODUCT)
	public List<GoodsType> findAll() {
		return goodsTypeMapper.findAll();
	}
}
