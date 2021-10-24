package com.fulldoping.QnA.dto;

import java.sql.Date;

public class QnAComments {
	private int commentNo;
	private int boardNo;
	private int userNo;
	private String userNick;
	private String commentContent;
	private Date commentDate;
	
	@Override
	public String toString() {
		return "QnAComment=[ commentNo=" + commentNo + ", boardNo" + boardNo + ", userNo=" + userNo + ", userNick="+userNick+", commentContent=" + commentContent + ", commentDate=" + commentDate + "]";
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}
