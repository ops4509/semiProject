package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.javalec.util.ShareVar;

public class DaoUser_OKH {
	// table에 정보를 모아서 쌓아두는 것.

	// field
	public final String url_mysql = ShareVar.DBName;
	public final String id_mysql = ShareVar.DBUser;
	public final String pw_mysql = ShareVar.DBPass;
	public String loginname;
	public String loginid;

	int seq;

	// constructor

	public DaoUser_OKH() {

	}

	// Method
	// login 이름 가져오기
	public String loginname() {

		String loginQuery = "Select u.uname from user u, userlogin l where u.uid = l.ulid and l.ulstatus =1";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet lrs = stmt_mysql.executeQuery(loginQuery);

			if (lrs.next()) {
				loginname = lrs.getString(1);
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginname;
	}

	// login id 가져오기
	public String loginid() {

		String loginQuery = "Select ulid from userlogin where ulstatus =1";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet lrs = stmt_mysql.executeQuery(loginQuery);

			if (lrs.next()) {
				loginid = lrs.getString(1);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginid;
	}

	// logout 하기
	public void logout() {
		PreparedStatement ps = null;
		String logoutQuery = "update userlogin set ulstatus = 0 where ulstatus =? x";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ps = conn_mysql.prepareStatement(logoutQuery);
			ps.setInt(1, 1);

			ps.executeUpdate();
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
