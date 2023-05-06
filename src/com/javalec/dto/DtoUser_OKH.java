package com.javalec.dto;

public class DtoUser_OKH {
	
	//	Field
	String uid;
	String uname;
	
	//	Constructor
	public DtoUser_OKH(){
	
	}

	public DtoUser_OKH(String uid, String uname) {
		super();
		this.uid = uid;
		this.uname = uname;
	}

	//	Method 
	//	Getter, Setter
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	

}
