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
public class Details extends JPanel {// 显示具体一题的信息，包括题干、选项信息、正确答案和解析
	/**
	 * Create the panel.
	 */
	String userID;
	Questions zuo[];
	JFrame presentframe; // 用于panel的切换
	JPanel presentpanel = this;
	public int num;

	public Details(int r, Questions zuoti[], String userID,int length) {// 构造函数，r为Table中的第几行
		this.num=length;
		Questions mouti = zuoti[r]; // 某一题的信息
		this.zuo = zuoti; // 本次做过的题
		setVisible(true); // 设置panel相关信息
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		// 返回按钮的设置
		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));// 设置初始的图片
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));// 设置鼠标移动到上面时的图片
		btnBack.addMouseListener(new MouseAdapter() {// 鼠标监听器：点击后返回上一个界面，重要的是实现panel切换
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

		// 设置一个panel来显示本题的相关信息
		JPanel checkDetail = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/check/checkdetail.png"));// 背景图片
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		checkDetail.setBounds(195, 31, 610, 560);
		checkDetail.setOpaque(false);
		add(checkDetail);
		checkDetail.setLayout(null);

		// 设置一个JScrollPane和JTextArea的组合来实现滚动窗口区域内输出文字
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(107, 142, 461, 84);
		checkDetail.add(scrollPane);
		MultilineLabel textArea = new MultilineLabel();
		textArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setBounds(168, 70, 341, 54);
		textArea.setText(mouti.MainQuestion);
		scrollPane.setViewportView(textArea);// 将JTextArea加入到JScrollPane中

		// 显示选项A的label

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
		


		// 显示答案的label
		JLabel label_Answer = new JLabel("");
		label_Answer.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		label_Answer.setBounds(105, 412, 81, 15);
		label_Answer.setText("" + mouti.Answer);
		checkDetail.add(label_Answer);
		
		// 设置一个JScrollPane和JTextArea的组合来实现滚动窗口区域内输出文字
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

	protected void paintComponent(Graphics g) {// 画出整个panel的背景图片
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
		 setLineWrap(true); // 设置为自动换行
		 setWrapStyleWord(true);
		 setHighlighter(null);
		 setEditable(false);
		 LookAndFeel.installBorder(this, "Label.border");// 设置为label的边框，颜色和字体
		 LookAndFeel.installColorsAndFont(this, "Label.background", "Label.foreground", "Century Gothic");
		 
	 }
 }
