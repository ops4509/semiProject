package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.daoAdmin_KMJ;
import com.javalec.dto.dtoAdmin_KMJ;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminMain_KMJ extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lbName;
	private JLabel lblId;
	private JLabel lblNewLabel_2;
	private JLabel lbId;
	private JLabel lblId_1;
	private JLabel lbAddress;
	private JLabel lblEmail;
	private JLabel lbEmail;
	private JLabel lblEmail_1;
	private JLabel lbPhone;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain_KMJ frame = new AdminMain_KMJ();
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
	public AdminMain_KMJ() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				getAdminInfo();
			}
		});
		setTitle("HI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLbName());
		contentPane.add(getLblId());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLbId());
		contentPane.add(getLblId_1());
		contentPane.add(getLbAddress());
		contentPane.add(getLblEmail());
		contentPane.add(getLbEmail());
		contentPane.add(getLblEmail_1());
		contentPane.add(getLbPhone());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("관리자 이름 : ");
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setBounds(45, 97, 76, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLbName() {
		if (lbName == null) {
			lbName = new JLabel("New label");
			lbName.setHorizontalAlignment(SwingConstants.LEFT);
			lbName.setBounds(154, 97, 68, 15);
		}
		return lbName;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("관리자 id : ");
			lblId.setHorizontalAlignment(SwingConstants.LEFT);
			lblId.setBounds(45, 145, 76, 15);
		}
		return lblId;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("접속한 관리자 정보");
			lblNewLabel_2.setBounds(142, 15, 104, 15);
		}
		return lblNewLabel_2;
	}
	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("New label");
			lbId.setHorizontalAlignment(SwingConstants.LEFT);
			lbId.setBounds(154, 145, 68, 15);
		}
		return lbId;
	}
	private JLabel getLblId_1() {
		if (lblId_1 == null) {
			lblId_1 = new JLabel("관리자 주소 : ");
			lblId_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblId_1.setBounds(45, 190, 76, 15);
		}
		return lblId_1;
	}
	private JLabel getLbAddress() {
		if (lbAddress == null) {
			lbAddress = new JLabel("New label");
			lbAddress.setHorizontalAlignment(SwingConstants.LEFT);
			lbAddress.setBounds(154, 190, 68, 15);
		}
		return lbAddress;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("관리자 Email : ");
			lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
			lblEmail.setBounds(45, 235, 87, 15);
		}
		return lblEmail;
	}
	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("New label");
			lbEmail.setHorizontalAlignment(SwingConstants.LEFT);
			lbEmail.setBounds(154, 235, 137, 15);
		}
		return lbEmail;
	}
	private JLabel getLblEmail_1() {
		if (lblEmail_1 == null) {
			lblEmail_1 = new JLabel("관리자 전화번호 : ");
			lblEmail_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblEmail_1.setBounds(45, 283, 104, 15);
		}
		return lblEmail_1;
	}
	private JLabel getLbPhone() {
		if (lbPhone == null) {
			lbPhone = new JLabel("New label");
			lbPhone.setHorizontalAlignment(SwingConstants.LEFT);
			lbPhone.setBounds(154, 283, 137, 15);
		}
		return lbPhone;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("신규주문현황");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setBounds(66, 354, 119, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("재고관리");
			btnNewButton_1.setBounds(213, 354, 119, 23);
		}
		return btnNewButton_1;
	}
	//function
	private void getAdminInfo() {
		daoAdmin_KMJ dao = new daoAdmin_KMJ();
		ArrayList<dtoAdmin_KMJ> dtoList = dao.getInfo();
		lbId.setText(dtoList.get(0).getAid());
		lbName.setText(dtoList.get(0).getAname());
		lbAddress.setText(dtoList.get(0).getAddress());
		lbEmail.setText(dtoList.get(0).getAemail());
		lbPhone.setText(dtoList.get(0).getAphone());
	};
	//End
}
