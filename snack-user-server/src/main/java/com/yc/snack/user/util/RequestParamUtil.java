package com.yc.snack.user.util;

import java.util.Map;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月26日
 * Email haijunzhou@hnit.edu.cn
 */
public class RequestParamUtil {
	/**
	 * 处理分页数据
	 * @param map
	 * @return
	 */
	public static Map<String, Object> updateFindByPage(Map<String, Object> map) {
		int page = Integer.parseInt(String.valueOf(map.get("page")));
		int rows = Integer.parseInt(String.valueOf(map.get("rows")));
		map.put("page", (page - 1) * rows);
		map.put("rows", rows);
		return map;
	}
}
