package com.spring.model;

public class PaymentVO {
	private String billing_key;
	private String pg_name;
	private String pg;
	private String method_name;
	private String method;
	private Object data;
	private String e_at;
	private String c_at;
	public String getBilling_key() {
		return billing_key;
	}
	public void setBilling_key(String billing_key) {
		this.billing_key = billing_key;
	}
	public String getPg_name() {
		return pg_name;
	}
	public void setPg_name(String pg_name) {
		this.pg_name = pg_name;
	}
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getE_at() {
		return e_at;
	}
	public void setE_at(String e_at) {
		this.e_at = e_at;
	}
	public String getC_at() {
		return c_at;
	}
	public void setC_at(String c_at) {
		this.c_at = c_at;
	}
	@Override
	public String toString() {
		return "PaymentVO [billing_key=" + billing_key + ", pg_name=" + pg_name + ", pg=" + pg + ", method_name="
				+ method_name + ", method=" + method + ", data=" + data + ", e_at=" + e_at + ", c_at=" + c_at + "]";
	}
	
	

}
