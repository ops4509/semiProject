package com.javalec.dto;

import java.io.InputStream;

public class DtoInbound {

	// Filed

	int ibpcode;
	int ibqty;
	int pcode;
	String psize;
	String ibdate;
	String pname;
	String pcolor;
	String pbrand;
	InputStream pfpic;
	String pbpic;
	String pspic;
	String aid;
	int stock;

	// Constructor-----------------------------------------------

	public DtoInbound(InputStream pfpic) {
		super();
		this.pfpic = pfpic;
	}

	public DtoInbound(String aid, int ibqty, int pcode) {
		super();
		this.aid = aid;
		this.ibqty = ibqty;
		this.pcode = pcode;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public DtoInbound(int pcode, String psize, String pname, String pcolor, String pbrand, InputStream pfpic) {
		super();
		this.pcode = pcode;
		this.psize = psize;
		this.pname = pname;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.pfpic = pfpic;
	}

	public DtoInbound(int pcode, String pname, String pcolor, String pbrand, String psize) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.psize = psize;
	}

	
	

	
	
	public DtoInbound(int stock) {
		super();
		this.stock = stock;
	}

	public DtoInbound(int pcode, String psize, String pname, String pcolor, String pbrand,
			int stock) {
		super();
		this.pcode = pcode;
		this.psize = psize;
		this.pname = pname;
		this.pcolor = pcolor;
		this.pbrand = pbrand;

		this.stock = stock;
	}

	// Method--

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

	public int getPcode() {
		return pcode;
	}

	public void setPcode(int pcode) {
		this.pcode = pcode;
	}

	public String getPsize() {
		return psize;
	}

	public void setPsize(String psize) {
		this.psize = psize;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcolor() {
		return pcolor;
	}

	public void setPcolor(String pcolor) {
		this.pcolor = pcolor;
	}

	public String getPbrand() {
		return pbrand;
	}

	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}

	public InputStream getPfpic() {
		return pfpic;
	}

	public void setPfpic(InputStream pfpic) {
		this.pfpic = pfpic;
	}

	public String getPbpic() {
		return pbpic;
	}

	public void setPbpic(String pbpic) {
		this.pbpic = pbpic;
	}

	public String getPspic() {
		return pspic;
	}

	public void setPspic(String pspic) {
		this.pspic = pspic;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}



}

