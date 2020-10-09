package com.yc.snacknet.service;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.AdminInfo;
import com.yc.snacknet.vo.ResultVO;

public interface IAdminInfoService {
	public AdminInfo login(Map<String, String> map);
	public List<AdminInfo> finds();
	public ResultVO add(AdminInfo af);
	public ResultVO update(AdminInfo af);
	public ResultVO restPwd(String aid);
	public List<AdminInfo> findByCondition(Map<String, Object> map);
}
