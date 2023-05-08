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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.javalec.dto.DtoInbound;

import com.javalec.util.ShareVar;

public class DaoInbound {

	int ibpcode;
	int ibqty;
	int pcode;
	String psize;
	String pname;
	String pcolor;
	String pbrand;
	String ibdate;
	String pfpic;
	String pbpic;
	String pspic;
	String aid;

	FileInputStream file;

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	public DaoInbound() {

	}

	public DaoInbound(int pcode) {
		super();
		this.pcode = pcode;
	}

	public DaoInbound(int pcode, String pname, String pcolor, String pbrand, String psize) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.psize = psize;
	}

	public DaoInbound(String aid, int ibpcode, int ibqty) {
		super();
		this.ibpcode = ibpcode;
		this.ibqty = ibqty;
		this.aid = aid;
	}

	public DaoInbound(int ibpcode, int ibqty, int pcode, String psize, String pname, String pcolor, String pbrand,
			String ibdate, String pfpic, String pbpic, String pspic) {
		super();
		this.ibpcode = ibpcode;
		this.ibqty = ibqty;
		this.pcode = pcode;
		this.psize = psize;
		this.pname = pname;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.ibdate = ibdate;
		this.pfpic = pfpic;
		this.pbpic = pbpic;
		this.pspic = pspic;
	}

	public DaoInbound(int ibpcode, int ibqty, String ibdate) {
		super();
		this.ibpcode = ibpcode;
		this.ibqty = ibqty;
		this.ibdate = ibdate;
	}

	public DaoInbound(int ibpcode, int ibqty, int pcode, String psize, String pname, String pcolor, String pbrand,
			String ibdate) {
		super();
		this.ibpcode = ibpcode;
		this.ibqty = ibqty;
		this.pcode = pcode;
		this.psize = psize;
		this.pname = pname;
		this.pcolor = pcolor;
		this.pbrand = pbrand;
		this.ibdate = ibdate;
	}

	// --------- Function ----------

	// 1. 재고현황 페이지 제품리스트 항목 가져오기
	public ArrayList<DtoInbound> selectList() {
		ArrayList<DtoInbound> dtoList = new ArrayList<DtoInbound>();
		String whereDefault = "select pcode, pname, pcolor, pbrand, psize from product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			while (rs.next()) {
				int wkpcode = rs.getInt(1);
				String wkpname = rs.getString(2);
				String wkpcolor = rs.getString(3);
				String wkpbrand = rs.getString(4);
				String wkpsize = rs.getString(5);

				DtoInbound dtoInbound = new DtoInbound(wkpcode, wkpname, wkpcolor, wkpbrand, wkpsize);
				dtoList.add(dtoInbound);
			}
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	// 2. 입고페이지 Table 클릭시 DB내용 가져오는 기능
	public DtoInbound tableClick() {
		DtoInbound dto = null;
		String whereDefault = "select pcode, pname, pcolor, pbrand, psize, pfpic from product where pcode = " + pcode;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			if (rs.next()) {

				int ibstock = 0;
				int obstock = 0;

				// 1. 입고 테이블 접속해서 받아온 상품코드가 같은 상품코드의 모든 입고량 합구하기.
				// 2. 출고 테이블 접속해서 받아온 상품코드가 같은 상품의 모든 출고량 합 구하기.
				// 3. 2-1 한 값구하기
				// 4. DotInbound에 3번 추가하기.
				// 5. 메인에서 해당값 출력해주기.
				try {
					int tempTCpc = rs.getInt(1);
					String sql_IBstock = "select ibpcode, ibqty from inbound where ibpcode = " + tempTCpc;
					Connection conn_tempTCib = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
					Statement stmt_tempTCib = conn_tempTCib.createStatement();
					ResultSet rs_tempTCib = stmt_tempTCib.executeQuery(sql_IBstock);

					while (rs_tempTCib.next()) {

						ibstock = ibstock + rs_tempTCib.getInt(2);

					}
					conn_tempTCib.close();
					String sql_OBstock = "select obpcode, obqty from outbound where obpcode = " + tempTCpc;

					Connection conn_tempTCob = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
					Statement stmt_tempTCob = conn_tempTCob.createStatement();
					ResultSet rs_tempTCob = stmt_tempTCob.executeQuery(sql_OBstock);

					while (rs_tempTCob.next()) {

						obstock = obstock + rs_tempTCob.getInt(2);
					}
					conn_tempTCob.close();

				} catch (Exception e) {

				}

				int stock = ibstock - obstock;

				int wkpcode = rs.getInt(1);
				String wkpname = rs.getString(2);
				String wkpcolor = rs.getString(3);
				String wkpbrand = rs.getString(4);
				String wkpsize = rs.getString(5);

				ShareVar.filename += 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				rs.getBinaryStream(6);
				InputStream input = rs.getBinaryStream(6); // Data bass 가져오는 부분
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				dto = new DtoInbound(wkpcode, wkpsize, wkpname, wkpcolor, wkpbrand, stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

		}
    // 재고페이지 테이블 클릭시 현재 재고 가지고 오기  
	public int tableClick2() {
		String whereDefault = "select pcode, pname, pcolor, pbrand, psize, pfpic from product where pcode = " + pcode;
		int stock = 0;
		int ibstock = 0;
		int obstock = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);

			if (rs.next()) {
				// 1. 입고 테이블 접속해서 받아온 상품코드가 같은 상품코드의 모든 입고량 합구하기.
				// 2. 출고 테이블 접속해서 받아온 상품코드가 같은 상품의 모든 출고량 합 구하기.
				// 3. 2-1 한 값구하기
				// 4. DotInbound에 3번 추가하기.
				// 5. 메인에서 해당값 출력해주기.
				try {
					int tempTCpc = rs.getInt(1);
					String sql_IBstock = "select ibpcode, ibqty from inbound where ibpcode = " + tempTCpc;
					Connection conn_tempTCib = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
					Statement stmt_tempTCib = conn_tempTCib.createStatement();
					ResultSet rs_tempTCib = stmt_tempTCib.executeQuery(sql_IBstock);
					while (rs_tempTCib.next()) {
						ibstock = ibstock + rs_tempTCib.getInt(2);
						}conn_tempTCib.close();

					String sql_OBstock = "select obpcode, obqty from outbound where obpcode = " + tempTCpc;

					Connection conn_tempTCob = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
					Statement stmt_tempTCob = conn_tempTCob.createStatement();
					ResultSet rs_tempTCob = stmt_tempTCob.executeQuery(sql_OBstock);

					while (rs_tempTCob.next()) {
						obstock = obstock + rs_tempTCob.getInt(2);
						}
						conn_tempTCob.close();
						} catch (Exception e) {
						}
						stock = ibstock - obstock;
						}
						} catch (Exception e) {
							e.printStackTrace();
						}
						return stock;
						}

	// 3. 입고페이지에서 수량입 후 입고버튼을 누르면 DB에 입고등록 완료되는 기능
	public boolean insertAction() {
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			LocalDateTime Date = LocalDateTime.now();
			Timestamp time = Timestamp.valueOf(Date);
			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ''
			// HH:mm:ss");

			String query = "Insert into inbound (ibaid, ibpcode, ibqty, ibdate)";
			String query1 = " values(?,?,?,?)";

			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, aid);
			ps.setInt(2, ibpcode);
			ps.setInt(3, ibqty);
			ps.setTimestamp(4, time);
			// Main에서 받아온 input값을 스트림으로 받아서 DB에 넣어준다.

			ps.executeUpdate();
			conn_mysql.close();

			} catch (Exception e) {
			e.printStackTrace();
			return false;
			}
			return true;
			}

	// ----- End -----
}
