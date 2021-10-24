package com.fulldoping.product.dto;

public class SymptomInfo {
	private long productId;
	private int symptomId;
	@Override
	public String toString() {
		return "SymptomInfo [productId=" + productId + ", symptomId=" + symptomId + "]";
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}
	
	
}
