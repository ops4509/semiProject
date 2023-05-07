package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.DaoUser_Yj;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;


public class MainLogin_Yj extends JFrame {
	private JLabel lblNewLabel;
	private JLabel lblPs;
	private JTextField tfId;
	private JButton btnIogin;
	private JButton btnJoin;
	private JPasswordField tfPassword;
	private JCheckBox chAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainLogin_Yj frame = new MainLogin_Yj();
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
	public MainLogin_Yj() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblPs());
		contentPane.add(getTfId());
		contentPane.add(getBtnIogin());
		contentPane.add(getBtnJoin());
		contentPane.add(getPasswordField());
		contentPane.add(getChAdmin());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID : ");
			lblNewLabel.setBounds(62, 277, 30, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblPs() {
		if (lblPs == null) {
			lblPs = new JLabel("PS :");
			lblPs.setBounds(62, 310, 30, 16);
		}
		return lblPs;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setBounds(92, 272, 234, 26);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JButton getBtnIogin() {
		if (btnIogin == null) {
			btnIogin = new JButton("로그인");
			btnIogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loginAction();
				}
			});
			btnIogin.setBounds(101, 348, 191, 29);
		}
		return btnIogin;
	}
	private JButton getBtnJoin() {
		if (btnJoin == null) {
			btnJoin = new JButton("회원가입");
			btnJoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					joinAction();
				}
			});
			btnJoin.setBounds(101, 389, 191, 29);
		}
		return btnJoin;
	}
	private JPasswordField getPasswordField() {
		if (tfPassword == null) {
			tfPassword = new JPasswordField();
			tfPassword.setBounds(92, 305, 234, 26);
		}
		return tfPassword;
	}
	
	private JCheckBox getChAdmin() {
		if (chAdmin == null) {
			chAdmin = new JCheckBox("관리자");
			chAdmin.setBounds(294, 349, 66, 23);
		}
		return chAdmin;
	}
	
	// ----------- Functions
	
	// 로그인
	public void loginAction() {
		
		// 관리자 로그인
		 
		if(chAdmin.isSelected()) {
			
			String id;
			String ps = "";
			char b[] = tfPassword.getPassword();
			
			for(int i = 0; i < b.length; i++) {
				ps = ps + b[i];
			}
			id = tfId.getText().trim();
			
			DaoUser_Yj daoUser_yj = new DaoUser_Yj(id, ps);
			
		
		
			boolean result = daoUser_yj.adminLoginAction();
			
			if(result) {
				JOptionPane.showMessageDialog(this, tfId.getText() + "관리자님 환영합니다.", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "아이디를 다시 입력하세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			}
			
			
		
			
		}
		else { // 일반 로그인
			
			String id;
			String ps = "";
			char b[] = tfPassword.getPassword();
			
			for(int i = 0; i < b.length; i++) {
				ps = ps + b[i];
			}
			id = tfId.getText().trim();

			
			DaoUser_Yj daoUser_yj = new DaoUser_Yj(id, ps);
			
		
		
			boolean result = daoUser_yj.loginAction();
			
			if(result) {
				JOptionPane.showMessageDialog(this, tfId.getText() + "님 환영합니", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this,  "아이디를 다시 입력하세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			daoUser_yj.loginAction();
		}
	
	}
	
	public void joinAction() {
		
		MainJoin_Yj mainJoin_Yj = new MainJoin_Yj();
		mainJoin_Yj.setVisible(true);
		
	}
} // End
