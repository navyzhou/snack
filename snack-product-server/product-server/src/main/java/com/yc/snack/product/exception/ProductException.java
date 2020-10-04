package com.yc.snack.product.exception;

import com.yc.snack.product.enums.ResultEnum;

/**
 * 商品异常类
 * company 源辰信息
 * @author navy
 * @date 2020年10月4日
 * Email haijunzhou@hnit.edu.cn
 */
public class ProductException extends RuntimeException{
	private static final long serialVersionUID = 6324665555459632879L;
	
	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public ProductException(Integer code, String message) {
		super(message);
		this.code = code;
	}
	
	public ProductException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
}
