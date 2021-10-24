package com.fulldoping.free.dto;

import java.util.Date;

public class Free {

	private int boardNo;
	private int userNo;
	private String userId;
	private String userNick;
	private String boardTitle;
	private Date boardDate;
	private String boardContent;
	private String declare;
	private int hit;
	
	@Override
	public String toString() {
		return "Free [boardNo=" + boardNo + ", userNo=" + userNo + ", userId=" + userId + ", boardTitle=" + boardTitle
				+ ", boardDate=" + boardDate + ", boardContent=" + boardContent + ", declare=" + declare + ", hit="
				+ hit + ", userNick=" + userNick + "]";
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getDeclare() {
		return declare;
	}

	public void setDeclare(String declare) {
		this.declare = declare;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	

	
	
}
