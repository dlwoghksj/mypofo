package com.spring.model;

public class OttVO {
	private String userEmail;
	private String groupCode;
	private String ottCode;
	private String ottName;
	private String startDate;
	private String endDate;
	private String ottID;
	private String ottPW;
	private String ottPaymentDate;
	private String groupCount;
	private String createDate;
	private String paymentStatus;
	private String userPaymentDate;
	private String new_first;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getOttCode() {
		return ottCode;
	}
	public void setOttCode(String ottCode) {
		this.ottCode = ottCode;
	}
	public String getOttName() {
		return ottName;
	}
	public void setOttName(String ottName) {
		this.ottName = ottName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOttID() {
		return ottID;
	}
	public void setOttID(String ottID) {
		this.ottID = ottID;
	}
	public String getOttPW() {
		return ottPW;
	}
	public void setOttPW(String ottPW) {
		this.ottPW = ottPW;
	}
	public String getOttPaymentDate() {
		return ottPaymentDate;
	}
	public void setOttPaymentDate(String ottPaymentDate) {
		this.ottPaymentDate = ottPaymentDate;
	}
	public String getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(String groupCount) {
		this.groupCount = groupCount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getUserPaymentDate() {
		return userPaymentDate;
	}
	public void setUserPaymentDate(String userPaymentDate) {
		this.userPaymentDate = userPaymentDate;
	}
	public String getNew_first() {
		return new_first;
	}
	public void setNew_first(String new_first) {
		this.new_first = new_first;
	}
	@Override
	public String toString() {
		return "OttVO [userEmail=" + userEmail + ", groupCode=" + groupCode + ", ottCode=" + ottCode + ", ottName="
				+ ottName + ", startDate=" + startDate + ", endDate=" + endDate + ", ottID=" + ottID + ", ottPW="
				+ ottPW + ", ottPaymentDate=" + ottPaymentDate + ", groupCount=" + groupCount + ", createDate="
				+ createDate + ", paymentStatus=" + paymentStatus + ", userPaymentDate=" + userPaymentDate
				+ ", new_first=" + new_first + "]";
	}
	
	
}
