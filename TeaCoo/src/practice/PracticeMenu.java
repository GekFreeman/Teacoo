package practice;

import javax.swing.*;

import portal.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PracticeMenu extends JPanel {
	//选择做题方式的Jpanel
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	
	private JButton btnBack;
	private JButton btnQc;
	private JButton btnSp;
	private JButton btnSm;
	
	private JPanel container;
	
	private JLabel qcGif;
	private JLabel spGif;
	private JLabel smGif;
	
	private JLabel backLabel;

	/**
	 * Create the panel.
	 */
	public PracticeMenu(String userID) {
		this.userID = userID;

		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);
		
		container=new JPanel();
		container.setBounds(200,0,800,700);
		container.setLayout(null);
		container.setOpaque(false);
		add(container);
		
		qcGif = new JLabel();
		qcGif.setBounds(0, 0, 800, 700);
		qcGif.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/qcBG.gif")));
		container.add(qcGif);
		
		spGif = new JLabel();
		spGif.setBounds(0, 0, 800, 700);
		spGif.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/spBG.gif")));
		
		smGif = new JLabel();
		smGif.setBounds(0, 0, 800, 700);
		smGif.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/smBG.gif")));
		
		btnBack=new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PortalPane pPanel = new PortalPane(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pPanel);
				pPanel.presentframe=presentframe;
				pPanel.presentpanel=pPanel;
			}
		});
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(0,0,136,42);
		add(btnBack);
		backLabel=new JLabel();
		backLabel.setBounds(0, 0, 200, 100);
		backLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/backlabel.png")));
		add(backLabel);
		
		btnQc=new JButton();
		btnQc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQc.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/qc.png")));
		btnQc.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/qcO.png")));
		btnQc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(spGif);
				container.remove(smGif);
				container.revalidate();
				container.repaint();
				container.add(qcGif);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			QuickTest tPanel = new QuickTest(userID);
			presentpanel.setVisible(false);
			presentframe.getContentPane().add(tPanel);
			tPanel.presentframe=presentframe;
			tPanel.presentpanel=tPanel;
			}
		});
		
		
		
		btnQc.setBounds(0,100,200,200);
		btnQc.setContentAreaFilled(false);
		btnQc.setBorderPainted(false);
		add(btnQc);
		
		btnSp=new JButton();
		btnSp.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/sp.png")));
		btnSp.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/spO.png")));
		btnSp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(qcGif);
				container.remove(smGif);
				container.revalidate();
				container.repaint();
				container.add(spGif);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SpecializedTest sPanel = new SpecializedTest(userID);
			presentpanel.setVisible(false);
			presentframe.getContentPane().add(sPanel);
			sPanel.presentframe=presentframe;
			sPanel.presentpanel=sPanel;
			}
		});
		btnSp.setBounds(0,300,200,200);
		btnSp.setContentAreaFilled(false);
		btnSp.setBorderPainted(false);
		add(btnSp);

		btnSm=new JButton();
		btnSm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Test tPane = new Test(userID,7,7,6);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		btnSm.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/sm.png")));
		btnSm.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/practice menu/smO.png")));
		btnSm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(spGif);
				container.remove(qcGif);
				container.revalidate();
				container.repaint();
				container.add(smGif);
			}
		});
		btnSm.setBounds(0,500,200,200);
		btnSm.setContentAreaFilled(false);
		btnSm.setBorderPainted(false);
		add(btnSm);
		
		
	}
	
}
