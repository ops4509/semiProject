package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.Dao;
import com.javalec.dto.Dto;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JRadioButton rbInsert;
	private JRadioButton rbUpdate;
	private JRadioButton rbQuery;
	private JRadioButton rbDelete;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblNewLabel;
	private JTextField tfSeqno;
	private JLabel lblNewLabel_1;
	private JTextField tfName;
	private JLabel lblNewLabel_2;
	private JTextField tfTelno;
	private JLabel lblNewLabel_3;
	private JTextField tfAddress;
	private JLabel lblNewLabel_4;
	private JTextField tfEmail;
	private JLabel lblNewLabel_5;
	private JTextField tfRelation;
	private JButton btnOK;
	private JLabel lblNewLabel_6;
	private JTextField tfCount;

	// Table
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	String message = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				searchAction();
				screenPartition();
			}
		});
		setTitle("주소록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getRbInsert());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbQuery());
		contentPane.add(getRbDelete());
		contentPane.add(getCbSelection());
		contentPane.add(getTfSelection());
		contentPane.add(getBtnQuery());
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfSeqno());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfName());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getTfTelno());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getTfAddress());
		contentPane.add(getLblNewLabel_4());
		contentPane.add(getTfEmail());
		contentPane.add(getLblNewLabel_5());
		contentPane.add(getTfRelation());
		contentPane.add(getBtnOK());
		contentPane.add(getLblNewLabel_6());
		contentPane.add(getTfCount());
	}
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			rbInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbInsert);
			rbInsert.setBounds(17, 17, 67, 23);
		}
		return rbInsert;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbUpdate);
			rbUpdate.setBounds(83, 17, 67, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbQuery() {
		if (rbQuery == null) {
			rbQuery = new JRadioButton("검색");
			rbQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			rbQuery.setSelected(true);
			buttonGroup.add(rbQuery);
			rbQuery.setBounds(217, 17, 67, 23);
		}
		return rbQuery;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			rbDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbDelete);
			rbDelete.setBounds(151, 17, 67, 23);
		}
		return rbDelete;
	}
	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"이름", "주소", "관계"}));
			cbSelection.setBounds(17, 52, 78, 27);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(93, 52, 174, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					conditionQuery();
				}
			});
			btnQuery.setBounds(271, 52, 117, 29);
		}
		return btnQuery;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 91, 379, 129);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seq No :");
			lblNewLabel.setBounds(17, 232, 61, 16);
		}
		return lblNewLabel;
	}
	private JTextField getTfSeqno() {
		if (tfSeqno == null) {
			tfSeqno = new JTextField();
			tfSeqno.setEditable(false);
			tfSeqno.setHorizontalAlignment(SwingConstants.TRAILING);
			tfSeqno.setBounds(102, 227, 49, 26);
			tfSeqno.setColumns(10);
		}
		return tfSeqno;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("이름 :");
			lblNewLabel_1.setBounds(17, 265, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(102, 260, 91, 26);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("전화번호 :");
			lblNewLabel_2.setBounds(17, 298, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JTextField getTfTelno() {
		if (tfTelno == null) {
			tfTelno = new JTextField();
			tfTelno.setEditable(false);
			tfTelno.setColumns(10);
			tfTelno.setBounds(102, 293, 130, 26);
		}
		return tfTelno;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("주소 :");
			lblNewLabel_3.setBounds(17, 331, 61, 16);
		}
		return lblNewLabel_3;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setEditable(false);
			tfAddress.setColumns(10);
			tfAddress.setBounds(102, 326, 286, 26);
		}
		return tfAddress;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("전자우편 :");
			lblNewLabel_4.setBounds(17, 364, 61, 16);
		}
		return lblNewLabel_4;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setEditable(false);
			tfEmail.setColumns(10);
			tfEmail.setBounds(102, 359, 203, 26);
		}
		return tfEmail;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("관계 :");
			lblNewLabel_5.setBounds(17, 397, 61, 16);
		}
		return lblNewLabel_5;
	}
	private JTextField getTfRelation() {
		if (tfRelation == null) {
			tfRelation = new JTextField();
			tfRelation.setEditable(false);
			tfRelation.setColumns(10);
			tfRelation.setBounds(102, 392, 286, 26);
		}
		return tfRelation;
	}
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			btnOK.setEnabled(false);
			btnOK.setBounds(271, 436, 117, 29);
		}
		return btnOK;
	}
	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("Count :");
			lblNewLabel_6.setBounds(254, 232, 61, 16);
		}
		return lblNewLabel_6;
	}
	private JTextField getTfCount() {
		if (tfCount == null) {
			tfCount = new JTextField();
			tfCount.setEditable(false);
			tfCount.setHorizontalAlignment(SwingConstants.TRAILING);
			tfCount.setColumns(10);
			tfCount.setBounds(306, 227, 49, 26);
		}
		return tfCount;
	}
	
	// --- Functions -----
	
	// 화면의 Table 정리
	private void tableInit() {
		outerTable.addColumn("순서");
		outerTable.addColumn("이름");
		outerTable.addColumn("전화번호");
		outerTable.addColumn("관계");
		outerTable.setColumnCount(4);
		
		int i = outerTable.getRowCount();
		for(int j=0; j<i; j++) {
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
		width = 200;
		col.setPreferredWidth(width);

	}
	
	// DB의 Table에서 화면의 Table에 들어갈 정보 가져오기
	private void searchAction() {
		Dao dao = new Dao();
		ArrayList<Dto> dtoList = dao.selectList();
		int listCount = dtoList.size();
		
		for(int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getSeqno());
			String[] qTxt = {temp, dtoList.get(i).getName(), dtoList.get(i).getTelno(), dtoList.get(i).getRelation()};
			outerTable.addRow(qTxt);
		}
		tfCount.setText(Integer.toString(listCount));
	}

	private void screenPartition() {
		// 검색을 경우
		if(rbQuery.isSelected()) {
			tfName.setEditable(false);
			tfTelno.setEditable(false);
			tfAddress.setEditable(false);
			tfEmail.setEditable(false);
			tfRelation.setEditable(false);
			btnOK.setVisible(false);
		}
		
		// 입력일 경우
		if(rbInsert.isSelected()) {
			tfName.setEditable(true);
			tfTelno.setEditable(true);
			tfAddress.setEditable(true);
			tfEmail.setEditable(true);
			tfRelation.setEditable(true);
			btnOK.setVisible(true);		
			btnOK.setEnabled(true);
		}
		
		// 수정이나 삭제일 경우
		if(rbUpdate.isSelected() || rbDelete.isSelected() ) {
			tfName.setEditable(false);
			tfTelno.setEditable(false);
			tfAddress.setEditable(false);
			tfEmail.setEditable(false);
			tfRelation.setEditable(false);
			btnOK.setVisible(true);		
			btnOK.setEnabled(false);
			
		}
		
	}
	
	private void actionPartition() {
		// 입력인 경우
		if(rbInsert.isSelected()) {
			int i_chk = insertFieldCheck();
			if(i_chk == 0) {
				insertAction();
				tableInit();
				searchAction();
				clearColumn();
				
			}else {
				JOptionPane.showMessageDialog(this, "주소록 정보 입력!\n" + message + "확인하세요!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		// 수정인 경우
		if(rbUpdate.isSelected()) {
			int i_chk = insertFieldCheck();
			if(i_chk == 0) {
				updateAction();
				tableInit();
				searchAction();
				clearColumn();
				
			}else {
				JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + message + "확인하세요!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		// 삭제인 경우
		if(rbDelete.isSelected()) {
			deleteAction();
			tableInit();
			searchAction();
			clearColumn();
		}
				
	}
	
	private void deleteAction() {
		int seqno = Integer.parseInt(tfSeqno.getText());
		
		Dao dao = new Dao(seqno);
		boolean result = dao.deleteAction(); 
		
		if(result) {
			JOptionPane.showMessageDialog(this, "주소록 정보 삭제!\n" + tfName.getText() + "님의 정보가 삭제 되었습니다.!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + "입력중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	
	private void updateAction() {
		String name = tfName.getText();
		String telno = tfTelno.getText();
		String address = tfAddress.getText();
		String email = tfEmail.getText();
		String relation = tfRelation.getText();
		int seqno = Integer.parseInt(tfSeqno.getText());
		
		Dao dao = new Dao(seqno, name, telno, address, email, relation);
		boolean result = dao.updateAction(); 
		
		if(result) {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + tfName.getText() + "님의 정보가 수정 되었습니다.!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "주소록 정보 수정!\n" + "입력중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	
	private void insertAction() {
		String name = tfName.getText();
		String telno = tfTelno.getText();
		String address = tfAddress.getText();
		String email = tfEmail.getText();
		String relation = tfRelation.getText();
		
		Dao dao = new Dao(name, telno, address, email, relation);
		boolean result = dao.insertAction();
		
		if(result) {
			JOptionPane.showMessageDialog(this, "주소록 정보 입력!\n" + tfName.getText() + "님의 정보가 입력 되었습니다.!", "주소록 정보", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "주소록 정보 입력!\n" + "입력중 문제가 발생했습니다. \n관리자에게 문의하세요!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void clearColumn() {
		tfSeqno.setText("");
		tfName.setText("");
		tfTelno.setText("");
		tfAddress.setText("");
		tfEmail.setText("");
		tfRelation.setText("");
	}

	
	
	
	private int insertFieldCheck() {
		int i = 0;
		
		if(tfName.getText().trim().length() == 0) {
			i++;
			message = "이름을 ";
			tfName.requestFocus();
		}
		if(tfTelno.getText().trim().length() == 0) {
			i++;
			message = "전화번호를 ";
			tfTelno.requestFocus();
		}
		if(tfAddress.getText().trim().length() == 0) {
			i++;
			message = "주소를 ";
			tfAddress.requestFocus();
		}
		if(tfEmail.getText().trim().length() == 0) {
			i++;
			message = "전자우편 ";
			tfEmail.requestFocus();
		}
		if(tfRelation.getText().trim().length() == 0) {
			i++;
			message = "관계를 ";
			tfRelation.requestFocus();
		}
				
		return i;
	}

	private void tableClick() {
		
		if(rbUpdate.isSelected()) {
			tfName.setEditable(true);
			tfTelno.setEditable(true);
			tfAddress.setEditable(true);
			tfEmail.setEditable(true);
			tfRelation.setEditable(true);
			btnOK.setVisible(true);		
			btnOK.setEnabled(true);
		}
		
		if(rbDelete.isSelected()) {
			tfName.setEditable(false);
			tfTelno.setEditable(false);
			tfAddress.setEditable(false);
			tfEmail.setEditable(false);
			tfRelation.setEditable(false);
			btnOK.setVisible(true);		
			btnOK.setEnabled(true);

		}
		
		int i = innerTable.getSelectedRow();
		String wkSequence = (String) innerTable.getValueAt(i, 0);
		int wkSeqno = Integer.parseInt(wkSequence);
		
		Dao dao = new Dao(wkSeqno);
		Dto dto = dao.tableClick();
		
		tfSeqno.setText(Integer.toString(dto.getSeqno()));
		tfName.setText(dto.getName());
		tfTelno.setText(dto.getTelno());
		tfAddress.setText(dto.getAddress());
		tfEmail.setText(dto.getEmail());
		tfRelation.setText(dto.getRelation());

	}
	
	private void conditionQuery() {
		int i = cbSelection.getSelectedIndex();
		String conditionQueryColumn = "";
		switch(i) {
		case 0:
			conditionQueryColumn = "name";
			break;
		case 1:
			conditionQueryColumn = "address";
			break;
		case 2:
			conditionQueryColumn = "relation";
			break;
		default:
			break;
		}
		
		tableInit();
		clearColumn();
		conditionQueryAction(conditionQueryColumn);
	}

	private void conditionQueryAction(String conditionQueryColumn) {
		Dao dao = new Dao(conditionQueryColumn, tfSelection.getText());
		ArrayList<Dto> dtoList = dao.conditionList();
		int listCount = dtoList.size();
		
		for(int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getSeqno());
			String[] qTxt = {temp, dtoList.get(i).getName(), dtoList.get(i).getTelno(), dtoList.get(i).getRelation()};
			outerTable.addRow(qTxt);
		}
		tfCount.setText(Integer.toString(listCount));

	}
	
	
} // End
