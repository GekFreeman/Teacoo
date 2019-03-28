package register;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComboBoxUI;

import login.CustomizedJComboBox.MyComboBoxUI;

import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistFrame extends JFrame implements ActionListener {
	static String userID;
	private JPanel container;

	// 头像框
	private JLabel lblNewLabel = new JLabel();
	private JTextField picpath;
	private JTextField Username;
	private JTextField Phone;
	private JTextField Email;
	private JTextField Question;

	// 密码文本域
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	// 用户名是否合法
	boolean t = true;

	private PicFrame picFrame = null;
	private static String picPath = null;
	private int n = 0;

	private JButton btnChoose;// 选择头像
	private JButton btnUpload;// 上传头像
	private JButton btnReset;
	private JButton btnRegist;
	private JButton btnAccept;
	private boolean isAccept = false;

	private JComboBox mailBox;
	private JComboBox questionBox;

	private JButton btnX;
	private JButton btnMove;
	private Point loc = null;
	private Point tmp = null;
	boolean isDragged = false;

	// 变量
	static String username;
	static String password;
	static String phone;
	static StringBuffer email = new StringBuffer();
	static String question;
	static String answer;

	static String UserID_Last;
	static String UserID;

	// 数据库
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prestatment;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistFrame frame = new RegistFrame();
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
	public RegistFrame() {

		setResizable(false);
		// 去掉窗口的装饰
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(550, 100, 500, 650);

		container = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/register/registBG.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		container.setBounds(0, 0, 500, 650);
		container.setLayout(null);
		getContentPane().add(container);

		// 头像框
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBounds(32, 37, 100, 100);
		container.add(lblNewLabel);

		this.picPath = "";
		ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(""));
		img.setImage(img.getImage().getScaledInstance(100, 100, 3));
		lblNewLabel.setIcon(img);

		// 图片路径
		picpath = new JTextField("Default");
		picpath.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		picpath.setBorder(null);
		picpath.setEditable(false);
		picpath.setBounds(159, 48, 217, 35);
		container.add(picpath);
		picpath.setColumns(10);

		// 关闭
		btnX = new JButton();
		btnX.setBounds(460, 0, 36, 35);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/login/btnX.png")));
		btnX.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/login/btnXO.png")));
		btnX.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		container.add(btnX);

		// 移动
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

		// 选择头像
		btnChoose = new JButton();
		btnChoose.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/register/choose.png")));
		btnChoose.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/register/chooseO.png")));
		btnChoose.setBounds(159, 102, 100, 35);
		btnChoose.setContentAreaFilled(false);
		btnChoose.setBorderPainted(false);
		container.add(btnChoose);
		this.btnChoose.addActionListener(this);

		// 上传头像
		btnUpload = new JButton();
		btnUpload.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/register/upload.png")));
		btnUpload.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/register/uploadO.png")));
		btnUpload.setBounds(276, 102, 100, 35);
		btnUpload.setContentAreaFilled(false);
		btnUpload.setBorderPainted(false);
		container.add(btnUpload);
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser("E:\\");
				fileChooser.addChoosableFileFilter(new PortraitFilter("jpg"));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					picpath.setText(file.getPath());
					picPath = file.getPath();

					ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage(file.getPath()));
					image.setImage(image.getImage().getScaledInstance(100, 100,
							3));
					lblNewLabel.setIcon(image);

				}

				else if (result == JFileChooser.CANCEL_OPTION) {
//					picpath.setText("Default");
				}
			}
		});

		// 用户名是否合规
		JLabel lblRight = new JLabel("");
		lblRight.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblRight.setBounds(379, 195, 54, 23);
		container.add(lblRight);

		// 用户名
		Username = new JTextField();
		Username.setBorder(null);
		Username.setBounds(192, 195, 184, 29);
		Username.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		container.add(Username);
		Username.setColumns(10);
		Username.setToolTipText("numbers,letters and '_' are allowed only");
		Username.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int n = Username.getText().length();
				t = true;
				if (n >= 1) {
					for (int i = 0; i <= n - 1; i++) {
						int a = Username.getText().charAt(i);
						if (a == 95 || a >= 48 && a <= 57 || a >= 97
								&& a <= 122 || a >= 65 && a <= 90)
							;
						else {
							t = false;
						}
					}
				}
				if (t == true) {
					lblRight.setText("");
				}

				if (t == false) {
					lblRight.setText("wrong");
				}
			}
		});

		// 密码强度
		JLabel lblStrength = new JLabel("");
		lblStrength.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblStrength.setBounds(379, 234, 61, 23);
		container.add(lblStrength);

		// 输入密码
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setBounds(192, 230, 184, 29);
		container.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				n = 0;
				for (int i = 1; i <= passwordField.getText().length(); i++) {
					n += Password(passwordField.getText().charAt(i - 1));
				}

				if (n == 0)
					lblStrength.setText("");

				if (n < 15 && n > 0) {
					lblStrength.setBackground(Color.RED);
					lblStrength.setBackground(Color.WHITE);
					lblStrength.setBackground(Color.WHITE);
					lblStrength.setText("weak");
				} else if (n < 30 && n > 0) {
					lblStrength.setBackground(Color.RED);
					lblStrength.setBackground(Color.ORANGE);
					lblStrength.setBackground(Color.WHITE);
					lblStrength.setText("medium");
				} else if (n >= 45) {
					lblStrength.setBackground(Color.RED);
					lblStrength.setBackground(Color.ORANGE);
					lblStrength.setBackground(Color.GREEN);
					lblStrength.setText("strong");
				}

			}
		});

		// 密码核对
		JLabel lblMatch = new JLabel("");
		lblMatch.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblMatch.setBounds(379, 264, 79, 23);
		container.add(lblMatch);

		// 确认密码
		passwordField_1 = new JPasswordField();
		passwordField_1.setBorder(null);
		passwordField_1.setBounds(192, 265, 184, 29);
		container.add(passwordField_1);
		passwordField_1.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if (!passwordField_1.getText().equals(passwordField.getText()))
					lblMatch.setText("mismatch");
				if (passwordField_1.getText().equals(passwordField.getText()))
					lblMatch.setText("");
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				lblMatch.setText("");
			}
		});

		// 手机
		Phone = new JTextField();
		Phone.setBorder(null);
		Phone.setBounds(192, 301, 184, 29);
		Phone.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		container.add(Phone);
		Phone.setColumns(10);

		// 邮箱
		Email = new JTextField();
		Email.setBorder(null);
		Email.setBounds(192, 344, 96, 29);
		Email.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		container.add(Email);
		Email.setColumns(10);

		// 答案
		Question = new JTextField();
		Question.setBorder(null);
		Question.setBounds(192, 421, 184, 29);
		Question.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		container.add(Question);
		Question.setColumns(10);

		btnAccept = new JButton();
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isAccept) {
					btnAccept.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage("image/register/treatyX.png")));
					btnRegist.setEnabled(false);
					isAccept = false;
				} else {
					btnAccept.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage("image/register/treatyY.png")));
					btnRegist.setEnabled(true);
					isAccept = true;

				}
			}
		});
		btnAccept.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/register/treatyX.png")));
		btnAccept.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/register/treatyO.png")));

		btnAccept.setBounds(115, 492, 250, 35);
		btnAccept.setContentAreaFilled(false);
		btnAccept.setBorderPainted(false);
		container.add(btnAccept);

		btnReset = new JButton();
		btnReset.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/register/reset.png")));
		btnReset.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/register/resetO.png")));
		btnReset.setBounds(100, 605, 100, 35);
		btnReset.setContentAreaFilled(false);
		btnReset.setBorderPainted(false);

		container.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Username.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
				Phone.setText(null);
				Email.setText(null);
				mailBox.setSelectedItem(null);
				questionBox.setSelectedItem(null);
				Question.setText(null);
				lblMatch.setText("");
				lblStrength.setText("");
				lblRight.setText("");
			}
		});

		btnRegist = new JButton();
		btnRegist.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/register/regist.png")));
		btnRegist.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/register/registO.png")));
		btnRegist.setBounds(304, 605, 100, 35);
		btnRegist.setContentAreaFilled(false);
		btnRegist.setBorderPainted(false);
		btnRegist.setEnabled(false);
		container.add(btnRegist);
		btnRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 用户名
				if (Username.getText().equals("")) {
					Dialog d = new Dialog(1);
					d.setVisible(true);
					return;
				}

				// 用户名包含不合法字符
				if (t == false) {
					Dialog d = new Dialog(13);
					d.setVisible(true);
					return;
				}

				// 密码
				if (passwordField.getPassword().length == 0) {
					Dialog d = new Dialog(2);
					d.setVisible(true);
					return;
				}
				// 确认密码
				if (passwordField.getPassword().length < 6) {
					Dialog d = new Dialog(3);
					d.setVisible(true);
					return;
				}

				// 强度太弱
				// if(n<15){
				// Dialog d=new Dialog(4);
				// return;
				// }

				// 确认密码
				if (passwordField_1.getPassword().length == 0) {
					Dialog d = new Dialog(5);
					d.setVisible(true);
					return;
				}

				// 手机
				if (Phone.getText().equals("")) {
					Dialog d = new Dialog(6);
					d.setVisible(true);
					return;
				}

				// 邮箱
				if (Email.getText().equals("")) {
					Dialog d = new Dialog(7);
					d.setVisible(true);
					return;
				}

				// 邮箱后缀
				if (mailBox.getSelectedItem() == null) {
					Dialog d = new Dialog(8);
					d.setVisible(true);
					return;
				}

				// 密保问题
				if (questionBox.getSelectedItem() == null) {
					Dialog d = new Dialog(9);
					d.setVisible(true);
					return;
				}

				// 问题答案
				if (Question.getText().equals("")) {
					Dialog d = new Dialog(10);
					d.setVisible(true);
					return;
				}

				else {
					// 确认密码是否正确
					if (!passwordField.getText().equals(
							(passwordField_1.getText()))) {
						Dialog d = new Dialog(11);
						d.setVisible(true);
						return;
					}
					username = Username.getText();
					password = passwordField.getText();
					phone = Phone.getText();
					email.append(Email.getText());
					email.append((String) mailBox.getSelectedItem());
					question = (String) questionBox.getSelectedItem();
					answer = Question.getText();

					// 注册成功

					query();
					int userID = Integer.parseInt(UserID_Last) + 1;
					UserID = userID + "";
					Dialog d = new Dialog(12);
					d.userID = UserID;
					d.complete();
					d.setVisible(true);

					dispose();
					insert();
					update();

				}
			}
		});

		mailBox = new JComboBox();
		mailBox.setModel(new DefaultComboBoxModel(new String[] { "@qq.com",
				"@gmail.com", "@126.com" }));
		mailBox.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		mailBox.setUI((ComboBoxUI) MyComboBoxUI.createUI(mailBox));
		mailBox.setOpaque(false);
		mailBox.setEditable(true);
		mailBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		mailBox.setBounds(292, 344, 107, 29);
		container.add(mailBox);

		questionBox = new JComboBox();
		questionBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		questionBox.setModel(new DefaultComboBoxModel(new String[] {
				"what`s your mother`s name?", "what's your father's name?",
				"what's your student ID?" }));
		questionBox.setUI((ComboBoxUI) MyComboBoxUI.createUI(questionBox));
		questionBox.setOpaque(false);
		questionBox.setEditable(true);
		questionBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		questionBox.setBounds(192, 386, 207, 29);
		container.add(questionBox);

		// 点击ficFrame外，关闭面板
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() != picFrame)
					if (picFrame != null)
						picFrame.dispose();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnChoose) {

			// 当点击选择按钮后，选择头像
			if (this.picFrame == null) {
				this.picFrame = new PicFrame(this, btnChoose.getX() + 500,
						btnChoose.getY() + 150);
				
			} else {
				// 选择后，关闭面板
				this.picFrame.dispose();
				this.picFrame = null;

			}
		}

		if (e.getSource() == btnUpload) {
			if (this.picFrame != null) {
				this.picFrame.dispose();
				this.picFrame = null;
			}
		}
	}

	public void setPic(String picPath) {

		this.picPath = picPath;
		ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				this.picPath));
		this.lblNewLabel.setIcon(image);
		picpath.setText(picPath);

	}

	public void windowClosing(WindowEvent e) {

		// 窗口正在关闭的按钮
		if (this.picFrame != null) {
			this.picFrame.dispose();
			this.picFrame = null;
		}
	}

	// 密码强度加权
	public int Password(char a) {
		int b = a;
		if (b >= 48 && b <= 57)
			return 1;
		if (b >= 97 && b <= 122)
			return 2;
		if (b >= 65 && b <= 90)
			return 4;
		else
			return 8;
	}

	// 插入数据库
	public static void insert()// 插入
	{

		connection = getConnection();
		try {
			prestatment = connection
					.prepareStatement("insert into userinfo(UserID,Username,Password,Phone,Email,SecureQuestion,SecureAnswer,Profile) values (?,?,?,?,?,?,?,?)");
			prestatment.setString(1, UserID);
			prestatment.setString(2, username);
			prestatment.setString(3, password);
			prestatment.setString(4, phone);
			String email_1 = email.toString();
			prestatment.setString(5, email_1);
			prestatment.setString(6, question);
			prestatment.setString(7, answer);
			prestatment.setString(8, picPath);
			int count = prestatment.executeUpdate();// execute statement
			System.out.println("Insert " + count
					+ " records into departments table");
			connection.close();

		} catch (SQLException e) {
			System.out.println("insert record failed" + e.getMessage());
		}

	}

	public static void query() { // 查询

		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo  order by UserID desc "; // select
																			// statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			System.out.println("Query results are as follow");
			rs.next();
			UserID_Last = rs.getString("UserID");
			System.out.println(UserID_Last);

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
	}

	public static Connection getConnection()// 连接
	{
		Connection c = null;

		String ur1 = "jdbc:mysql://localhost/teacoo";
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

	public static void update() {
		connection = getConnection();
		try {
			String UID = UserID;

			String sta1 = "create table teacoo.wrong" + UID
					+ " like teacoo.wrongcollection";
			System.out.println(sta1);
			prestatment = connection.prepareStatement(sta1);
			prestatment.executeUpdate();

			String sta2 = "create table teacoo.star" + UID
					+ " like teacoo.star";
			System.out.println(sta2);
			prestatment = connection.prepareStatement(sta2);
			prestatment.executeUpdate();

//			String sta3 = "create table teacoo.store" + UID
//					+ " like teacoo.store";
//			System.out.println(sta3);
//			prestatment = connection.prepareStatement(sta3);
//			prestatment.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			System.out.println("Update failed " + e.getMessage());
		}
	}
}
