package com.yc.snack.order.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie操作类
 * 源辰信息
 * @author navy
 * @date 2020年9月22日
 * @email haijunzhou@hnit.edu.cn
 */
public class CookieUtil {
	/**
	 * 设置cookie信息
	 * @param response
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	public static void set(HttpServletResponse response, String key, String value, int maxAge) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	
	/**
	 * 获取Cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie get(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}
}
