package login;

import portal.*;
import register.RegistFrame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import login.CustomizedJComboBox.MyComboBoxUI;
import management.AdminPane;
import javax.swing.DefaultComboBoxModel;
public class LoginMain extends JFrame implements ActionListener {
	String userID;
	private JPanel contentPane;
	// ����ͼƬ
	private ImageIcon BGimg;
	private JLabel BGLabel;
	static JPasswordField passwordField;
	private JLabel portraitShade;
	private JLabel portrait;
	private JLabel userIcon;
	private JButton btnLogin;
	private JButton btnRegist;
	private JButton btnFgtPw;
	private JButton btnRmb;
	private boolean isRmb = false;
	// �رմ���
	private JButton btnX;
	// �϶�����
	private JButton btnMove;
	private Point loc = null;
	private Point tmp = null;
	boolean isDragged = false;
	private JButton button;
	// �����
	private Keyboard kbFrame;
	private JButton btnKb;
	private JComboBox comboBox;
	// BGM
	Sound bgm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMain frame = new LoginMain();
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
	public LoginMain() {
		loginUI();
	}
	public void loginUI() {
		setResizable(false);
		setBounds(650, 200, 300, 500);
		setUndecorated(true); // ȥ�����ڵ�װ��
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		// BGM
		bgm = new Sound();
		getContentPane().add(bgm);

		// ���ñ���ͼƬ
		BGimg = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/loginBG1.gif"));// ����ͼƬ
		BGLabel = new JLabel(BGimg);// ������ͼ���ڱ�ǩ�
		getLayeredPane().add(BGLabel, new Integer(Integer.MIN_VALUE));// ��������ǩ��ӵ�jframe��
																		// LayeredPane����
		BGLabel.setBounds(0, 0, 300, 500);// ���ñ�����ǩ��λ��

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);

		btnMove = new JButton();
		btnMove.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/btn/moveOP.png")));
		btnMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// �϶�event
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
		btnMove.setContentAreaFilled(false);
		btnMove.setBorderPainted(false);
		btnMove.setBounds(0, 0, 260, 23);
		contentPane.add(btnMove);

		btnX = new JButton();
		btnX.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/btnX.png")));
		btnX.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/btnXO.png")));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setBounds(264, 0, 26, 35);
		contentPane.add(btnX);
		
		userIcon =new JLabel();
		userIcon.setBounds(47, 193, 24, 24);
		userIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/user.png")));
		contentPane.add(userIcon);

		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBounds(83, 230, 129, 23);
		contentPane.add(passwordField);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"10000", "10001", "10002"}));
		comboBox.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboBox));
		comboBox.setOpaque(false);
		comboBox.setEditable(true);
		comboBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		comboBox.setBounds(83, 195, 161, 23);
		contentPane.add(comboBox);

		btnFgtPw = new JButton();
		btnFgtPw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgetPop f = new ForgetPop(userID);
				f.setVisible(true);
			}
		});
		btnFgtPw.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/fgtpwO.png")));
		btnFgtPw.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/fgtpw.png")));
		btnFgtPw.setContentAreaFilled(false);
		btnFgtPw.setBorderPainted(false);
		btnFgtPw.setBounds(74, 270, 150, 30);
		contentPane.add(btnFgtPw);

		btnLogin = new JButton();
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userID;
				String password;
				userID = comboBox.getSelectedItem().toString().trim();
				password = passwordField.getText().trim();
				if (userID.equals("Administrator") || password.equals("315")) {
					AdminPane pPanel = new AdminPane();
					MainFrame pFrame = new MainFrame(userID);
					pFrame.setVisible(true);
					pFrame.getContentPane().add(pPanel);
					pPanel.presentframe = pFrame;
					dispose();
				} else {
					System.out.println("��ʼ���������֤");
					Connection connection;
					connection = getConnection();
					if (userID.equals("") || password.equals(""))// ���û���û���������,����ʾ�Բ���,�������û���������
					{
						
						TipsPop pop=new TipsPop(1);
						pop.setVisible(true);
					} else// ������������˾Ϳ�ʼ�������ݿ���֤
					{
						Statement sta1 = null;
						try {
							sta1 = connection.createStatement();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ResultSet res1 = null;
						try {
							res1 = sta1
									.executeQuery("select * from userinfo where UserID='"
											+ userID + "' ");

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						try {
							if (res1.next())// �������,����֤����
							{
								System.out.println("��ʼ��֤��");

								if (res1.getString("password").equals(password))// ���������ȷ����ʾ,��֮
								{

									PortalPane pPanel = new PortalPane(userID);
									MainFrame pFrame = new MainFrame(userID);
									pFrame.setVisible(true);
									pFrame.getContentPane().add(pPanel);
									pPanel.presentframe = pFrame;
									dispose();
								}

								else {
									TipsPop pop=new TipsPop(2);
									pop.setVisible(true);
									
								}
							}

							else// ���û�в��ҵ��û�������ʾ
							{
							
								TipsPop pop=new TipsPop(3);
								pop.setVisible(true);

							}

							connection.close();
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnLogin.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/btnLogin.png")));
		btnLogin.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/btnLoginP.png")));
		btnLogin.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/btnLoginO.png")));
		btnLogin.setBounds(70, 325, 161, 38);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		contentPane.add(btnLogin);

		btnRegist = new JButton();
		btnRegist.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/register.png")));
		btnRegist.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/registerO.png")));
		btnRegist.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/registerP.png")));
		btnRegist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistFrame r = new RegistFrame();
				r.setVisible(true);
			}
		});
		btnRegist.setBounds(75, 458, 150, 30);
		btnRegist.setContentAreaFilled(false);
		btnRegist.setBorderPainted(false);
		contentPane.add(btnRegist);

		btnRmb = new JButton();
		btnRmb.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/rmbX.png")));
		btnRmb.addMouseListener(new MouseAdapter() {
			@Override	
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isRmb) {
					btnRmb.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage("image/login/rmbX.png")));
					isRmb = false;
				} else {
					btnRmb.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage("image/login/rmbY.png")));
					isRmb = true;
				}
			}
		});
		btnRmb.setBounds(43, 226, 30, 30);
		btnRmb.setContentAreaFilled(false);
		btnRmb.setBorderPainted(false);
		contentPane.add(btnRmb);

		portraitShade = new JLabel();
		portraitShade.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage("image/login/portraitshade2.png")));
		portraitShade.setOpaque(false);
		portraitShade.setBounds(100, 67, 100, 100);
		contentPane.add(portraitShade);
		
		portrait=new JLabel();
		portrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage("image/portal/defaultPortrait.jpg")));
		portrait.setBounds(100, 67, 100, 100);
		contentPane.add(portrait);

		// ������̡���ť
		btnKb = new JButton();
		 btnKb.setIcon(new
		 ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/kb.png")));
		 btnKb.setRolloverIcon(new
				 ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/kbO.png")));
		 btnKb.setContentAreaFilled(false);
		 btnKb.setBorderPainted(false);
		btnKb.setBounds(222, 229, 24, 24);
		this.btnKb.addActionListener(this);
		getContentPane().add(btnKb);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnKb) {
			if (this.kbFrame == null) {
				this.kbFrame = new Keyboard(this, btnKb.getX() + 740,
						btnKb.getY() + 200);
			} else {

				this.kbFrame.dispose();
				this.kbFrame = null;

			}
		}
	}

	public static Connection getConnection() {
		Connection c = null;
		String ur1 = "jdbc:mysql://localhost:3306/teacoo?characterEncoding=utf8";
		String username = "root";
		String password = "root";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			c = DriverManager.getConnection(ur1, username, password);
			System.out.println("Connect Success");

		} catch (ClassNotFoundException cnfex) {
			System.out.println("Failed to load JDBC driver.");
			cnfex.printStackTrace();
			System.exit(1);
		} catch (SQLException sqlex) {
			System.err.println("Unable to connect");
			sqlex.printStackTrace();
		}
		return c;
	}

}
