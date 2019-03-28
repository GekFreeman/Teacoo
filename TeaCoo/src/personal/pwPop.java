package personal;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class pwPop extends JFrame {
	static String userID;
	private JButton btnX;
	private JButton btnMove;
	private JButton btnConfirm;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;

	private JPanel container;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pwPop frame = new pwPop(userID);
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
	public pwPop(String userID) {
		this.userID = userID;
		setResizable(false);
		// 去掉窗口的装饰
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(550, 300, 400, 200);

		container = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/personalInfo/pwPop.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		container.setBounds(0, 0, 400, 200);
		container.setLayout(null);
		getContentPane().add(container);

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
				// System.exit(0);
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
					loc = new Point(getLocation().x + e.getX() - tmp.x,
							getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
		container.add(btnMove);

		btnConfirm = new JButton();
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnConfirm.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/confirm.png")));
		btnConfirm.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/confirmO.png")));
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setBorderPainted(false);
		btnConfirm.setBounds(152, 155, 100, 35);
		container.add(btnConfirm);
	}

}
