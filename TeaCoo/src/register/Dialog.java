package register;

import java.awt.BorderLayout;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class Dialog extends JFrame {

	private JButton btnX;
	private JButton btnMove;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;
	private JPanel container;
	public String userID;
	JLabel label= new JLabel("New label",JLabel.CENTER);
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Dialog(int n) {
		
		setResizable(false);
		// 去掉窗口的装饰
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(950, 300, 300, 200);
		
		container=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/pop300200.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		container.setBounds(0, 0, 300, 200);
		container.setLayout(null);
		getContentPane().add(container);
		
		
		label.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		label.setBounds(11, 76, 280, 21);
		container.add(label);
		
		btnX = new JButton();
		btnX.setBounds(255, 0, 36, 35);
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
		btnMove.setBounds(0, 0, 760, 35);
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
		
		JButton btnOK=new JButton();
		btnOK.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/confirm.png")));
		btnOK.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/confirmO.png")));
		btnOK.setContentAreaFilled(false);
		btnOK.setBorderPainted(false);
		btnOK.setBounds(100, 134, 100, 35);
		container.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	

	
	
	if(n==1){
		label.setText("Please enter username");
	}
	
	if(n==2){
		label.setText("Please enter password");
	}
	
	if(n==3){
		label.setText("Password is too short");
	}
	
	if(n==4){
		label.setText("Password is too weak");
	}
	
	if(n==5){
		label.setText("Please re-enter password");
	}
	
	if(n==6){
		label.setText("Please enter your phone number");
	}
	
	if(n==7){
		label.setText("Please enter your e-mail");
	}
	
	if(n==8){
		label.setText("Please choose an e-mail suffix");
	}
	
	if(n==9){
		label.setText("Please choose a security problem");
	}
	
	if(n==10){
		label.setText("Please enter the answer");
	}
	
	if(n==11){
		label.setText("Passwords wrong");
	}
	
//	if(n==12){
//		label.setText("registration completed.UserID:"+RegistFrame.userID);
//	}
//	
	if(n==13){
		label.setText("username contains illegal character");
	}
	
	if(n==14){
		label.setText("Please enter verification code");
	}
	
	if(n==15){
		label.setText("UserID doesn't exist");
	}
	
	if(n==16){
		label.setText("Answer Wrong");
	}
	
	if(n==17){
		label.setText("Completed!");
	}
	}
	public void complete(){
		label.setText("registration completed.UserID:"+userID);
	}

}

