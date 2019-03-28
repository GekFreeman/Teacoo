package collection;

import java.awt.*;
import javax.swing.*;

public class StarList extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btndetail;
	private JScrollPane scrollPane;

	String userID;
	MYTable mtable;

	public StarList(String userID) {
		this.userID = userID;

		setBounds(305, 92, 600, 550);
		setLayout(null);

		btndetail = new JButton();

		String[] headNames = { "Q No.", "Question Type", "Stem", "" };
		// String sql="select qid,qtype,mainquestion from question";
		String sql = "select qid,qtype,mainquestion from star" + userID;
		
		mtable = new MYTable(sql, btndetail, headNames, "star" + userID);// question--->"star"+userID

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 580, 384);
		scrollPane.setViewportView(MYTable.table);
		add(scrollPane);

	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/collection/starlist.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}

}
