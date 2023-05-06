package com.javalec.dto;

public class DtoProduct_OKH {
	//	Fields
	int pcode;
	String pname;
	String psize;
	String pcolor;
	String pbrand;
	String pffile;
	String psfile;
	String pbfile;
	int pprice;
	
	
	//	Constructor
	public DtoProduct_OKH() {
		
	}

	public DtoProduct_OKH(String pffile, String pbfile, String psfile) {
		super();
		this.pffile = pffile;
		this.psfile = psfile;
		this.pbfile = pbfile;
	}

	public DtoProduct_OKH(int pcode, String pname, String psize, String pcolor, String pbrand, int pprice) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.psize = psize;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.pprice = pprice;
	}
	
	public DtoProduct_OKH(String pname, String pbrand, String pcolor, String psize, int pprice) {
		super();
		this.pname = pname;
		this.psize = psize;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.pprice = pprice;
	}
	
	public DtoProduct_OKH(String pname, String pbrand, String pcolor, String psize, int pprice, int pcode) {
		super();
		this.pname = pname;
		this.psize = psize;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.pprice = pprice;
		this.pcode = pcode;
	}

	//	Method
	// Getter , Setter

	public int getPcode() {
		return pcode;
	}


	public void setPcode(int pcode) {
		this.pcode = pcode;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getPsize() {
		return psize;
	}


	public void setPsize(String psize) {
		this.psize = psize;
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


	public int getPprice() {
		return pprice;
	}


	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getPffile() {
		return pffile;
	}

	public void setPffile(String pffile) {
		this.pffile = pffile;
	}

	public String getPsfile() {
		return psfile;
	}

	public void setPsfile(String psfile) {
		this.psfile = psfile;
	}

	public String getPbfile() {
		return pbfile;
	}

	public void setPbfile(String pbfile) {
		this.pbfile = pbfile;
	}
	
	
	
}
