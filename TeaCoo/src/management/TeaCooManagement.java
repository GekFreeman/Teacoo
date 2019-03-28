package management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.Document;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import login.CustomizedJComboBox.MyComboBoxUI;


public class TeaCooManagement extends JPanel {
	public static  String QID;
    public static String Qtype,Stem,optionA,optionB,optionC,optionD,Answer,level,Analysis,qtype;
    public  static String mQID=null,mQtype=null,mStem,moptionA,moptionB,moptionC,moptionD,mAnswer,mlevel,mAnalysis,mqtype;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	private JPanel container;

	private JPanel teacooList;
	private JPanel teacooDetail;
	private JPanel teacooAdd;
	
	private JButton btnBack;
	private JButton btnReturn,btnReturn1;
	private JButton btnTry;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnAdd1;
	private JTable teacootable;
	private MultilineLabel1 qid,qlevel,stem,optiona,optionb,optionc,optiond,answer,analysis;
	private MultilineLabel1 mqid,mqlevel,mstem,moptiona,moptionb,moptionc,moptiond,manswer,manalysis;
	private JComboBox comboBox1,comboBox2;
	/**
	 * Create the panel.
	 */
	public TeaCooManagement() {
		//this.userID = userID;
		
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
				AdminPane aPane = new AdminPane();
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(aPane);
				aPane.presentframe=presentframe;
				aPane.presentpanel=aPane;
			}
		});
		btnBack.setBounds(0,658,121,42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		container.add(btnBack);
		
		teacooList=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/teacoolist.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		teacooList.setBounds(200, 166, 610, 510);
		teacooList.setLayout(null);
		container.add(teacooList);
		
		btnAdd=new JButton();
		btnAdd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/add.png")));
		btnAdd.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/addO.png")));
		btnAdd.setBounds(505,16,110,50);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorderPainted(false);
		teacooList.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.remove(teacooList);
				teacooList.setVisible(false);
				container.add(teacooAdd);
				teacooAdd.setVisible(true);
				container.revalidate();
				container.repaint();
		
			}
		});
		
		
		
		//读取题库的信息到表格中
		GetData showqdata = new GetData();
	    showqdata.getConnection();
	    showqdata.getTikuTable();
	    validate();
	    teacootable = new JTable(showqdata.rows, showqdata.columnHeads);
	    teacootable.setBounds(new Rectangle(35, 75, 458, 373));
	    JScrollPane scroller = new JScrollPane(teacootable);
	    scroller.setBounds(0, 42, 600,400);
		teacooList.add(scroller);
		scroller.setVisible(true);

		// usertable.getTableHeader().setFont(new Font("Century Gothic",
		// Font.BOLD, 14)); // 表头字体

		teacootable.getTableHeader().setBackground(
				Color.getHSBColor(111, 139, 120)); // 表头颜色
		teacootable.getTableHeader().setBorder(null);
		teacootable.getTableHeader().setPreferredSize(new Dimension(1, 35));
		// 设置表头高度

		teacootable.setFont(new Font("Century Gothic", Font.PLAIN, 14)); // 设置单元格字体

		teacootable.setRowHeight(35); // 设置行高

		// 单元格居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		teacootable.setDefaultRenderer(Object.class, r);

		// 固定列宽（最大值和最小值一样）
		teacootable.getColumnModel().getColumn(0).setPreferredWidth(200);
		teacootable.getColumnModel().getColumn(0).setMaxWidth(200);
		teacootable.getColumnModel().getColumn(0).setMinWidth(200);

		teacootable.getColumnModel().getColumn(1).setPreferredWidth(200);
		teacootable.getColumnModel().getColumn(1).setMaxWidth(200);
		teacootable.getColumnModel().getColumn(1).setMinWidth(200);

		teacootable.getColumnModel().getColumn(2).setPreferredWidth(200);
		teacootable.getColumnModel().getColumn(2).setMaxWidth(200);
		teacootable.getColumnModel().getColumn(2).setMinWidth(200);


		// 由题库列表切换到单个题目具体信息
		teacooDetail = new JPanel() {
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/management/teacoodetail.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		teacooDetail.setBounds(200, 166, 610, 510);
		teacooDetail.setLayout(null);
		// container.add(teacooDetail);
		// 修改题库界面中各种textarea
		MultilineLabel1 mqid = new MultilineLabel1();
		mqid.setBounds(175, 55, 90, 30);
		teacooDetail.add(mqid);
		moptiona = new MultilineLabel1();
		moptiona.setBounds(110, 200, 200, 40);
		teacooDetail.add(moptiona);
		moptionb = new MultilineLabel1();
		moptionb.setBounds(350, 200, 200, 40);
		teacooDetail.add(moptionb);
		moptionc = new MultilineLabel1();
		moptionc.setBounds(110, 250, 200, 40);
		teacooDetail.add(moptionc);
		moptiond = new MultilineLabel1();
		moptiond.setBounds(350, 250, 200, 40);
		teacooDetail.add(moptiond);
		mstem = new MultilineLabel1();
		JScrollPane mscr1 = new JScrollPane(mstem);
		mscr1.setBounds(115, 100, 435, 80);
		mscr1.setViewportView(mstem);
		teacooDetail.add(mscr1);
		manalysis = new MultilineLabel1();
		JScrollPane mscr2 = new JScrollPane(manalysis);
		mscr2.setBounds(150, 310, 400, 80);
		mscr2.setViewportView(manalysis);
		teacooDetail.add(mscr2);
		manswer = new MultilineLabel1();
		manswer.setBounds(140, 415, 80, 30);
		teacooDetail.add(manswer);
		mqlevel = new MultilineLabel1();
		mqlevel.setBounds(380, 415, 80, 30);
		teacooDetail.add(mqlevel);

		teacootable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int r = teacootable.getSelectedRow();
				int c = teacootable.getSelectedColumn();
				if (c == 2) {
					mQID = teacootable.getValueAt(r, 0).toString();
					showqdata.getConnection();
					mqid.setText(teacootable.getValueAt(r, 0).toString());
					mstem.setText(showqdata.getMainQuestion(teacootable
							.getValueAt(r, 0).toString()));
					moptiona.setText(showqdata.getOptionA(teacootable
							.getValueAt(r, 0).toString()));
					moptionb.setText(showqdata.getOptionB(teacootable
							.getValueAt(r, 0).toString()));
					moptionc.setText(showqdata.getOptionC(teacootable
							.getValueAt(r, 0).toString()));
					moptiond.setText(showqdata.getOptionD(teacootable
							.getValueAt(r, 0).toString()));
					mqlevel.setText(showqdata.getLevel(teacootable.getValueAt(
							r, 0).toString()));
					manswer.setText(showqdata.getAnswer(teacootable.getValueAt(
							r, 0).toString()));
					manalysis.setText(showqdata.getAnalysis(teacootable
									.getValueAt(r, 0).toString()));
					if(showqdata.getQtype(teacootable.getValueAt(r, 0).toString())=="Type1")
						comboBox1.setSelectedItem("priciple of management");
					else if(showqdata.getQtype(teacootable.getValueAt(r, 0).toString())=="Type2")
						comboBox1.setSelectedItem("financial management");
					else if(showqdata.getQtype(teacootable.getValueAt(r, 0).toString())=="Type2")
						comboBox1.setSelectedItem("corporate governance");
							
							showqdata.shutDown();
							container.remove(teacooList);
							teacooList.setVisible(false);
							container.add(teacooDetail);
							teacooDetail.setVisible(true);
							container.revalidate();
							container.repaint();
						
						}}});
				
		
		
		
		
		
		
		teacooAdd=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/teacooAdd.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		teacooAdd.setBounds(200, 166, 610, 510);
		teacooAdd.setLayout(null);
		//container.add(teacooAdd);
		btnReturn1=new JButton();
		btnReturn1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/return.png")));
		btnReturn1.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/returnO.png")));
		btnReturn1.setBounds(505,20,110,50);
		btnReturn1.setContentAreaFilled(false);
		btnReturn1.setBorderPainted(false);
		teacooAdd.add(btnReturn1);
		btnReturn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.remove(teacooAdd);
				teacooAdd.setVisible(false);
				container.add(teacooList);
				teacooList.setVisible(true);
				container.revalidate();
				container.repaint();
		
			}
		});
		//添加题目的界面中各种textarea
		qid=new MultilineLabel1();
		qid.setBounds(175,55,90,30);
		teacooAdd.add(qid);
		optiona=new MultilineLabel1();
		optiona.setBounds(110,200,200,40);
		teacooAdd.add(optiona);
		optionb=new MultilineLabel1();
		optionb.setBounds(350,200,200,40);
		teacooAdd.add(optionb);
		optionc=new MultilineLabel1();
		optionc.setBounds(110,250,200,40);
		teacooAdd.add(optionc);
		optiond=new MultilineLabel1();
		optiond.setBounds(350,250,200,40);
		teacooAdd.add(optiond);
		stem=new MultilineLabel1();
        JScrollPane scr1 = new JScrollPane(stem);
        scr1.setBounds(115,100,435,80);
		scr1.setViewportView(stem);
		teacooAdd.add(scr1);
		analysis=new MultilineLabel1();
        JScrollPane scr2 = new JScrollPane(analysis);
        scr2.setBounds(150,310,400,80);
		scr2.setViewportView(analysis);
		teacooAdd.add(scr2);
		answer=new MultilineLabel1();
		answer.setBounds(140,415,80,30);
		teacooAdd.add(answer);
		qlevel=new MultilineLabel1();
        qlevel.setBounds(380,415,80,30);
        teacooAdd.add(qlevel);
		
      //为各种textarea注册监听器
		 class ButtonListener implements ActionListener,DocumentListener {
			public void actionPerformed(ActionEvent e) {
				}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Document doc=e.getDocument();
				if (doc==stem.getDocument()) Stem=stem.getText().toString();
				else if (doc==optiona.getDocument()) optionA=optiona.getText().toString();
				else if (doc==optionb.getDocument()) optionB=optiona.getText().toString();
				else if (doc==optionc.getDocument()) optionC=optiona.getText().toString();
				else if (doc==optiond.getDocument()) optionD=optiona.getText().toString();
				else if (doc==moptiona.getDocument()) moptionB=moptiona.getText().toString();
				else if (doc==moptionb.getDocument()) moptionA=moptionb.getText().toString();
				else if (doc==moptionc.getDocument()) moptionC=moptionc.getText().toString();
				else if (doc==moptiond.getDocument()) moptionD=moptiond.getText().toString();
				else if (doc==stem.getDocument()) Stem=stem.getText().toString();
				else if (doc==mstem.getDocument()) mStem=mstem.getText().toString();
				else if (doc==answer.getDocument()) Answer=answer.getText().toString();
				else if (doc==manswer.getDocument()) mAnswer=manswer.getText().toString();
				else if (doc==analysis.getDocument()) Analysis=analysis.getText().toString();
				else if (doc==manalysis.getDocument()) mAnalysis=manalysis.getText().toString();
				else if (doc==qlevel.getDocument()) level=qlevel.getText().toString();
				else if (doc==mqlevel.getDocument()) mlevel=mqlevel.getText().toString();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			}
		 class ButtonListener1 implements ActionListener,DocumentListener {
				public void actionPerformed(ActionEvent e) {
					}

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					Document doc=e.getDocument();
					if (doc==stem.getDocument()) Stem=stem.getText().toString();
					else if (doc==optiona.getDocument()) optionA=optiona.getText().toString();
					else if (doc==optionb.getDocument()) optionB=optiona.getText().toString();
					else if (doc==optionc.getDocument()) optionC=optiona.getText().toString();
					else if (doc==optiond.getDocument()) optionD=optiona.getText().toString();
					else if (doc==moptiona.getDocument()) moptionB=moptiona.getText().toString();
					else if (doc==moptionb.getDocument()) moptionA=moptionb.getText().toString();
					else if (doc==moptionc.getDocument()) moptionC=moptionc.getText().toString();
					else if (doc==moptiond.getDocument()) moptionD=moptiond.getText().toString();
					else if (doc==stem.getDocument()) Stem=stem.getText().toString();
					else if (doc==mstem.getDocument()) mStem=mstem.getText().toString();
					else if (doc==answer.getDocument()) Answer=answer.getText().toString();
					else if (doc==manswer.getDocument()) mAnswer=manswer.getText().toString();
					else if (doc==qlevel.getDocument()) level=qlevel.getText().toString();
					else if (doc==mqlevel.getDocument()) mlevel=mqlevel.getText().toString();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				}
		ButtonListener lis=new ButtonListener();
		//添加题目的输入框添加监听器
		stem.getDocument().addDocumentListener(lis);
		optiona.getDocument().addDocumentListener(lis);
		optionb.getDocument().addDocumentListener(lis);
		optionc.getDocument().addDocumentListener(lis);
		optiond.getDocument().addDocumentListener(lis);
		analysis.getDocument().addDocumentListener(lis);
		qlevel.getDocument().addDocumentListener(lis);
		answer.getDocument().addDocumentListener(lis);
		
		
		
		//修改题目的输入框添加监听器
		ButtonListener1 lis1=new ButtonListener1();
		mstem.getDocument().addDocumentListener(lis1);
		moptiona.getDocument().addDocumentListener(lis1);
		moptionb.getDocument().addDocumentListener(lis1);
		moptionc.getDocument().addDocumentListener(lis1);
		moptiond.getDocument().addDocumentListener(lis1);
		manalysis.getDocument().addDocumentListener(lis1);
		mqlevel.getDocument().addDocumentListener(lis1);
		manswer.getDocument().addDocumentListener(lis1);
		
        
		
        
        //题目类型
        comboBox1 = new JComboBox();
		comboBox1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		comboBox1.setForeground(Color.DARK_GRAY);
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"priciple of management", "financial management", "corporate governance"}));
		comboBox1.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboBox1));
		comboBox1.setOpaque(false);
		comboBox1.setBorder(null);
		comboBox1.setBounds(370, 55, 200, 23);
		teacooDetail.add(comboBox1);
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox1.getModel().getSelectedItem().toString().equals("priciple of management")) mQtype="Type1";
				if (comboBox1.getModel().getSelectedItem().toString().equals("financial management")) mQtype="Type2";
				if (comboBox1.getModel().getSelectedItem().toString().equals("corporate governance")) mQtype="Type3";
			}
		});
		
		
		comboBox2 = new JComboBox();
		comboBox2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		comboBox2.setForeground(Color.DARK_GRAY);
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"priciple of management", "financial management", "corporate governance"}));
		comboBox2.setUI((ComboBoxUI) MyComboBoxUI.createUI(comboBox2));
		comboBox2.setOpaque(false);
		comboBox2.setBorder(null);
		comboBox2.setBounds(370, 55, 200, 23);
		teacooAdd.add(comboBox2);
		
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox2.getModel().getSelectedItem().toString().equals("priciple of management")) Qtype="Type1";
				if (comboBox2.getModel().getSelectedItem().toString().equals("financial management")) Qtype="Type2";
				if (comboBox2.getModel().getSelectedItem().toString().equals( "corporate governance")) Qtype="Type3";
			}
		});
		
		
		UpdateDB u=new UpdateDB();
		Integer a=(u.findMaxNo()+1);
		qid.setText(a.toString());
		btnAdd1=new JButton();
		btnAdd1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/add1.png")));
		btnAdd1.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/add1O.png")));
		btnAdd1.setBounds(0,454,600,46);
		btnAdd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加新题的题号
		        UpdateDB ud=new UpdateDB();
				Integer a=(ud.findMaxNo()+1);//找到当前数据库中的最大题号，加1得到新题号
				QID=a.toString();
				InsertPop i=new InsertPop(QID);
				i.setVisible(true);
			}
		});
		btnAdd1.setContentAreaFilled(false);
		btnAdd1.setBorderPainted(false);
		teacooAdd.add(btnAdd1);
		
		btnModify=new JButton();
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				ModifyQPop m=new ModifyQPop(mQID);
				m.setVisible(true);
			}
		});
		btnModify.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/modify.png")));
		btnModify.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/modifyO.png")));
		btnModify.setBounds(200,454,200,46);
		btnModify.setContentAreaFilled(false);
		btnModify.setBorderPainted(false);
		teacooDetail.add(btnModify);
		
		btnReturn=new JButton();
		btnReturn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/return.png")));
		btnReturn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/returnO.png")));
		btnReturn.setBounds(505,20,110,50);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		teacooDetail.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.remove(teacooDetail);
				teacooDetail.setVisible(false);
				container.add(teacooList);
				teacooList.setVisible(true);
				container.revalidate();
				container.repaint();
		
			}
		});
		
		btnTry=new JButton();
		btnTry.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/try.png")));
		btnTry.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/ttryO.png")));
		btnTry.setBounds(0,454,200,46);
		btnTry.setContentAreaFilled(false);
		btnTry.setBorderPainted(false);
		teacooDetail.add(btnTry);
		
		
		btnDelete=new JButton();
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			DeleteQPop d=new DeleteQPop(mQID);
			d.setVisible(true);
			}
		});
		btnDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/delete.png")));
		btnDelete.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/deleteO.png")));
		btnDelete.setBounds(400,454,200,46);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorderPainted(false);
		teacooDetail.add(btnDelete);
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/managementMenu.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}

}
