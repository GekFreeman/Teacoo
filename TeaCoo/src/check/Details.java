package check;

import javax.swing.JPanel;
import practice.*;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
public class Details extends JPanel {// ��ʾ����һ�����Ϣ��������ɡ�ѡ����Ϣ����ȷ�𰸺ͽ���
	/**
	 * Create the panel.
	 */
	String userID;
	Questions zuo[];
	JFrame presentframe; // ����panel���л�
	JPanel presentpanel = this;
	public int num;

	public Details(int r, Questions zuoti[], String userID,int length) {// ���캯����rΪTable�еĵڼ���
		this.num=length;
		Questions mouti = zuoti[r]; // ĳһ�����Ϣ
		this.zuo = zuoti; // ������������
		setVisible(true); // ����panel�����Ϣ
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		// ���ذ�ť������
		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));// ���ó�ʼ��ͼƬ
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));// ��������ƶ�������ʱ��ͼƬ
		btnBack.addMouseListener(new MouseAdapter() {// ��������������󷵻���һ�����棬��Ҫ����ʵ��panel�л�
			@Override
			public void mouseClicked(MouseEvent e) {
				TablePane cPane = new TablePane(zuo, userID,num);
				cPane.setVisible(true);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(cPane);
				cPane.presentframe = presentframe;
				cPane.presentpanel = cPane;
			}
		});
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		add(btnBack);

		// ����һ��panel����ʾ����������Ϣ
		JPanel checkDetail = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/check/checkdetail.png"));// ����ͼƬ
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		checkDetail.setBounds(195, 31, 610, 560);
		checkDetail.setOpaque(false);
		add(checkDetail);
		checkDetail.setLayout(null);

		// ����һ��JScrollPane��JTextArea�������ʵ�ֹ��������������������
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(107, 142, 461, 84);
		checkDetail.add(scrollPane);
		MultilineLabel textArea = new MultilineLabel();
		textArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setBounds(168, 70, 341, 54);
		textArea.setText(mouti.MainQuestion);
		scrollPane.setViewportView(textArea);// ��JTextArea���뵽JScrollPane��

		// ��ʾѡ��A��label

		JScrollPane aPane = new JScrollPane();
		aPane.setBorder(null);
		aPane.setBounds(107, 226, 461, 23);
		checkDetail.add(aPane);
		MultilineLabel textAreaA = new MultilineLabel();
		textAreaA.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textAreaA.setEditable(false);
		textAreaA.setText(mouti.OptionA);
		aPane.setViewportView(textAreaA);
		

		
		JScrollPane bPane = new JScrollPane();
		bPane.setBorder(null);
		bPane.setBounds(107, 277, 461, 23);
		checkDetail.add(bPane);
		MultilineLabel textAreaB = new MultilineLabel();
		textAreaB.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textAreaB.setEditable(false);
		textAreaB.setText(mouti.OptionB);
		bPane.setViewportView(textAreaB);
		


		
		JScrollPane cPane = new JScrollPane();
		cPane.setBorder(null);
		cPane.setBounds(107, 323, 461, 23);
		checkDetail.add(cPane);
		MultilineLabel textAreaC = new MultilineLabel();
		textAreaC.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textAreaC.setEditable(false);
		textAreaC.setText(mouti.OptionC);
		cPane.setViewportView(textAreaC);	


		JScrollPane dPane = new JScrollPane();
		dPane.setBorder(null);
		dPane.setBounds(107, 371, 461, 23);
		checkDetail.add(dPane);
		MultilineLabel textAreaD = new MultilineLabel();
		textAreaD.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textAreaD.setEditable(false);
		textAreaD.setText(mouti.OptionD);
		dPane.setViewportView(textAreaD);
		


		// ��ʾ�𰸵�label
		JLabel label_Answer = new JLabel("");
		label_Answer.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_Answer.setBounds(105, 412, 81, 15);
		label_Answer.setText("" + mouti.Answer);
		checkDetail.add(label_Answer);
		
		// ����һ��JScrollPane��JTextArea�������ʵ�ֹ��������������������
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBorder(null);
		scrollPane1.setBounds(107, 448, 461, 74);
		checkDetail.add(scrollPane1);
		MultilineLabel textArea1 = new MultilineLabel();
		textArea1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textArea1.setEditable(false);
		textArea1.setBounds(150, 364, 341, 97);
		textArea1.setText(mouti.Analysis);
		scrollPane1.setViewportView(textArea1);
	}

	protected void paintComponent(Graphics g) {// ��������panel�ı���ͼƬ
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/checkBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}


 class MultilineLabel extends JTextArea {
	
	private static final long serialVersionUID = 1L;
	public MultilineLabel() {
		 super();
		 }
	 public void updateUI() {
		 super.updateUI();  
		 setLineWrap(true); // ����Ϊ�Զ�����
		 setWrapStyleWord(true);
		 setHighlighter(null);
		 setEditable(false);
		 LookAndFeel.installBorder(this, "Label.border");// ����Ϊlabel�ı߿���ɫ������
		 LookAndFeel.installColorsAndFont(this, "Label.background", "Label.foreground", "Century Gothic");
		 
	 }
 }
