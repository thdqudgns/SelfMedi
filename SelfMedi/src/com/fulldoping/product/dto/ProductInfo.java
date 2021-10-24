package com.fulldoping.product.dto;

public class ProductInfo {
	private long productId;
	private String productName;
	private String manuCom;
	private String type;
	private String image;
	private String purchaseLink;
	private String allergyInfo;
	private String starScore;
	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productName=" + productName + ", manuCom=" + manuCom
				+ ", type=" + type + ", image=" + image + ", purchaseLink=" + purchaseLink + ", allergyInfo="
				+ allergyInfo + ", starScore=" + starScore + "]";
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManuCom() {
		return manuCom;
	}
	public void setManuCom(String manuCom) {
		this.manuCom = manuCom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPurchaseLink() {
		return purchaseLink;
	}
	public void setPurchaseLink(String purchaseLink) {
		this.purchaseLink = purchaseLink;
	}
	public String getAllergyInfo() {
		return allergyInfo;
	}
	public void setAllergyInfo(String allergyInfo) {
		this.allergyInfo = allergyInfo;
	}
	public String getStarScore() {
		return starScore;
	}
	public void setStarScore(String starScore) {
		this.starScore = starScore;
	}
	
	
}
