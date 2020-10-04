package com.yc.snack.zuul.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yc.snack.user.dto.CookieConstant;
import com.yc.snack.zuul.util.CookieUtil;

import io.micrometer.core.instrument.util.StringUtils;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限过滤
 * company 源辰信息
 * @author navy
 * @date 2020年10月4日
 * Email haijunzhou@hnit.edu.cn
 */
@Component
public class AuthorityFilter extends ZuulFilter{
	@Autowired
	private RedisTemplate<String, Object> redisTemplate; 
	
	@Override
	public String filterType() {
		return PRE_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1;
	}

	/**
	 * 控制过滤器是否生效
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		String uri = request.getRequestURI();
		if (uri.startsWith("/order/")) { // 如果你是访问订单服务，则满足我这个拦截条件
			return true;
		}
		
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		HttpServletResponse response = context.getResponse();
		
		response.setContentType("text/html;charset=utf-8");
		
		// 先从cookie中取值指令
		Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
		
		if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
			anthorized(context);
			return null;
		}
		
		if (redisTemplate.opsForValue().get(cookie.getValue()) == null) {
			anthorized(context);
			return null;
		}
		
		return null;
	}
	
	private void anthorized(RequestContext context) {
		context.setSendZuulResponse(false); // 说明验证不通过
		context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value()); // 权限不足
		context.setResponseBody("<script>alert('请先登录...');location.href='/user/login.html'</script>");
	}
}
