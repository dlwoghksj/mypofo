package com.spring.model;

public class UserVO {
	private String userEmail;
	private String userPw;
	private String userName;
	private String joinDate;
	private String groupCode;
	private String groupuse;


	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupuse() {
		return groupuse;
	}
	public void setGroupuse(String groupuse) {
		this.groupuse = groupuse;
	}
	@Override
	public String toString() {
		return "UserVO [userEmail=" + userEmail + ", userPw=" + userPw + ", userName=" + userName + ", joinDate="
				+ joinDate + ", groupCode=" + groupCode + ", groupuse=" + groupuse + "]";
	}
	
	
}
