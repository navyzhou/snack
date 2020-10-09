package com.yc.snacknet.mapper;

import java.util.List;
import java.util.Map;

import com.yc.snacknet.bean.AdminInfo;

public interface IAdminInfoMapper {
	public AdminInfo login(Map<String, String> map);
	public List<AdminInfo> finds();
	public int add(AdminInfo af);
	public int update(AdminInfo af);
	public int restPwd(String aid);
	public List<AdminInfo> findByCondition(Map<String, Object> map);
}
