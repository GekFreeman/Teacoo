package collection;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DialogPop extends SuperPop {

	private static final long serialVersionUID = 1L;
	
public JButton btnY,btnN;
	public DialogPop() {
super();
	}
	public void addButton(){
		btnY = new JButton();
		btnY.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/btnY.png")));
		btnY.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/collection/btnYO.png")));
		btnY.setContentAreaFilled(false);
		btnY.setBorderPainted(false);
		btnY.setBounds(91, 140, 100, 50);
		container.add(btnY);

		btnN = new JButton();
		btnN.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/btnN.png")));
		btnN.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/collection/btnNO.png")));
		btnN.setContentAreaFilled(false);
		btnN.setBorderPainted(false);
		btnN.setBounds(211, 140, 100, 50);
		container.add(btnN);

		showLabel = new MultilineLabel();
		showLabel.setBounds(80, 79, 250, 44);
		showLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		showLabel.setOpaque(false);
		container.add(showLabel);

		MyButtonListener lis = new MyButtonListener();

		btnY.addActionListener(lis);
		btnN.addActionListener(lis);

	}

	public String userID, stem, a, b, c, d, anwr, type, anlys;

	private class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == btnY) {// 单击Yes按钮，将申请插入数据库

				new InsertToDB();
				/* 获取当前日期 */
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",
						Locale.CHINESE);

				InsertToDB.insert(userID, df.format(cal.getTime()).toString(),
						stem, a, b, c, d, anwr, type, anlys);

				dispose();
				TipsPop tip = new TipsPop();
				tip.showLabel.setText("Your application is waiting for check!");
				tip.setVisible(true);

			} else if (obj == btnN) {
				dispose();
			}
		}
	}
}
