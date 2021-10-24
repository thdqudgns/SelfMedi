package com.fulldoping.product.dto;

public class SymptomCode {
	private int symptomId;
	private String symptomName;
	@Override
	public String toString() {
		return "SymptomCode [symptomId=" + symptomId + ", symptomName=" + symptomName + "]";
	}
	public int getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}
	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	
	
}
