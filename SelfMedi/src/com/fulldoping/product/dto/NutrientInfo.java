package com.fulldoping.product.dto;

public class NutrientInfo {
	private long productId;
	private int nutId;
	private String nutContent;
	@Override
	public String toString() {
		return "NutrientInfo [productId=" + productId + ", nutId=" + nutId + ", nutContent=" + nutContent + "]";
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getNutId() {
		return nutId;
	}
	public void setNutId(int nutId) {
		this.nutId = nutId;
	}
	public String getNutContent() {
		return nutContent;
	}
	public void setNutContent(String nutContent) {
		this.nutContent = nutContent;
	}

}
