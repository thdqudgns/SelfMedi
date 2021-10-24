package com.fulldoping.member.dto;

import java.util.Date;

public class Member {
	
	private int userNo;       //회원번호
	private int userKind;	  //회원구분 (0: 관리자, 1: 일반, 2: 사업자)
	private String userId;    //아이디
	private String userPw;	  //비밀번호
	private String userName;  //이름
	private String userNick;  //닉네임
	private String userPh;    //휴대폰 번호
	private String userEm;    //이메일 주소
	private String userGen;   //성별 (F,M)
	private String userBirth; //생년월일
	private Date joinDate;    //가입일자
	private String businessNo;   //사업자번호 (약사번호 5자리)
	
	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userKind=" + userKind + ", userId=" + userId + ", userPw=" + userPw
				+ ", userName=" + userName + ", userNick=" + userNick + ", userPh=" + userPh + ", userEm=" + userEm
				+ ", userGen=" + userGen + ", userBirth=" + userBirth + ", joinDate=" + joinDate + ", businessNo="
				+ businessNo + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getUserKind() {
		return userKind;
	}

	public void setUserKind(int userKind) {
		this.userKind = userKind;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPh() {
		return userPh;
	}

	public void setUserPh(String userPh) {
		this.userPh = userPh;
	}

	public String getUserEm() {
		return userEm;
	}

	public void setUserEm(String userEm) {
		this.userEm = userEm;
	}

	public String getUserGen() {
		return userGen;
	}

	public void setUserGen(String userGen) {
		this.userGen = userGen;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

}