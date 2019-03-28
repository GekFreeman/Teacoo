package personal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import portal.PortalPane;

public class editInfo extends JPanel implements ActionListener {
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;

	private JPanel container;
	private JPanel basicPane;// 基本资料
	private JPanel morePane;// 更多资料
	private JButton btnBack;
	private JButton btnChoose;// 选择头像
	private JButton btnUpload;// 上传头像
	private JButton btnReset;// 置空信息
	private JButton btnSaveAll;// 保存全部修改
	private JButton btnBasic;
	private JButton btnMore;

	private JLabel labprofile;
	private JLabel labuserID;
	private JLabel labuserLevel;
	private JLabel labsignature;

	private JLabel labname;
	private JTextField txtgender;
	private JTextField txtphone;
	private JTextField txtmail;
	private JTextField txtmajor;
	private JTextField txtgrade;
	private JTextField txtadvan;
	private JTextField txthobby;
	private JTextField txtsignature;

	// 存储用户基本信息的数组
	String[] info = new String[13];

	private PicFrame picFrame = null;

	// 存储新头像的路径
	private String picPath = null;

	public editInfo(String userID) {
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

		// 基本信息和更多信息Panel的切换
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				morePane.setVisible(true);
				basicPane.setVisible(false);
			}
		});

		btnBasic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				morePane.setVisible(false);
				basicPane.setVisible(true);
			}
		});
		// 从数据库获取用户信息并赋值到相应位置
		info = Query.query_perinfo(userID);
		labname = new JLabel(info[0]);
		txtgender = new JTextField(info[1]);
		txtphone = new JTextField(info[2]);
		txtmail = new JTextField(info[3]);
		txtmajor = new JTextField(info[4]);
		txtgrade = new JTextField(info[5]);
		txtadvan = new JTextField(info[6]);
		txthobby = new JTextField(info[7]);
		txtsignature = new JTextField(info[8]);
		// 头像数据的获取
		labprofile = new JLabel("");
		labprofile.setBounds(239, 115, 100, 100);
		picPath = info[10].replaceAll("#", "\\\\");// “#”字符转化为“\”
		labprofile.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				picPath)));
		container.add(labprofile);
		// 用户信息的显示
		labuserID = new JLabel("USER ID:         " + userID);
		labuserID.setBounds(400, 110, 500, 30);
		container.add(labuserID);
		labuserID.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labuserID.setForeground(Color.white);

		labuserLevel = new JLabel("USER LEVEL:   " + info[9]);
		labuserLevel.setBounds(600, 110, 300, 30);
		container.add(labuserLevel);
		labuserLevel.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labuserLevel.setForeground(Color.white);

		labsignature = new JLabel("SIGNATURE:  ");
		labsignature.setBounds(400, 140, 500, 30);
		container.add(labsignature);
		labsignature.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labsignature.setForeground(Color.white);

		txtsignature.setBounds(500, 140, 200, 30);
		txtsignature.setBackground(null);
		container.add(txtsignature);
		txtsignature.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtsignature.setForeground(Color.black);

		labname.setBounds(250, 49, 150, 20);
		basicPane.add(labname);
		labname.setFont(new Font("century Gothic", Font.PLAIN, 16));
		labname.setForeground(Color.black);

		txtgender.setBounds(250, 92, 150, 20);
		basicPane.add(txtgender);
		txtgender.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtgender.setForeground(Color.black);

		txtphone.setBounds(250, 135, 150, 20);
		basicPane.add(txtphone);
		txtphone.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtphone.setForeground(Color.black);

		txtmail.setBounds(250, 179, 150, 20);
		basicPane.add(txtmail);
		txtmail.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtmail.setForeground(Color.black);

		txtmajor.setBounds(249, 48, 150, 20);
		morePane.add(txtmajor);
		txtmajor.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtmajor.setForeground(Color.black);

		txtgrade.setBounds(249, 91, 150, 20);
		morePane.add(txtgrade);
		txtgrade.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtgrade.setForeground(Color.black);

		txtadvan.setBounds(249, 134, 150, 20);
		morePane.add(txtadvan);
		txtadvan.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txtadvan.setForeground(Color.black);

		txthobby.setBounds(249, 178, 150, 20);
		morePane.add(txthobby);
		txthobby.setFont(new Font("century Gothic", Font.PLAIN, 16));
		txthobby.setForeground(Color.black);
		// 系统头像选择按钮
		btnChoose = new JButton();
		btnChoose.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/choose.png")));
		btnChoose.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/chooseO.png")));
		btnChoose.setBounds(393, 197, 100, 35);
		btnChoose.setContentAreaFilled(false);
		btnChoose.setBorderPainted(false);
		this.btnChoose.addActionListener(this);
		container.add(btnChoose);
		// 上传头像按钮
		btnUpload = new JButton();
		btnUpload.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/upload.png")));
		btnUpload.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/uploadO.png")));
		this.btnUpload.addActionListener(this);
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser("E:\\");
				fileChooser.addChoosableFileFilter(new PortraitFilter("jpg"));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String path = file.getPath();// 获取路径
					// 存储路径
					try {
						new ProfileUpload(path,userID);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					picPath="image/userprofile/"+userID+".png";
					ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit()
							.getImage(file.getPath()));
					image.setImage(image.getImage().getScaledInstance(100, 100,
							3));
					labprofile.setIcon(image);

				}

				else if (result == JFileChooser.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(null,
							"You didn't choose a photo!");
				}

			}

		});
		btnUpload.setBounds(561, 197, 100, 35);
		btnUpload.setContentAreaFilled(false);
		btnUpload.setBorderPainted(false);
		container.add(btnUpload);
		// 置空按钮的实现
		btnReset = new JButton();
		btnReset.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/reset.png")));
		btnReset.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/resetO.png")));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtgender.setText(null);
				txtphone.setText(null);
				txtmail.setText(null);
				txtmajor.setText(null);
				txtgrade.setText(null);
				txtadvan.setText(null);
				txthobby.setText(null);
			}
		});
		btnReset.setBounds(237, 636, 150, 35);
		btnReset.setContentAreaFilled(false);
		btnReset.setBorderPainted(false);
		container.add(btnReset);

		// 对用户更改后的信息进行保存
		btnSaveAll = new JButton();
		btnSaveAll.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/saveall.png")));
		btnSaveAll.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/personalInfo/saveallO.png")));
		btnSaveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Gender = txtgender.getText();
				String Phone = txtphone.getText();
				String Email = txtmail.getText();
				String Major = txtmajor.getText();
				String Grade = txtgrade.getText();
				String Advantage = txtadvan.getText();
				String Hobby = txthobby.getText();
				String Profile = picPath;// 头像路径的存储
				String Signature = txtsignature.getText();
				JDBC.update(Gender, "Gender", userID);
				JDBC.update(Phone, "Phone", userID);
				JDBC.update(Email, "Email", userID);
				JDBC.update(Major, "Major", userID);
				JDBC.update(Grade, "Grade", userID);
				JDBC.update(Advantage, "Advantage", userID);
				JDBC.update(Hobby, "Hobby", userID);
				JDBC.update(Signature, "signature", userID);
				JDBC.update(Profile, "Profile", userID);
				PersonalPane pPane = new PersonalPane(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pPane);
				pPane.presentframe = presentframe;
				pPane.presentpanel = pPane;
			}
		});
		btnSaveAll.setBounds(600, 636, 150, 35);
		btnSaveAll.setContentAreaFilled(false);
		btnSaveAll.setBorderPainted(false);
		container.add(btnSaveAll);
	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/personalInfo/personalBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnChoose) {

			// 当点击选择按钮后，选择头像
			if (this.picFrame == null) {
				this.picFrame = new PicFrame(this, this.getX() + 417,
						this.getY());
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
		ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage(this.picPath).getScaledInstance(100, 100, 0));
		/* 宽 长 未知 */
		this.labprofile.setIcon(image);

	}

	public void windowClosing(WindowEvent e) {

		// 窗口正在关闭的按钮
		if (this.picFrame != null) {
			this.picFrame.dispose();
			this.picFrame = null;
		}
	}
}