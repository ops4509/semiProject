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
	private ArrayList<DtoProduct_OKH> dtoList ;

	// Constructor
	public DaoBuying_OKH() {

	}
	
	public DaoBuying_OKH(String loginid, int count,ArrayList<DtoProduct_OKH> dtoList) {
		super();
		this.loginid = loginid;
		this.count = count;
		this.dtoList = dtoList; 
	}


	// Method
	public void buyingList() {
	
		String query = "Insert into buying (buuid, bupcode, buqty, budate)";
		String query1 = " values (?,?,1,now())";
		PreparedStatement pstmt_mysql=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			
			System.out.println(dtoList.get(1).getPcode());
			for(int i=1; i<= count-1; i++) {
			pstmt_mysql = conn_mysql.prepareStatement(query+query1);
			pstmt_mysql.setString(1, loginid);
			pstmt_mysql.setInt(2, dtoList.get(i).getPcode());
			pstmt_mysql.executeUpdate();
			
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
}
