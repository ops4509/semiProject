package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.javalec.dto.DtoProduct_OKH;
import com.javalec.util.ShareVar;

public class DaoBuying_OKH {
	// Field
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	private String loginid;
	private int count;

	// Constructor
	public DaoBuying_OKH() {

	}
	
	public DaoBuying_OKH(String loginid, int count) {
		super();
		this.loginid = loginid;
		this.count = count;
	}


	// Method
	public void buyingList() {
	
		String query = "Insert into buying (buuid, bupcode, buqty, budate)";
		String query1 = " values (?,?,1,now())";
		PreparedStatement pstmt_mysql=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			DaoBasket_OKH dao = new DaoBasket_OKH(loginid);
			
			ArrayList<DtoProduct_OKH> dtoList = dao.basketList();
			System.out.println(dtoList.get(1).getPcode());
			for(int i=1; i<= count; i++) {
			pstmt_mysql = conn_mysql.prepareStatement(query+query1);
			pstmt_mysql.setString(1, loginid);
			pstmt_mysql.setInt(2, dtoList.get(i).getPcode());
			System.out.println(dtoList.get(i).getPcode());
			
			}
			pstmt_mysql.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
}
