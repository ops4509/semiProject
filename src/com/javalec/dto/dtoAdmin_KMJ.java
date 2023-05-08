package com.javalec.dto;

public class dtoAdmin_KMJ {
	
	String aid;
	String aname;
	String apw;
	String address;
	String aedate;
	String audate;
	String aemail;
	String aphone;
	
	public dtoAdmin_KMJ() {
		// TODO Auto-generated constructor stub
	}

	public dtoAdmin_KMJ(String aname, String aid, String address, String aemail, String aphone) {
		super();
		this.aname = aname;
		this.aid = aid;
		this.address = address;
		this.aemail = aemail;
		this.aphone = aphone;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAemail() {
		return aemail;
	}

	public void setAemail(String aemail) {
		this.aemail = aemail;
	}

	public String getAphone() {
		return aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}
	
	
}
