package com.yc.snack.product.mapper;

import java.util.List;
import java.util.Map;

import com.yc.snack.product.bean.CartInfo;

public interface ICartInfoMapper {
	public List<CartInfo> finds(String mno);
	
	/**
	 * 这个只查当前用不的购物车编号和商品编号 
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> info(String mno);
	
	public int updateNum(Map<String, Object> map);
	
	public int add(CartInfo cf);
	
	public int delete(String[] cnos);
	
	public List<CartInfo> findByCno(String[] cnos);
}
