package com.yc.snack.order.dto;

import java.util.List;

import com.yc.snack.order.bean.OrderItemInfo;

/**
 * 订单信息传输对象
 * 源辰信息
 * @author navy
 * @date 2020年9月10日
 * @email haijunzhou@hnit.edu.cn
 */
public class OrderListDTO {
	private String ono; // 订单编号
	private String sdate; // 下单日期
	private Double totalPrice; // 订单总额
	private Integer status; // 订单状态
	
	private List<OrderItemInfo> orderItemList; // 订单详细
	
	@Override
	public String toString() {
		return "OrderListDTO [ono=" + ono + ", sdate=" + sdate + ", totalPrice=" + totalPrice + ", status=" + status
				+ ", orderItemList=" + orderItemList + "]";
	}

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<OrderItemInfo> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItemInfo> orderItemList) {
		this.orderItemList = orderItemList;
	}
}
