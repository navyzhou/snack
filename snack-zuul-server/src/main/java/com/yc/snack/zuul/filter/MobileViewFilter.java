package com.yc.snack.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yc.snack.zuul.util.CheckMobile;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 移动访问过滤器
 * 时机：是在请求被转发之前调用，所以他应该放在前置过滤器最靠前的地方
 * company 源辰信息
 * @author navy
 * @date 2020年10月6日
 * Email haijunzhou@hnit.edu.cn
 */
@Component
public class MobileViewFilter extends ZuulFilter{
	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER + 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String uri = request.getRequestURI();
		if (uri.startsWith("/product/")) {
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
		String uri = request.getRequestURI();

		if (CheckMobile.checkMobile(request)) { // 说明是移动设备
			uri = uri.replaceFirst("/product/", "/mobile/");
			context.setSendZuulResponse(false); // 说明验证不通过
			context.setResponseStatusCode(HttpStatus.FORBIDDEN.value()); //
			context.setResponseBody("<script>location.href='" + uri + "'</script>");
		} 
		return null;
	}
}
