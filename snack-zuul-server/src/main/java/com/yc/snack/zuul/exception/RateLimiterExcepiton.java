package com.yc.snack.zuul.exception;

/**
 * 流量异常
 * company 源辰信息
 * @author navy
 * @date 2020年10月6日
 * Email haijunzhou@hnit.edu.cn
 */
public class RateLimiterExcepiton extends RuntimeException{
	private static final long serialVersionUID = -920401009442775030L;

	public RateLimiterExcepiton() {
		super("流量太大，访问受限，请晚点再来吧...");
	}
}
