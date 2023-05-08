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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainInbound extends JFrame {

	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	
	DtoInbound dto;

	String message = "";
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JButton btnWare;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JTextField tfFilePath;
	private JLabel lblImage;
	private JButton btnFilepath;
	private JLabel lblNewLabel_1;
	private JTextField tfQty;
	
	int wkSeqno = 0;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInbound frame = new MainInbound();
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
	public MainInbound() {
		setTitle("재고현황페이지");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				searchAction();
	
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getBtnWare());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfQty());
		contentPane.add(getBtnNewButton());

	}

	// 제품리스트 판넬 
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 93, 315, 440);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	// 제품리스트 보이는 곳
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick2();
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}

	// 입고버튼
	private JButton getBtnWare() {
		if (btnWare == null) {
			btnWare = new JButton("입고하기");
			btnWare.setBounds(243, 545, 117, 47);
			btnWare.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changepage();
				}
			});
		}
		return btnWare;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("현재 재고 목록");
			lblNewLabel.setBounds(45, 73, 89, 16);
		}
		return lblNewLabel;
	}
	
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("현 재고 :");
			lblNewLabel_1.setBounds(55, 556, 52, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfQty() {
		if (tfQty == null) {
			tfQty = new JTextField();
			tfQty.setEditable(false);
			tfQty.setBounds(109, 545, 52, 38);
			tfQty.setColumns(10);
		}
		return tfQty;
	}

	// Function----------------------------------------------------------------------------------------

	// 실행시 보여지는 테이블 내용 
	private void searchAction() {
		DaoInbound dao = new DaoInbound();
		ArrayList<DtoInbound> dtoList = dao.selectList();
		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getPcode());
			String[] qTxt = { temp, dtoList.get(i).getPname(), dtoList.get(i).getPcolor(), dtoList.get(i).getPbrand(),
					dtoList.get(i).getPsize() };
			outerTable.addRow(qTxt);
		}
	}

	
	// DB의 제품 Table에서 화면에 들어갈 정보가져오기
	private void tableInit() {
		outerTable.addColumn("제품코드");
		outerTable.addColumn("제품명");
		outerTable.addColumn("컬러");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("현재고");
		outerTable.setColumnCount(6);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(vColIndex);
		int width = 30;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

		vColIndex = 4;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);
		
		vColIndex = 5;
		col = innerTable.getColumnModel().getColumn(vColIndex);
		width = 120;
		col.setPreferredWidth(width);

	
	}

	// 다음페이지로 이동
	private void changepage() {
		setVisible(false);
		MainInbound2 maininbound2 = new MainInbound2();
		maininbound2.setVisible(true);
	}

 	
	
	private void tableClick2() {
		int i = innerTable.getSelectedRow();

		String wkSequence = (String) innerTable.getValueAt(i,0);
		wkSeqno = Integer.parseInt(wkSequence);
		DaoInbound dao = new DaoInbound(wkSeqno);
		int stock = dao.tableClick2();
	
	

		tfQty.setText(Integer.toString(stock));
 	 }
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("뒤로가기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AdminMain_KMJ adminMain_KMJ = new AdminMain_KMJ();
					adminMain_KMJ.setVisible(true);
					
					setVisible(false);
					
				}
			});
			btnNewButton.setBounds(45, 591, 117, 29);
		}
		return btnNewButton;
	}
}



