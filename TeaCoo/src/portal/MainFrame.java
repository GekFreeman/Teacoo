package portal;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
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

public class MainFrame extends JFrame {

	String userID;
	JFrame presentframe;
	private JButton btnX;
	private JButton btnMove;
	private boolean isDragged = false;
	private Point loc = null;
	private Point tmp = null;
	AePlayWave p;
	private JButton btnPlay;
	private JButton btnStop;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainFrame(String userID) {
		this.userID=userID;

		setResizable(false);
		// 去掉窗口的装饰
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setBounds(300, 50, 1000, 700);
		
		btnPlay = new JButton();
		btnPlay.setBounds(869, 0, 36, 35);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/btnPlay.png")));
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPlay.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/btnPlayO.png")));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				p = new AePlayWave("voice/BGM.wav");
				p.start();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnPlay);
		
		
		btnStop = new JButton();
		btnStop.setBounds(914, 0, 36, 35);
		btnStop.setContentAreaFilled(false);
		btnStop.setBorderPainted(false);
		btnStop.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/btnStop.png")));
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStop.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/btnStopO.png")));
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				p.stop();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnStop);
		
		btnX = new JButton();
		btnX.setBounds(960, 0, 36, 35);
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
				System.exit(0);
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnX);

		btnMove = new JButton();
		btnMove.setBounds(200, 0, 664, 35);
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
		getContentPane().add(btnMove);
	}

}
