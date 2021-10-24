package com.fulldoping.product.dto;

public class CompBasket {
	private long productId;
	private int userNo;
	@Override
	public String toString() {
		return "CompBasket [productId=" + productId + ", userNo=" + userNo + "]";
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long l) {
		this.productId = l;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
