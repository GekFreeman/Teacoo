package login;


import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JPanel;
import javax.swing.JRootPane;


public class TipsPop extends JFrame {

	private static final long serialVersionUID = 1L;
	

	private JButton btnX;
	private JButton btnMove;
	public JButton btnOK;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;
	
	private JPanel container;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TipsPop frame = new TipsPop();
					frame.showLabel.setText("Your question has been inserted into your star collection!");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	int condition;
	public TipsPop(int condition) {
		
		this.condition=condition;
		
		setResizable(false);
		// 去掉窗口的装饰
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(650, 400, 300, 150);
	    setBackground(new Color(0,0,0,0));
		
	    if(condition==1){
		container=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/enterPop.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		container.setBounds(0, 0, 300, 150);
		container.setLayout(null);
		getContentPane().add(container);}
	    else if(condition==2){
	    	container=new JPanel(){protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/wrongpw.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
			}};
			container.setBounds(0, 0, 300, 150);
			container.setLayout(null);
			getContentPane().add(container);
	    }
	    else
	    {
	    	container=new JPanel(){protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/accountPop.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
			}};
			container.setBounds(0, 0, 300, 150);
			container.setLayout(null);
			getContentPane().add(container);
	    }
		btnX = new JButton();
		btnX.setBounds(250, 0, 36, 35);
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
		btnMove.setBounds(0, 0, 250, 35);
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
		
		btnOK=new JButton();
		btnOK.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/confirm.png")));
		btnOK.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/login/confirmO.png")));
		btnOK.setContentAreaFilled(false);
		btnOK.setBorderPainted(false);
		btnOK.setBounds(80, 100, 100, 35);
		container.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
	}
}

