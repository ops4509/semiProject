package com.javalec.dto;

public class DtoOutbound {
	
	
	//Field
	
	int onum;
	
	String pname;
	int pqty;
	int pprice;
	
	String uid;
	String uaddress;
	
	int sales;
	String orderdate;

	int pcode;
	String psize;
	String pcolor;
	String uname;
	String uphone;
	String pbrand;
	//constructor
	
	
	public DtoOutbound() {
		super();
	}

	public DtoOutbound(int onum, String orderdate, String pname, int pqty, int pprice, String uid, String uaddress, int sales) {
		super();
		this.onum = onum;
		this.orderdate =orderdate;
		this.pname = pname;
		this.pqty = pqty;
		this.pprice = pprice;
		this.uid = uid;
		this.uaddress = uaddress;
		this.sales = sales;
	}

	//(int onum, String odate, String pname, String psize, String pcolor, int oqty, int pprice, int sales String uid, String uname,String address)
	
	
	public DtoOutbound(int onum, String orderdate, String pname, String psize, String pcolor, int pqty, int pprice, int sales,
			String uid, String uname,String uaddress) {
		super();
		this.onum = onum;
		this.orderdate = orderdate;
		this.pname = pname;
		this.psize = psize;
		this.pcolor = pcolor;
		this.pqty = pqty;
		this.pprice = pprice;
		this.uid = uid;
		this.uname = uname;
		this.uaddress = uaddress;
		this.sales = sales;
	}
	
	public DtoOutbound(int onum, String odate, int oqty, 
			String uid, String uname, String uphone, String uaddress, 
			int pcode, String pname, String pbrand, String pcolor, String psize, int pprice ) {
		super();
		
		this.onum = onum;
		this.orderdate = odate;
		this.pqty=oqty;
		this.uid = uid;
		this.uname = uname;
		this.uphone = uphone;
		this.uaddress=uaddress;
		this.pcode=pcode;
		this.pname=pname;
		this.pbrand=pbrand;
		this.pcolor=pcolor;
		this.psize=psize;
		this.pprice=pprice;
		
	}
	
	
	
	
	
	
	
	
	//Method
	
	


	public int getOnum() {
		return onum;
	}

	

	public void setOnum(int onum) {
		this.onum = onum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPqty() {
		return pqty;
	}

	public void setPqty(int pqty) {
		this.pqty = pqty;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String oderdate) {
		this.orderdate = oderdate;
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}



	public String getPbrand() {
		return pbrand;
	}

	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}

	public int getPcode() {
		return pcode;
	}

	public void setPcode(int pcode) {
		this.pcode = pcode;
	}



	
	
	
	
	
	

} //DtoOutbound 의 엔드게
