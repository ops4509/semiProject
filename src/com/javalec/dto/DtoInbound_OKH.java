package com.javalec.dto;

public class DtoInbound_OKH {
	//field
	String ibaid;
	int ibpcode;
	int ibqty;
	String ibdate;
	int ibcode;
	
	//	constructor
	public DtoInbound_OKH() {
		
	}
	
	public DtoInbound_OKH(int ibqty) {
		super();
		this.ibqty = ibqty;
	}


	// Field G/S
	
	public String getIbaid() {
		return ibaid;
	}

	public void setIbaid(String ibaid) {
		this.ibaid = ibaid;
	}

	public int getIbpcode() {
		return ibpcode;
	}

	public void setIbpcode(int ibpcode) {
		this.ibpcode = ibpcode;
	}

	public int getIbqty() {
		return ibqty;
	}

	public void setIbqty(int ibqty) {
		this.ibqty = ibqty;
	}

	public String getIbdate() {
		return ibdate;
	}

	public void setIbdate(String ibdate) {
		this.ibdate = ibdate;
	}

	public int getIbcode() {
		return ibcode;
	}

	public void setIbcode(int ibcode) {
		this.ibcode = ibcode;
	}
	
	
}
