package practice;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import check.Report;
import collection.MultilineLabel;

public class Test extends JPanel {
	String userID;// �����userID
	public JFrame presentframe;// �ײ㴰��
	public JPanel presentpanel = this;// ��ǰ���

	private JPanel container;// ����

	private JPanel paperPane;// �Ծ����

	// abcd�ĸ�ѡ���button
	private JButton a;
	private JButton b;
	private JButton c;
	private JButton d;

	// ���أ���һ�⣬��һ�⣬�ύ���ղأ������ж�����ť
	private JButton btnBack;
	private JButton btnPre;
	private JButton btnNex;
	private JButton btnSubmit;
	private JButton btnCollect;
	private JButton btnShare;
	private JButton btnCorrect;

	// ���Ƶ�label��������ʾѡ���ɣ�
	private MultilineLabel answerA;
	private MultilineLabel answerB;
	private MultilineLabel answerC;
	private MultilineLabel answerD;
	private MultilineLabel stemText;
	private JTextField textField;// ������ʾ��ǰ�ǵڼ���

	// �������
	private JScrollPane scrollA;
	private JScrollPane scrollB;
	private JScrollPane scrollC;
	private JScrollPane scrollD;
	private JScrollPane scrollS;

	int i = 1;// ��ǰ�ڵڼ���
	int num;// �Ծ�����
	int type1;// ����1����
	int type2;// ����2����
	int type3;// ����3����
	Questions[] zuoti;// �Ծ�����

	// ����ˢ�µķ��������ڵ����һ����һ��������ػ�
	public void fresh() {
		initButton();
		this.stemText.setText(zuoti[i].MainQuestion);
		this.answerA.setText(zuoti[i].OptionA);
		this.answerB.setText(zuoti[i].OptionB);
		this.answerC.setText(zuoti[i].OptionC);
		this.answerD.setText(zuoti[i].OptionD);
		this.textField.setText(i + "");
		if (zuoti[i].userAnswer == 'A')
			a.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
					"image/practice/aO.png")));

		else if (zuoti[i].userAnswer == 'B')
			b.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
					"image/practice/bO.png")));
		else if (zuoti[i].userAnswer == 'C')
			c.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
					"image/practice/cO.png")));
		else if (zuoti[i].userAnswer == 'D')
			d.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
					"image/practice/dO.png")));
		if (zuoti[i].collected == false) {
			btnCollect.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/practice/btnCollect.png")));
		} else {
			btnCollect.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/practice/btnCollectP.png")));
		}

	}

	// ���췽��
	public Test(String userID, int type1, int type2, int type3) {

		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);

		paperPane = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/practice/sheet.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		paperPane.setBounds(252, 28, 500, 550);
		paperPane.setOpaque(false);
		container.add(paperPane);
		paperPane.setLayout(null);

		btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PracticeMenu pMenu = new PracticeMenu(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pMenu);
				pMenu.presentframe = presentframe;
				pMenu.presentpanel = pMenu;
			}
		});
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);

		// ��һ��İ�ť�¼������ڸı�i��ֵ���Լ��ػ����
		btnPre = new JButton();
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i--;
				if (i <= 1) {
					i = 1;
				}
				fresh();

			}
		});
		btnPre.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/previous.png")));
		btnPre.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/previousP.png")));
		btnPre.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/previousO.png")));
		btnPre.setBounds(90, 300, 100, 100);
		btnPre.setContentAreaFilled(false);
		btnPre.setBorderPainted(false);
		container.add(btnPre);

		// ��һ��İ�ť�¼������ڸı�i��ֵ���Լ��ػ����
		btnNex = new JButton();
		btnNex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i++;
				
					if (i >= num) {
						i = num;
					}
				
				fresh();
			}
		});
		btnNex.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/next.png")));
		btnNex.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/nextP.png")));
		btnNex.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/nextO.png")));
		btnNex.setBounds(810, 300, 100, 100);
		btnNex.setContentAreaFilled(false);
		btnNex.setBorderPainted(false);
		container.add(btnNex);

		a = new JButton();
		// ѡ��A���¼��������洢�û�����zuoti���飬Ȼ��ı�ͼƬ������ѡ��ͬ��
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zuoti[i].userAnswer = 'A';
				initButton();
				a.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						"image/practice/aO.png")));

			}
		});
		a.setBounds(58, 316, 40, 40);
		a.setContentAreaFilled(false);
		a.setBorderPainted(false);
		paperPane.add(a);

		b = new JButton();
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zuoti[i].userAnswer = 'B';
				initButton();
				b.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						"image/practice/bO.png")));
			}
		});
		b.setBounds(58, 362, 40, 40);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		paperPane.add(b);

		c = new JButton();
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zuoti[i].userAnswer = 'C';
				initButton();
				c.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						"image/practice/cO.png")));

			}
		});
		c.setBounds(58, 420, 40, 40);
		c.setContentAreaFilled(false);
		c.setBorderPainted(false);
		paperPane.add(c);

		d = new JButton();
		d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zuoti[i].userAnswer = 'D';
				initButton();
				d.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						"image/practice/cO.png")));

			}
		});
		d.setBounds(58, 478, 40, 40);
		d.setContentAreaFilled(false);
		d.setBorderPainted(false);
		paperPane.add(d);

		// �ύ��ť�����������飬�������û�ID���ݸ�check��Ȼ���û��ղص�����뽨�õ��û���Ӧ�ı�
		btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				System.out.println("submit����" + zuoti[1].MainQuestion);
				InsertToStar s = new InsertToStar();
				Report re = new Report(zuoti, userID, num);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(re);
				re.presentframe = presentframe;
				for (i = 1; i <= num; i++) {
					String c = String.valueOf(zuoti[i].Answer);
					if (zuoti[i].collected) {
						System.out.println(i + "�������");
						s.insert(userID, zuoti[i].QID, zuoti[i].MainQuestion,
								zuoti[i].OptionA, zuoti[i].OptionB,
								zuoti[i].OptionC, zuoti[i].OptionD, c,
								zuoti[i].Qtype, zuoti[i].Analysis);
					}

				}
			}
		});
		btnSubmit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/submit.png")));
		btnSubmit.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/submitO.png")));
		btnSubmit.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/submitP.png")));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnSubmit.setBounds(429, 588, 180, 55);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorderPainted(false);
		container.add(btnSubmit);

		btnCollect = new JButton();
		btnCollect.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/btnCollect.png")));
		btnCollect.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/btnCollectO.png")));

		btnCollect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			// �ղذ�ť���¼��л�ͼƬ�Լ��ı����������е�ֵ
			public void mouseClicked(MouseEvent e) {
				if (zuoti[i].collected == true) {
					zuoti[i].collected = false;
					btnCollect.setIcon(new ImageIcon(Toolkit
							.getDefaultToolkit().getImage(
									"image/practice/btnCollect.png")));
				} else {
					zuoti[i].collected = true;
					btnCollect.setIcon(new ImageIcon(Toolkit
							.getDefaultToolkit().getImage(
									"image/practice/btnCollectP.png")));
				}
			}
		});
		btnCollect.setBounds(434, 659, 140, 41);
		btnCollect.setContentAreaFilled(false);
		btnCollect.setBorderPainted(false);
		container.add(btnCollect);

		btnShare = new JButton();
		btnShare.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/btnShare.png")));
		btnShare.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/btnShareO.png")));
		btnShare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnShare.setBounds(339, 659, 140, 41);
		btnShare.setContentAreaFilled(false);
		btnShare.setBorderPainted(false);
		container.add(btnShare);

		btnCorrect = new JButton();
		btnCorrect.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/btnCorrect.png")));
		btnCorrect.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/practice/btnCorrectO.png")));
		btnCorrect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnCorrect.setBounds(539, 659, 140, 41);
		btnCorrect.setContentAreaFilled(false);
		btnCorrect.setBorderPainted(false);
		container.add(btnCorrect);

		answerA = new MultilineLabel();
		answerA.setEditable(false);
		// answerA.setText("a");
		answerA.setBounds(118, 316, 314, 42);

		scrollA = new JScrollPane();
		scrollA.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollA.setViewportView(answerA);
		scrollA.setBounds(118, 316, 314, 42);
		paperPane.add(scrollA);

		answerB = new MultilineLabel();
		answerB.setEditable(false);
		// answerB.setText("b");
		answerB.setBounds(118, 368, 314, 42);

		scrollB = new JScrollPane();
		scrollB.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollB.setViewportView(answerB);
		scrollB.setBounds(118, 368, 314, 42);
		paperPane.add(scrollB);

		answerC = new MultilineLabel();
		answerC.setEditable(false);
		// answerC.setText("c");
		answerC.setBounds(118, 420, 314, 42);

		scrollC = new JScrollPane();
		scrollC.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollC.setViewportView(answerC);
		scrollC.setBounds(118, 420, 314, 42);
		paperPane.add(scrollC);

		answerD = new MultilineLabel();
		answerD.setEditable(false);
		// answerD.setText("d");
		answerD.setBounds(118, 478, 314, 42);

		scrollD = new JScrollPane();
		scrollD.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollD.setViewportView(answerD);
		scrollD.setBounds(118, 478, 314, 42);
		paperPane.add(scrollD);

		stemText = new MultilineLabel();
		stemText.setEditable(false);
		// stemText.setText("123");
		stemText.setBounds(100, 87, 296, 183);

		scrollS = new JScrollPane();
		scrollS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollS.setViewportView(stemText);
		scrollS.setBounds(100, 87, 296, 183);
		paperPane.add(scrollS);

		textField = new JTextField();
		textField.setOpaque(false);
		textField.setBorder(null);
		textField.setEditable(false);
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 24));
		textField.setText("01");
		textField.setBounds(290, 10, 76, 36);
		paperPane.add(textField);
		textField.setColumns(10);

		this.userID = userID;
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
		this.num = type1 + type2 + type3;
		System.out.println("num" + num);
		TestPaper test = new TestPaper(type1, type2, type3);
		zuoti = test.getPaper();
		i = 1;
		fresh();
		System.out.println("��������" + num + "��ǰ��Ŀ��Ŀ" + i + "����1��" + type1
				+ "����2��" + type2 + "����3��" + type3);
		this.setVisible(true);
	}

	public void initButton() {
		a.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/a.png")));
		b.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/b.png")));
		c.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/c.png")));
		d.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/d.png")));

	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/practice/testBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}
