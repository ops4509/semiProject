package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.DaoOutbound;
import com.javalec.dto.DtoOutbound;
import com.javalec.util.ShareVar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

public class ObDetail extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField tfObdonum;
	private JLabel lblNewLabel_1_1;
	private JTextField tfObdodate;
	private JLabel lblNewLabel_1_2;
	private JTextField tfObduid;
	private JLabel lblNewLabel_1_2_1;
	private JTextField tfObduname;
	private JLabel lblNewLabel_1_2_1_1;
	private JTextField tfObduphone;
	private JTextField tfObduadress;
	private JLabel lblNewLabel_1_2_1_1_1;
	private JLabel lblNewLabel_3;
	private JTextField tfObdpqty;
	private JLabel lblNewLabel_3_1;
	private JTextField tfobdsale;
	private JButton btnObdback;
	private JButton btnObdinsert;
	private JButton tnObddelete;
	private JLabel lblNewLabel_3_2;
	private JTextField tfObdpcode;
	private JLabel lblNewLabel_3_2_1;
	private JTextField tfObdpbrand;
	private JLabel lblNewLabel_3_2_2;
	private JTextField tfObdpname;
	private JLabel lblNewLabel_2;
	private JLabel lblObdpic;
	private JLabel lblNewLabel_3_2_2_1;
	private JTextField tfObdcolor;
	private JLabel lblNewLabel_3_2_2_2;
	private JTextField tfObdsize;
	private JLabel lblNewLabel_3_2_1_1;
	private JTextField tfObdpprice;

	
	String tObaid = "강경구"; // 관리자 아이디 넣는 곳.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ObDetail frame = new ObDetail();
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
	public ObDetail() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				showDetail();
				obdscreenPartition();

			}
		});
		setTitle("주문 상세 확인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfObdonum());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getTfObdodate());
		contentPane.add(getLblNewLabel_1_2());
		contentPane.add(getTfObduid());
		contentPane.add(getLblNewLabel_1_2_1());
		contentPane.add(getTfObduname());
		contentPane.add(getLblNewLabel_1_2_1_1());
		contentPane.add(getTfObduphone());
		contentPane.add(getTfObduadress());
		contentPane.add(getLblNewLabel_1_2_1_1_1());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getTfObdpqty());
		contentPane.add(getLblNewLabel_3_1());
		contentPane.add(getTfobdsale());
		contentPane.add(getBtnObdback());
		contentPane.add(getBtnObdinsert());
		contentPane.add(getTnObddelete());
		contentPane.add(getLblNewLabel_3_2());
		contentPane.add(getTfObdpcode());
		contentPane.add(getLblNewLabel_3_2_1());
		contentPane.add(getTfObdpbrand());
		contentPane.add(getLblNewLabel_3_2_2());
		contentPane.add(getTfObdpname());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblObdpic());
		contentPane.add(getLblNewLabel_3_2_2_1());
		contentPane.add(getTfObdcolor());
		contentPane.add(getLblNewLabel_3_2_2_2());
		contentPane.add(getTfObdsize());
		contentPane.add(getLblNewLabel_3_2_1_1());
		contentPane.add(getTfObdpprice());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("고객 정보");
			lblNewLabel.setBounds(45, 78, 125, 29);
			lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("주문 번호 : ");
			lblNewLabel_1.setBounds(45, 23, 61, 16);
		}
		return lblNewLabel_1;
	}

	private JTextField getTfObdonum() {
		if (tfObdonum == null) {
			tfObdonum = new JTextField();
			tfObdonum.setEditable(false);
			tfObdonum.setBounds(109, 18, 70, 26);
			tfObdonum.setColumns(10);
		}
		return tfObdonum;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("주문 날짜: ");
			lblNewLabel_1_1.setBounds(191, 23, 61, 16);
		}
		return lblNewLabel_1_1;
	}

	private JTextField getTfObdodate() {
		if (tfObdodate == null) {
			tfObdodate = new JTextField();
			tfObdodate.setEditable(false);
			tfObdodate.setBounds(254, 18, 105, 26);
			tfObdodate.setColumns(10);
		}
		return tfObdodate;
	}

	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("고객 ID :");
			lblNewLabel_1_2.setBounds(190, 89, 48, 16);
		}
		return lblNewLabel_1_2;
	}

	private JTextField getTfObduid() {
		if (tfObduid == null) {
			tfObduid = new JTextField();
			tfObduid.setEditable(false);
			tfObduid.setBounds(239, 84, 120, 26);
			tfObduid.setColumns(10);
		}
		return tfObduid;
	}

	private JLabel getLblNewLabel_1_2_1() {
		if (lblNewLabel_1_2_1 == null) {
			lblNewLabel_1_2_1 = new JLabel("고객명 :");
			lblNewLabel_1_2_1.setBounds(45, 141, 61, 16);
		}
		return lblNewLabel_1_2_1;
	}

	private JTextField getTfObduname() {
		if (tfObduname == null) {
			tfObduname = new JTextField();
			tfObduname.setEditable(false);
			tfObduname.setBounds(109, 136, 188, 26);
			tfObduname.setColumns(10);
		}
		return tfObduname;
	}

	private JLabel getLblNewLabel_1_2_1_1() {
		if (lblNewLabel_1_2_1_1 == null) {
			lblNewLabel_1_2_1_1 = new JLabel("연락처 :");
			lblNewLabel_1_2_1_1.setBounds(45, 177, 61, 16);
		}
		return lblNewLabel_1_2_1_1;
	}

	private JTextField getTfObduphone() {
		if (tfObduphone == null) {
			tfObduphone = new JTextField();
			tfObduphone.setEditable(false);
			tfObduphone.setBounds(109, 172, 188, 26);
			tfObduphone.setColumns(10);
		}
		return tfObduphone;
	}

	private JTextField getTfObduadress() {
		if (tfObduadress == null) {
			tfObduadress = new JTextField();
			tfObduadress.setEditable(false);
			tfObduadress.setBounds(109, 210, 188, 26);
			tfObduadress.setColumns(10);
		}
		return tfObduadress;
	}

	private JLabel getLblNewLabel_1_2_1_1_1() {
		if (lblNewLabel_1_2_1_1_1 == null) {
			lblNewLabel_1_2_1_1_1 = new JLabel("수령지 :");
			lblNewLabel_1_2_1_1_1.setBounds(45, 215, 61, 16);
		}
		return lblNewLabel_1_2_1_1_1;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("총 수량 : ");
			lblNewLabel_3.setBounds(45, 522, 90, 16);
		}
		return lblNewLabel_3;
	}

	private JTextField getTfObdpqty() {
		if (tfObdpqty == null) {
			tfObdpqty = new JTextField();
			tfObdpqty.setEditable(false);
			tfObdpqty.setBounds(118, 517, 120, 26);
			tfObdpqty.setColumns(10);
		}
		return tfObdpqty;
	}

	private JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("총 주문 금액 : ");
			lblNewLabel_3_1.setBounds(45, 553, 90, 16);
		}
		return lblNewLabel_3_1;
	}

	private JTextField getTfobdsale() {
		if (tfobdsale == null) {
			tfobdsale = new JTextField();
			tfobdsale.setEditable(false);
			tfobdsale.setBounds(118, 548, 120, 26);
			tfobdsale.setColumns(10);
		}
		return tfobdsale;
	}

	private JButton getBtnObdback() {
		if (btnObdback == null) {
			btnObdback = new JButton("돌아가기");
			btnObdback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ObMain obdm = new ObMain();
					obdm.setVisible(true);

				}
			});
			btnObdback.setBounds(45, 604, 120, 29);
		}
		return btnObdback;
	}

	private JButton getBtnObdinsert() {
		if (btnObdinsert == null) {
			btnObdinsert = new JButton("출고 입력");
			btnObdinsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					insertOutbound();
					obdscreenPartition();

				}
			});
			btnObdinsert.setBounds(166, 604, 187, 70);
			btnObdinsert.setBackground(new Color(4, 50, 255));
		}
		return btnObdinsert;
	}

	private JButton getTnObddelete() {
		if (tnObddelete == null) {
			tnObddelete = new JButton("주문 삭제");
			tnObddelete.setBounds(45, 645, 120, 29);
			tnObddelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return tnObddelete;
	}

	private JLabel getLblNewLabel_3_2() {
		if (lblNewLabel_3_2 == null) {
			lblNewLabel_3_2 = new JLabel("제품코드 :");
			lblNewLabel_3_2.setBounds(168, 295, 61, 16);
		}
		return lblNewLabel_3_2;
	}

	private JTextField getTfObdpcode() {
		if (tfObdpcode == null) {
			tfObdpcode = new JTextField();
			tfObdpcode.setEditable(false);
			tfObdpcode.setBounds(239, 290, 105, 26);
			tfObdpcode.setColumns(10);
		}
		return tfObdpcode;
	}

	private JLabel getLblNewLabel_3_2_1() {
		if (lblNewLabel_3_2_1 == null) {
			lblNewLabel_3_2_1 = new JLabel("브랜드 : ");
			lblNewLabel_3_2_1.setBounds(45, 380, 45, 16);
		}
		return lblNewLabel_3_2_1;
	}

	private JTextField getTfObdpbrand() {
		if (tfObdpbrand == null) {
			tfObdpbrand = new JTextField();
			tfObdpbrand.setEditable(false);
			tfObdpbrand.setBounds(90, 375, 98, 26);
			tfObdpbrand.setColumns(10);
		}
		return tfObdpbrand;
	}

	private JLabel getLblNewLabel_3_2_2() {
		if (lblNewLabel_3_2_2 == null) {
			lblNewLabel_3_2_2 = new JLabel("제품명 : ");
			lblNewLabel_3_2_2.setBounds(45, 345, 45, 16);
		}
		return lblNewLabel_3_2_2;
	}

	private JTextField getTfObdpname() {
		if (tfObdpname == null) {
			tfObdpname = new JTextField();
			tfObdpname.setEditable(false);
			tfObdpname.setBounds(90, 340, 250, 26);
			tfObdpname.setColumns(10);
		}
		return tfObdpname;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("제품 정보");
			lblNewLabel_2.setBounds(45, 290, 111, 26);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 25));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblObdpic() {
		if (lblObdpic == null) {
			lblObdpic = new JLabel("");
			lblObdpic.setBounds(214, 380, 139, 129);
			lblObdpic.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblObdpic;
	}

	private JLabel getLblNewLabel_3_2_2_1() {
		if (lblNewLabel_3_2_2_1 == null) {
			lblNewLabel_3_2_2_1 = new JLabel("Color :");
			lblNewLabel_3_2_2_1.setBounds(45, 445, 45, 16);
		}
		return lblNewLabel_3_2_2_1;
	}

	private JTextField getTfObdcolor() {
		if (tfObdcolor == null) {
			tfObdcolor = new JTextField();
			tfObdcolor.setEditable(false);
			tfObdcolor.setBounds(89, 440, 99, 26);
			tfObdcolor.setColumns(10);
		}
		return tfObdcolor;
	}

	private JLabel getLblNewLabel_3_2_2_2() {
		if (lblNewLabel_3_2_2_2 == null) {
			lblNewLabel_3_2_2_2 = new JLabel("Size :");
			lblNewLabel_3_2_2_2.setBounds(45, 478, 45, 16);
		}
		return lblNewLabel_3_2_2_2;
	}

	private JTextField getTfObdsize() {
		if (tfObdsize == null) {
			tfObdsize = new JTextField();
			tfObdsize.setEditable(false);
			tfObdsize.setBounds(89, 473, 99, 26);
			tfObdsize.setColumns(10);
		}
		return tfObdsize;
	}

	private JLabel getLblNewLabel_3_2_1_1() {
		if (lblNewLabel_3_2_1_1 == null) {
			lblNewLabel_3_2_1_1 = new JLabel("가 격 : ");
			lblNewLabel_3_2_1_1.setBounds(45, 413, 45, 16);
		}
		return lblNewLabel_3_2_1_1;
	}

	private JTextField getTfObdpprice() {
		if (tfObdpprice == null) {
			tfObdpprice = new JTextField();
			tfObdpprice.setEditable(false);
			tfObdpprice.setBounds(90, 408, 98, 26);
			tfObdpprice.setColumns(10);
		}
		return tfObdpprice;
	}

	// ---------------------------Function--------------------------

	// ObMain에서 obdonum 가져와서 oder 테이블에 접속한다.
	// order 테이블에서 opcode, ouid, odate, opqty 가져온다.
	// user 테이블에서 uid, uname, uphone, uaddress
	// product 테이블에서 pcode, pname, pbrand, pcolor, psize, pprice
	
	
	// 사진 가져와서 보여주기 추가하기.

	private void showDetail() {

		ObMain obdmain = new ObMain();

		int i = obdmain.obdonum;

		DaoOutbound dao = new DaoOutbound();
		DtoOutbound dto = dao.detailList(i);

		
		//image처리 추가작업
		
		String obdFilePath = Integer.toString(ShareVar.filename) ;
		lblObdpic.setIcon(new ImageIcon(obdFilePath));
		
		
		lblObdpic.setHorizontalAlignment(SwingConstants.CENTER);
		
		File obdFile = new File(obdFilePath);
		obdFile.delete();
		

		
		//가져온 값들 tf에 입력해주기.
		tfObdonum.setText(Integer.toString(dto.getOnum()));
		tfObdodate.setText(dto.getOrderdate());
		tfObduid.setText(dto.getUid());
		tfObduname.setText(dto.getUname());
		tfObduphone.setText(dto.getUphone());
		tfObduadress.setText(dto.getUaddress());
		tfObdpcode.setText(Integer.toString(dto.getPcode()));
		tfObdpname.setText(dto.getPname());
		tfObdpbrand.setText(dto.getPbrand());
		tfObdcolor.setText(dto.getPcolor());
		tfObdsize.setText(dto.getPsize());
		tfObdpqty.setText(Integer.toString(dto.getPqty()));
		tfObdpprice.setText(Integer.toString(dto.getPprice()));

		int tempsale = dto.getPqty() * dto.getPprice();
		tfobdsale.setText(Integer.toString(tempsale));
		

		

	}

	// ----------------------- insertOubbound 입력하기 ------------------------
	// 현재 보고있는 주문을 처리하는 기능이다.
	// 지금 보고있는 주문의 수량 만큼만 outbound table에 입력한다.
	// 입력해야 하는 attribute 는 다음과 같다.
	// obaid, obonum, obpcode, obqty, obdate

	// 1. 현재 tf에 있는 값들중 필요한 값들 가져오기.
	// 2.
	// 2. Dao에서 해당 값들 입력하기.

	private void insertOutbound() {
		tObaid = "강경구"; // 관리자 아이디 넣는 곳.
		int tObonum = Integer.parseInt(tfObdonum.getText());
		int tObpcode = Integer.parseInt(tfObdpcode.getText());
		int tObqty = Integer.parseInt(tfObdpqty.getText());

		DaoOutbound dao = new DaoOutbound();
		boolean obresult = dao.insertOB(tObaid, tObonum, tObpcode, tObqty);

		if (obresult) {
			JOptionPane.showMessageDialog(this, "출고 처리가 완료되었습니다.", "출고 처리 완료.", JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(this, "출고가 정상적으로 처리되지 못 했습니다. 관리자에게 문의하세요.", "출고 입력 실패",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void obdscreenPartition() {

		int tempOnum = Integer.parseInt(tfObdonum.getText());

		DaoOutbound dao = new DaoOutbound();
		int checkorder = dao.checkOR(tempOnum);
		boolean chorder;

		// checkorder 를 (dao 에 있음)실행해서 주문번호가 출고 테이블에 있으면 0반환, 없으면 1반환, 그외의 오류에는 2를 반환한다.
		// 반환한 int값을 통해서 버튼 활성화를 정하고, 그외의 오류에 대해서는 오류메시지를 출력한다.

		if (checkorder == 0) {
			btnObdinsert.setEnabled(false);
			btnObdinsert.setText("주문 처리 완료");

		} else if (checkorder == 2) {
			btnObdinsert.setEnabled(false);
			btnObdinsert.setText("주문 검색 오류");
			JOptionPane.showMessageDialog(this, "주문번호 검색 과정에서 오류가 발생했습니다. 관리자에게 문의하세요.", "주문 번호 검색 오류",
					JOptionPane.ERROR_MESSAGE);
		} else {
			btnObdinsert.setEnabled(true);
			btnObdinsert.setText("주문 출고 입력");

		}

	}
}// obDetail endgame
