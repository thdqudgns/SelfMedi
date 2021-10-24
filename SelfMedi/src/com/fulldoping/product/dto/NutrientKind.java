package com.fulldoping.product.dto;

public class NutrientKind {
	
	private int nutId;
	private String nutKind;
	private String deficiency;
	private String effect;
	private String hyperact;
	private String rcmYth;
	private String rcmAdult;
	private String rcmSen;


	
	@Override
	public String toString() {
		return "NutrientKind [nutId=" + nutId + ", nutKind=" + nutKind + ", deficiency=" + deficiency + ", effect="
				+ effect + ", hyperact=" + hyperact + ", rcmYth=" + rcmYth + ", rcmAdult=" + rcmAdult + ", rcmSen="
				+ rcmSen + "]";
	}
	



	public int getNutId() {
		return nutId;
	}

	public void setNutId(int nutId) {
		this.nutId = nutId;
	}

	public String getNutKind() {
		return nutKind;
	}
	public void setNutKind(String nutKind) {
		this.nutKind = nutKind;
	}
	public String getDeficiency() {
		return deficiency;
	}
	public void setDeficiency(String deficiency) {
		this.deficiency = deficiency;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getHyperact() {
		return hyperact;
	}
	public void setHyperact(String hyperact) {
		this.hyperact = hyperact;
	}
	public String getRcmYth() {
		return rcmYth;
	}
	public void setRcmYth(String rcmYth) {
		this.rcmYth = rcmYth;
	}
	public String getRcmAdult() {
		return rcmAdult;
	}
	public void setRcmAdult(String rcmAdult) {
		this.rcmAdult = rcmAdult;
	}
	public String getRcmSen() {
		return rcmSen;
	}
	public void setRcmSen(String rcmSen) {
		this.rcmSen = rcmSen;
	}
	
	
}
