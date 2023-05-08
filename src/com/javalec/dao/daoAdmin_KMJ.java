package com.javalec.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.base.MainLogin_Yj;
//import com.javalec.base.MainLogin_Yj;
import com.javalec.dto.dtoAdmin_KMJ;
import com.javalec.util.ShareVar;

public class daoAdmin_KMJ {
	
	//field
	String aid;
	String aname;
	String apw;
	String address;
	String aedate;
	String audate;
	String aemail;
	String aphone;
	
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	//constructor
	public daoAdmin_KMJ() {
		// TODO Auto-generated constructor stub
	}
	
	//method
	public ArrayList<dtoAdmin_KMJ> getInfo() {
		ArrayList<dtoAdmin_KMJ> dtoList = new ArrayList<dtoAdmin_KMJ>();
		//if loginid == aid 
		String query = "select aname, aid, aaddress, aemail, aphone from admin";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getString(2).equals("ops4509")) {
					String wkaname = rs.getString(1);
					String wkaid = rs.getString(2);
					String wkaddress = rs.getString(3);
					String wkaemail = rs.getString(4);
					String wkphone = rs.getString(5);
					
					dtoAdmin_KMJ dto = new dtoAdmin_KMJ(wkaname,wkaid,wkaddress,wkaemail,wkphone);
					dtoList.add(dto);
				}
			}
			
			conn_mysql.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dtoList;
	}
	//method
}
