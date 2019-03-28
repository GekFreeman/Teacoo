package check;
import practice.*;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import portal.PortalPane;
import practice.Questions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import personal.Query;
import report.Mi;


public class Report extends JPanel {// 做题报告的panel
	String userID;
	Questions zuoti[];// 本次做过题目的相关信息

	public JFrame presentframe;
	JPanel presentpanel = this;
	public static Connection connection;
	private JPanel report;
	private static PreparedStatement prestatment;
	private double rate, rate1, rate2, rate3;// 总正确率和三种类型题目的正确率
    public int num;
	/**
	 * Create the panel.
	 */
	public Report(Questions[] zt, String userID,int length) {
		 System.out.println(userID);
		 System.out.println(zt[1].MainQuestion+"sssssssssssssssssssssssssssssssssssssss"+length);
		this.zuoti = zt;
		 System.out.println(zuoti[1].MainQuestion);
		this.num=length;
		int total = this.num;// 做题的总数
		int totalRight = 0, type1 = 0, type2 = 0, type3 = 0, typeRight1 = 0, typeRight2 = 0, typeRight3 = 0, level1 = 0, level2 = 0, level3 = 0,
				level4 = 0, level5 = 0, levelRight1 = 0, levelRight2 = 0, levelRight3 = 0, levelRight4 = 0, levelRight5 = 0, scores;// 为这些变量赋初值
		 System.out.println("怎么了1");
		for (int i = 1; i <= length; i++) {
			// 通过循环对做过的每一道题进行判断是否做对，以及根据其类型和难度来对相应的变量赋值
			System.out.println(i+"调试");
			System.out.println(i+zuoti[i].MainQuestion);
			if (zuoti[i].Answer == zuoti[i].userAnswer) {
				totalRight++;// 做对题数增加
				JDBC.update2("RightTimes", JDBC.query2(zuoti[i].QID)+1, zuoti[i].QID);
				System.out.println("开始建了？");
				System.out.println(zuoti[i].MainQuestion);
				if (zuoti[i].Qtype.equals("Type1"))
					typeRight1++;// 类型1做对题数增加
				else if (zuoti[i].Qtype.equals("Type2"))
					typeRight2++;// 类型2做对题数增加
				else
					typeRight3++;// 类型3做对题数增加
				if (zuoti[i].level == "1")
					levelRight1++;// 难度1做对题数增加
				else if (zuoti[i].level == "2")
					levelRight2++;// 难度2做对题数增加
				else if (zuoti[i].level == "3")
					levelRight3++;// 难度3做对题数增加
				else if (zuoti[i].level == "4")
					levelRight4++;// 难度4做对题数增加
				else
					levelRight5++;// 难度5做对题数增加
			} else {// 做错的题在数据库中错题集进行修改
				connection = getConnection();// 建立连接
				try {
					char c;
					c = zuoti[i].Answer;
					prestatment = connection
							.prepareStatement("insert into wrong" + userID
					+ "(QID,MainQuestion,OptionA,OptionB,OptionC,"
									+ "OptionD,Answer,Qtype,Analysis) values (?,?,?,?,?,?,'"
									+ c + "',?,?)");// 插入相应的数据
					prestatment.setString(1, zuoti[i].QID);
					prestatment.setString(2, zuoti[i].MainQuestion);
					prestatment.setString(3, zuoti[i].OptionA);
					prestatment.setString(4, zuoti[i].OptionB);
					prestatment.setString(5, zuoti[i].OptionC);
					prestatment.setString(6, zuoti[i].OptionD);
					prestatment.setString(7, zuoti[i].Qtype);
					prestatment.setString(8, zuoti[i].Analysis);
					prestatment.executeUpdate();// execute statement
					shutDown();// 关闭连接
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
			// 让每个类型的题目数量增加
			if (zuoti[i].Qtype.equals("Type1"))
				type1++;
			else if (zuoti[i].Qtype.equals("Type2"))
				type2++;
			else
				type3++;
			if (zuoti[i].level == "1")
				level1++;
			else if (zuoti[i].level == "2")
				level2++;
			else if (zuoti[i].level == "3")
				level3++;
			else if (zuoti[i].level == "4")
				level4++;
			else
				level5++;
			JDBC.update2("TotalTimes", JDBC.query1(zuoti[i].QID)+1, zuoti[i].QID);
			level((double)(JDBC.query2(zuoti[i].QID)*100/JDBC.query1(zuoti[i].QID)),zuoti[i].QID);
			System.out.print(JDBC.query2(zuoti[i].QID)*100/JDBC.query1(zuoti[i].QID));
		}

		scores = (total - totalRight) * 1 + levelRight1 * 10 + levelRight2 * 20
				+ levelRight3 * 30 + levelRight4 * 40 + levelRight5 * 50;// 计算本次做题得到的积分
		System.out.println("怎么了2");
		connection = getConnection();// 建立连接
		try {
			int tn1 = 0, tr1 = 0, tn2 = 0, tr2 = 0, tn3 = 0, tr3 = 0, l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, lr1 = 0, 
					lr2 = 0, lr3 = 0, lr4 = 0, lr5 = 0, total1 = 0, grades = 0;// 查询在qinfo表中的相关数据后存贮在这些变量中

			// 查询的语句
			prestatment = connection
					.prepareStatement("select * from qinfo where UserID = ?");
			prestatment.setString(1, userID);
			ResultSet rs1 = prestatment.executeQuery();
			if (rs1.next()) {// 赋值给变量
				tn1 = rs1.getInt("T1Num") + type1; // 类型1做题总数
				tr1 = rs1.getInt("T1RightNum") + typeRight1; // 类型1做对总数
				tn2 = rs1.getInt("T2Num") + type2; // 类型2做题总数
				tr2 = rs1.getInt("T2RightNum") + typeRight2; // 类型2做对总数
				tn3 = rs1.getInt("T3Num") + type3; // 类型3做题总数
				tr3 = rs1.getInt("T3RightNum") + typeRight3; // 类型3做对总数
				l1 = rs1.getInt("Level1") + level1; // 难度1做题总数
				l2 = rs1.getInt("Level2") + level2; // 难度2做题总数
				l3 = rs1.getInt("Level3") + level3; // 难度3做题总数
				l4 = rs1.getInt("Level4") + level4; // 难度4做题总数
				l5 = rs1.getInt("Level5") + level5; // 难度5做题总数
				lr1 = rs1.getInt("Level1Right") + levelRight1; // 难度1做对总数
				lr2 = rs1.getInt("Level2Right") + levelRight2; // 难度2做对总数
				lr3 = rs1.getInt("Level3Right") + levelRight3; // 难度3做对总数
				lr4 = rs1.getInt("Level4Right") + levelRight4; // 难度4做对总数
				lr5 = rs1.getInt("Level5Right") + levelRight5; // 难度5做对总数
			}
			// 查询语句
			prestatment = connection
					.prepareStatement("select * from userinfo where UserID = ?");
			prestatment.setString(1, userID);
			ResultSet rs2 = prestatment.executeQuery();
			if (rs2.next()) {
				total1 = rs2.getInt("Teanums"); // 做题总数
				grades = rs2.getInt("Exp"); // 总积分
			}

			// 通过调用JDBC类中的方法来修改数据库中相应的数据
			JDBC.update("T1Num", tn1, userID);
			JDBC.update("T1RightNum", tr1, userID);
			JDBC.update("T2Num", tn2, userID);
			JDBC.update("T2RightNum", tr2, userID);
			JDBC.update("T3Num", tn3, userID);
			JDBC.update("T3RightNum", tr3, userID);
			JDBC.update("Level1", l1, userID);
			JDBC.update("Level2", l2, userID);
			JDBC.update("Level3", l3, userID);
			JDBC.update("Level4", l4, userID);
			JDBC.update("Level5", l5, userID);
			JDBC.update("Level1Right", tn1, userID);
			JDBC.update("Level2Right", tn1, userID);
			JDBC.update("Level3Right", tn1, userID);
			JDBC.update("Level4Right", tn1, userID);
			JDBC.update("Level5Right", tn1, userID);
			int levelup_0 = Integer.parseInt(Query.query_level(userID));// 级别
			double levelup = Mi.a(3, levelup_0 - 1) * 500;// 升级所需要的积分值
			JDBC.update1("Teanums", total + total1, userID);
			JDBC.update1("Exp", grades + scores, userID);
			if ((grades+scores)>=levelup)
				JDBC.update1("Level",levelup_0+1,userID);
			
			// 查询语句
			prestatment = connection
					.prepareStatement("select * from userinfo ");
			ResultSet rs3 = prestatment.executeQuery();
			while(rs3.next()) {//利用循环对用户积分排名进行修改
				if(grades<=rs3.getInt("Exp")&&rs3.getInt("Exp")<grades+scores){//该分数不小于该用户原有积分且小于该用户现有的积分
					JDBC.update1("Rank", rs3.getInt("Rank")+1, rs3.getString("UserID"));//排名加1
					JDBC.update1("Rank", JDBC.query(userID)-1,userID);//该用户排名减1
				}
				else if(rs3.getInt("Exp")==grades+scores&&!rs3.getString("UserID").equalsIgnoreCase(userID)){//若出现其他用户积分与该用户现有积分相等时
					JDBC.update1("Rank", JDBC.query(userID)-1,userID);
				}
			}

			shutDown();// 关闭连接
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		DecimalFormat df = new DecimalFormat("#####0.00");// 定义一种输出类型，保留小数点后两位

		// 计算正确率
		rate = count(total, totalRight);
		rate1 = count(type1, typeRight1);
		rate2 = count(type2, typeRight2);
		rate3 = count(type3, typeRight3);

		// 对这个panel的参数进行设置
		setVisible(true);
		setLayout(null);
		setBounds(0, 0, 1000, 700);

		report = new JPanel() {// 建立一个新的panel来展示相关的数据
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/check/checkPane.png"));// 设置背景图片
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		report.setBounds(203, 31, 610, 560);
		report.setOpaque(false);
		add(report);
		report.setLayout(null);

		// 显示类型1做对题数的label
		JLabel lblReport = new JLabel("" + typeRight1);
		lblReport.setBounds(190, 240, 39, 20);
		lblReport.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblReport);

		// 显示类型1题数的label
		JLabel lblnm = new JLabel("" + type1);
		lblnm.setBounds(400, 240, 39, 20);
		lblnm.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblnm);

		// 显示类型1题目正确率的label
		JLabel lblNewLabel_1 = new JLabel(df.format(rate1 * 100) + "%");
		lblNewLabel_1.setBounds(505, 240, 105, 20);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_1);

		// 显示类型2做对题数的label
		JLabel lblb = new JLabel("" + typeRight2);
		lblb.setBounds(190, 332, 39, 20);
		lblb.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblb);

		// 显示类型2题数的label
		JLabel lblNewLabel = new JLabel("" + type2);
		lblNewLabel.setBounds(400, 332, 39, 20);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel);

		// 显示类型2题目正确率的label
		JLabel lblNewLabel_2 = new JLabel(df.format(rate2 * 100) + "%");
		lblNewLabel_2.setBounds(505, 332, 105, 20);
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_2);

		// 显示类型3做对题数的label
		JLabel label = new JLabel("" + typeRight3);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		label.setBounds(190, 424, 39, 20);
		report.add(label);

		// 显示类型3题数的label
		JLabel lblNewLabel_3 = new JLabel("" + type3);
		lblNewLabel_3.setBounds(400, 424, 39, 20);
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_3);

		// 显示类型3题目正确率的label
		JLabel lblNewLabel_4 = new JLabel(df.format(rate3 * 100) + "%");
		lblNewLabel_4.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		lblNewLabel_4.setBounds(505, 424, 105, 20);
		report.add(lblNewLabel_4);

		// 显示总做对题数的label
		JLabel label_1 = new JLabel("" + totalRight);
		label_1.setBounds(190, 475, 39, 20);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(label_1);

		// 显示做题总数的label
		JLabel lblNewLabel_5 = new JLabel("" + total);
		lblNewLabel_5.setBounds(400, 475, 39, 20);
		lblNewLabel_5.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_5);

		// 显示题目总正确率的label
		JLabel lblNewLabel_6 = new JLabel(df.format(rate * 100) + "%");
		lblNewLabel_6.setBounds(505, 475, 105, 20);
		lblNewLabel_6.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_6);

		// 点击进行展示所有题目信息的按钮
		JButton btnFull = new JButton();
		btnFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFull.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/full.png")));// 设置点击前的图片
		btnFull.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/check/fullO.png")));// 设置在上面时的图片
		btnFull.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/check/fullP.png")));// 设置点击后的图片

		btnFull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 鼠标监听器：切换一个panel
				TablePane cPane = new TablePane(zuoti, userID, num);
				cPane.setVisible(true);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(cPane);
				cPane.presentframe = presentframe;
				cPane.presentpanel = cPane;
			}
		});
		btnFull.setBounds(428, 615, 161, 38);
		btnFull.setContentAreaFilled(false);
		btnFull.setBorderPainted(false);
		add(btnFull);

		// 设置返回按钮
		JButton btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));// 设置点击前的图片
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));// 设置鼠标在上面时的图片
		btnBack.addMouseListener(new MouseAdapter() {// 鼠标监听器来切换panel
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
		add(btnBack);
	}

	// 建立连接
	public static Connection getConnection() {
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

	// 设置panel背景图片
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/checkBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

	public static void shutDown() {// 关闭连接
		try {
			connection.close();
			System.out.println("Disconnect successfully");
		} catch (SQLException sqlex) {
			System.err.println("Unable to disconnect");
			sqlex.printStackTrace();
		}
	}

	// 计算正确率的方法
	public double count(int a, int b) {
		double c = Math.round(a * 10000) / 10000.0000;
		;
		if (a == 0)
			return 0.00;
		else {
			c = (double) b / a;
			return c;
		}
	}
	
	//更改题目难度
	public void level(double a,String qid){
		int i;
		if(a<20)   i=5;
		else if(a<40) i=4;
		else if(a<60) i=3;
		else if(a<80) i=2;
		else  i=1;
		JDBC.update2("Level", i, qid);
	}

//	public static void main(String[] args) throws SQLException {
//		Questions[] zuoti = new Questions[3];
//		zuoti[0] = new Questions();
//		zuoti[0].Answer = 'b';
//		zuoti[0].MainQuestion = "ddddddddd";
//		zuoti[0].UserAnswer = 'b';
//		zuoti[0].QID = "10001";
//		zuoti[0].OptionA = "dd";
//		zuoti[0].OptionB = "ss";
//		zuoti[0].OptionC = "ww";
//		zuoti[0].OptionD = "rr";
//		zuoti[0].Qtype = "1";
//		zuoti[0].Explain = "dhhh";
//		zuoti[0].level = 1;
//		zuoti[1] = new Questions();
//		zuoti[1].Answer = 'b';
//		zuoti[1].MainQuestion = "aaaaddddd";
//		zuoti[1].UserAnswer = 'b';
//		zuoti[1].QID = "10005";
//		zuoti[1].OptionA = "dd";
//		zuoti[1].OptionB = "ss";
//		zuoti[1].OptionC = "ww";
//		zuoti[1].OptionD = "rr";
//		zuoti[1].Qtype = "1";
//		zuoti[1].Explain = "dhhhgggggggggggggggggggg\ngggggggggggggggggggggggggggggggggggggg\nggggggggggggggggggggggggggggg\ngggggggggggg\nggggggg";
//		zuoti[1].level = 1;
//		zuoti[2] = new Questions();
//		zuoti[2].Answer = 'a';
//		zuoti[2].MainQuestion = "aaaaddddd";
//		zuoti[2].UserAnswer = 'b';
//		zuoti[2].QID = "10004";
//		zuoti[2].OptionA = "dd";
//		zuoti[2].OptionB = "ss";
//		zuoti[2].OptionC = "ww";
//		zuoti[2].OptionD = "rr";
//		zuoti[2].Qtype = "1";
//		zuoti[2].Explain = "dhhhgggggggggggggggggggg\ngggggggggggggggggggggggggggggggggggggg\nggggggggggggggggggggggggggggg\ngggggggggggg\nggggggg";
//		zuoti[2].level = 1;
//		Report main = new Report(zuoti, "10000");
//		main.setVisible(true);
//
//		JFrame J = new JFrame();
//		J.getContentPane().add(main);
//		J.setBounds(50, 50, 1000, 700);
//		J.setVisible(true);
//		main.presentframe = J;
//		main.setVisible(true);
	
}
