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
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class MainJoin_Yj extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField tfId;
	private JLabel lblPw;
	private JTextField tfPw;
	private JLabel lblPw_1;
	private JTextField tfPwCheck;
	private JLabel lblPhone;
	private JTextField tfPhone;
	private JLabel lblNewLabel_4;
	private JTextField tfAddress;
	private JButton bttCheck;
	private JButton bttJoin;
	private JButton bttCancel;
	private JLabel lblEmail_1;
	private JTextField tfName;
	int dupToken = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJoin_Yj frame = new MainJoin_Yj();
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
	public MainJoin_Yj() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfId());
		contentPane.add(getLblPw());
		contentPane.add(getTfPw());
		contentPane.add(getLblPw_1());
		contentPane.add(getTfPwCheck());
		contentPane.add(getLblPhone());
		contentPane.add(getTfPhone());
		contentPane.add(getLblNewLabel_4());
		contentPane.add(getTfAddress());
		contentPane.add(getBttCheck());
		contentPane.add(getBttJoin());
		contentPane.add(getBttCancel());
		contentPane.add(getLblEmail_1());
		contentPane.add(getTfName());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID :");
			lblNewLabel.setBounds(33, 34, 28, 16);
		}
		return lblNewLabel;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setBounds(103, 29, 192, 26);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JLabel getLblPw() {
		if (lblPw == null) {
			lblPw = new JLabel("PW :");
			lblPw.setBounds(33, 75, 58, 16);
		}
		return lblPw;
	}
	private JTextField getTfPw() {
		if (tfPw == null) {
			tfPw = new JTextField();
			tfPw.setColumns(10);
			tfPw.setBounds(103, 70, 192, 26);
		}
		return tfPw;
	}
	private JLabel getLblPw_1() {
		if (lblPw_1 == null) {
			lblPw_1 = new JLabel("PW 확인 :");
			lblPw_1.setBounds(33, 113, 58, 16);
		}
		return lblPw_1;
	}
	private JTextField getTfPwCheck() {
		if (tfPwCheck == null) {
			tfPwCheck = new JTextField();
			tfPwCheck.setColumns(10);
			tfPwCheck.setBounds(103, 108, 192, 26);
		}
		return tfPwCheck;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("Phone :");
			lblPhone.setBounds(33, 151, 58, 16);
		}
		return lblPhone;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setColumns(10);
			tfPhone.setBounds(103, 146, 192, 26);
		}
		return tfPhone;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("주소 :");
			lblNewLabel_4.setBounds(33, 189, 47, 16);
		}
		return lblNewLabel_4;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(103, 184, 192, 26);
		}
		return tfAddress;
	}
	private JButton getBttCheck() {
		if (bttCheck == null) {
			bttCheck = new JButton("중복체크");
			bttCheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 doubleCheckAction();
				}
			});
			bttCheck.setBounds(307, 29, 78, 29);
		}
		return bttCheck;
	}
	private JButton getBttJoin() {
		if (bttJoin == null) {
			bttJoin = new JButton("회원가입");
			bttJoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(dupToken == 1) {
					joinAction();
					}
				}
			});
			bttJoin.setBounds(81, 310, 117, 29);
		}
		return bttJoin;
	}
	private JButton getBttCancel() {
		if (bttCancel == null) {
			bttCancel = new JButton("취소");
			bttCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			bttCancel.setBounds(215, 310, 117, 29);
		}
		return bttCancel;
	}
	
	private JLabel getLblEmail_1() {
		if (lblEmail_1 == null) {
			lblEmail_1 = new JLabel("이름 : ");
			lblEmail_1.setBounds(33, 222, 58, 16);
		}
		return lblEmail_1;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(103, 217, 192, 26);
		}
		return tfName;
	}
	
	// ----------- Functions
	
	// 회원가입
	
public void joinAction() {
		
		String id;
		String ps;
		String address;
		String phone;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		String name;
		
		Date date = new Date();
		
		if(tfPw.getText().trim().equals(tfPwCheck.getText().trim())) {
			
			id = tfId.getText().trim();
			ps = tfPw.getText().trim();
			address = tfAddress.getText().trim();
			phone = tfPhone.getText().trim();
			String str = formatter.format(date);
			name = tfName.getText().trim();
			
			DaoUser_Yj daoUser_yj = new DaoUser_Yj(id, ps, address, phone, str, name);
			
			JOptionPane.showMessageDialog(this,"가입을 성공했습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
			
			dispose(); // 회원가입 성공시 회원가입 창을 닫음
			
			daoUser_yj.insertAction();
		}
		else {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "실패", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	
	}

// 아이디 중복 확인

public void doubleCheckAction() {
	
    String id;
    id = tfId.getText().trim();
    
    DaoUser_Yj daoUser_yj = new DaoUser_Yj(id);

    boolean isDuplicate = daoUser_yj.doubleCheckAction();
    
    if (isDuplicate) {
    	 JOptionPane.showMessageDialog(null,tfId.getText() + "는 사용 가능한 아이디입니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
    	 dupToken = 1;
    } else {
    	JOptionPane.showMessageDialog(null,tfId.getText() + "는 이미 사용 중인 아이디입니다. \n다시 입력해주세", "아이디 중복", JOptionPane.ERROR_MESSAGE);
    	dupToken = 0;
    }
    
	
	}
	
}	// end
