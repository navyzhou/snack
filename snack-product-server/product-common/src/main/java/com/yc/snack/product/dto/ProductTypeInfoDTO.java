package com.yc.snack.product.dto;

/**
 * 商品类型传输对象
 * company 源辰信息
 * @author navy
 * @date 2020年10月2日
 * Email haijunzhou@hnit.edu.cn
 */
public class ProductTypeInfoDTO {
	private String tno;
	private String tname;
	
	@Override
	public String toString() {
		return "ProductTypeInfoDTO [tno=" + tno + ", tname=" + tname + "]";
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public ProductTypeInfoDTO(String tno, String tname) {
		super();
		this.tno = tno;
		this.tname = tname;
	}
}
