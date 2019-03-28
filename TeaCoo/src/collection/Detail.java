package collection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;


public class Detail extends JPanel {

	private static final long serialVersionUID = 1L;

	public JLabel hnawerLabel;
	public MultilineLabel ALabel, BLabel, CLabel, DLabel,stemArea, anlysArea;
	public JButton btnReturn, btnAnswer,btnAnalysis,btnA,btnB,btnC,btnD;
	private JScrollPane stemscroll, anlyscroll;

	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	public String backToWhere=null;

	public Detail(String userID) {
		this.userID = userID;
		setVisible(true);
		setLayout(null);
		setOpaque(false);
		
		stemArea = new MultilineLabel();
		stemArea.setFont(new Font("Century Gothic",Font.BOLD, 14));
		stemArea.setBounds(45, 22, 440, 144);
		stemArea.setRows(3);
		stemArea.setColumns(10);
		stemArea.setEditable(false);

		anlysArea = new  MultilineLabel();
		anlysArea.setFont(new Font("Century Gothic",Font.PLAIN, 14));
		anlysArea.setBounds(45, 100, 440, 144);
		anlysArea.setRows(3);
		anlysArea.setColumns(10);
		anlysArea.setVisible(false);
		anlysArea.setEditable(false);

		stemscroll = new JScrollPane();
		stemscroll.setBorder(null);
		stemscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		stemscroll.setBounds(67, 45, 472, 116);
		stemscroll.setViewportView(stemArea);

		anlyscroll = new JScrollPane();
		anlyscroll.setBorder(null);
		anlyscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		anlyscroll.setBounds(134, 460, 394, 69);
		anlyscroll.setViewportView(anlysArea);

		
		btnA=new JButton();
		btnA.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/a.png")));
		btnA.setBounds(30,184,40,40);
		btnA.setContentAreaFilled(false);
		btnA.setBorderPainted(false);
		add(btnA);
		btnB=new JButton();
		btnB.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/b.png")));
		btnB.setBounds(30,244,40,40);
		btnB.setContentAreaFilled(false);
		btnB.setBorderPainted(false);
		add(btnB);
		
		btnC=new JButton();
		btnC.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/c.png")));
		btnC.setBounds(30,304,40,40);
		btnC.setContentAreaFilled(false);
		btnC.setBorderPainted(false);
	    add(btnC);
		
		btnD=new JButton();
		btnD.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/d.png")));
		btnD.setBounds(30,364,40,40);
		btnD.setContentAreaFilled(false);
		btnD.setBorderPainted(false);
		add(btnD);
		
		btnAnalysis=new JButton();
		btnAnalysis.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/analysis.png")));
		btnAnalysis.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/analysisO.png")));
		btnAnalysis.setBounds(30,460,85,35);
		btnAnalysis.setContentAreaFilled(false);
		btnAnalysis.setBorderPainted(false);
		add(btnAnalysis);
		
		btnAnswer=new JButton();
		btnAnswer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/answer.png")));
		btnAnswer.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/answerO.png")));
		btnAnswer.setBounds(30,424,85,35);
		btnAnswer.setContentAreaFilled(false);
		btnAnswer.setBorderPainted(false);
		add(btnAnswer);
		
		ALabel = new MultilineLabel();
		ALabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		ALabel.setBounds(102, 184, 472, 40);

		BLabel = new MultilineLabel();
		BLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		BLabel.setBounds(102, 244, 472, 40);

		CLabel = new MultilineLabel();
		CLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		CLabel.setBounds(102, 304, 472, 40);

		DLabel = new MultilineLabel();
		DLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		DLabel.setBounds(102, 364, 472, 40);


		hnawerLabel = new JLabel();
		hnawerLabel.setBounds(134, 424, 68, 23);
		hnawerLabel.setVisible(false);

	
		btnReturn=new JButton();
		btnReturn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/return.png")));
		btnReturn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/returnO.png")));
		btnReturn.setBounds(500,0,85,35);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);


		add(stemscroll);
		add(anlyscroll);
	
		add(ALabel);
		add(BLabel);
		add(CLabel);
		add(DLabel);
		add(hnawerLabel);

		add(btnAnswer);
		add(btnReturn);
		

		btnAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				hnawerLabel.setVisible(true);
			}
		});
		

		btnAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				anlysArea.setVisible(true);
			}
		});

		
	}
	
	
}
