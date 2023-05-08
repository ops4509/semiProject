package com.javalec.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.RepaintManager;

import com.javalec.dto.DtoOutbound;
import com.javalec.util.ShareVar;

public class DaoOutbound {

	// DB에 접속하기 위한 url / id / pw 가져와서 변수에 저장하기.

	private final String url_mysql = ShareVar.DBName;
	private final String id_mysql = ShareVar.DBUser;
	private final String pw_mysql = ShareVar.DBPass;

	// Field

	int seqno;
	int onum;
	String tObaid;
	int tObonum;
	int tObpcode;
	int tObqty;

	// constructor
	public DaoOutbound() {
		super();
	}

	public DaoOutbound(int onum) {
		super();
		this.onum = onum;
	}

	public DaoOutbound(String tObaid, int tObonum, int tObpcode, int tObqty) {
		super();
		this.tObaid = tObaid;
		this.tObonum = tObonum;
		this.tObpcode = tObpcode;
		this.tObqty = tObqty;
	}

	// 초기화면 검색 결과를 Table에서 불러오자
	public ArrayList<DtoOutbound> selectList() {
		ArrayList<DtoOutbound> dtoList = new ArrayList<DtoOutbound>();

		String whereDefault1 = "select onum, ouid, opcode, oqty, odate from porder";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			Connection conn_mysql1 = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql1 = conn_mysql1.createStatement();

			Connection conn_mysql2 = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql2 = conn_mysql2.createStatement();

			ResultSet rs1 = stmt_mysql.executeQuery(whereDefault1);

			while (rs1.next()) {

				// 출고테이블에 접속해서 같은 주문번호가 처리되었는지 확인하기.
				// 처리되었으면 끊고 위로 가기.
				// 처리 안되었으면 그대로 진행.
				// try문안에서 ResultSet이 널값 에러를 발생했다면, 주문번 겹치는게 없다는 이야기. 즉 try 아래 구문 실행.
				// try문안에서 ResultSet이 널값 에러 발생하지 않았다면, 주문번호 겹치는게 있다는 이야기. 즉 와일문 아래 실행 안하고 조으로
				// 돌아감.
				int tempIndex = rs1.getInt(1);
				System.out.println(tempIndex + "작업 시작 ");

				Connection conn_tempIndex = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_tempIndex = conn_tempIndex.createStatement();
				String check_tempIndex = "select obonum from outbound where obonum = " + tempIndex;
				ResultSet rs_tempIndex = stmt_tempIndex.executeQuery(check_tempIndex);

				//rs_tempIndex.next()로 커서 찍어주고.
				// try문에서 rs_tempIndex의 값을 가져옴
				// 에러가 발생하지 않으면 whil(rs1.next()) 으로 돌아감.
				// 에러 발생시 무시하고 아래 코드 이어서 진행.
				
				//System.out.println(rs_tempIndex.next());
				
				
				try {
					rs_tempIndex.next();
					int a = rs_tempIndex.getInt(1);
					continue;

				} catch (Exception e) {
				}
				//System.out.println("a 값은 : "+ a);

				System.out.println(tempIndex+"번은 아직 처리 안되어서 하면에 표시되어야. 결과는?\n\n");

				int obonum = rs1.getInt(1); // 주문번호저장
				String obouid = rs1.getString(2); // 주문자 아이디 저장
				String obopcode = rs1.getString(3); // 주문 코드 저장
				int oboqty = rs1.getInt(4); // 주문수량저장

				// 주문일자를 String형태로 obodate 저장 (yy/mm/dd형태로 저장하기 위해서.)
				Timestamp orderdate = Timestamp.valueOf(rs1.getString(5));
				LocalDateTime orddate = orderdate.toLocalDateTime();
				DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yy/MM/dd");
				String obodate = orddate.format(dFormatter);

				String obopname = "";
				int obopprice = 0;
				String obouaddress = "";

				// obopcode를 가지고 product 테이블에서 pname 가져와서 저장하기.
				String forproduct = "Select pcode, pname, pprice from product where pcode = " + obopcode;
				ResultSet rs_forproduct = stmt_mysql1.executeQuery(forproduct);

				while (rs_forproduct.next()) {
					obopname = rs_forproduct.getString(2);
					obopprice = rs_forproduct.getInt(3);
				}

				// obouid를 가지고 user 테이블에서 uaddress 가져오
				String foruaddress = "Select uid, uaddress from user where uid = '" + obouid + "'";
				ResultSet rs_foruser = stmt_mysql2.executeQuery(foruaddress);

				// 가져온 정보들을 dto에 넣어서 dt List에 추가하기.
				while (rs_foruser.next()) {
					obouaddress = rs_foruser.getString(2);
				}

				DtoOutbound dto = new DtoOutbound(obonum, obodate, obopname, oboqty, obopprice, obouid, obouaddress,
						oboqty * obopprice);
				dtoList.add(dto);

			} // dtoList 를 만들기 위한 while문 완료 .

			conn_mysql.close();
			conn_mysql1.close();
			conn_mysql2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dtoList;
	}

	// 테이블 클릭시 tf에 상세정보 나도록 하기.

	/*
	 * 필요한 정보들 : onum / odate / pname /pcolor / psize /oqty / pprice / uid / uname /
	 * uaddress obInnertable에 있는 정보는 : onum / odate / pname / oqty / pprice / sales
	 * / uid / uaddress 추가로 db에서 뽑아야와 하는 정보 : uname / udate 다시한번
	 * 
	 * onum으로 odate userid pcode oqty 가져오기 pcode로 pname psize pcolor pprice 가져오기
	 * uid로 uname uaddress 가져오기
	 * 
	 * 리턴값은 dto이고 (int onum, String odate, String pname, String psize, String
	 * pcolor, int oqty, int pprice, int sales, String uid, String uname,String
	 * address)
	 */

	public DtoOutbound tableClick() {
		DtoOutbound dto = null;

		String usingOrder = "select onum, odate, ouid, opcode, oqty from porder where onum = " + onum;
		String usingProduct = "select pname, psize, pcolor, pprice from product where pcode = ";
		String usingUser = "select uid, uname, uaddress from user where uid = '";

		int obonum = 0;
		int obopcode = 0;
		int oboqty = 0;
		int obpprice = 0;
		int obsales = 0;
		String obodate = "";
		String obpname = "";
		String obpsize = "";
		String obpcolor = "";
		String obouid = "";
		String obouname = "";
		String obuaddress = "";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			Statement stmt_mysqlP = conn_mysql.createStatement();
			Statement stmt_mysqlU = conn_mysql.createStatement();

			ResultSet rsOrder = stmt_mysql.executeQuery(usingOrder);

			if (rsOrder.next()) {
				obonum = rsOrder.getInt(1);
				// obodate = rsOrder.getString(2);
				obouid = rsOrder.getString(3);
				obopcode = rsOrder.getInt(4);
				oboqty = rsOrder.getInt(5);

				// 주문일자를 String형태로 obodate 저장 (yy/mm/dd형태로 저장하기 위해서.)
				Timestamp orderdate = Timestamp.valueOf(rsOrder.getString(2));
				LocalDateTime orddate = orderdate.toLocalDateTime();
				DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("MM/dd");
				String obodate1 = orddate.format(dFormatter);

				
				// 현재 날짜 찍는 법
				LocalDateTime nowdate = LocalDateTime.now(); // 현재 날짜와 시간을 가져옴
				DateTimeFormatter nFormatter = DateTimeFormatter.ofPattern("MM/dd"); // 날짜와 시간을 출력할 포맷 설정
				String obodate2 = nowdate.format(nFormatter);

				Duration odateDur = Duration.between(orddate, nowdate);
				long durday = odateDur.toDays();

				obodate = durday + " 일전 ";

				ResultSet rsProduct = stmt_mysqlP.executeQuery(usingProduct + obopcode);
				ResultSet rsUser = stmt_mysqlU.executeQuery(usingUser + obouid + "'");

				if (rsProduct.next()) {
					obpname = rsProduct.getString(1);
					obpsize = rsProduct.getString(2);
					obpcolor = rsProduct.getString(3);
					obpprice = rsProduct.getInt(4);
					obsales = oboqty * obpprice;
				}

				if (rsUser.next()) {
					obouid = rsUser.getString(1);
					obouname = rsUser.getString(2);
					obuaddress = rsUser.getString(3);

				}

				dto = new DtoOutbound(obonum, obodate, obpname, obpsize, obpcolor, oboqty, obpprice, obsales, obouid,
						obouname, obuaddress);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;

	}

	// -------------------------obDetail화면 구현을 위함. 1_ShowdDetail을 위한
	// 코드--------------------------------------

	// ObMain에서 obdonum 가져와서 oder 테이블에 접속한다.
	// order 테이블에서 onum, opcode, ouid, odate, oqty 가져온다.
	// user 테이블에서 uid, uname, uphone, uaddress
	// product 테이블에서 pcode, pname, pbrand, pcolor, psize, pprice
	// DtoOutbound(onum, odate, oqty, ouid, uname, uphone, uaddress, pcode, pname,
	// pbrand, pcolor, psize, pprice );
	// DtoOutbound(int onum, String odate, int oqty, String ouid, String uname,
	// String uphone, String uaddress, int pcode, String pname, String pbrand,
	// String pcolor, String psize, int pprice );
	
	public DtoOutbound detailList(int i) {
		DtoOutbound dto = null;

		String usingOrder = "select onum, opcode, ouid, odate, oqty from porder where onum = " + i;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_order = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_order = conn_order.createStatement();

			Connection conn_user = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_user = conn_user.createStatement();

			Connection conn_product = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_product = conn_product.createStatement();

			ResultSet rsOrder = stmt_order.executeQuery(usingOrder);

			if (rsOrder.next()) {
				int onum = rsOrder.getInt(1);

				// 주문일자를 String형태로 obodate 저장 (yy/mm/dd형태로 저장하기 위해서.)
				Timestamp orderdate = Timestamp.valueOf(rsOrder.getString(4));
				LocalDateTime orddate = orderdate.toLocalDateTime();
				DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yy/MM/dd");

				String odate = orddate.format(dFormatter);
				int oqty = rsOrder.getInt(5);

				int pcode = 0;
				String pname = "";
				String pbrand = "";
				String pcolor = "";
				String psize = "";
				int pprice = 0;

				// obopcode를 가지고 product 테이블에서 제품 정보  가져와서 저장하기.
				String forproduct = "Select pcode, pname, pbrand, pcolor, psize, pprice, pfpic from product where pcode = "
						+ rsOrder.getInt(2);
				ResultSet rs_forproduct = stmt_product.executeQuery(forproduct);

				if (rs_forproduct.next()) {
					pcode = rs_forproduct.getInt(1);
					pname = rs_forproduct.getString(2);
					pbrand = rs_forproduct.getString(3);
					pcolor = rs_forproduct.getString(4);
					psize = rs_forproduct.getString(5);
					pprice = rs_forproduct.getInt(6);
					
					ShareVar.filename += 1;
					File obdfile = new File(Integer.toString(ShareVar.filename));
					FileOutputStream obdOutput = new FileOutputStream(obdfile);
					InputStream obdInput = rs_forproduct.getBinaryStream(7);
					byte[] obdbuffer = new byte[1024];
					while(obdInput.read(obdbuffer)>0){
						obdOutput.write(obdbuffer);
						
					}
					
					
					
					
				}

				// obouid를 가지고 user 테이블에서 String uid, String uname, String uphone, String
				// uaddress,
				String foruaddress = "Select uid, uname, uphone, uaddress from user where uid = '"
						+ rsOrder.getString(3) + "'";
				ResultSet rs_foruser = stmt_user.executeQuery(foruaddress);

				String uid = "";
				String uname = "";
				String uphone = "";
				String uaddress = "";

				if (rs_foruser.next()) {
					uid = rs_foruser.getString(1);
					uname = rs_foruser.getString(2);
					uphone = rs_foruser.getString(3);
					uaddress = rs_foruser.getString(4);

				}
				

				
				
				

				dto = new DtoOutbound(onum, odate, oqty, uid, uname, uphone, uaddress, pcode, pname, pbrand, pcolor,
						psize, pprice);

			}

			conn_order.close();
			conn_product.close();
			conn_user.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;

	}

	// -------------출고 작업을 위한 메소드 : insertOB-------------------
	public boolean insertOB(String tObaid, int tObonum, int tObpcode, int Obqty) {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			LocalDateTime currentDateTime = LocalDateTime.now();
			Timestamp obTimestamp = Timestamp.valueOf(currentDateTime);

			String insert_obsql = "insert into outbound (obaid, obonum, obpcode, obqty, obdate) values(?, ?, ?, ?, ?)";

			ps = conn_mysql.prepareStatement(insert_obsql);
			ps.setString(1, tObaid.trim());
			ps.setInt(2, tObonum);
			ps.setInt(3, tObpcode);
			ps.setInt(4, Obqty);
			ps.setTimestamp(5, obTimestamp);

			ps.executeUpdate();
			conn_mysql.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	// 주문 번호를 가지고 출고 테이블에 들어감.
	// 출고 테이블에 동일한 주문 번호가 있으면 False 반환 --> 버튼 비활성화
	// 출고 테이블에 동일한 주문 번호가 없으면 True 반환 --> 버튼 활성

	public int checkOR(int tempOnum) {

		DtoOutbound dto = null;

		String checkOBT = "select obonum from outbound where obonum = " + tempOnum;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_checkOBT = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_checkOBT = conn_checkOBT.createStatement();
			ResultSet rs_checkOBT = stmt_checkOBT.executeQuery(checkOBT);
			// System.out.println(rs_checkOBT.next());

			// if(rs_checkOBT.next())

			if (rs_checkOBT.next()) {
				return 0; // 값이 있으면 0
			} else {
				return 1; // 값이 없으면 1
			}

		} catch (NullPointerException e) {
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

			return 2;

		}

	}

} // DaoOutbound 의 엔드게임.
