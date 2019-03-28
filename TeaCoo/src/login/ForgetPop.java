package login;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import management.DeletePop;

public class ForgetPop extends JFrame {
	static String userID;
	
	private JButton btnX;
	private JButton btnMove;
	private JButton btnQuestion;
	private JButton btnMessage;
	private JButton btnCurrent;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;
	
	private JPanel container;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPop frame = new ForgetPop(userID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgetPop(String userID) {
		this.userID=userID;
	setResizable(false);
	// 去掉窗口的装饰
	setUndecorated(true);
	getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	setBounds(950, 300, 400, 300);
	
	container=new JPanel(){protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/forgetPop.png"));
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
	
	btnQuestion=new JButton();
	btnQuestion.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			QuestionPop q=new QuestionPop(userID);
			q.setVisible(true);
			setVisible(false);
		}
	});
	btnQuestion.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/fquestion.png")));
	btnQuestion.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/fquestionO.png")));
	btnQuestion.setContentAreaFilled(false);
	btnQuestion.setBorderPainted(false);
	btnQuestion.setBounds(79, 141, 250, 35);
	container.add(btnQuestion);
	
	btnMessage=new JButton();
	btnMessage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/fmessage.png")));
	btnMessage.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/fmessageO.png")));
	btnMessage.setContentAreaFilled(false);
	btnMessage.setBorderPainted(false);
	btnMessage.setBounds(79, 191, 250, 35);
	container.add(btnMessage);
	
	btnCurrent=new JButton();
	btnCurrent.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/fpassword.png")));
	btnCurrent.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/fpasswordO.png")));
	btnCurrent.setContentAreaFilled(false);
	btnCurrent.setBorderPainted(false);
	btnCurrent.setBounds(79, 241, 250, 35);
	container.add(btnCurrent);
	}
}
