package com.javalec.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.javalec.dto.DtoProduct_OKH;
import com.javalec.util.ShareVar;

public class DaoProduct_OKH {

	// Fields
	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	int pcode;
	String pname;
	String psize;
	String pcolor;
	String pbrand;
	String pprice;
	String loginid;

	FileInputStream pfpic;
	FileInputStream pbpic;
	FileInputStream pspic;

	// Constructor

	public DaoProduct_OKH() {

	}

	// table click constructor
	public DaoProduct_OKH(String loginid, int pcode) {
		super();
		this.pcode = pcode;
		this.loginid = loginid;
	}

	public DaoProduct_OKH(int pcode) {
		super();
		this.pcode = pcode;

	}

	// Method

	// 초기 검색 화면 Table 에서 불러오기
	public ArrayList<DtoProduct_OKH> selectList() {
		ArrayList<DtoProduct_OKH> beanList = new ArrayList<DtoProduct_OKH>();

		String query = "Select pcode, pname, psize, pcolor, pbrand, pprice from shoesmarket.product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {
				int wpcode = rs.getInt(1);
				String wpname = rs.getString(2);
				String wpsize = rs.getString(3);
				String wpcolor = rs.getString(4);
				String wpbrand = rs.getString(5);
				int wpprice = rs.getInt(6);

				DtoProduct_OKH dto = new DtoProduct_OKH(wpcode, wpname, wpsize, wpcolor, wpbrand, wpprice);
				beanList.add(dto);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	// order로 넘기
	public void purchasetoOrder() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String query = "Insert into buying (buuid, bupcode, buqty, budate)";
			String query1 = " values (?, ?, 1, now()) ";

			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, loginid);
			ps.setInt(2, pcode);

			ps.executeUpdate();
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 바로구매 문구 보이기
	public DtoProduct_OKH purchase() {
		DtoProduct_OKH dto = null;

		String query = "select p.pname, p.pbrand, p.pcolor, p.psize, p.pprice " + " from product p" + " where pcode = "
				+ pcode;

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

				dto = new DtoProduct_OKH(wpname, wpbrand, wpcolor, wpsize, wpprice);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// Row Click시, table에 사진 노출
	public ArrayList<DtoProduct_OKH> getimg() {
		ArrayList<DtoProduct_OKH> beanList = new ArrayList<DtoProduct_OKH>();
		String whereDefault = "select pffile, pfpic from product";
		String whereDefault1 = " where pcode = " + pcode;
		// sql 문구 띄어쓰기 주의.

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1);

			while (rs.next()) {
				String wpffile = rs.getString(1);
				InputStream pfinput = rs.getBinaryStream(2);

				File pffile = new File("./" + wpffile);

				FileOutputStream pfoutput = new FileOutputStream(pffile);

				byte[] pfbuffer = new byte[1024];
				while (pfinput.read(pfbuffer) > 0) {
					pfoutput.write(pfbuffer);
				}

				DtoProduct_OKH dtopic = new DtoProduct_OKH(wpffile);
				beanList.add(dtopic);
			}
			conn_mysql.close();
		} catch (Exception e) {

			e.printStackTrace(); // Error가 걸리면, Error 코드가 보여준다. 만약프로젝트 오픈한다고 하면 다이얼로그를 보여준다.
		}
		return beanList;
	}

	// 사진 하나만 ImageIcon으로 가져오기
	public String getimgicon() {
		PreparedStatement ps = null;
		String wpffile = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql.cj가 mysql 8버젼부터 사용된거다.
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			String query = "select pffile, pfpic from product";
			;
			String query1 = " where pcode = ?";

			ps = conn_mysql.prepareStatement(query + query1);
			ps.setInt(1, pcode+1);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				wpffile = rs.getString(1);
				InputStream pfinput = rs.getBinaryStream(2);

				File pffile = new File("./" + wpffile);

				FileOutputStream pfoutput = new FileOutputStream(pffile);

				byte[] pfbuffer = new byte[1024];
				while (pfinput.read(pfbuffer) > 0) {
					pfoutput.write(pfbuffer);
				}

			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wpffile;

	}

}
