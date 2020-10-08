package com.yc.snack.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 限流过滤器
 * 时机：是在请求被转发之前调用，所以他应该放在前置过滤器最靠前的地方
 * company 源辰信息
 * @author navy
 * @date 2020年10月6日
 * Email haijunzhou@hnit.edu.cn
 */
@Component
public class DefaultFilter extends ZuulFilter{
	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return SERVLET_DETECTION_FILTER_ORDER - 9; // 我们比最小值还小
	}

	@Override
	public boolean shouldFilter() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String uri = request.getRequestURI();
		if (uri.equals("/")) {
			return true;
		}
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse response = context.getResponse();
		response.setContentType("text/html;charset=utf-8");

		context.setSendZuulResponse(false); // 说明验证不通过
		context.setResponseStatusCode(HttpStatus.FORBIDDEN.value()); //
		context.setResponseBody("<script>location.href='/product/index.html'</script>");
		return null;
	}
}
