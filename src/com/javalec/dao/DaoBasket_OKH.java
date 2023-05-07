package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoProduct_OKH;
import com.javalec.util.ShareVar;

public class DaoBasket_OKH {
	// Field
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	public String loginid;
	public int pcode;

	// Constructor
	public DaoBasket_OKH() {

	}
	
	

	public DaoBasket_OKH(String loginid, int pcode) {
		super();
		this.loginid = loginid;
		this.pcode = pcode;
	}



	public DaoBasket_OKH(String loginid) {
		super();
		this.loginid = loginid;
	}

	// Method
	
	//	inner table 띄우기
	public ArrayList<DtoProduct_OKH> basketList() {
		ArrayList<DtoProduct_OKH> beanList = new ArrayList<DtoProduct_OKH>();
		
		String query = "SELECT p.pname, p.pbrand, p.pcolor, p.psize, p.pprice, p.pcode" + " from product p, basket b, user u"
				+ " where b.buid = u.uid and b.bpcode = p.pcode " + " and u.uid = '" + loginid + "'";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				String wpname = rs.getString(1);
				String wpbrand = rs.getString(2);
				String wpcolor = rs.getString(3);
				String wpsize = rs.getString(4);
				int wpprice = rs.getInt(5);
				int wpcode = rs.getInt(6);

				DtoProduct_OKH dto = new DtoProduct_OKH(wpname, wpbrand, wpcolor, wpsize, wpprice, wpcode);
				beanList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	// 삭제!
	public void deleteAction() {
		String query = "delete from basket b where b.buid = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			PreparedStatement pstmt_mysql = conn_mysql.prepareStatement(query);
			
			pstmt_mysql.setString(1, loginid);

	        pstmt_mysql.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	//	삽입!
	public void insertBasket() {
		String query = "insert into basket (buid, bpcode, bqty, bidate)";
		String query1 = " values (?, ?, ?, now())";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			PreparedStatement pstmt_mysql = conn_mysql.prepareStatement(query+query1);
			
			pstmt_mysql.setString(1, loginid);
			pstmt_mysql.setInt(2, pcode);
			pstmt_mysql.setInt(3, 1);

	        pstmt_mysql.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


}
