package collection;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Dialog extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel container;
	public MultilineLabel showLabel;

	private JButton btnX;
	private JButton btnMove;
	public JButton btnY;
	private JButton btnN;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;

	String status;
	String applyid;
	

	public Dialog() {
	
		setResizable(false);
		setUndecorated(true); // 去掉窗口的装饰
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(550, 300, 400, 200);
		
		container=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/blankPop.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		container.setBounds(0, 0, 400, 200);
		container.setLayout(null);
		getContentPane().add(container);
		
		btnX = new JButton();
		btnX.setBounds(360, 0, 36, 35);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/btnX.png")));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnX.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/btnXO.png")));
			}

			@Override
			public void mousePressed(MouseEvent e) {
                 dispose();
			}
		});
		container.add(btnX);
		
		btnMove = new JButton();
		btnMove.setBounds(0, 0, 360, 35);
		btnMove.setContentAreaFilled(false);
		btnMove.setBorderPainted(false);
		btnMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 拖动event
			@Override
			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
				setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});

		btnMove.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(getLocation().x + e.getX() - tmp.x, getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
		container.add(btnMove);

		btnY=new JButton();
		btnY.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/btnY.png")));
		btnY.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/btnYO.png")));
		btnY.setContentAreaFilled(false);
		btnY.setBorderPainted(false);
		btnY.setBounds(91, 140, 100, 50);
		container.add(btnY);
		
		btnN=new JButton();
		btnN.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/btnN.png")));
		btnN.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/btnNO.png")));
		btnN.setContentAreaFilled(false);
		btnN.setBorderPainted(false);
		btnN.setBounds(211, 140, 100, 50);
		container.add(btnN);
		
		showLabel = new MultilineLabel();
		showLabel.setBounds(80, 79,250, 44);
		showLabel.setFont(new Font("Century Gothic", Font.BOLD,14));
		showLabel.setOpaque(false);
		container.add(showLabel);
	

		MyButtonListener lis = new MyButtonListener();

		btnY.addActionListener(lis);
		btnN.addActionListener(lis);
	

	}
	

	public String userID, uname, stem, a, b, c, d, anwr, type, anlys;

	private class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == btnY) {
				
					new InsertToDB();
		
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",
							Locale.CHINESE);

					InsertToDB.insert(userID, df.format(cal.getTime())
							.toString(), stem, a, b, c, d, anwr, type, anlys);

					dispose();
					TipsPop tip=new TipsPop();
					tip.showLabel.setText("Your application is waiting for check!");
					tip.setVisible(true);
				
			} else if (obj == btnN) {
				dispose();
			}
		}
	}
}
