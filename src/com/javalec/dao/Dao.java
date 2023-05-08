package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.dto.Dto;
import com.javalec.util.ShareVar;

public class Dao {

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;
	
	int seqno;
	String name;
	String telno;
	String address;
	String email;
	String relation;
	
	String conname;
	String condata;
	
	public Dao() {
		// TODO Auto-generated constructor stub
	}

	public Dao(int seqno, String name, String telno, String relation) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.telno = telno;
		this.relation = relation;
	}
		
	public Dao(String name, String telno, String address, String email, String relation) {
		super();
		this.name = name;
		this.telno = telno;
		this.address = address;
		this.email = email;
		this.relation = relation;
	}
	
	public Dao(int seqno) {
		super();
		this.seqno = seqno;
	}

	public Dao(int seqno, String name, String telno, String address, String email, String relation) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.telno = telno;
		this.address = address;
		this.email = email;
		this.relation = relation;
	}
	
	public Dao(String conname, String condata) {
		super();
		this.conname = conname;
		this.condata = condata;
	}

	// 초기화면 검색 결과를 Table에서 불러오자
	public ArrayList<Dto> selectList(){
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		
		String whereDefault = "select seqno, name, telno, relation from userinfo";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			
			while(rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkName = rs.getString(2);
				String wkTelno = rs.getString(3);
				String wkRelation = rs.getString(4);
				
				Dto dto = new Dto(wkSeq, wkName, wkTelno, wkRelation);
				dtoList.add(dto);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dtoList;
	}

	public ArrayList<Dto> conditionList(){
		ArrayList<Dto> dtoList = new ArrayList<Dto>();
		
		String whereDefault = "select seqno, name, telno, relation from userinfo";
		String whereDefault1 = " where " + conname + " like '%" + condata + "%'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1);
			
			while(rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkName = rs.getString(2);
				String wkTelno = rs.getString(3);
				String wkRelation = rs.getString(4);
				
				Dto dto = new Dto(wkSeq, wkName, wkTelno, wkRelation);
				dtoList.add(dto);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dtoList;
	}

	
	
	
	public boolean updateAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "update userinfo set name = ?, telno = ?, address = ?,";
			String query1 = "email=?, relation=? where seqno = ?";
			
			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, name.trim());
			ps.setString(2, telno.trim());
			ps.setString(3, address.trim());
			ps.setString(4, email.trim());
			ps.setString(5, relation.trim());
			ps.setInt(6, seqno);
			
			ps.executeUpdate();
			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public boolean deleteAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "delete from userinfo where seqno = ?";
			
			ps = conn_mysql.prepareStatement(query);
			ps.setInt(1, seqno);
			
			ps.executeUpdate();
			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	
	public boolean insertAction() {
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "insert into userinfo (name, telno, address, email, relation)";
			String query1 = " values (?, ?, ?, ?, ?)";
			
			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, name.trim());
			ps.setString(2, telno.trim());
			ps.setString(3, address.trim());
			ps.setString(4, email.trim());
			ps.setString(5, relation.trim());
			
			ps.executeUpdate();
			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Dto tableClick() {
		Dto dto = null;
		
		String whereDefault = "select seqno, name, telno, address, email, relation from userinfo";
		String whereDefault1 = " where seqno =" + seqno;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault + whereDefault1);
			
			if(rs.next()) {
				int wkSeq = rs.getInt(1);
				String wkName = rs.getString(2);
				String wkTelno = rs.getString(3);
				String wkAddress = rs.getString(4);
				String wkEmail = rs.getString(5);
				String wkRelation = rs.getString(6);
				
				dto = new Dto(wkSeq, wkName, wkTelno, wkAddress, wkEmail, wkRelation);
			}

			conn_mysql.close();
						
		}catch(Exception e) {
			e.printStackTrace();
		}

		return dto;

	}
	
	
	
	
	
}
