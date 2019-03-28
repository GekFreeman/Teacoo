package collection;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.ComboBoxUI;
import login.CustomizedJComboBox.MyComboBoxUI;

public class AddNewApp extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton btnSubmit, btnReturn,btnSave;
	public JScrollPane stemScroll, aScroll, bScroll, cScroll, dScroll,
			anlysScroll;
	public JTextArea stemTextA, aTextA, bTextA, cTextA, dTextA, anlysTextA;
	public JTextField anwrTextF;
	public JComboBox<String> comBoxType;
	
	String userID, stem, a, b, c, d, anwr, type,anlys;

	public AddNewApp(String userID) {
		
		this.userID = userID;
		
		setVisible(true);
		setBounds(318, 92, 600, 550);
		setLayout(null);
	
		btnSubmit=new JButton();
		btnSubmit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/submit.png")));
		btnSubmit.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/submitO.png")));
		btnSubmit.setBounds(310,510,85,35);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorderPainted(false);
		
		btnSave=new JButton();
		btnSave.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/save.png")));
		btnSave.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/saveO.png")));
		btnSave.setBounds(220,510,85,35);
		btnSave.setContentAreaFilled(false);
		btnSave.setBorderPainted(false);
		
		btnReturn=new JButton();
		btnReturn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/return.png")));
		btnReturn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/returnO.png")));
		btnReturn.setBounds(515,10,85,35);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		/*组合框，将按钮和下拉框组合在一起*/
		comBoxType = new JComboBox<String>();
		comBoxType.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		comBoxType.setForeground(Color.DARK_GRAY);
		comBoxType.setModel(new DefaultComboBoxModel<String>(new String[] {"principle of management", "financial management", "corporate governance"}));
		comBoxType.setUI((ComboBoxUI) MyComboBoxUI.createUI(comBoxType));
		comBoxType.setOpaque(false);
		comBoxType.setBorder(null);
		comBoxType.setBounds(198, 53, 256, 23);
		
		stemTextA = new JTextArea();
		stemTextA.setWrapStyleWord(true);
		stemTextA.setLineWrap(true);
		stemTextA.setColumns(70);
		stemTextA.setFont(new Font("Century Gothic",Font.BOLD, 14));

		aTextA = new JTextArea();
		aTextA.setLineWrap(true);
		aTextA.setWrapStyleWord(true);
		aTextA.setColumns(70);
		aTextA.setFont(new Font("Century Gothic",Font.BOLD, 14));
		
		bTextA = new JTextArea();
		bTextA.setWrapStyleWord(true);
		bTextA.setLineWrap(true);
		bTextA.setColumns(70);
		bTextA.setFont(new Font("Century Gothic",Font.BOLD, 14));
		
		cTextA = new JTextArea();
		cTextA.setLineWrap(true);
		cTextA.setWrapStyleWord(true);
		cTextA.setColumns(70);
		cTextA.setFont(new Font("Century Gothic",Font.BOLD, 14));
		
		dTextA = new JTextArea();
		dTextA.setWrapStyleWord(true);
		dTextA.setLineWrap(true);
		dTextA.setColumns(70);
		dTextA.setFont(new Font("Century Gothic",Font.BOLD, 14));
		
		anlysTextA = new JTextArea();
		anlysTextA.setLineWrap(true);
		anlysTextA.setWrapStyleWord(true);
		anlysTextA.setColumns(70);
		anlysTextA.setFont(new Font("Century Gothic",Font.BOLD, 14));
		
		stemScroll = new JScrollPane();
		stemScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		stemScroll.setBounds(125, 99, 444, 69);
		stemScroll.setViewportView(stemTextA);

		aScroll = new JScrollPane();
		aScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		aScroll.setBounds(125, 219, 444, 35);
		aScroll.setViewportView(aTextA);

		bScroll = new JScrollPane();
		bScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		bScroll.setBounds(125, 270, 444, 35);
		bScroll.setViewportView(bTextA);

		cScroll = new JScrollPane();
		cScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		cScroll.setBounds(125, 315, 444, 35);
		cScroll.setViewportView(cTextA);

		dScroll = new JScrollPane();
		dScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dScroll.setBounds(125, 365, 444, 35);
		dScroll.setViewportView(dTextA);

		anlysScroll = new JScrollPane();
		anlysScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		anlysScroll.setBounds(125, 410, 444, 51);
		anlysScroll.setViewportView(anlysTextA);
		
		anwrTextF = new JTextField();
		anwrTextF.setBounds(126, 480, 66, 21);	
		anwrTextF.setColumns(10);
			
		add(btnSubmit);
		add(btnReturn);
		add(btnSave);
		add(comBoxType);
		
		add(stemScroll);
		add(aScroll);
		add(bScroll);
		add(cScroll);
		add(dScroll);
		add(anlysScroll);
		add(anwrTextF);
		
	}
	 
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/collection/addPane.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
}
