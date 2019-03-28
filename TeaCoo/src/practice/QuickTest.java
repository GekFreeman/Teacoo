package practice;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import portal.PortalPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuickTest extends JPanel {
	//quicktest快速测试的界面
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	
	private JPanel container;
	private JPanel typePane;
	
	private JButton btnBack;
	private JButton btnStart;
	private JButton btnReset;
	
	private JTextPane type1;
	private JTextPane type2;
	private JTextPane type3;
	/**
	 * Create the panel.
	 */
	public  QuickTest(String userID) {
		this.userID=userID;
		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);
		
		btnBack=new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PracticeMenu pMenu = new PracticeMenu(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pMenu);
				pMenu.presentframe=presentframe;
				pMenu.presentpanel=pMenu;
			}
		});
		btnBack.setBounds(0,0,121,42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);
		
		typePane=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		typePane.setBounds(100, 80, 820, 470);
		typePane.setLayout(null);
		container.add(typePane);
		
		type1 = new JTextPane();
		type1.setText("0");
		type1.setForeground(Color.WHITE);
		type1.setOpaque(false);
		type1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		type1.setBounds(476, 60, 33, 30);
		typePane.add(type1);
		
		type2 = new JTextPane();
		type2.setText("0");
		type2.setForeground(Color.WHITE);
		type2.setOpaque(false);
		type2.setFont(new Font("Century Gothic", Font.BOLD, 14));
		type2.setBounds(472, 215, 33, 30);
		typePane.add(type2);
		
		type3 = new JTextPane();
		type3.setText("0");
		type3.setForeground(Color.WHITE);
		type3.setOpaque(false);
		type3.setFont(new Font("Century Gothic", Font.BOLD, 14));
		type3.setBounds(473, 360, 33, 30);
		typePane.add(type3);
		
		//开始按钮的事件监听，实例化一个Test类
		btnStart=new JButton();
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Test tPane = new Test(userID,Integer.parseInt(type1.getText()),Integer.parseInt(type2.getText()),Integer.parseInt(type3.getText()));
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		btnStart.setBounds(325, 579, 150, 50);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorderPainted(false);
		btnStart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/start.png")));
		btnStart.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/startO.png")));
		btnStart.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/startP.png")));
		container.add(btnStart);
		
		//题量重置
		btnReset=new JButton();
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type1.setText("0");
				type2.setText("0");
				type3.setText("0");
			}
		});
		btnReset.setBounds(550, 579, 150, 50);
		btnReset.setContentAreaFilled(false);
		btnReset.setBorderPainted(false);
		btnReset.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/reset.png")));
		btnReset.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/resetO.png")));
		btnReset.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/resetP.png")));
		container.add(btnReset);
		
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/typeBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
}
