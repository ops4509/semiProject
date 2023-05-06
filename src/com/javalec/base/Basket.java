package com.javalec.base;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoBasket_OKH;
import com.javalec.dao.DaoBuying_OKH;
import com.javalec.dao.DaoProduct_OKH;
import com.javalec.dao.DaoUser_OKH;
import com.javalec.dto.DtoProduct_OKH;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Basket extends JFrame {

	private JPanel contentPane;
	private JLabel lblHello;
	private JLabel lbl;
	private JScrollPane scrollPane;
	private JTable tableList;
	public String loginname;
	public String loginid;

	// table
	private final DefaultTableModel outerTableList = new DefaultTableModel();

//	
	private JLabel lblNewLabel;
	private JTextField tfprice;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField tfdelivery;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_2_1;
	private JTextField tfresult;
	private JLabel lblNewLabel_3;
	private JButton btnback;
	private JButton btndelete;
	private JButton btnconfirm;
	
	int result=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Basket frame = new Basket();
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
	public Basket() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableListInit();
				searchAction();
				screenPartition();
			}
		});
		setTitle("장바구니");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblHello());
		contentPane.add(getLbl());
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfprice());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getTfdelivery());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getTfresult());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getBtnback());
		contentPane.add(getBtndelete());
		contentPane.add(getBtnconfirm());
	}

	private JLabel getLblHello() {
		if (lblHello == null) {
			lblHello = new JLabel(loginname() + "님, 안녕하세요!");
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

				}
			});
			tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableList.setModel(outerTableList); // <<
		}
		return tableList;
	}

	private JLabel getLbl() {
		if (lbl == null) {
			lbl = new JLabel("나의 구매 목록");
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lbl.setBounds(45, 60, 100, 25);
		}
		return lbl;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("총 금액 : ");
			lblNewLabel.setBounds(45, 504, 61, 16);
		}
		return lblNewLabel;
	}

	private JTextField getTfprice() {
		if (tfprice == null) {
			tfprice = new JTextField();
			tfprice.setEditable(false);
			tfprice.setBounds(107, 499, 100, 26);
			tfprice.setColumns(10);
		}
		return tfprice;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("상품가격");
			lblNewLabel_1.setBounds(122, 471, 61, 16);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("+");
			lblNewLabel_2.setBounds(210, 504, 10, 16);
		}
		return lblNewLabel_2;
	}

	private JTextField getTfdelivery() {
		if (tfdelivery == null) {
			tfdelivery = new JTextField();
			tfdelivery.setEditable(false);
			tfdelivery.setColumns(10);
			tfdelivery.setBounds(229, 499, 100, 26);
		}
		return tfdelivery;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("배송비");
			lblNewLabel_1_1.setBounds(244, 471, 61, 16);
		}
		return lblNewLabel_1_1;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("=");
			lblNewLabel_2_1.setBounds(107, 532, 10, 16);
		}
		return lblNewLabel_2_1;
	}

	private JTextField getTfresult() {
		if (tfresult == null) {
			tfresult = new JTextField();
			tfresult.setBounds(129, 532, 130, 26);
			tfresult.setColumns(10);
		}
		return tfresult;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("100,000원 이상 구매시, 배송비 무료");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBounds(45, 571, 315, 16);
		}
		return lblNewLabel_3;
	}

	private JButton getBtnback() {
		if (btnback == null) {
			btnback = new JButton("계속 쇼핑");
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changetoList();
				}
			});
			btnback.setBounds(28, 628, 117, 29);
		}
		return btnback;
	}

	private JButton getBtndelete() {
		if (btndelete == null) {
			btndelete = new JButton("삭제");
			btndelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteAction();
					tableListInit();
					searchAction();
					
				}
			});
			btndelete.setBounds(142, 628, 117, 29);
		}
		return btndelete;
	}

	private JButton getBtnconfirm() {
		if (btnconfirm == null) {
			btnconfirm = new JButton("구매확정");
			btnconfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirm();
					orderAction();
//					deleteAction();
				}
			});
			btnconfirm.setBounds(256, 628, 117, 29);
		}
		return btnconfirm;
	}

	// function

	// login 이름 뜨기
	private String loginname() {
		DaoUser_OKH dao = new DaoUser_OKH();
		loginname = dao.loginname();
		return loginname;

	}
	
	//	login id 뜨기
	private String loginid() {
		DaoUser_OKH dao = new DaoUser_OKH();
		loginid = dao.loginid();
		return loginid;

	}

	// table 정리
	private void tableListInit() {
		outerTableList.addColumn("이름");
		outerTableList.addColumn("사이즈");
		outerTableList.addColumn("색깔");
		outerTableList.addColumn("브랜드");
		outerTableList.addColumn("가격");

		outerTableList.setColumnCount(5);

		int i = outerTableList.getRowCount();

		for (int j = 0; j < i; j++) {
			outerTableList.removeRow(0);
		}

		tableList.setAutoResizeMode(tableList.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = tableList.getColumnModel().getColumn(vColIndex);
		int width = 55;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 40;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 45;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 50;
		col.setPreferredWidth(width);

		vColIndex = 4;
		col = tableList.getColumnModel().getColumn(vColIndex);
		width = 55;
		col.setPreferredWidth(width);
	}

	// innertable 띄우기!
	private void searchAction() {
		DaoBasket_OKH dao = new DaoBasket_OKH(loginid());
		ArrayList<DtoProduct_OKH> dtoList = dao.basketList();
		int listCount = dtoList.size();
		int price = 0;

		for (int i = 0; i < listCount; i++) {
			String[] qTxt = { dtoList.get(i).getPname(), dtoList.get(i).getPsize(), dtoList.get(i).getPcolor(),
					dtoList.get(i).getPbrand(), Integer.toString(dtoList.get(i).getPprice()) };
			outerTableList.addRow(qTxt);
			price = price + dtoList.get(i).getPprice();
		}
		tfprice.setText(String.format("%,d", price));
		int delivery = 0;
		if (price < 100000 && price !=0) {
			delivery = 3000;
		}
		tfdelivery.setText(String.format("%,d", delivery));
		result = price + delivery;
		tfresult.setText(String.format("%,d", result));

	}

	// 구매 확정!
	private void confirm() {
		if(result>0) {
		JOptionPane.showMessageDialog(null, loginname() + "님!\n " + String.format("%,d", result)  + "원 결재가 확정되었습니다!");}
		else {
			JOptionPane.showMessageDialog(null, loginname() + "님!\n " + "아무것도 사지 않으셨습니다. 장바구니를 확인해 주세요!");
		}
	}
	
	//	삭제!
	private void deleteAction() {

		DaoBasket_OKH dao = new DaoBasket_OKH(loginid());
		dao.deleteAction();
	}
	
	// 오더!
	private void orderAction() {
		DaoBuying_OKH dao = new DaoBuying_OKH(loginname(), outerTableList.getRowCount());
		dao.buyingList();
	}
	
	//	쇼핑리스트로 장면 변경
	private void changetoList() {
		setVisible(false);
		Shoppinglist shoppinglist = new Shoppinglist();

		shoppinglist.setVisible(true);
		
	}

	// 버튼 제한
	private void screenPartition() {
		if(result==0) {
			btndelete.setVisible(false);
		}
	}
}
