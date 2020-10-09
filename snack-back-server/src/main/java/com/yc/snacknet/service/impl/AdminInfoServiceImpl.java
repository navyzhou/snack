package com.yc.snacknet.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.enums.ResultEnum;
import com.yc.snacknet.mapper.IAdminInfoMapper;
import com.yc.snacknet.service.IAdminInfoService;
import com.yc.snacknet.util.StringUtil;
import com.yc.snacknet.vo.ResultVO;

@Service
public class AdminInfoServiceImpl implements IAdminInfoService {
	@Autowired
	private IAdminInfoMapper adminInfoMapper;
	
	public AdminInfo login(Map<String, String> map) {
		if (StringUtil.checkNull(map.get("account"), map.get("pwd"))) {
			return null;
		}
		return adminInfoMapper.login(map);
	}

	@Override
	public List<AdminInfo> finds() {
		return adminInfoMapper.finds();
	}

	@Override
	public ResultVO add(AdminInfo af) {
		if (StringUtil.checkNull(af.getAname(), af.getPwd())) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		if (adminInfoMapper.add(af) > 0) {
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}

	@Override
	public ResultVO update(AdminInfo af) {
		if (StringUtil.checkNull(af.getAname())) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		if (adminInfoMapper.update(af) > 0) {
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}

	@Override
	public ResultVO restPwd(String aid) {
		if (StringUtil.checkNull(aid)) {
			return new ResultVO(ResultEnum.DATA_NULL);
		}
		if (adminInfoMapper.restPwd(aid) > 0) {
			return new ResultVO(ResultEnum.SUCCESS);
		}
		return new ResultVO(ResultEnum.ERROR);
	}

	@Override
	public List<AdminInfo> findByCondition(Map<String, Object> map) {
		return adminInfoMapper.findByCondition(map);
	}
}
