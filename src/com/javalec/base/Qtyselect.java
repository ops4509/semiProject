package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.DaoBasket_OKH;
import com.javalec.dao.DaoInbound_OKH;
import com.javalec.dao.DaoOutbound_OKH;
import com.javalec.dao.DaoProduct_OKH;
import com.javalec.dao.DaoUser_OKH;
import com.javalec.dto.DtoInbound_OKH;
import com.javalec.dto.DtoOutbound_OKH;
import com.javalec.dto.DtoProduct_OKH;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Qtyselect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_2;
	private JLabel lbpname;
	private JLabel lbsize;
	private JLabel lbcolor;
	private JLabel lblNewLabel_6;
	private JLabel lbpic;
	private JLabel lblNewLabel_3;

	ArrayList<DtoProduct_OKH> beanList = null;

	DaoProduct_OKH dao = new DaoProduct_OKH();
	DaoInbound_OKH dao2 = new DaoInbound_OKH();
	DaoOutbound_OKH dao3 = new DaoOutbound_OKH();
	ArrayList<DtoProduct_OKH> dtoList = dao.selectList();
	ArrayList<DtoInbound_OKH> dtoList2 = dao2.selectQty();
	ArrayList<DtoOutbound_OKH> dtoList3 = dao3.minusQty();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Qtyselect dialog = new Qtyselect();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Qtyselect() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getComboBox());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getLblNewLabel_1_1());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getLbpname());
		contentPanel.add(getLbsize());
		contentPanel.add(getLbcolor());
		contentPanel.add(getLblNewLabel_6());
		contentPanel.add(getLbpic());
		contentPanel.add(getLblNewLabel_3());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("구매하기");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gotoBasket();

					}
				});
				btnOK.setActionCommand("구매하기");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("돌아가기");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("돌아가기");
				buttonPane.add(btnCancel);
			}

		}

	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
			comboBox.setBounds(270, 180, 97, 30);
		}
		return comboBox;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("구매 원하는 수량을 입력하세요 :");
			lblNewLabel.setBounds(70, 170, 190, 30);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("상품 : ");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel_1.setBounds(60, 63, 50, 16);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("사이즈 : ");
			lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel_1_1.setBounds(60, 93, 50, 16);
		}
		return lblNewLabel_1_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("색깔 :");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNewLabel_2.setBounds(55, 123, 50, 16);
		}
		return lblNewLabel_2;
	}

	private JLabel getLbpname() {
		if (lbpname == null) {
			DaoProduct_OKH dao = new DaoProduct_OKH();
			ArrayList<DtoProduct_OKH> dtoList = dao.selectList();
			lbpname = new JLabel(dtoList.get(Shoppinglist.selectedRow).getPname());
			lbpname.setBounds(124, 63, 130, 16);
		}
		return lbpname;
	}

	private JLabel getLbsize() {
		if (lbsize == null) {
			DaoProduct_OKH dao = new DaoProduct_OKH();
			ArrayList<DtoProduct_OKH> dtoList = dao.selectList();
			lbsize = new JLabel(dtoList.get(Shoppinglist.selectedRow).getPbrand());
			lbsize.setBounds(124, 93, 130, 16);
		}
		return lbsize;
	}

	private JLabel getLbcolor() {
		if (lbcolor == null) {
			DaoProduct_OKH dao = new DaoProduct_OKH();
			ArrayList<DtoProduct_OKH> dtoList = dao.selectList();
			lbcolor = new JLabel(dtoList.get(Shoppinglist.selectedRow).getPcolor());
			lbcolor.setBounds(124, 123, 130, 16);
		}
		return lbcolor;
	}

	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("장바구니 선택 품목");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_6.setBounds(161, 18, 130, 20);
		}
		return lblNewLabel_6;
	}

	private JLabel getLbpic() {
		DaoProduct_OKH daoimg = new DaoProduct_OKH(Shoppinglist.selectedRow);
		beanList = daoimg.getimg();

		if (lbpic == null) {
			lbpic = new JLabel("");

			lbpic.setIcon(new ImageIcon(
//					("./" + beanList.get(0).getPffile())
			));
			lbpic.setBounds(270, 63, 104, 104);
		}
		return lbpic;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("* 최대 가능 구매 수량 8개");
			lblNewLabel_3.setForeground(Color.GRAY);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBounds(70, 199, 161, 16);
		}
		return lblNewLabel_3;
	}

	// function

	// 수량 가져오기
	public void gotoBasket() {
		String oname, osize, ocolor, obrand;
		
		DaoInbound_OKH dao2 = new DaoInbound_OKH();
		DaoOutbound_OKH dao3 = new DaoOutbound_OKH();
		ArrayList<DtoInbound_OKH> dtoList2 = dao2.selectQty();
		ArrayList<DtoOutbound_OKH> dtoList3 = dao3.minusQty();
		DaoProduct_OKH dao = new DaoProduct_OKH(Shoppinglist.loginid, Shoppinglist.selectedRow);
		DtoProduct_OKH dto = dao.purchase();
		int qty = Integer.parseInt(comboBox.getSelectedItem().toString());
		oname = dto.getPname();
		obrand = dto.getPbrand();
		ocolor = dto.getPcolor();
		osize = dto.getPsize();
		if (dtoList2.get(Shoppinglist.selectedRow).getIbqty() - dtoList3.get(Shoppinglist.selectedRow)
				.getObqty() < qty) {
			JOptionPane.showMessageDialog(null,Shoppinglist.loginname + "님!\n " + obrand + "브랜드 의 " + oname + "<" + ocolor + ">의 "
					+ osize + "사이즈 " + "는 재고가 부족합니다.\n죄송합니다.!");
			Shoppinglist shoppinglist = new Shoppinglist();
			shoppinglist.setVisible(true);
			dispose();
		}else {
			DaoBasket_OKH daobasket = new DaoBasket_OKH(Shoppinglist.loginid, Shoppinglist.selectedRow, Integer.parseInt(comboBox.getSelectedItem().toString()));
			daobasket.insertBasket();
			
			setVisible(false);
			Basket basket = new Basket();
			basket.setVisible(true);
			
		}
	}
	
	
}
