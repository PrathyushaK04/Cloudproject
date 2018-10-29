package com.cloudproject.model;

import java.sql.Timestamp;

public class UserModel {
	private String filename;
	private String description;
	private String fileSize;
	private String emailid;
	private Timestamp createdtime;
	private Timestamp updatedtime;
	
	
	public String getFileName() {
        return filename;
    }
	public void setFileName(String filename) {
        this.filename = filename;
    }
	public String getDescription() {
        return description;
    }
	public void setDescription(String description) {
        this.description = description;
    }
	public String getFileSize() {
        return fileSize;
    }
	public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
	public Timestamp getCreatedTime() {
        return createdtime;
    }
	public void setCreatedTime(Timestamp createdtime) {
        this.createdtime = createdtime;
    }
	public Timestamp getUpdatedTime() {
        return updatedtime;
    }
	public void setUpdatedTime(Timestamp updatedtime) {
        this.updatedtime = updatedtime;
    }
	public String getEmailId() {
        return emailid;
    }
	public void setEmailId(String emailid) {
        this.emailid = emailid;
    }
}
