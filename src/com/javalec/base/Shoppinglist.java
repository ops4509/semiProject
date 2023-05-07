package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoBasket_OKH;
import com.javalec.dao.DaoProduct_OKH;
import com.javalec.dao.DaoUser_OKH;
import com.javalec.dto.DtoProduct_OKH;
import com.javalec.dto.DtoUser_OKH;
import com.javalec.util.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Shoppinglist extends JFrame {

	private JPanel contentPane;
	private JLabel lblHello;
	private JScrollPane scrollPane;
	private JTable tableList;
	private JLabel lbl;
	private JScrollPane scrollPane_1;
	private JTable tablePic;
	private JButton btnBasket;
	private JButton btnPurchase;
	public String loginname;
	public String loginid;

	// table
	private final DefaultTableModel outerTableList = new DefaultTableModel();
	private final DefaultTableModel outerTablePic = new DefaultTableModel();

	ArrayList<DtoProduct_OKH> beanList = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shoppinglist frame = new Shoppinglist();
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
	public Shoppinglist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableListInit();
				tablePicInit();
				searchAction();
				
			}
		});
		setTitle("상품목록");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblHello());
		contentPane.add(getLbl());
		contentPane.add(getScrollPane());
		contentPane.add(getScrollPane_1());
		contentPane.add(getBtnBasket());
		contentPane.add(getBtnPurchase());
	}

	private JLabel getLblHello() {
		if (lblHello == null) {
			lblHello = new JLabel(login() + "님, 안녕하세요!");
			lblHello.setVerticalAlignment(SwingConstants.BOTTOM);
			lblHello.setHorizontalAlignment(SwingConstants.TRAILING);
			lblHello.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			lblHello.setBounds(250, 15, 110, 30);
		}
		return lblHello;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 110, 315, 315);
			scrollPane.setViewportView(getTableList());
		}
		return scrollPane;
	}

	private JTable getTableList() {
		if (tableList == null) {
			tableList = new JTable() {
			};
			tableList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
					screenPartition();
				}
			});
			tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableList.setModel(outerTableList); // <<
		}
		return tableList;
	}

	private JLabel getLbl() {
		if (lbl == null) {
			lbl = new JLabel("쇼핑 목록");
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lbl.setBounds(45, 60, 100, 25);
		}
		return lbl;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(44, 455, 316, 151);
			scrollPane_1.setViewportView(getTablePic());
		}
		return scrollPane_1;
	}

	private JTable getTablePic() {
		if (tablePic == null) {
			tablePic = new JTable() {

				// 가장 중요한 부분!
				public Class getColumnClass(int column) { // <<<
					return (column <= 3) ? Icon.class : Object.class; // <<<
				}
			};
			// Table의 목록은 다 원래 오브젝트, 그래서 Icon을 명시해줌.

			tablePic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablePic.setModel(outerTablePic); // <<
			tablePic.setRowHeight(104);

		}

		return tablePic;
	}

	private JButton getBtnBasket() {
		if (btnBasket == null) {
			btnBasket = new JButton("장바구니");
			btnBasket.setEnabled(false);
			btnBasket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changetoBasket();
					basketInsert();
				}
			});
			btnBasket.setBounds(45, 635, 95, 40);
		}
		return btnBasket;
	}

	private JButton getBtnPurchase() {
		if (btnPurchase == null) {
			btnPurchase = new JButton("바로구매");
			btnPurchase.setEnabled(false);
			btnPurchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					purchase();
				}
			});
			btnPurchase.setBounds(264, 635, 95, 40);
		}
		return btnPurchase;
	}

	// function
	
	// login- user 이름 가져오기
	private String login() {
		DaoUser_OKH dao = new DaoUser_OKH();
		loginname = dao.loginname();
		return loginname;

	}
	
	private String loginid() {
		DaoUser_OKH dao = new DaoUser_OKH();
		loginid = dao.loginid();
		return loginid;

	}
	
	// input 제한
	private void screenPartition() {
		if(tableList.getSelectedRow()>=0) {
			btnBasket.setEnabled(true);
			btnPurchase.setEnabled(true);
		}
	}
	
	
	// tableList 정리
	private void tableListInit() {
		outerTableList.addColumn("코드");
		outerTableList.addColumn("이름");
		outerTableList.addColumn("사이즈");
		outerTableList.addColumn("색깔");
		outerTableList.addColumn("브랜드");
		outerTableList.addColumn("가격");
		outerTableList.addColumn("수량");

		outerTableList.setColumnCount(7);

		int i = outerTableList.getRowCount();

		for (int j = 0; j < i; j++) {
			outerTableList.removeRow(0);
		}

		tableList.setAutoResizeMode(tableList.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = tableList.getColumnModel().getColumn(vColIndex);
		int width = 30;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 55;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 40;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 45;
		col.setPreferredWidth(width);

		vColIndex = 4;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 50;
		col.setPreferredWidth(width);

		vColIndex = 5;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 55;
		col.setPreferredWidth(width);

		vColIndex = 6;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 30;
		col.setPreferredWidth(width);
	}

	// tablePic 정리

	private void tablePicInit() {
		outerTablePic.addColumn("전면사진");
		outerTablePic.addColumn("후면사진");
		outerTablePic.addColumn("측면사진");

		outerTablePic.setColumnCount(3);

		int i = outerTablePic.getRowCount();

		for (int j = 0; j < i; j++) {
			outerTableList.removeRow(0);
		}

		tablePic.setAutoResizeMode(tableList.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = tablePic.getColumnModel().getColumn(vColIndex);
		int width = 104;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = tablePic.getColumnModel().getColumn(vColIndex);
		width = 104;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = tablePic.getColumnModel().getColumn(vColIndex);
		width = 104;
		col.setPreferredWidth(width);
	}

	// Row Click

	private void tableClick() {
		beanList = new ArrayList<DtoProduct_OKH>();

		int i = tableList.getSelectedRow();

		String wkSequence = (String) tableList.getValueAt(i, 0);
		int pccode = Integer.parseInt(wkSequence);

		DaoProduct_OKH dao = new DaoProduct_OKH(pccode);
		beanList = dao.tableclick();

		int listCount = beanList.size();

		for (int j = 0; j < listCount; j++) {
			ImageIcon pfpic = new ImageIcon("./" + beanList.get(j).getPsfile());
			ImageIcon pbpic = new ImageIcon("./" + beanList.get(j).getPffile());
			ImageIcon pspic = new ImageIcon("./" + beanList.get(j).getPbfile());
			Object[] tempData = { pfpic, pbpic, pspic }; // -> 문제가 있다.
			outerTablePic.addRow(tempData);

		}
	}

	// DB의 Table에서 화면의 Table에 들어갈 정보 가져오기 -> DB관련 내용은 하나도 없다. 단, 불러내서 화면구현만 하고 있을뿐.
	private void searchAction() {
		DaoProduct_OKH dao = new DaoProduct_OKH();
		ArrayList<DtoProduct_OKH> dtoList = dao.selectList();
		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
			String[] qTxt = { Integer.toString(dtoList.get(i).getPcode()), dtoList.get(i).getPname(),
					dtoList.get(i).getPsize(), dtoList.get(i).getPcolor(), dtoList.get(i).getPbrand(),
					Integer.toString(dtoList.get(i).getPprice()) };
			outerTableList.addRow(qTxt);
		}
	}
	
	// 바로 구매 문구 표시
	public void purchase(){
		String oname, osize, ocolor, obrand, oprice;
		
		int i = tableList.getSelectedRow();
		String pCode = (String) tableList.getValueAt(i, 0);
		
		DaoProduct_OKH dao = new DaoProduct_OKH(loginid(), Integer.parseInt(pCode));
		dao.purchasetoOrder();
		
		
		DtoProduct_OKH dto = dao.purchase();
		
		oname =dto.getPname();
		obrand =dto.getPbrand();
		ocolor =dto.getPcolor();
		osize =dto.getPsize();
		oprice =Integer.toString(dto.getPprice()) ;
		
		JOptionPane.showMessageDialog(null, login() + "님!\n "+obrand+"브랜드 의 "+oname+"<"+ocolor+">의 "+osize+"사이즈를 "+oprice+"에 구매하셨습니다!\n감사합니다!");
		
	}
	
	//	장바구니 이동
	public void basketInsert() {
		int i = tableList.getSelectedRow();
		String pCode = (String) tableList.getValueAt(i, 0);
		
		DaoUser_OKH daouser = new DaoUser_OKH();
		DaoBasket_OKH daobasket = new DaoBasket_OKH(daouser.loginid(),Integer.parseInt(pCode));
		daobasket.insertBasket();
	}
	
	//	장바구니 화면 이동
	public void changetoBasket() {
		setVisible(false);
		Basket basket = new Basket();
		
		basket.setVisible(true);
	}
	
}
