package personal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

public class PwConfirm extends JPanel {

	static String userID;
	private JButton btnX;
	private JButton btnMove;
	private JButton btnConfirm;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;

	private JPanel container;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPanel intoedit;
	public static String password_1;
	public static String password_2;

	private JLabel labintoedit;
	private JLabel labfailedit;
	private JLabel labnoequ;

	public static boolean a = false;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public PwConfirm(String userID) {
		this.userID = userID;
		setVisible(false);
		setBounds(300, 310, 400, 200);
		setLayout(null);

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
		container.setVisible(true);
		add(container);

		intoedit = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/personalInfo/pwPop_1.jpg"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		intoedit.setBounds(0, 0, 400, 200);
		intoedit.setLayout(null);
		intoedit.setVisible(false);
		add(intoedit);

		labintoedit = new JLabel("You can edit your information now!");
		labintoedit.setBounds(30, 80, 500, 20);
		labintoedit.setFont(new Font("century Gothic", Font.ITALIC, 20));
		labintoedit.setForeground(Color.white);
		labintoedit.setVisible(true);
		intoedit.add(labintoedit);

		labfailedit = new JLabel("The password input is wrong!");
		labfailedit.setBounds(51, 10, 500, 20);
		labfailedit.setFont(new Font("century Gothic", Font.ITALIC, 20));
		labfailedit.setForeground(Color.white);
		labfailedit.setVisible(false);
		container.add(labfailedit);

		labnoequ = new JLabel("The new passwords input are not equal!");
		labnoequ.setBounds(10, 10, 500, 20);
		labnoequ.setFont(new Font("century Gothic", Font.ITALIC, 20));
		labnoequ.setForeground(Color.white);
		labnoequ.setVisible(false);
		container.add(labnoequ);
		
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
			setVisible(false);
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
		btnConfirm.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/confirm.png")));
		btnConfirm.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/confirmO.png")));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				labfailedit.setVisible(false);
				labnoequ.setVisible(false);
				password_1 = String.valueOf(passwordField.getPassword());
				password_2 = String.valueOf(passwordField_1.getPassword());
				// 如果两次密码输入不相等
				if (!password_1.equalsIgnoreCase(password_2)) {
					labnoequ.setVisible(true);
					passwordField.setText(null);
					passwordField_1.setText(null);
				}
				// 如果都有数据了就开始连接数据库验证
				else {
					String password_0 = Query.query_pw(userID); // 查询该用户的密码
					if (password_0.equalsIgnoreCase(password_1)) {
						a = true; // 标记登入成功
						container.setVisible(false); // 密码输入界面消失
						intoedit.setVisible(true); // 登入成功提示界面出现
					}
					// 密码输入错误
					else {
						labfailedit.setVisible(true);
						passwordField.setText(null);
						passwordField_1.setText(null);
					}

				}

			}
		});
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setBorderPainted(false);
		btnConfirm.setBounds(152, 155, 100, 35);
		container.add(btnConfirm);

		passwordField = new JPasswordField();
		passwordField.setBounds(161, 88, 127, 18);
		passwordField.setBackground(null);
		container.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(161, 130, 127, 18);
		passwordField_1.setBackground(null);
		container.add(passwordField_1);

	}
}
