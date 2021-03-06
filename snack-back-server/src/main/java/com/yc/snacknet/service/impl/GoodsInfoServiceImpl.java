package com.yc.snacknet.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.GoodsInfo;
import com.yc.snacknet.mapper.IGoodsInfoMapper;
import com.yc.snacknet.service.IGoodsInfoService;
import com.yc.snacknet.util.RequestParamUtil;
import com.yc.snacknet.util.StringUtil;

@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService{
	@Autowired
	private IGoodsInfoMapper goodsInfoMapper;

	@Override
	public int add(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), gf.getPrice(), gf.getPics())) {
			return -1;
		}
		return goodsInfoMapper.add(gf);
	}

	@Override
	public int update(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), gf.getPrice(), gf.getPics())) {
			return -1;
		}
		return goodsInfoMapper.update(gf);
	}

	@Override
	public GoodsInfo findByGid(String gno) {
		if (StringUtil.checkNull(gno)) {
			return null;
		}
		return goodsInfoMapper.findByGid(gno);
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		map = RequestParamUtil.updateFindByPage(map);
		map.put("total", goodsInfoMapper.total(map));
		map.put("rows", goodsInfoMapper.findByPage(map));
		return map;
	}

	@Override
	public Map<String, Object> findByFirst(Map<String, Object> map) {
		map = RequestParamUtil.updateFindByPage(map);
		map.put("total", goodsInfoMapper.totals(map));
		map.put("rows", goodsInfoMapper.findByPage(map));
		return map;
	}
}
