package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoOutbound_OKH;
import com.javalec.util.ShareVar;

public class DaoOutbound_OKH {
	// field
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	int obpcode;
	int obqty;

	// constructor
	public DaoOutbound_OKH() {

	}

	public DaoOutbound_OKH(int obpcode) {
		super();
		this.obpcode = obpcode;
	}

	// Method

	public ArrayList<DtoOutbound_OKH> minusQty() {
		ArrayList<DtoOutbound_OKH> beanList = new ArrayList<DtoOutbound_OKH>();

		String query = "Select obqty from outbound";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				int ibqty = rs.getInt(1);

				DtoOutbound_OKH dto = new DtoOutbound_OKH(ibqty);
				beanList.add(dto);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

}
