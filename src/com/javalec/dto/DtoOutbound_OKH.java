package com.javalec.dto;

public class DtoOutbound_OKH {
	//field
	String obaid;
	int obpcode;
	int obqty;
	String obdate;
	int obcode;
	
	//	constructor
	public DtoOutbound_OKH() {
		
	}
	
	public DtoOutbound_OKH(int obqty) {
		super();
		this.obqty = obqty;
	}

	public String getObaid() {
		return obaid;
	}

	public void setObaid(String obaid) {
		this.obaid = obaid;
	}

	public int getObpcode() {
		return obpcode;
	}

	public void setObpcode(int obpcode) {
		this.obpcode = obpcode;
	}

	public int getObqty() {
		return obqty;
	}

	public void setObqty(int obqty) {
		this.obqty = obqty;
	}

	public String getObdate() {
		return obdate;
	}

	public void setObdate(String obdate) {
		this.obdate = obdate;
	}

	public int getObcode() {
		return obcode;
	}

	public void setObcode(int obcode) {
		this.obcode = obcode;
	}


	// Field G/S
	
	
	
	
}
