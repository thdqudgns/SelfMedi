package com.fulldoping.free.dto;

import java.util.Date;

public class FreeFile {

	private int fileNo;
	private int boardNo;
	private String originName;
	private String storedName;
	private int fileSize;
	private Date writeDate;
	
	@Override
	public String toString() {
		return "FreeFile [fileNo=" + fileNo + ", boardNo=" + boardNo + ", originName=" + originName + ", storedName="
				+ storedName + ", fileSize=" + fileSize + ", writeDate=" + writeDate + "]";
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	
	
	
}
