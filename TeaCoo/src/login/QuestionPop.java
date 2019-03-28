package login;




import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComboBoxUI;

import register.*;
import login.CustomizedJComboBox.MyComboBoxUI;

public class QuestionPop extends JFrame {
	static String userID;
	
	private JButton btnX;
	private JButton btnMove;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;
	private JButton btnConfirm;
	private JButton btnReset;
	private JComboBox questionBox;
	
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	//变量
	private static String username;
	private static String question;
	private static String  answer;
	private static String question_1;
	private static String  answer_1;
	private static String password;
	//密码强度
	private int n;
	//判断用户名是否合法
	boolean t=true;
	
	//连接数据库
		private static Connection connection;
		private static Statement statement;
		private static PreparedStatement prestatment;
		private static boolean exist=true;
	
	private JPanel container;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionPop frame = new QuestionPop(userID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public QuestionPop(String userID) {
		this.userID=userID;
		
		setResizable(false);
		// 去掉窗口的装饰
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(950, 300, 400, 300);
		
		container=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/questionPop.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		container.setBounds(0, 0, 400, 300);
		container.setLayout(null);
		getContentPane().add(container);
		
		btnX = new JButton();
		btnX.setBounds(360, 0, 36, 35);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/btnX.png")));
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnX.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/btnXO.png")));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		
		container.add(btnX);

		btnMove = new JButton();
		btnMove.setBounds(0, 0, 360, 35);
		btnMove.setContentAreaFilled(false);
		btnMove.setBorderPainted(false);
		btnMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isDragged = false;
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 拖动event
			@Override
			public void mousePressed(MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
				setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});

		btnMove.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(getLocation().x + e.getX() - tmp.x, getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
		container.add(btnMove);
		
		questionBox=new JComboBox();
		questionBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		questionBox.setModel(new DefaultComboBoxModel(new String[] {"what`s your mother's name?","what's your father's name?", "what's your student ID?"}));
		questionBox.setUI((ComboBoxUI) MyComboBoxUI.createUI(questionBox));
		questionBox.setOpaque(false);
		questionBox.setEditable(true);
		questionBox.setBorder(new EmptyBorder(0, 0, 0, 0));
		questionBox.setBounds(138, 118, 200, 23);
		container.add(questionBox);
		
		//UserID
		textField = new JTextField();
		textField.setBorder(null);
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				textField.setBounds(138, 78, 177, 23);
				container.add(textField);
				textField.setColumns(10);
				
				//答案
				textField_1 = new JTextField();
				textField_1.setBorder(null);
				textField_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				textField_1.setBounds(138, 146, 177, 23);
				container.add(textField_1);
				textField_1.setColumns(10);
				
				//密码强度
				JLabel lblStrength = new JLabel("");
				lblStrength.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				container.add(lblStrength);
				lblStrength.setBounds(280, 181, 54, 24);
				
				//新密码
				passwordField = new JPasswordField();
				passwordField.setBorder(null);
				passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				container.add(passwordField);
				passwordField.setBounds(176, 184, 94, 21);
				passwordField.addKeyListener(new KeyAdapter()
				{
					public void keyPressed(KeyEvent e){
						n=0;
						for(int i=1;i<=passwordField.getText().length();i++){
							n+=Password(passwordField.getText().charAt(i-1));
						}
						if(n<15){
							lblStrength.setBackground(Color.RED);
							lblStrength.setBackground(Color.WHITE);
							lblStrength.setBackground(Color.WHITE);
						    lblStrength.setText("weak");
						}
						else if(n<30){
							lblStrength.setBackground(Color.RED);
							lblStrength.setBackground(Color.ORANGE);
							lblStrength.setBackground(Color.WHITE);
							lblStrength.setText("medium");
						}
						else if(n>=45){
							lblStrength.setBackground(Color.RED);
							lblStrength.setBackground(Color.ORANGE);
							lblStrength.setBackground(Color.GREEN);
							lblStrength.setText("strong");
						}
					}
				});
				
				//密码确认
				JLabel lblMatch = new JLabel("");
				lblMatch.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				lblMatch.setBounds(280, 210, 80, 23);
				container.add(lblMatch);
				
				//确认密码
				passwordField_1 = new JPasswordField();
				passwordField_1.setBorder(null);
				passwordField_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				passwordField_1.setBounds(176, 210, 94, 21);
				container.add(passwordField_1);
				passwordField_1.addFocusListener(new FocusListener(){
					public void focusLost(FocusEvent e){
						if(!passwordField_1.getText().equals(passwordField.getText()))
							lblMatch.setText("mismatch");
						if(passwordField_1.getText().equals(passwordField.getText()))
							lblMatch.setText("");
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						lblMatch.setText("");
					}
				});
				
				
				
				
				btnConfirm=new JButton();
				btnConfirm.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						QuestionPop q=new QuestionPop(userID);
					}
				});
				btnConfirm.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/confirm.png")));
				btnConfirm.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/confirmO.png")));
				btnConfirm.setContentAreaFilled(false);
				btnConfirm.setBorderPainted(false);
				btnConfirm.setBounds(96, 255, 100, 35);
				container.add(btnConfirm);
				btnConfirm.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
							
						//用户名
						if(textField.getText().equals("")){
							Dialog d=new Dialog(1);
							d.setVisible(true);
							return; 
						}
						
						//用户名不合法
						if(t==false){
							Dialog d=new Dialog(13);
							d.setVisible(true);
							return;
						}
						
						//选择密保问题
						if(questionBox.getSelectedItem()==null){
							Dialog d=new Dialog(9);
							d.setVisible(true);
							return;
						}
						
						//密保答案
						if(textField_1.getText().equals("")){
							Dialog d=new Dialog(10);
							d.setVisible(true);
							return;
						}
						
						//密码
						if(passwordField.getPassword().length==0){
							Dialog d=new Dialog(2);
							d.setVisible(true);
							return;
						}
						
						//密码长度
						if(passwordField.getPassword().length<6){
							Dialog d=new Dialog(3);
							d.setVisible(true);
							return;
						}
						
						//确认密码
						if(passwordField_1.getPassword().length==0){
							Dialog d=new Dialog(5);
							d.setVisible(true);
							return;
						}
						
						if(!passwordField.getText().equals(passwordField_1.getText())){
							Dialog d=new Dialog(11);
							d.setVisible(true);
							return;
						}
						
						else{
							username=textField.getText();
							question=(String)questionBox.getSelectedItem();
							answer=textField_1.getText();
							password=passwordField.getText();
							exist=true;
							query();
							if(exist==false){
								Dialog d=new Dialog(15);
								d.setVisible(true);
							}
							else{
								if(answer_1.equals(answer) && question_1.equals(question)){
							         update();
							         Dialog d=new Dialog(17);
							         d.setVisible(true);
							         setVisible(false);
								}
								else{
									Dialog d=new Dialog(16);
									d.setVisible(true);
									System.out.print(answer_1+"   "+answer+"   "+question+"    "+question_1);
								}
							}
						}
					}
				});
				
				btnReset=new JButton();
				btnReset.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/reset.png")));
				btnReset.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/resetO.png")));
				btnReset.setContentAreaFilled(false);
				btnReset.setBorderPainted(false);
				btnReset.setBounds(206, 255, 100, 35);
				container.add(btnReset);
				btnReset.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						textField.setText("");
					    textField_1.setText("");
					    questionBox.setSelectedItem(null);
					    passwordField.setText("");
					    passwordField_1.setText("");
					    lblMatch.setText("");
					    lblStrength.setText("");
					}
				});
				
				
	}
	//密码强度加权
	public int Password(char a){
    	int b=a;
    	if(b>=48 && b<=57)
    		return 1;
    	if(b>=97 && b<=122)
    		return 2;
    	if(b>=65 && b<=90)
    		return 4;
    	else return 8;
    }
	
public static void query() {  //查询
        
        connection = getConnection(); //connect to database
        
        try {  
        	// select statement   
        	 prestatment = connection.prepareStatement( "select SecureQuestion,SecureAnswer from userinfo  where UserID=? ");
        	 prestatment.setString(1, username);
        	 ResultSet rs = prestatment.executeQuery();
    
             System.out.println("Query results are as follow");  
             rs.next();
             answer_1= rs.getString("SecureAnswer"); 
             question_1 = rs.getString("SecureQuestion");
             System.out.println(answer+question);   
              
            
            connection.close();   
              
        } catch (SQLException e) {  
            System.out.println("Query failed" + e.getMessage());  
            System.out.println("bucunzaiya");
            exist=false;
            return;
        }  
    }  
	
	 public static void update() {  //修改
	        connection = getConnection(); 
	        try {  
	            prestatment = connection.prepareStatement("update userinfo set Password =? where UserID= ?");
	            prestatment.setString(1,password);
	            prestatment.setString(2,username);
	            int count = prestatment.executeUpdate();
	              
	            System.out.println("update " + count + " records in departments");      	  
	            
	            connection.close();   
	              
	        } catch (SQLException e) {  
	            System.out.println("Update failed " + e.getMessage());  
	        }  
	    }  
	
	public static Connection getConnection()//连接
	{
		Connection c = null;
		
		String ur1="jdbc:mysql://localhost/teacoo";
		String username="root";
		String password="root";
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			c = DriverManager.getConnection(ur1,username,password);
			System.out.println("Connect Success");
			
		}
		catch(ClassNotFoundException cnfex){
			System.out.println("Failed to load JDBC driver.");
			cnfex.printStackTrace();
			System.exit(1);
		}catch(SQLException sqlex){
			System.err.println("Unable to connect");
			sqlex.printStackTrace();
		}
		return c;
	}
}
		


