package collection;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class ApplyList extends JPanel {

	private static final long serialVersionUID = 1L;

	
	public JButton btndetail,btnAdd;
	private JScrollPane scrollPane;

	MYTable mtable;
	String sql;
	String[] headNames={"No.","Apply Date","Status",""};

	String userID;

	public ApplyList(String  userID) {
		this. userID = userID;
		setVisible(true);
		setBounds(318, 92, 600, 550);
		setLayout(null);
	
		btndetail=new JButton();
		/*向MYTable中传递需要查询的SQL语句*/
		sql="SELECT applyid,applydate,applystatus FROM apply where userid="+userID+" order by (applyid"+"+"+0+")";	
		mtable=new MYTable(sql,btndetail,headNames,"apply");
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(10, 66, 580, 445);
		scrollPane.setViewportView(MYTable.table);
		scrollPane.setOpaque(false);
	    add(scrollPane);
			
		btnAdd=new JButton();
		btnAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/addnew.png")));
		btnAdd.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/addnewO.png")));
		btnAdd.setBounds(515,10,85,35);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorderPainted(false);
		add(btnAdd);	
	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/applicationlist.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}

}
