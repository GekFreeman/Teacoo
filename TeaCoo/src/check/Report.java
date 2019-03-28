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


public class Report extends JPanel {// ���ⱨ���panel
	String userID;
	Questions zuoti[];// ����������Ŀ�������Ϣ

	public JFrame presentframe;
	JPanel presentpanel = this;
	public static Connection connection;
	private JPanel report;
	private static PreparedStatement prestatment;
	private double rate, rate1, rate2, rate3;// ����ȷ�ʺ�����������Ŀ����ȷ��
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
		int total = this.num;// ���������
		int totalRight = 0, type1 = 0, type2 = 0, type3 = 0, typeRight1 = 0, typeRight2 = 0, typeRight3 = 0, level1 = 0, level2 = 0, level3 = 0,
				level4 = 0, level5 = 0, levelRight1 = 0, levelRight2 = 0, levelRight3 = 0, levelRight4 = 0, levelRight5 = 0, scores;// Ϊ��Щ��������ֵ
		 System.out.println("��ô��1");
		for (int i = 1; i <= length; i++) {
			// ͨ��ѭ����������ÿһ��������ж��Ƿ����ԣ��Լ����������ͺ��Ѷ�������Ӧ�ı�����ֵ
			System.out.println(i+"����");
			System.out.println(i+zuoti[i].MainQuestion);
			if (zuoti[i].Answer == zuoti[i].userAnswer) {
				totalRight++;// ������������
				JDBC.update2("RightTimes", JDBC.query2(zuoti[i].QID)+1, zuoti[i].QID);
				System.out.println("��ʼ���ˣ�");
				System.out.println(zuoti[i].MainQuestion);
				if (zuoti[i].Qtype.equals("Type1"))
					typeRight1++;// ����1������������
				else if (zuoti[i].Qtype.equals("Type2"))
					typeRight2++;// ����2������������
				else
					typeRight3++;// ����3������������
				if (zuoti[i].level == "1")
					levelRight1++;// �Ѷ�1������������
				else if (zuoti[i].level == "2")
					levelRight2++;// �Ѷ�2������������
				else if (zuoti[i].level == "3")
					levelRight3++;// �Ѷ�3������������
				else if (zuoti[i].level == "4")
					levelRight4++;// �Ѷ�4������������
				else
					levelRight5++;// �Ѷ�5������������
			} else {// ������������ݿ��д��⼯�����޸�
				connection = getConnection();// ��������
				try {
					char c;
					c = zuoti[i].Answer;
					prestatment = connection
							.prepareStatement("insert into wrong" + userID
					+ "(QID,MainQuestion,OptionA,OptionB,OptionC,"
									+ "OptionD,Answer,Qtype,Analysis) values (?,?,?,?,?,?,'"
									+ c + "',?,?)");// ������Ӧ������
					prestatment.setString(1, zuoti[i].QID);
					prestatment.setString(2, zuoti[i].MainQuestion);
					prestatment.setString(3, zuoti[i].OptionA);
					prestatment.setString(4, zuoti[i].OptionB);
					prestatment.setString(5, zuoti[i].OptionC);
					prestatment.setString(6, zuoti[i].OptionD);
					prestatment.setString(7, zuoti[i].Qtype);
					prestatment.setString(8, zuoti[i].Analysis);
					prestatment.executeUpdate();// execute statement
					shutDown();// �ر�����
				} catch (SQLException sqlex) {
					sqlex.printStackTrace();
				}
			}
			// ��ÿ�����͵���Ŀ��������
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
				+ levelRight3 * 30 + levelRight4 * 40 + levelRight5 * 50;// ���㱾������õ��Ļ���
		System.out.println("��ô��2");
		connection = getConnection();// ��������
		try {
			int tn1 = 0, tr1 = 0, tn2 = 0, tr2 = 0, tn3 = 0, tr3 = 0, l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, lr1 = 0, 
					lr2 = 0, lr3 = 0, lr4 = 0, lr5 = 0, total1 = 0, grades = 0;// ��ѯ��qinfo���е�������ݺ��������Щ������

			// ��ѯ�����
			prestatment = connection
					.prepareStatement("select * from qinfo where UserID = ?");
			prestatment.setString(1, userID);
			ResultSet rs1 = prestatment.executeQuery();
			if (rs1.next()) {// ��ֵ������
				tn1 = rs1.getInt("T1Num") + type1; // ����1��������
				tr1 = rs1.getInt("T1RightNum") + typeRight1; // ����1��������
				tn2 = rs1.getInt("T2Num") + type2; // ����2��������
				tr2 = rs1.getInt("T2RightNum") + typeRight2; // ����2��������
				tn3 = rs1.getInt("T3Num") + type3; // ����3��������
				tr3 = rs1.getInt("T3RightNum") + typeRight3; // ����3��������
				l1 = rs1.getInt("Level1") + level1; // �Ѷ�1��������
				l2 = rs1.getInt("Level2") + level2; // �Ѷ�2��������
				l3 = rs1.getInt("Level3") + level3; // �Ѷ�3��������
				l4 = rs1.getInt("Level4") + level4; // �Ѷ�4��������
				l5 = rs1.getInt("Level5") + level5; // �Ѷ�5��������
				lr1 = rs1.getInt("Level1Right") + levelRight1; // �Ѷ�1��������
				lr2 = rs1.getInt("Level2Right") + levelRight2; // �Ѷ�2��������
				lr3 = rs1.getInt("Level3Right") + levelRight3; // �Ѷ�3��������
				lr4 = rs1.getInt("Level4Right") + levelRight4; // �Ѷ�4��������
				lr5 = rs1.getInt("Level5Right") + levelRight5; // �Ѷ�5��������
			}
			// ��ѯ���
			prestatment = connection
					.prepareStatement("select * from userinfo where UserID = ?");
			prestatment.setString(1, userID);
			ResultSet rs2 = prestatment.executeQuery();
			if (rs2.next()) {
				total1 = rs2.getInt("Teanums"); // ��������
				grades = rs2.getInt("Exp"); // �ܻ���
			}

			// ͨ������JDBC���еķ������޸����ݿ�����Ӧ������
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
			int levelup_0 = Integer.parseInt(Query.query_level(userID));// ����
			double levelup = Mi.a(3, levelup_0 - 1) * 500;// ��������Ҫ�Ļ���ֵ
			JDBC.update1("Teanums", total + total1, userID);
			JDBC.update1("Exp", grades + scores, userID);
			if ((grades+scores)>=levelup)
				JDBC.update1("Level",levelup_0+1,userID);
			
			// ��ѯ���
			prestatment = connection
					.prepareStatement("select * from userinfo ");
			ResultSet rs3 = prestatment.executeQuery();
			while(rs3.next()) {//����ѭ�����û��������������޸�
				if(grades<=rs3.getInt("Exp")&&rs3.getInt("Exp")<grades+scores){//�÷�����С�ڸ��û�ԭ�л�����С�ڸ��û����еĻ���
					JDBC.update1("Rank", rs3.getInt("Rank")+1, rs3.getString("UserID"));//������1
					JDBC.update1("Rank", JDBC.query(userID)-1,userID);//���û�������1
				}
				else if(rs3.getInt("Exp")==grades+scores&&!rs3.getString("UserID").equalsIgnoreCase(userID)){//�����������û���������û����л������ʱ
					JDBC.update1("Rank", JDBC.query(userID)-1,userID);
				}
			}

			shutDown();// �ر�����
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		DecimalFormat df = new DecimalFormat("#####0.00");// ����һ��������ͣ�����С�������λ

		// ������ȷ��
		rate = count(total, totalRight);
		rate1 = count(type1, typeRight1);
		rate2 = count(type2, typeRight2);
		rate3 = count(type3, typeRight3);

		// �����panel�Ĳ�����������
		setVisible(true);
		setLayout(null);
		setBounds(0, 0, 1000, 700);

		report = new JPanel() {// ����һ���µ�panel��չʾ��ص�����
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/check/checkPane.png"));// ���ñ���ͼƬ
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		report.setBounds(203, 31, 610, 560);
		report.setOpaque(false);
		add(report);
		report.setLayout(null);

		// ��ʾ����1����������label
		JLabel lblReport = new JLabel("" + typeRight1);
		lblReport.setBounds(190, 240, 39, 20);
		lblReport.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblReport);

		// ��ʾ����1������label
		JLabel lblnm = new JLabel("" + type1);
		lblnm.setBounds(400, 240, 39, 20);
		lblnm.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblnm);

		// ��ʾ����1��Ŀ��ȷ�ʵ�label
		JLabel lblNewLabel_1 = new JLabel(df.format(rate1 * 100) + "%");
		lblNewLabel_1.setBounds(505, 240, 105, 20);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_1);

		// ��ʾ����2����������label
		JLabel lblb = new JLabel("" + typeRight2);
		lblb.setBounds(190, 332, 39, 20);
		lblb.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblb);

		// ��ʾ����2������label
		JLabel lblNewLabel = new JLabel("" + type2);
		lblNewLabel.setBounds(400, 332, 39, 20);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel);

		// ��ʾ����2��Ŀ��ȷ�ʵ�label
		JLabel lblNewLabel_2 = new JLabel(df.format(rate2 * 100) + "%");
		lblNewLabel_2.setBounds(505, 332, 105, 20);
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_2);

		// ��ʾ����3����������label
		JLabel label = new JLabel("" + typeRight3);
		label.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		label.setBounds(190, 424, 39, 20);
		report.add(label);

		// ��ʾ����3������label
		JLabel lblNewLabel_3 = new JLabel("" + type3);
		lblNewLabel_3.setBounds(400, 424, 39, 20);
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_3);

		// ��ʾ����3��Ŀ��ȷ�ʵ�label
		JLabel lblNewLabel_4 = new JLabel(df.format(rate3 * 100) + "%");
		lblNewLabel_4.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		lblNewLabel_4.setBounds(505, 424, 105, 20);
		report.add(lblNewLabel_4);

		// ��ʾ������������label
		JLabel label_1 = new JLabel("" + totalRight);
		label_1.setBounds(190, 475, 39, 20);
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(label_1);

		// ��ʾ����������label
		JLabel lblNewLabel_5 = new JLabel("" + total);
		lblNewLabel_5.setBounds(400, 475, 39, 20);
		lblNewLabel_5.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_5);

		// ��ʾ��Ŀ����ȷ�ʵ�label
		JLabel lblNewLabel_6 = new JLabel(df.format(rate * 100) + "%");
		lblNewLabel_6.setBounds(505, 475, 105, 20);
		lblNewLabel_6.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		report.add(lblNewLabel_6);

		// �������չʾ������Ŀ��Ϣ�İ�ť
		JButton btnFull = new JButton();
		btnFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFull.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/full.png")));// ���õ��ǰ��ͼƬ
		btnFull.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/check/fullO.png")));// ����������ʱ��ͼƬ
		btnFull.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/check/fullP.png")));// ���õ�����ͼƬ

		btnFull.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �����������л�һ��panel
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

		// ���÷��ذ�ť
		JButton btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));// ���õ��ǰ��ͼƬ
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));// �������������ʱ��ͼƬ
		btnBack.addMouseListener(new MouseAdapter() {// �����������л�panel
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

	// ��������
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

	// ����panel����ͼƬ
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/checkBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

	public static void shutDown() {// �ر�����
		try {
			connection.close();
			System.out.println("Disconnect successfully");
		} catch (SQLException sqlex) {
			System.err.println("Unable to disconnect");
			sqlex.printStackTrace();
		}
	}

	// ������ȷ�ʵķ���
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
	
	//������Ŀ�Ѷ�
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
