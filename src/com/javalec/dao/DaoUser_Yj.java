package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.util.ShareVar;

public class DaoUser_Yj {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	String uid; // 아이디
	String upw; // 비밀번호
	String uaddress; // 주소
	String uphone; // 번호
	String uenrolldate; // 가입 년도 날짜
	String uname; // 유저이름
	
	String aid; // 관리자 아이디
	String apw; // 관리자 비밀번호
	
	



	public DaoUser_Yj() {
		// TODO Auto-generated constructor stub
	}

	public DaoUser_Yj(String uid, String upw, String uaddress, String uphone, String uenrolldate, String uname) {
		super();
		this.uid = uid;
		this.upw = upw;
		this.uaddress = uaddress;
		this.uphone = uphone;
		this.uenrolldate = uenrolldate;

		this.uname = uname;
	}

	public DaoUser_Yj(String uid, String upw) {
		super();
		this.uid = uid;
		this.upw = upw;
	}



	public DaoUser_Yj(String uid) {
		super();
		this.uid = uid;
	}
	
	
	
	
	/*
	 * public void exception() {
	 * 
	 * } uid upw
	 */

	// 로그인

	public boolean loginAction() {

		String whereDefault = "select uid, upw from user";
		PreparedStatement ps = null;
		int token=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			
			while (rs.next()) {

				if (rs.getString(1).equals(uid)&&rs.getString(2).equals(upw)) {
					
					token = 1;
				}
			}
			
			if(token == 1) {
				String query = "update userlogin set ulstatus = 1 where ulid = '" + uid + "'";
				ps = conn_mysql.prepareStatement(query);
				
				ps.executeUpdate();
			}else {
				return false;
			}
				
			conn_mysql.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	// 회원가입

	public boolean insertAction() {

		String whereDefault = "select uid, upw from user";

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Connection conn_mysql1 = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			// Connection conn_mysql1 = DriverManager.getConnection(url_mysql, id_mysql,
			// pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			String query = "insert into user (uid, upw, uaddress, uphone, uenrolldate, uname)";
			String query1 = " values (?, ?, ?, ?, ?, ?)";
			
			String query2 = "insert into userlogin (ulid, ulstatus)";
			String query3 = " values (?, ?)";
			// int token = 0;
			ps = conn_mysql.prepareStatement(query + query1);
			ps1 = conn_mysql1.prepareStatement(query2 + query3);
			// ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			/*while(rs.next()) {
				if (rs.getString(1).equals(uid)) {
					token = 0;
				} else {
					token = 1;
				}
			}*/
			/*
			if(token == 1) {
				
			}*/
			ps.setString(1, uid.trim());
			ps.setString(2, upw.trim());
			ps.setString(3, uaddress.trim());
			ps.setString(4, uphone.trim());
			ps.setString(5, uenrolldate.trim());
			ps.setString(6, uname.trim());

		
			ps1.setString(1, uid.trim());
			ps1.setInt(2, 0);
		
			
			ps.executeUpdate();
			ps1.executeUpdate();
		

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 아이디 중복 확인
	
	public boolean doubleCheckAction() {
		// TODO Auto-generated method stub
			
		String whereDefault = "select uid from user";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			// Connection conn_mysql1 = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault);



			while (rs.next()) {
				if (rs.getString(1).equals(uid)) {
					return false;
				}
				else {
					
				}
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
		
		return true;
	}

	// 관리자 로그인
	
	public boolean adminLoginAction() {

		String whereDefault = "select aid, apw from admin";
		PreparedStatement ps = null;
		int token = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			while (rs.next()) {
				
				
				if (rs.getString(1).equals(uid) && rs.getString(2).equals(upw)) {

					token = 1;
				}
			
			}
		
			if(token == 1) {
				String query = "update adminlogin set alstatus = 1 where alid = '" + uid + "'";
				ps = conn_mysql.prepareStatement(query);
				
				ps.executeUpdate();
				
				
			}else {
				return false;
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	
}
