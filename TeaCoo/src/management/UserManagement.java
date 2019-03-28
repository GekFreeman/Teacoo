package management;

import java.awt.Graphics;



import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import portal.PortalPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import personal.Query;

public class UserManagement extends JPanel {
	public static String userID,userl;
	public JFrame presentframe;
	public JPanel presentpanel = this;

	private JPanel container;
	private JPanel userList;
	private JPanel userDetail;
	
	private JButton btnBack;
	private JButton btnReturn;
	private JButton btnXuser,btnMuser;
	
	private JLabel portrait;
	private JLabel portraitShade;
	private JTable usertable;
	
	private String path;
	
	private MultilineLabel userid,username,useremail,userhobby,usermajor,
	userphone,usergender,usergrade,useradv,usersig,userlevel;
	private MultilineLabel1 userauthority;
	public UserManagement() {

		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);
		
		//返回到管理菜单
		btnBack=new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
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
		
		
		userDetail=new JPanel(){protected void paintComponent(Graphics g) {
			
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/userdetail.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		userDetail.setBounds(200, 166, 610, 510);
		userDetail.setLayout(null);
		
		username=new MultilineLabel();
        username.setBounds(150,90,80,30);
        userDetail.add(username);
        userid=new MultilineLabel();
        userid.setBounds(150,150,80,30);
        userDetail.add(userid);
        useremail=new MultilineLabel();
        useremail.setBounds(150,200,150,30);
        userDetail.add(useremail);
        usermajor=new MultilineLabel();
        usermajor.setBounds(150,255,100,30);
        userDetail.add(usermajor);
        useradv=new MultilineLabel();
        useradv.setBounds(150,310,150,30);
        userDetail.add(useradv);
        userhobby=new MultilineLabel();
        userhobby.setBounds(150,375,150,30);
        userDetail.add(userhobby);
        usersig=new MultilineLabel();
        usersig.setBounds(150,430,150,40);
        userDetail.add(usersig);
        usergender=new MultilineLabel();
        usergender.setBounds(420,200,80,30);
        userDetail.add(usergender);
        userlevel=new MultilineLabel();
        userlevel.setBounds(420,250,80,30);
        userDetail.add(userlevel);
        userauthority=new MultilineLabel1();
        userauthority.setBounds(420,430,50,30);
        userDetail.add( userauthority);
        userphone=new MultilineLabel();
        userphone.setBounds(420,310,100,30);
        userDetail.add(userphone);
        usergrade=new MultilineLabel();
        usergrade.setBounds(420,370,80,30);
        userDetail.add(usergrade);
         // 将表加入JScroller中，信息多时显示滚动条
		GetData showuserdata = new GetData();
	    showuserdata.getConnection();
	    showuserdata.getUserTable();
	    validate();
		
	    usertable = new JTable(showuserdata.rows, showuserdata.columnHeads);
		usertable.setBounds(new Rectangle(35, 75, 458, 373));
		//usertable.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14)); // 表头字体

		usertable.getTableHeader().setBackground(
				Color.getHSBColor(111, 139, 120)); // 表头颜色
		usertable.getTableHeader().setBorder(null);
		usertable.getTableHeader().setPreferredSize(new Dimension(1, 35));
		// 设置表头高度

		//usertable.setFont(new Font("Century Gothic", Font.PLAIN, 14)); // 设置单元格字体

		usertable.setRowHeight(35); // 设置行高

		// 单元格居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		usertable.setDefaultRenderer(Object.class, r);

		// 固定列宽（最大值和最小值一样）
		usertable.getColumnModel().getColumn(0).setPreferredWidth(150);
		usertable.getColumnModel().getColumn(0).setMaxWidth(150);
		usertable.getColumnModel().getColumn(0).setMinWidth(150);

		usertable.getColumnModel().getColumn(1).setPreferredWidth(150);
		usertable.getColumnModel().getColumn(1).setMaxWidth(150);
		usertable.getColumnModel().getColumn(1).setMinWidth(150);

		usertable.getColumnModel().getColumn(2).setPreferredWidth(150);
		usertable.getColumnModel().getColumn(2).setMaxWidth(150);
		usertable.getColumnModel().getColumn(2).setMinWidth(150);
		
		usertable.getColumnModel().getColumn(3).setPreferredWidth(150);
		usertable.getColumnModel().getColumn(3).setMaxWidth(150);
		usertable.getColumnModel().getColumn(3).setMinWidth(150);
		
		JScrollPane scroller = new JScrollPane(usertable);
		scroller.setBounds(0, 42, 600, 400);
		userList = new JPanel() {
			protected void paintComponent(Graphics g) {

				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/management/userlist.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		userList.setBounds(200, 166, 610, 510);
			userList.setLayout(null);
			container.add(userList);
			userList.setVisible(true);
			userList.add(scroller);
			scroller.setVisible(true);
	
		
	    
	    //用户详情	
	    usertable.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {   
				int r = usertable.getSelectedRow();
				int c = usertable.getSelectedColumn();
				if(c==3){//点击列表的最后一列中任何一格，弹出用户详情的Panel
                    userID= usertable.getValueAt(r, 0).toString();
					showuserdata.getConnection();
					userid.setText(usertable.getValueAt(r, 0).toString());
					username.setText(usertable.getValueAt(r, 1).toString());
					usergender.setText(usertable.getValueAt(r, 2).toString());
					userlevel.setText(showuserdata.getULevel(usertable.getValueAt(r, 0).toString()));
					usermajor.setText(usertable.getValueAt(r, 3).toString());
					useremail.setText(showuserdata.getEmail(usertable.getValueAt(r, 0).toString()));
                    userphone.setText(showuserdata.getPhone(usertable.getValueAt(r, 0).toString()));
                    usergrade.setText(showuserdata.getGrade(usertable.getValueAt(r, 0).toString()));
                    userhobby.setText(showuserdata.getHobby(usertable.getValueAt(r, 0).toString()));
                    useradv.setText(showuserdata.getPhone(usertable.getValueAt(r, 0).toString()));
                    usersig.setText(showuserdata.getSig(usertable.getValueAt(r, 0).toString()));
                    userauthority.setText(showuserdata.getAuthority(usertable.getValueAt(r, 0).toString()));
                    portraitShade=new JLabel();
            		portraitShade.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/portraitshade.png")));
            		portraitShade.setBounds(340,60,120,120);
            		userDetail.add(portraitShade);
            		
            		path=Query.query_path(userID);
            		portrait=new JLabel();
            		portrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(path)));
            		portrait.setBounds(350,70,100,100);
            		userDetail.add(portrait);
                    
                    showuserdata.shutDown();
                    //弹出用户详情
					container.remove(userList);
					userList.setVisible(false);
					container.add(userDetail);
					userDetail.setVisible(true);
					container.revalidate();
					container.repaint();
			
				}}});
		
		 
         //为userlevel注册监听器
 		 class ButtonListener implements ActionListener,DocumentListener {
 			public void actionPerformed(ActionEvent e) {
 				}

 			@Override
 			public void changedUpdate(DocumentEvent arg0) {
 				
 			}

 			@Override
 			public void insertUpdate(DocumentEvent e) {
 				Document doc=e.getDocument();
 				if (doc==userauthority.getDocument()) 
 					userl=userauthority.getText().toString();
 			}

 			@Override
 			public void removeUpdate(DocumentEvent arg0) {
 				// TODO Auto-generated method stub
 				
 			}
 			}
 		ButtonListener lis=new ButtonListener();
		userauthority.getDocument().addDocumentListener(lis);
         
		btnXuser=new JButton();
		btnXuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteUserPop m=new DeleteUserPop(userID);//弹出deleteUserPop弹窗
				m.setVisible(true);
			}
		});
		//删除用户
		btnXuser.setBounds(550, 450, 50, 50);
		btnXuser.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/Xuser.png")));
		btnXuser.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/XuserO.png")));
		btnXuser.setContentAreaFilled(false);
		btnXuser.setBorderPainted(false);
		userDetail.add(btnXuser);
		
		//修改用户信息（仅限等级修改以便开放用户申请修改题库）
		btnMuser=new JButton();
		btnMuser.setBounds(500, 450, 50, 50);
		btnMuser.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/Muser.png")));
		btnMuser.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/MuserO.png")));
		btnMuser.setContentAreaFilled(false);
		btnMuser.setBorderPainted(false);
		userDetail.add(btnMuser);
		btnMuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyUserPop m=new ModifyUserPop(userID);
				m.setVisible(true);
			}
		});
		
//		portraitShade=new JLabel();
//		portraitShade.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/portraitshade.png")));
//		portraitShade.setBounds(340,60,120,120);
//		userDetail.add(portraitShade);
//		
//		path=Query.query_path(userID);
//		portrait=new JLabel();
//		portrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(path)));
//		portrait.setBounds(350,70,100,100);
//		userDetail.add(portrait);
		
		btnReturn=new JButton();																																																													
		btnReturn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/return.png")));
		btnReturn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/returnO.png")));
		btnReturn.setBounds(505,20,110,50);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		userDetail.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				container.remove(userDetail);
				userDetail.setVisible(false);
				container.add(userList);
				userList.setVisible(true);
				container.revalidate();
				container.repaint();
			}
		});
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/managementMenu.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
	
}
	


