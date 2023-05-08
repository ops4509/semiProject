package com.javalec.dto;

public class DtoBasket_OKH {
	//	Field
	String buid;
	int bpcode;
	int pqty;
	
	
	//	Constructor
	public DtoBasket_OKH() {
		
	}


	public DtoBasket_OKH(String buid, int bpcode, int pqty) {
		super();
		this.buid = buid;
		this.bpcode = bpcode;
		this.pqty = pqty;
	}

	//	Method(GETTER/SETTER)

	public String getBuid() {
		return buid;
	}


	public void setBuid(String buid) {
		this.buid = buid;
	}


	public int getBpcode() {
		return bpcode;
	}


	public void setBpcode(int bpcode) {
		this.bpcode = bpcode;
	}


	public int getPqty() {
		return pqty;
	}


	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
	
	
	
}
