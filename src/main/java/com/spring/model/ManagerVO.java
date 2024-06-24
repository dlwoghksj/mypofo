package com.spring.model;

public class ManagerVO {
	private String managerCode;
	private String managerPw;
	private String managerName;
	private String managerEmail;
	private String position;
	
	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(String managerPw) {
		this.managerPw = managerPw;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "ManagerVO [managerCode=" + managerCode + ", managerPw=" + managerPw + ", managerName=" + managerName
				+ ", managerEmail=" + managerEmail + ", position=" + position + "]";
	}
	
}
