package practice;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class SpecializedTest extends JPanel {
	//专项练习，点击每一项，便会生成该种题型的试卷，试卷题量默认为五题

	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	
	private JPanel container;
	private JPanel typePane;
	
	private JButton btnBack;
	private JButton btnStart;
	private JButton btnReset;
	
	private JButton btnType1;
	private JButton btnType2;
	private JButton btnType3;
	/**
	 * Create the panel.
	 */
	public SpecializedTest(String userID) {
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
		
		
		
		btnStart=new JButton();
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Test tPane = new Test(userID,0,0,0);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		
		btnType1=new JButton();
		btnType1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type1.png")));
		btnType1.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type1O.png")));
		btnType1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Test tPane = new Test(userID,5,0,0);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		btnType1.setBounds(249,49,296,45);
		btnType1.setContentAreaFilled(false);
		btnType1.setBorderPainted(false);
		typePane.add(btnType1);
		
		btnType2=new JButton();
		btnType2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type2.png")));
		btnType2.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type2O.png")));
		btnType2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Test tPane = new Test(userID,0,5,0);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		btnType2.setBounds(250,205,296,45);
		btnType2.setContentAreaFilled(false);
		btnType2.setBorderPainted(false);
		typePane.add(btnType2);
		
		btnType3=new JButton();
		btnType3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type3.png")));
		btnType3.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/type3O.png")));
		btnType3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Test tPane = new Test(userID,0,0,5);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		btnType3.setBounds(250,350,296,45);
		btnType3.setContentAreaFilled(false);
		btnType3.setBorderPainted(false);
		typePane.add(btnType3);
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice/typeSBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
}
