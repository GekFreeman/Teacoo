package personal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import portal.PortalPane;

import java.io.File;

public class PersonalPane extends JPanel {
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;

	// 更改密码
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	public static String password_1;
	public static String password_2;
	public static String password_3;
	private JButton btnconfirm;

	private JPanel container;
	private JPanel basicPane;// 基本资料
	private JPanel morePane;// 更多资料
	private JPanel labchangepw;// 旧密码更改密码
	private JPanel labchangeres;
	private JButton btnBack;
	private JButton btnEdit;
	private JButton btnChangePw;// 更改密码
	private JButton btnUpdate;// 更新修改
	private JButton btnBasic;
	private JButton btnMore;

	private JLabel labuserID;
	private JLabel labuserLevel;
	private JLabel labprofile;
	
	private JLabel succ;
	private JLabel fail;
	private JLabel noequ;

	private JLabel labsignature;
	private JLabel labname;
	private JLabel labgender;
	private JLabel labphone;
	private JLabel labmail;
	private JLabel labmajor;
	private JLabel labgrade;
	private JLabel labAdvan;
	private JLabel labHobby;

	String[] info = new String[13];

	boolean edit = false;
	boolean changepw = false;

	public PersonalPane(String userID) {
		this.userID = userID;

		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);

		btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PortalPane pPane = new PortalPane(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pPane);
				pPane.presentframe = presentframe;
				pPane.presentpanel = pPane;
			}
		});
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);

		btnBasic = new JButton();
		btnBasic.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/basic.png")));
		btnBasic.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/basicO.png")));
		btnBasic.setBounds(333, 264, 150, 40);
		btnBasic.setContentAreaFilled(false);
		btnBasic.setBorderPainted(false);
		container.add(btnBasic);

		btnMore = new JButton();
		btnMore.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/more.png")));
		btnMore.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/moreO.png")));
		btnMore.setBounds(516, 264, 150, 40);
		btnMore.setContentAreaFilled(false);
		btnMore.setBorderPainted(false);
		container.add(btnMore);

		basicPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/personalInfo/basicpane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		basicPane.setBounds(237, 281, 513, 259);
		basicPane.setOpaque(false);
		basicPane.setLayout(null);
		container.add(basicPane);

		morePane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/personalInfo/morepane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		morePane.setBounds(238, 282, 513, 259);
		morePane.setOpaque(false);
		morePane.setLayout(null);
		morePane.setVisible(false);
		container.add(morePane);

		PwConfirm passwordPanel = new PwConfirm(userID);
		add(passwordPanel);

		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				morePane.setVisible(true);
				basicPane.setVisible(false);
				passwordPanel.setVisible(false);
				labchangepw.setVisible(false);
				labchangeres.setVisible(false);
			}
		});

		btnBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				morePane.setVisible(false);
				basicPane.setVisible(true);
				passwordPanel.setVisible(false);
				labchangepw.setVisible(false);
				labchangeres.setVisible(false);
			}
		});

		// 进入信息修改界面
		btnEdit = new JButton();
		btnEdit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/edit.png")));
		btnEdit.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/editO.png")));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordPanel.setVisible(true);
				morePane.setVisible(false);
				basicPane.setVisible(false);
				labchangepw.setVisible(false);
				if (passwordPanel.a) {
					editInfo ePane = new editInfo(userID);
					presentpanel.setVisible(false);
					presentframe.getContentPane().add(ePane);
					ePane.presentframe = presentframe;
					ePane.presentpanel = ePane;
				}

			}
		});
		btnEdit.setBounds(237, 636, 150, 35);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setBorderPainted(false);
		container.add(btnEdit);

		// 旧修改密码
		labchangeres = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/personalInfo/pwPop_1.jpg"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		labchangeres.setBounds(300, 315, 400, 200);
		labchangeres.setLayout(null);
		labchangeres.setVisible(false);
		container.add(labchangeres);
		succ=new JLabel("You have changed your password!");
		succ.setBounds(35, 45, 400, 100);
		succ.setVisible(false);
		labchangeres.add(succ);
		succ.setFont(new Font("century Gothic", Font.PLAIN, 20));
		succ.setForeground(Color.white);
		fail=new JLabel("The password input is wrong!");
		fail.setBounds(55, 45, 400, 100);
		fail.setVisible(false);
		labchangeres.add(fail);
		fail.setFont(new Font("century Gothic", Font.PLAIN, 20));
		fail.setForeground(Color.white);
		noequ=new JLabel("The passwords input are not equal!");
		noequ.setBounds(35, 45, 400, 100);
		noequ.setVisible(false);
		labchangeres.add(noequ);
		noequ.setFont(new Font("century Gothic", Font.PLAIN, 20));
		noequ.setForeground(Color.white);
		btnconfirm = new JButton();
		btnconfirm.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/confirm.png")));
		btnconfirm.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/confirmO.png")));
		btnconfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				password_1 = String.valueOf(passwordField.getPassword());
				password_2 = String.valueOf(passwordField_1.getPassword());
				password_3 = String.valueOf(passwordField_2.getPassword());
				basicPane.setVisible(false);
				morePane.setVisible(false);
				labchangepw.setVisible(false);
				labchangeres.setVisible(true);
				if (!password_2.equalsIgnoreCase(password_3))// 如果两次密码输入不相等
				{
					noequ.setVisible(true);
				} else// 如果都有数据了就开始连接数据库验证
				{
					String password_0 = Query.query_pw(userID);
					if (password_0.equalsIgnoreCase(password_1)) {
						succ.setVisible(true);
						JDBC.update(password_2, "Password", userID);
						labchangepw.setVisible(false);
//						basicPane.setVisible(true);
					} else {
						fail.setVisible(true);
						passwordField.setText(null);
						passwordField_1.setText(null);
					}

				}
			}
		});
		btnconfirm.setBounds(120, 3, 150, 35);
		btnconfirm.setContentAreaFilled(false);
		btnconfirm.setBorderPainted(false);
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 77, 150, 20);
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(200, 115, 150, 20);
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(200, 150, 150, 20);
		labchangepw = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/personalInfo/passwordpane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		labchangepw.setBounds(300, 315, 400, 200);
		labchangepw.setLayout(null);
		labchangepw.setVisible(false);
		labchangepw.add(passwordField);
		labchangepw.add(passwordField_1);
		labchangepw.add(passwordField_2);
		labchangepw.add(btnconfirm);
		container.add(labchangepw);
		

		btnChangePw = new JButton();
		btnChangePw.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/change.png")));
		btnChangePw.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/changeO.png")));
		btnChangePw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				labchangepw.setVisible(true);
				morePane.setVisible(false);
				basicPane.setVisible(false);
				passwordPanel.setVisible(false);
				labchangeres.setVisible(false);
				succ.setVisible(false);
				fail.setVisible(false);
				noequ.setVisible(false);
			}
		});
		btnChangePw.setBounds(600, 636, 150, 35);
		btnChangePw.setContentAreaFilled(false);
		btnChangePw.setBorderPainted(false);
		container.add(btnChangePw);

		info = Query.query_perinfo(userID);
		labname = new JLabel(info[0]);
		labgender = new JLabel(info[1]);
		labphone = new JLabel(info[2]);
		labmail = new JLabel(info[3]);
		labmajor = new JLabel(info[4]);
		labgrade = new JLabel(info[5]);
		labAdvan = new JLabel(info[6]);
		labHobby = new JLabel(info[7]);
		labsignature = new JLabel("SIGNATURE:  " + info[8]);
		labuserLevel = new JLabel("USER LEVEL:   " + info[9]);
		// 头像读取
		labprofile = new JLabel("");
		labprofile.setBounds(239, 115, 100, 100);
		String Profile = info[10];
		labprofile.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				Profile)));
		container.add(labprofile);

		labuserID = new JLabel("USER ID:         " + userID);
		labuserID.setBounds(400, 110, 500, 30);
		container.add(labuserID);
		labuserID.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labuserID.setForeground(Color.white);

		labuserLevel.setBounds(400, 150, 500, 30);
		container.add(labuserLevel);
		labuserLevel.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labuserLevel.setForeground(Color.white);

		labsignature.setBounds(400, 190, 500, 30);
		container.add(labsignature);
		labsignature.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labsignature.setForeground(Color.white);

		labname.setBounds(250, 9, 100, 100);
		basicPane.add(labname);
		labname.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labname.setForeground(Color.white);

		labgender.setBounds(250, 52, 100, 100);
		basicPane.add(labgender);
		labgender.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labgender.setForeground(Color.white);

		labphone.setBounds(250, 95, 100, 100);
		basicPane.add(labphone);
		labphone.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labphone.setForeground(Color.white);

		labmail.setBounds(250, 139, 200, 100);
		basicPane.add(labmail);
		labmail.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labmail.setForeground(Color.white);

		labmajor.setBounds(250, 8, 150, 100);
		morePane.add(labmajor);
		labmajor.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labmajor.setForeground(Color.white);

		labgrade.setBounds(250, 50, 100, 100);
		morePane.add(labgrade);
		labgrade.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labgrade.setForeground(Color.white);

		labAdvan.setBounds(250, 92, 100, 100);
		morePane.add(labAdvan);
		labAdvan.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labAdvan.setForeground(Color.white);

		labHobby.setBounds(250, 136, 200, 100);
		morePane.add(labHobby);
		labHobby.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labHobby.setForeground(Color.white);
	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/personalBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}
