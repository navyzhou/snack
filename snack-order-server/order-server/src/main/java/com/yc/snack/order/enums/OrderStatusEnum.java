package com.yc.snack.order.enums;

/**
 * 订单状态枚举
 * 源辰信息
 * @author navy
 * @date 2020年9月4日
 * @email haijunzhou@hnit.edu.cn
 */
public enum OrderStatusEnum {
	CANCEL(0, "已取消"),
	NEW(1, "已下单"),
	ORDER(2, "已支付"),
	SEND(3, "已发货"),
	SIGNATURE(4, "已签收"),
	FINISH(5, "已签收"),
	COMMENT(6, "已评论");
	
	private Integer code;
	private String message;

	OrderStatusEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
