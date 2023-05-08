package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoInbound_OKH;
import com.javalec.dto.DtoProduct_OKH;
import com.javalec.util.ShareVar;

public class DaoInbound_OKH {
	// field
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	int ibpcode;
	int inqty;

	// constructor
	public DaoInbound_OKH() {

	}

	public DaoInbound_OKH(int ibpcode) {
		super();
		this.ibpcode = ibpcode;
	}

	// Method

	public ArrayList<DtoInbound_OKH> selectQty() {
		ArrayList<DtoInbound_OKH> beanList = new ArrayList<DtoInbound_OKH>();

		String query = "Select ibqty from inbound";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				int ibqty = rs.getInt(1);

				DtoInbound_OKH dto = new DtoInbound_OKH(ibqty);
				beanList.add(dto);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

}
