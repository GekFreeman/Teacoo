package collection;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import portal.*;
import javax.swing.*;

public class CollectionMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	
	private JPanel container;
	private JPanel starPane;
	private JPanel wrongPane;
	private JPanel applicationPane;
	
	private JButton btnStar;
	private JButton btnWrong;
	private JButton btnApplication;
	
	private JButton btnBack;

	public CollectionMenu(String userID) {
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
				PortalPane pPane = new PortalPane(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pPane);
				pPane.presentframe=presentframe;
				pPane.presentpanel=pPane;
			}
		});
		btnBack.setBounds(0,0,121,42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);
		
		starPane=new JPanel();
		starPane.setBounds(89, 93,270, 510);
		starPane.setOpaque(false);
		container.add(starPane);
		
		wrongPane=new JPanel();
		wrongPane.setBounds(369, 93,270, 510);
		wrongPane.setOpaque(false);
		container.add(wrongPane);
		
		applicationPane=new JPanel();
		applicationPane.setBounds(649, 93,270, 510);
		applicationPane.setOpaque(false);
		container.add(applicationPane);
		
		btnStar=new JButton();
		btnStar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Star sPane = new Star(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(sPane);
				sPane.presentframe=presentframe;
				sPane.presentpanel=sPane;
			}
		});
		btnStar.setBounds(0, 0, 270, 510);
		btnStar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/star.png")));
		btnStar.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/starO.gif")));
		btnStar.setContentAreaFilled(false);
		btnStar.setBorderPainted(false);
		starPane.add(btnStar);
		
		btnWrong=new JButton();
		btnWrong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WrongTopic wPane = new WrongTopic(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(wPane);
				wPane.presentframe=presentframe;
				wPane.presentpanel=wPane;
			}
		});
		btnWrong.setBounds(0, 0, 270, 510);
		btnWrong.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/wrong.png")));
		btnWrong.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/wrongO.gif")));
		btnWrong.setContentAreaFilled(false);
		btnWrong.setBorderPainted(false);
		wrongPane.add(btnWrong);
		
		btnApplication=new JButton();
		btnApplication.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Apply aPane = new Apply(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(aPane);
				aPane.presentframe=presentframe;
				aPane.presentpanel=aPane;
			}
		});
		btnApplication.setBounds(0, 0, 270, 510);
		btnApplication.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/application.png")));
		btnApplication.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/applicationO.gif")));
		btnApplication.setContentAreaFilled(false);
		btnApplication.setBorderPainted(false);
		applicationPane.add(btnApplication);
		
		
		
}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/collectionBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
	}
