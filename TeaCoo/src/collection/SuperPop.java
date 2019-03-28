package collection;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class SuperPop extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton btnX, btnMove;
	public JButton btnOK;
	public JPanel container;
	private boolean isDragged = false;
	private Point loc = null, tmp = null;

	MultilineLabel showLabel;
	public SuperPop(){
		setResizable(false);
		/* 去掉窗口的装饰 */
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(550, 300, 400, 200);
		setBackground(new Color(0, 0, 0, 0));

		container = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/collection/blankpop.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		container.setBounds(0, 0, 400, 200);
		container.setLayout(null);
		getContentPane().add(container);

		showLabel = new MultilineLabel();
		showLabel.setBounds(80, 79, 250, 44);
		showLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		container.add(showLabel);
       /*将弹窗关掉的按钮*/
		btnX = new JButton();
		btnX.setBounds(360, 0, 36, 35);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/btnX.png")));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnX.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/login/btnXO.png")));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});

		container.add(btnX);
      /*可以拖动的区域*/
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
					loc = new Point(getLocation().x + e.getX() - tmp.x,
							getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
		container.add(btnMove);
		addButton();		
	}
	
	public void addButton(){
		
	}
}
