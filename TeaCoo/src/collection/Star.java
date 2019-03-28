package collection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Star extends JPanel {

	private static final long serialVersionUID = 1L;

	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;

	private JPanel container;
	private JButton btnBack;

	private StarList starList;
	private DetailInfo starDetail;

	public Star(String userID) {

		this.userID = userID;
		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);

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

		starDetail = new DetailInfo(userID) {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/collection/stardetail.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		starDetail.setBounds(305, 92, 600, 550);
		
		starList = new StarList(userID);
		container.add(starList);
		starList.btndetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int r = MYTable.table.getSelectedRow();

				MYTable.getText(MYTable.table.getValueAt(r, 0).toString());
				starDetail.ALabel.setText(MYTable.oA);
				starDetail.BLabel.setText(MYTable.oB);
				starDetail.CLabel.setText(MYTable.oC);
				starDetail.DLabel.setText(MYTable.oD);
				starDetail.stemArea.setText(MYTable.main);
				starDetail.hnawerLabel.setText(MYTable.answer);
				starDetail.anlysArea.setText(MYTable.analysis);

				container.remove(starList);
				container.add(starDetail);
				container.revalidate();
				container.repaint();

			}
		});

		starDetail.btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.remove(starDetail);
				container.add(starList);
				container.revalidate();
				container.repaint();

			}
		});

	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/starBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}
