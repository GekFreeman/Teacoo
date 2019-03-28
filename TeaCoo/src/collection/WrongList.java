package collection;

import javax.swing.*;
import java.awt.*;

public class WrongList extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btndetail,btnReturn;
	private JScrollPane scrollPane;

	String userID;
	JFrame presentframe;
	JPanel presentpanel=this;

	MYTable mtable;
	
	public WrongList(String userID) {
		this.userID=userID;
		
		setBounds(295, 92, 600, 550);
		setLayout(null);
		
        btndetail=new JButton();
			
		String[] headNames={"Q No.","Question Type","Stem",""};
		//String sql="select qid,qtype,mainquestion from question";
		String sql="select qid,qtype,mainquestion from wrong"+userID;
		mtable=new MYTable(sql,btndetail,headNames,"wrong"+userID);  //question --->"wrong"+userID
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 101, 580, 392);
		scrollPane.setViewportView(MYTable.table);
		add(scrollPane);

	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/wronglist.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
	
}
