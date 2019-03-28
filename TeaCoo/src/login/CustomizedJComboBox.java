package login;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.Color;

public class CustomizedJComboBox {

	public static class MyComboBoxUI extends BasicComboBoxUI {
		public static ComponentUI createUI(JComponent c) {
			return new MyComboBoxUI();
		}

		protected JButton createArrowButton() {
			ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
					"image/combo.png"));
			JButton button = new JButton(img);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			return button;
		}
	}
}