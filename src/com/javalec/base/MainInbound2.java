package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.DaoInbound;

import com.javalec.dto.DtoInbound;

import com.javalec.util.ShareVar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainInbound2 extends JFrame {

	private final DefaultTableModel outerTable3 = new DefaultTableModel();
	private final DefaultTableModel outerTable4 = new DefaultTableModel();

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane;
	private JTable innerTable3;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_1_1;
	private JTextField tfPname;
	private JTextField tfPsize;
	private JTextField tfPbrand;
	private JTextField tfIbqty;
	private JLabel lblNewLabel_1_1_1_1;
	private JTextField tfUpdate;
	private JButton btnUpdate;
	private JButton btnPage;
	private JLabel lblNewLabel_2;

	String message = "";
	private JLabel lblimg;
	DtoInbound dto;
	int wkSeqno = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInbound2 frame = new MainInbound2();
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
	public MainInbound2() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit3();
				searchAction3();

			}
		});

		setTitle("입고페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getLblNewLabel_1_1_1());
		contentPane.add(getTfPname());
		contentPane.add(getTfPsize());
		contentPane.add(getTfPbrand());
		contentPane.add(getTfIbqty());
		contentPane.add(getLblNewLabel_1_1_1_1());
		contentPane.add(getTfUpdate());
		contentPane.add(getBtnUpdate());
		contentPane.add(getBtnPage());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblimg());
	}

	// 제품 목록 박스
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 107, 315, 228);
			scrollPane.setViewportView(getInnerTable3());
		}
		return scrollPane;
	}

	// 제품 목록 innerTable
	private JTable getInnerTable3() {
		if (innerTable3 == null) {
			innerTable3 = new JTable();
			innerTable3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable3.setModel(outerTable3);
		}
		return innerTable3;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("상품명 :");
			lblNewLabel.setBounds(45, 347, 46, 16);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("사이즈 :");
			lblNewLabel_1.setBounds(45, 375, 46, 16);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("브랜드 :");
			lblNewLabel_1_1.setBounds(45, 403, 46, 16);
		}
		return lblNewLabel_1_1;
	}

	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("현재고 :");
			lblNewLabel_1_1_1.setBounds(230, 347, 59, 16);
		}
		return lblNewLabel_1_1_1;
	}

	// -- 상품명 TextField--
	private JTextField getTfPname() {
		if (tfPname == null) {
			tfPname = new JTextField();
			tfPname.setBounds(89, 342, 130, 26);
			tfPname.setEditable(false);
			tfPname.setColumns(10);
		}
		return tfPname;
	}

	// 상품 사이즈 TextField
	private JTextField getTfPsize() {
		if (tfPsize == null) {
			tfPsize = new JTextField();
			tfPsize.setBounds(89, 370, 67, 26);
			tfPsize.setEditable(false);
			tfPsize.setColumns(10);
		}
		return tfPsize;
	}

	// 상품 브랜드 TextField
	private JTextField getTfPbrand() {
		if (tfPbrand == null) {
			tfPbrand = new JTextField();
			tfPbrand.setBounds(89, 398, 94, 26);
			tfPbrand.setEditable(false);
			tfPbrand.setColumns(10);
		}
		return tfPbrand;
	}

	// 상품 현재수량 TextField
	private JTextField getTfIbqty() {
		if (tfIbqty == null) {
			tfIbqty = new JTextField();
			tfIbqty.setBounds(304, 342, 46, 26);
			tfIbqty.setEditable(false);
			tfIbqty.setColumns(10);
		}
		return tfIbqty;
	}

	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("입고수량 :");
			lblNewLabel_1_1_1_1.setBounds(230, 375, 59, 16);
		}
		return lblNewLabel_1_1_1_1;
	}

	// 상품 입고수량 TextField-- (작성가능)
	private JTextField getTfUpdate() {
		if (tfUpdate == null) {
			tfUpdate = new JTextField();
			tfUpdate.setBounds(304, 370, 46, 26);
			tfUpdate.setColumns(10);
		}
		return tfUpdate;
	}

	// 입고버튼
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("입고");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertAction();
					searchAction3();
				}
			});
			btnUpdate.setBounds(285, 398, 67, 29);
		}
		return btnUpdate;
	}

	// 이전 페이지로 돌아가기 (재고현황페이지)
	private JButton getBtnPage() {
		if (btnPage == null) {
			btnPage = new JButton("재고현황페이지");
			btnPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changepage();
				}
			});
			btnPage.setBounds(45, 45, 117, 29);
		}
		return btnPage;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("제품 이미지");
			lblNewLabel_2.setBounds(183, 458, 61, 16);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblimg() {
		if (lblimg == null) {
			lblimg = new JLabel("");
			lblimg.setHorizontalAlignment(SwingConstants.CENTER);
			lblimg.setBounds(89, 486, 244, 143);
		}
		return lblimg;
	}

	// ----------------Function ----------------

	private void searchAction3() {
		DaoInbound dao = new DaoInbound();
		ArrayList<DtoInbound> dtoList = dao.selectList();
		int listCount = dtoList.size();
		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getPcode());
			String[] qTxt = { temp, dtoList.get(i).getPname(), dtoList.get(i).getPcolor(), dtoList.get(i).getPbrand(),
					dtoList.get(i).getPsize() };
			outerTable3.addRow(qTxt);
		}
	}

	// DB의 제품 Table에서 화면에 들어갈 정보가져오기
	private void tableInit3() {
		outerTable3.addColumn("제품번호");
		outerTable3.addColumn("제품명");
		outerTable3.addColumn("컬러");
		outerTable3.addColumn("브랜드");
		outerTable3.addColumn("사이즈");
		outerTable3.setColumnCount(5);

		int i = outerTable3.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable3.removeRow(0);
		}

		innerTable3.setAutoResizeMode(innerTable3.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = innerTable3.getColumnModel().getColumn(vColIndex);
		int width = 30;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = innerTable3.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = innerTable3.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = innerTable3.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		vColIndex = 4;
		col = innerTable3.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

	}

	// 1.재고목록페이지와 입고페이지 왔다갔다 할수있는 기능
	private void changepage() {
		setVisible(false);
		MainInbound maininbound = new MainInbound();
		maininbound.setVisible(true);
	}

	// 2.Table 클릭시 제품정보 노출
	private void tableClick() {

		int i = innerTable3.getSelectedRow();
		String wkSequence = (String) innerTable3.getValueAt(i, 0);
		wkSeqno = Integer.parseInt(wkSequence);
		DaoInbound dao = new DaoInbound(wkSeqno);
		dto = dao.tableClick();

		tfPname.setText(dto.getPname());
		tfPsize.setText(dto.getPsize());
		tfPbrand.setText(dto.getPbrand());
		tfIbqty.setText(Integer.toString(dto.getStock()));

		String filePath = Integer.toString(ShareVar.filename);

		lblimg.setIcon(new ImageIcon(filePath));
		lblimg.setHorizontalAlignment(SwingConstants.CENTER);

		File file = new File(filePath);
		file.delete();

	}

	// Table 클릭시 입고 가능하게 만들어주는 기능
	private void insertAction() {
		DaoInbound dao = new DaoInbound(wkSeqno);

		dto = dao.tableClick();
		String name = "sss";// tfAdminid.getText();
		int pcode = dto.getPcode();
		System.out.println(pcode);
		int qty = Integer.parseInt(tfUpdate.getText());

		DaoInbound dao1 = new DaoInbound(name, pcode, qty);
		dao1.insertAction();

		JOptionPane.showMessageDialog(this, "입고처리가 완료되었습니다.!", "입고처리완료", JOptionPane.INFORMATION_MESSAGE);

	}

}// ----------------End ----------------
