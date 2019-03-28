package collection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TipsPop extends SuperPop {

	private static final long serialVersionUID = 1L;
	public TipsPop() {
		super();

	}
	public void addButton(){
		btnOK = new JButton();
		btnOK.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/btnOK.png")));
		btnOK.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/collection/btnOKO.png")));
		btnOK.setContentAreaFilled(false);
		btnOK.setBorderPainted(false);
		btnOK.setBounds(153, 155, 100, 35);
		container.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
}
