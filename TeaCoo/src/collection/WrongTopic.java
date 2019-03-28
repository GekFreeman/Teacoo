package collection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WrongTopic extends JPanel {

	private static final long serialVersionUID = 1L;

	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;

	private JPanel container;
	private JButton btnBack;
	
	private WrongList wrongList;
	private DetailInfo wrongDetail;

	public WrongTopic(String userID) {
		this.userID = userID;
		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		container.setLayout(null);
		add(container);

		btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CollectionMenu cMenu = new CollectionMenu(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(cMenu);
				cMenu.presentframe = presentframe;
				cMenu.presentpanel = cMenu;
			}
		});
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);

		wrongDetail = new DetailInfo(userID) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/collection/wrongdetail.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		wrongDetail.setBounds(295, 92, 600, 550);
		wrongDetail.btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.remove(wrongDetail);
				container.add(wrongList);
				container.revalidate();
				container.repaint();

			}
		});

		wrongList = new WrongList(userID);
		container.add(wrongList);
		wrongList.btndetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int r = MYTable.table.getSelectedRow();

				MYTable.getText(MYTable.table.getValueAt(r, 0).toString());
				wrongDetail.ALabel.setText(MYTable.oA);
				wrongDetail.BLabel.setText(MYTable.oB);
				wrongDetail.CLabel.setText(MYTable.oC);
				wrongDetail.DLabel.setText(MYTable.oD);
				wrongDetail.stemArea.setText(MYTable.main);
				wrongDetail.hnawerLabel.setText(MYTable.answer);
				wrongDetail.anlysArea.setText(MYTable.analysis);

				container.remove(wrongList);
				container.add(wrongDetail);
				container.revalidate();
				container.repaint();

			}
		});
	
	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/wrongBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}
