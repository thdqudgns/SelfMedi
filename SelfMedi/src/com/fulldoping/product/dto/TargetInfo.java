package com.fulldoping.product.dto;

public class TargetInfo {
	private long productId;
	private int targetId;
	@Override
	public String toString() {
		return "TargetInfo [productId=" + productId + ", targetId=" + targetId + "]";
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	
	
}
