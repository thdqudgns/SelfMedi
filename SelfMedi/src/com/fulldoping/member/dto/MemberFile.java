package com.fulldoping.member.dto;

public class MemberFile {

	private int fileNo;			//파일번호
	private int userNo;			//회원번호
	private String pharmacy;	//약국명
	private String originName;	//파일 원본명
	private String storedName;	//파일 UUID명 
	
	@Override
	public String toString() {
		return "MemberFile [fileNo=" + fileNo + ", userNo=" + userNo + ", pharmacy=" + pharmacy + ", originName="
				+ originName + ", storedName=" + storedName + "]";
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getPharmacy() {
		return pharmacy;
	}
	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getStoredName() {
		return storedName;
	}
	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	
}
