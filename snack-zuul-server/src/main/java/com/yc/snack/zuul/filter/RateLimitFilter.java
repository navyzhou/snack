package com.yc.snack.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.yc.snack.zuul.exception.RateLimiterExcepiton;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

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
public class RateLimitFilter extends ZuulFilter{
	// 令牌桶算法现在有开源的实现，我们就用这个开源的
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(100); // 这里是每1秒中往令牌桶中放多少令牌

	@Override
	public String filterType() {
		return PRE_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return SERVLET_DETECTION_FILTER_ORDER - 10; // 我们比最小值还小
	}
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		if (!RATE_LIMITER.tryAcquire()) { // 每个请求过来，先获取一个令牌，如果获取不到
			throw new RateLimiterExcepiton();
		}
		return null;
	}
}
