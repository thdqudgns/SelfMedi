package com.fulldoping.product.dto;

public class TargetCode {
	private int targetId;
	private String targetName;
	@Override
	public String toString() {
		return "TargetCode [targetId=" + targetId + ", targetName=" + targetName + "]";
	}
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
}
