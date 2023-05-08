package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoOutbound;
import com.javalec.dto.DtoOutbound;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ObMain extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblCountorder;
	private JScrollPane scrollPane;
	private JTable obInnertable;
	private JButton btnOblogout;
	private JButton btnObdetail;
	private JButton btnObhome;
	private JLabel lblNewLabel_2;
	private JTextField tfObonum;
	private JLabel lblNewLabel_2_1;
	private JTextField tfObpname;
	private JLabel lblNewLabel_2_2;
	private JTextField tfObpqty;
	private JLabel lblNewLabel_2_3;
	private JTextField tfObuid;
	private JLabel lblNewLabel_2_4;
	private JTextField tfObuname;
	private JLabel lblNewLabel_2_4_1;
	private JTextField tfObuadress;
	private JLabel lblNewLabel_2_5;
	private JTextField tfObodate;
	private JLabel lblNewLabel_2_2_1;
	private JTextField tfObsale;

	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();

	// 전역변수 선
	String message = ""; // 뭐에 쓰는 거더라??
	private JLabel lblNewLabel_2_2_2;
	private JTextField tfObpcolor;
	private JLabel lblNewLabel_2_2_2_1;
	private JTextField tfObpsize;
	private JLabel lblNewLabel_2_2_3;
	private JTextField tfobpprice;

	public static int obdonum = 0;
	int selectedRow = -1;
	String tObaid = "강경구"; // 관리자 아이디 넣는 곳.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObMain frame = new ObMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ObMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit(); // 화면정리1. Table 틀 만들기 실행.
				searchAction(); // 화면정리2. DB의 Table에서 정보들 가져와서 화면의 Table 에서 보여주기.
				screenPartition(); // 화면정리3. 각 상황별 버튼 및 화면 활성화 규정.

			}

		});

		setTitle("신규 주문 현황");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblCountorder());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnOblogout());
		contentPane.add(getBtnObdetail());
		contentPane.add(getBtnObhome());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getTfObonum());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getTfObpname());
		contentPane.add(getLblNewLabel_2_2());
		contentPane.add(getTfObpqty());
		contentPane.add(getLblNewLabel_2_3());
		contentPane.add(getTfObuid());
		contentPane.add(getLblNewLabel_2_4());
		contentPane.add(getTfObuname());
		contentPane.add(getLblNewLabel_2_4_1());
		contentPane.add(getTfObuadress());
		contentPane.add(getLblNewLabel_2_5());
		contentPane.add(getTfObodate());
		contentPane.add(getLblNewLabel_2_2_1());
		contentPane.add(getTfObsale());
		contentPane.add(getLblNewLabel_2_2_2());
		contentPane.add(getTfObpcolor());
		contentPane.add(getLblNewLabel_2_2_2_1());
		contentPane.add(getTfObpsize());
		contentPane.add(getLblNewLabel_2_2_3());
		contentPane.add(getTfobpprice());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("신규 주문 건수 : ");
			lblNewLabel.setBounds(45, 29, 91, 16);
		}
		return lblNewLabel;
	}

	private JLabel getLblCountorder() {
		if (lblCountorder == null) {
			lblCountorder = new JLabel("______건");
			lblCountorder.setBounds(148, 29, 119, 16);
		}
		return lblCountorder;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 64, 315, 275);
			scrollPane.setViewportView(getObInnertable());
		}
		return scrollPane;
	}

	private JTable getObInnertable() {
		if (obInnertable == null) {
			obInnertable = new JTable();
			obInnertable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					obTableclick();
				}
			});
			obInnertable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// 구현 내용
					selectedRow = obInnertable.getSelectedRow();
					screenPartition(); // 화면정리3. 각 상황별 버튼 및 화면 활성화 규정.

				}
			});

			obInnertable.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			obInnertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}

		obInnertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		obInnertable.setModel(outerTable);
		return obInnertable;
	}

	private JButton getBtnOblogout() {
		if (btnOblogout == null) {
			btnOblogout = new JButton("로그아웃");
			btnOblogout.setBounds(45, 624, 91, 51);
		}
		return btnOblogout;
	}

	private JButton getBtnObdetail() {
		if (btnObdetail == null) {
			btnObdetail = new JButton("<html><center>주문 상세<br>(출고)</center></html>");
			btnObdetail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ObDetail obd = new ObDetail();
					obd.setVisible(true);

				}
			});
			btnObdetail.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			btnObdetail.setBounds(157, 624, 91, 51);
		}
		return btnObdetail;
	}

	private JButton getBtnObhome() {
		if (btnObhome == null) {
			btnObhome = new JButton("확인");
			btnObhome.setBounds(269, 624, 91, 51);
			btnObhome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return btnObhome;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("주문 번호 : ");
			lblNewLabel_2.setBounds(45, 367, 87, 16);
		}
		return lblNewLabel_2;
	}

	private JTextField getTfObonum() {
		if (tfObonum == null) {
			tfObonum = new JTextField();
			tfObonum.setBounds(120, 362, 83, 26);
			tfObonum.setHorizontalAlignment(SwingConstants.CENTER);
			tfObonum.setEditable(false);
			tfObonum.setColumns(10);
		}
		return tfObonum;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("제품명  :");
			lblNewLabel_2_1.setBounds(45, 395, 87, 16);
		}
		return lblNewLabel_2_1;
	}

	private JTextField getTfObpname() {
		if (tfObpname == null) {
			tfObpname = new JTextField();
			tfObpname.setBounds(119, 390, 241, 26);
			tfObpname.setHorizontalAlignment(SwingConstants.CENTER);
			tfObpname.setEditable(false);
			tfObpname.setColumns(10);
		}
		return tfObpname;
	}

	private JLabel getLblNewLabel_2_2() {
		if (lblNewLabel_2_2 == null) {
			lblNewLabel_2_2 = new JLabel("총 주문 수량 :");
			lblNewLabel_2_2.setBounds(45, 451, 87, 16);
		}
		return lblNewLabel_2_2;
	}

	private JTextField getTfObpqty() {
		if (tfObpqty == null) {
			tfObpqty = new JTextField();
			tfObpqty.setBounds(120, 446, 65, 26);
			tfObpqty.setHorizontalAlignment(SwingConstants.CENTER);
			tfObpqty.setEditable(false);
			tfObpqty.setColumns(10);
		}
		return tfObpqty;
	}

	private JLabel getLblNewLabel_2_3() {
		if (lblNewLabel_2_3 == null) {
			lblNewLabel_2_3 = new JLabel("고객 ID :");
			lblNewLabel_2_3.setBounds(45, 508, 87, 16);
		}
		return lblNewLabel_2_3;
	}

	private JTextField getTfObuid() {
		if (tfObuid == null) {
			tfObuid = new JTextField();
			tfObuid.setBounds(120, 503, 130, 26);
			tfObuid.setHorizontalAlignment(SwingConstants.CENTER);
			tfObuid.setEditable(false);
			tfObuid.setColumns(10);
		}
		return tfObuid;
	}

	private JLabel getLblNewLabel_2_4() {
		if (lblNewLabel_2_4 == null) {
			lblNewLabel_2_4 = new JLabel("고객 이름 :");
			lblNewLabel_2_4.setBounds(45, 541, 87, 16);
		}
		return lblNewLabel_2_4;
	}

	private JTextField getTfObuname() {
		if (tfObuname == null) {
			tfObuname = new JTextField();
			tfObuname.setBounds(120, 536, 130, 26);
			tfObuname.setHorizontalAlignment(SwingConstants.CENTER);
			tfObuname.setEditable(false);
			tfObuname.setColumns(10);
		}
		return tfObuname;
	}

	private JLabel getLblNewLabel_2_4_1() {
		if (lblNewLabel_2_4_1 == null) {
			lblNewLabel_2_4_1 = new JLabel("수령 주소 : ");
			lblNewLabel_2_4_1.setBounds(45, 577, 87, 16);
		}
		return lblNewLabel_2_4_1;
	}

	private JTextField getTfObuadress() {
		if (tfObuadress == null) {
			tfObuadress = new JTextField();
			tfObuadress.setBounds(120, 572, 240, 26);
			tfObuadress.setHorizontalAlignment(SwingConstants.CENTER);
			tfObuadress.setEditable(false);
			tfObuadress.setColumns(10);
		}
		return tfObuadress;
	}

	private JLabel getLblNewLabel_2_5() {
		if (lblNewLabel_2_5 == null) {
			lblNewLabel_2_5 = new JLabel("주문 시간 : ");
			lblNewLabel_2_5.setBounds(215, 367, 65, 16);
		}
		return lblNewLabel_2_5;
	}

	private JTextField getTfObodate() {
		if (tfObodate == null) {
			tfObodate = new JTextField();
			tfObodate.setBounds(274, 362, 76, 26);
			tfObodate.setHorizontalAlignment(SwingConstants.RIGHT);
			tfObodate.setEditable(false);
			tfObodate.setColumns(10);
		}
		return tfObodate;
	}

	private JLabel getLblNewLabel_2_2_1() {
		if (lblNewLabel_2_2_1 == null) {
			lblNewLabel_2_2_1 = new JLabel("총 주문 금액 :");
			lblNewLabel_2_2_1.setBounds(45, 482, 87, 16);
		}
		return lblNewLabel_2_2_1;
	}

	private JTextField getTfObsale() {
		if (tfObsale == null) {
			tfObsale = new JTextField();
			tfObsale.setBounds(120, 477, 130, 26);
			tfObsale.setHorizontalAlignment(SwingConstants.CENTER);
			tfObsale.setEditable(false);
			tfObsale.setColumns(10);
		}
		return tfObsale;
	}

	private JLabel getLblNewLabel_2_2_2() {
		if (lblNewLabel_2_2_2 == null) {
			lblNewLabel_2_2_2 = new JLabel("제품 색상 :");
			lblNewLabel_2_2_2.setBounds(43, 422, 69, 16);
		}
		return lblNewLabel_2_2_2;
	}

	private JTextField getTfObpcolor() {
		if (tfObpcolor == null) {
			tfObpcolor = new JTextField();
			tfObpcolor.setBounds(118, 417, 69, 26);
			tfObpcolor.setHorizontalAlignment(SwingConstants.CENTER);
			tfObpcolor.setEditable(false);
			tfObpcolor.setColumns(10);
		}
		return tfObpcolor;
	}

	private JLabel getLblNewLabel_2_2_2_1() {
		if (lblNewLabel_2_2_2_1 == null) {
			lblNewLabel_2_2_2_1 = new JLabel("사이즈 :");
			lblNewLabel_2_2_2_1.setBounds(215, 422, 47, 16);
		}
		return lblNewLabel_2_2_2_1;
	}

	private JTextField getTfObpsize() {
		if (tfObpsize == null) {
			tfObpsize = new JTextField();
			tfObpsize.setBounds(274, 417, 69, 26);
			tfObpsize.setHorizontalAlignment(SwingConstants.CENTER);
			tfObpsize.setEditable(false);
			tfObpsize.setColumns(10);
		}
		return tfObpsize;
	}

	private JLabel getLblNewLabel_2_2_3() {
		if (lblNewLabel_2_2_3 == null) {
			lblNewLabel_2_2_3 = new JLabel("제품 가격 :");
			lblNewLabel_2_2_3.setBounds(215, 446, 61, 16);
		}
		return lblNewLabel_2_2_3;
	}

	private JTextField getTfobpprice() {
		if (tfobpprice == null) {
			tfobpprice = new JTextField();
			tfobpprice.setBounds(274, 441, 76, 26);
			tfobpprice.setHorizontalAlignment(SwingConstants.CENTER);
			tfobpprice.setEditable(false);
			tfobpprice.setColumns(10);
		}
		return tfobpprice;
	}

	// -----------------------------Function-----------------------------------------------

	private void screenPartition() {

		if (selectedRow != -1) {
			btnObdetail.setEnabled(true);
		} else {
			btnObdetail.setEnabled(false);

		}
	}

	// 화면의 Table 정리 --------------------- Table 틀 만들기
	// --------------------------------
	private void tableInit() {
		outerTable.addColumn("주문번호");
		outerTable.addColumn("주문시간");
		outerTable.addColumn("주문모델");
		outerTable.addColumn("주문수량");
		outerTable.addColumn("모델가격");
		outerTable.addColumn("주문금액");
		outerTable.addColumn("고객ID");
		outerTable.addColumn("배송지");

		outerTable.setColumnCount(8);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}

		obInnertable.setAutoResizeMode(obInnertable.AUTO_RESIZE_OFF);

		// 주문번호 보여주는
		int vColIndex = 0;
		TableColumn col = obInnertable.getColumnModel().getColumn(vColIndex);
		int width = 50;
		col.setPreferredWidth(width);

		// 주문 날짜 보여주는
		vColIndex = 1;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);

		// 모델명보여주는 셀
		vColIndex = 2;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		// 주문 가격 보여주는 셀
		vColIndex = 3;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);

		// 주문 수량 보여주는 셀
		vColIndex = 4;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);

		// 주문 총 가격 보여주는 셀
		vColIndex = 5;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);

		// 주문자 아이디 보여주는 셀
		vColIndex = 6;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 70;
		col.setPreferredWidth(width);

		// 주문자 주소 보여주는
		vColIndex = 7;
		col = obInnertable.getColumnModel().getColumn(vColIndex);
		width = 200;
		col.setPreferredWidth(width);

	}

	// table 화명정리 2 ------------ 화면정리2. DB의 Table에서 정보들 가져와서 화면의 Table 에서
	// 보여주기.-----------------
	private void searchAction() {
		DaoOutbound dao = new DaoOutbound();
		ArrayList<DtoOutbound> dtoList = dao.selectList();
		int listCount = dtoList.size();
		lblCountorder.setText(Integer.toString(listCount) + "건"); // 상단에 현재 주문 건수 입력.

		for (int i = 0; i < listCount; i++) {
			String obonum = Integer.toString(dtoList.get(i).getOnum());
			String obopqty = Integer.toString(dtoList.get(i).getPqty());
			String obopprice = Integer.toString(dtoList.get(i).getPprice());
			String obosales = Integer.toString(dtoList.get(i).getSales());
			String orderdate = dtoList.get(i).getOrderdate();

			String[] qTxt = { obonum, dtoList.get(i).getOrderdate(), dtoList.get(i).getPname(), obopqty, obopprice,
					obosales, dtoList.get(i).getUid(), dtoList.get(i).getUaddress() };

			outerTable.addRow(qTxt);
			// System.out.println("order no : " + obonum);
			// System.out.println(dtoList.get(i).getOderdate()) ;

		}
		lblCountorder.setText(Integer.toString(listCount));
	}

	// 화면정리 3 -------------------------- 화면정리 3. 테이블이 클릭 되었을 때에만, btn 활성화
	// private void screenPartition() {
	// if (obInnertable.is

	// }

	/*
	 * 필요한 정보들 : onum / odate / pname /pcolor / psize /oqty / pprice / uid / uname /
	 * uaddress obInnertable에 있는 정보는 : onum / odate / pname / oqty / pprice / sales
	 * / uid / uaddress 추가로 db에서 뽑아야와 하는 정보 : uname / udate 다시한번
	 * 
	 * onum으로 odate userid pcode oqty 가져오기 pcode로 oname psize pcolor pprice 가져오기
	 * uid로 uname uddress 가져오기
	 * 
	 * 리턴값은 dto이고 (int onum, String odate, String pname, String psize, String
	 * pcolor, int oqty, int pprice, int sales, String uid, String uname,String
	 * address)
	 * 
	 * 
	 */

	private void obTableclick() {
		int i = obInnertable.getSelectedRow(); // obInnertable 에서 onum 뽑아내기1. 클릭한 row의 row번호
		String tcOnum = (String) obInnertable.getValueAt(i, 0); // 해당 row에서 주문번호 뽑
		int tclickOnum = Integer.parseInt(tcOnum); // 해당 주문 번호를 int값으로 변

		DaoOutbound dao = new DaoOutbound(tclickOnum);
		DtoOutbound dto = dao.tableClick();

		tfObonum.setText(Integer.toString(dto.getOnum()));
		tfObpname.setText(dto.getPname());
		tfObodate.setText(dto.getOrderdate());
		tfObsale.setText(Integer.toString(dto.getSales()));
		tfObpcolor.setText(dto.getPcolor());
		tfObpsize.setText(dto.getPsize() + " mm");
		tfObpqty.setText(Integer.toString(dto.getPqty()));
		tfobpprice.setText(Integer.toString(dto.getPprice()));
		tfObuid.setText(dto.getUid());
		tfObuname.setText(dto.getUname());
		tfObuadress.setText(dto.getUaddress());

		obdonum = dto.getOnum();

	}

}// end game
