package report;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import org.jfree.chart.ChartPanel;

import portal.PortalPane;
import management.AdminPane;
import personal.Query;
import java.text.DecimalFormat.*;

public class reportPane extends JPanel {
	String userID = "10000";
	public JFrame presentframe;
	public JPanel presentpanel = this;

	private JPanel container;
	private JPanel difficultyPane;
	private JPanel typePane;
	private JPanel frequencyPane;
	private JPanel advicePane;
	private JPanel rank;

	private JLabel portrait;
	private JLabel portraitShade;
	private JLabel labLevel;
	private JLabel labrank;
	// 经验条
	private JLabel labgrades;
	private JLabel labfullgrades;
	// 排名
	private JLabel labname1;
	private JLabel lablevel1;
	private JLabel labexp1;
	private JLabel labname2;
	private JLabel lablevel2;
	private JLabel labexp2;
	private JLabel labname3;
	private JLabel lablevel3;
	private JLabel labexp3;

	private JButton btnBack;
	private JButton btnDifficulty;
	private JButton btnType;
	private JButton btnFrequency;
	private JButton btnAdvice;

	boolean a1 = true; // diff
	boolean a2 = false; // type
	boolean a3 = false; // time
	boolean a4 = false; // advice

	ChartPanel cp_diff;
	ChartPanel cp_typ;
	ChartPanel cp_tim;
	
	//top3用户的信息读取
	String[][] top3 = new String[3][3];

	public reportPane(String userID) {
		this.userID = userID;

		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);

		portraitShade = new JLabel();
		portraitShade.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/portraitshade1.png")));
		portraitShade.setBounds(68, 111, 120, 120);
		container.add(portraitShade);
		// 读取用户信息
		String[] info = new String[13];
		info = Query.query_perinfo(userID);
		String Profile = info[10].replaceAll("#", "\\\\");
		portrait = new JLabel();
		portrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				Profile)));
		portrait.setBounds(78, 121, 100, 100);
		container.add(portrait);

		labLevel = new JLabel("Level:  " + info[9]);
		labLevel.setBounds(50, 240, 140, 20);
		labLevel.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labLevel);

		labrank = new JLabel("Rank: " + info[11] + "/" + Query.usernums());
		labrank.setBounds(140, 240, 100, 20);
		labrank.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labrank);
		// 经验条
		double scores = Double.parseDouble(info[12]);// 当前积分值
		int levelup_0 = Integer.parseInt(info[9]);// 级别
		double levelup = Mi.a(3, levelup_0 - 1) * 500;// 升级所需要的积分值
		double scales = scores / levelup;// 当前积分值占升级所需积分值的比例
		labgrades = new JLabel();
		int a = Integer.parseInt(new java.text.DecimalFormat("0")
				.format(150 * scales));
		// 如果积分值四舍五入后不为0
		if (a != 0) {
			ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/report/experience.png"));// 经验值图像
			image.setImage(image.getImage().getScaledInstance(a, 16, 100));
			labgrades.setIcon(image);
		}
		labgrades.setBounds(48, 270, 150, 20);
		container.add(labgrades);

		labfullgrades = new JLabel();
		labfullgrades.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/exp.png")));// 升级积分值图像
		labfullgrades.setBounds(48, 270, 200, 20);
		container.add(labfullgrades);
		// 排行榜数据读取
		top3[0] = Query.query_rank(1);
		top3[1]= Query.query_rank(2);
		top3[2]= Query.query_rank(3);
		// 排行榜显示
		labname1 = new JLabel(top3[0][0]);
		labname1.setBounds(125, 347, 100, 100);
		labname1.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labname1);
		lablevel1 = new JLabel(top3[0][1]);
		lablevel1.setBounds(135, 378, 100, 100);
		lablevel1.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(lablevel1);
		labexp1 = new JLabel(top3[0][2]);
		labexp1.setBounds(135, 397, 100, 100);
		labexp1.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labexp1);

		labname2 = new JLabel(top3[1][0]);
		labname2.setBounds(125, 432, 100, 100);
		labname2.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labname2);
		lablevel2 = new JLabel(top3[1][1]);
		lablevel2.setBounds(135, 455, 100, 100);
		lablevel2.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(lablevel2);
		labexp2 = new JLabel(top3[1][2]);
		labexp2.setBounds(135, 473, 100, 100);
		labexp2.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labexp2);

		labname3 = new JLabel(top3[2][0]);
		labname3.setBounds(125, 503, 100, 100);
		labname3.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labname3);
		lablevel3 = new JLabel(top3[2][1]);
		lablevel3.setBounds(135, 523, 100, 100);
		lablevel3.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(lablevel3);
		labexp3 = new JLabel(top3[2][2]);
		labexp3.setBounds(135, 541, 100, 100);
		labexp3.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(labexp3);

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
		btnBack.setBounds(5, 5, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);

		rank = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/report/rank.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		rank.setBounds(39, 320, 205, 500);
		rank.setVisible(true);
		container.add(rank);

		btnDifficulty = new JButton();
		btnDifficulty.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/difficulty.png")));
		btnDifficulty.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/difficultyO.png")));
		btnDifficulty.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/difficultyP.png")));
		btnDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (a1) {
					//难度报告为开启，则无反应
				} else if (a2) {//关闭题型
					difficultyPane.setVisible(true);
					typePane.setVisible(false);
					a2 = false;
					a1 = true;
				} else if (a3) {
					frequencyPane.setVisible(false);
					difficultyPane.setVisible(true);
					a3 = false;
					a1 = true;
				} else if (a4) {
					advicePane.setVisible(false);
					difficultyPane.setVisible(true);
					a4 = false;
					a1 = true;
				} else {
					difficultyPane.setVisible(true);
					a1 = true;
				}

			}
		});
		btnDifficulty.setBounds(274, 553, 155, 52);
		btnDifficulty.setContentAreaFilled(false);
		btnDifficulty.setBorderPainted(false);
		container.add(btnDifficulty);

		btnType = new JButton();
		btnType.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/report/types.png")));
		btnType.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/typesO.png")));
		btnType.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/typesP.png")));
		btnType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (a1) {
					difficultyPane.setVisible(false);
					typePane.setVisible(true);
					a1 = false;
					a2 = true;
				} else if (a2) {
					typePane.setVisible(false);
					a2 = false;
				} else if (a3) {
					frequencyPane.setVisible(false);
					typePane.setVisible(true);
					a3 = false;
					a2 = true;
				} else if (a4) {
					advicePane.setVisible(false);
					typePane.setVisible(true);
					a4 = false;
					a2 = true;
				} else {
					typePane.setVisible(true);
					a2 = true;
				}

			}
		});
		btnType.setBounds(434, 553, 155, 52);
		btnType.setContentAreaFilled(false);
		btnType.setBorderPainted(false);
		container.add(btnType);

		btnFrequency = new JButton();
		btnFrequency.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/frequency.png")));
		btnFrequency.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/frequencyO.png")));
		btnFrequency.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/frequencyP.png")));
		btnFrequency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (a1) {
					difficultyPane.setVisible(false);
					frequencyPane.setVisible(true);
					a1 = false;
					a3 = true;
				} else if (a2) {
					frequencyPane.setVisible(true);
					typePane.setVisible(false);
					a2 = false;
					a3 = true;
				} else if (a3) {
					frequencyPane.setVisible(false);
					a3 = false;
				} else if (a4) {
					advicePane.setVisible(false);
					frequencyPane.setVisible(true);
					a4 = false;
					a3 = true;
				} else {
					frequencyPane.setVisible(true);
					a3 = true;
				}
			}
		});
		btnFrequency.setBounds(594, 553, 155, 52);
		btnFrequency.setContentAreaFilled(false);
		btnFrequency.setBorderPainted(false);
		container.add(btnFrequency);

		btnAdvice = new JButton();
		btnAdvice.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/report/advice.png")));
		btnAdvice.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/adviceO.png")));
		btnAdvice.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/report/adviceP.png")));
		btnAdvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (a1) {
					difficultyPane.setVisible(false);
					advicePane.setVisible(true);
					a1 = false;
					a4 = true;
				} else if (a2) {
					advicePane.setVisible(true);
					typePane.setVisible(false);
					a2 = false;
					a4 = true;
				} else if (a3) {
					advicePane.setVisible(true);
					frequencyPane.setVisible(false);
					a3 = false;
					a4 = true;
				} else if (a4) {
					advicePane.setVisible(false);
					a4 = false;
				} else {
					advicePane.setVisible(true);
					a4 = true;
				}
			}
		});
		btnAdvice.setBounds(754, 553, 155, 52);
		btnAdvice.setContentAreaFilled(false);
		btnAdvice.setBorderPainted(false);
		container.add(btnAdvice);

		difficultyPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/report/pane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		difficultyPane.setBounds(270, 172, 643, 348);
		difficultyPane.setLayout(null);

		Difficulty diff = new Difficulty("各难度级别正确率", userID);
		cp_diff = diff.createDemoPanel(userID);
		cp_diff.setBounds(0, 0, 643, 348);
		cp_diff.setFont(new Font("century Gothic", Font.PLAIN, 16));
		difficultyPane.add(cp_diff);
		difficultyPane.setVisible(true);
		difficultyPane.setFont(new Font("century Gothic", Font.PLAIN, 16));
		container.add(difficultyPane);

		typePane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/report/pane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		typePane.setBounds(270, 172, 643, 348);
		typePane.setLayout(null);

		Type typ = new Type("各题型做题数", userID);
		cp_typ = typ.createDemoPanel(userID);
		cp_typ.setBounds(0, 0, 643, 348);
		typePane.add(cp_typ);
		typePane.setVisible(false);
		container.add(typePane);

		frequencyPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/report/pane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		frequencyPane.setBounds(270, 172, 643, 348);
		frequencyPane.setLayout(null);

		Frequency record = new Frequency("一周做题记录", userID);
		cp_tim = record.createDemoPanel(userID);
		cp_tim.setBounds(0, 0, 643, 348);
		frequencyPane.add(cp_tim);
		frequencyPane.setVisible(false);
		container.add(frequencyPane);

		advicePane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/report/pane.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		advicePane.setBounds(270, 172, 643, 348);
		advicePane.setLayout(null);

		Advice adv = new Advice(Difficulty.a, Difficulty.Diff, Type.Type[0],
				Type.Type[1], Type.Type[2], Frequency.numstoday,
				Frequency.avenum);

		JTextPane textPane = new JTextPane();
		textPane.setText(adv.advicetext());
		textPane.setFont(new Font("century Gothic", Font.PLAIN, 16));
		textPane.setBounds(0, 0, 643, 348);
		textPane.setEditable(false);
		textPane.setBackground(null);
		advicePane.add(textPane);
		advicePane.setVisible(false);
		container.add(advicePane);

	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/report/reportBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

}
