package com.javalec.dto;

public class DtoUser_Yj {

	String uid;
	String upw;
	String uaddress;
	String uphone;
	String uenrolldate;
	String uname;
	
	String aid;
	String apw;




	public String getAid() {
		return aid;
	}




	public void setAid(String aid) {
		this.aid = aid;
	}




	public String getApw() {
		return apw;
	}




	public void setApw(String apw) {
		this.apw = apw;
	}




	public DtoUser_Yj() {
		// TODO Auto-generated constructor stub
	}
	
	


	public DtoUser_Yj(String uid, String upw, String uaddress, String uphone, String uenrolldate,
			String uname) {
		super();
		this.uid = uid;
		this.upw = upw;
		this.uaddress = uaddress;
		this.uphone = uphone;
		this.uenrolldate = uenrolldate;
		this.uname = uname;
	}

	public DtoUser_Yj(String aid, String apw) {
		super();
		this.aid = aid;
		this.apw = apw;
	}

	


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getUpw() {
		return upw;
	}


	public void setUpw(String upw) {
		this.upw = upw;
	}


	public String getUaddress() {
		return uaddress;
	}


	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}


	public String getUphone() {
		return uphone;
	}


	public void setUphone(String uphone) {
		this.uphone = uphone;
	}


	public String getUenrolldate() {
		return uenrolldate;
	}


	public void setUenrolldate(String uenrolldate) {
		this.uenrolldate = uenrolldate;
	}



	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
