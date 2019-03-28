package portal;

import java.awt.*;
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

import personal.PersonalPane;
import practice.*;
import report.*;
import collection.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class PortalPane extends JPanel {

	/**
	 * Create the panel.
	 */
	String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
private JPanel bottom;
private static Connection connection;
private static Statement statement;
private static PreparedStatement prestatment;

	
	private JPanel container;
	
	private JLabel reportBG;
	private JLabel practiceBG;
	private JLabel collectionBG;
	private JLabel donationBG;
	
	private JButton btnPractice;
	private JButton btnReport;
	private JButton btnCollection;
	private JButton btnDonation;
	private JLabel portrait;
	private JLabel portraitShade;

	public PortalPane(String userID) {
		this.userID = userID;
		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);
		String path=query(userID);
		container = new JPanel();
		container.setBounds(0, 0, 1000, 600);
		container.setOpaque(false);
		container.setLayout(null);
		
		reportBG=new JLabel();
		reportBG.setBounds(0, 0, 1000, 600);
		reportBG.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/reportBG.png")));
		container.add(reportBG);
		
		practiceBG=new JLabel();
		practiceBG.setBounds(0, 0, 1000, 600);
		practiceBG.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/practiceBG.png")));
		
		collectionBG=new JLabel();
		collectionBG.setBounds(0, 0, 1000, 600);
		collectionBG.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/collectionBG.png")));
		
		donationBG=new JLabel();
		donationBG.setBounds(0, 0, 1000, 600);
		donationBG.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/donationBG.png")));
		
		bottom = new JPanel();
		bottom.setBounds(0,550,1000,150);
		bottom.setLayout(null);
		bottom.setOpaque(false);
		add(container);
		add(bottom);
		

		portraitShade=new JLabel();
		portraitShade.setBounds(443,6,120,120);
		portraitShade.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/eclipse.png")));
		bottom.add(portraitShade);
		
		btnReport = new JButton();
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReport.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/report.png")));
		btnReport.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/reportP.png")));
		btnReport.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/reportO.png")));
		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				reportPane rPane = new reportPane(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(rPane);
				rPane.presentframe=presentframe;
				rPane.presentpanel=rPane;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(practiceBG);
				container.remove(collectionBG);
				container.remove(donationBG);
				container.revalidate();
				container.repaint();
				container.add(reportBG);
			}
		});
		btnReport.setBounds(0, 30, 250, 120);
		btnReport.setContentAreaFilled(false);
		btnReport.setBorderPainted(false);
		bottom.add(btnReport);
		
		btnPractice = new JButton();

		btnPractice.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/practice.png")));
		btnPractice.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/practiceP.png")));
		btnPractice.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/practiceO.png")));
		btnPractice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PracticeMenu pmenu = new PracticeMenu(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pmenu);
				pmenu.presentframe=presentframe;
				pmenu.presentpanel=pmenu;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(reportBG);
				container.remove(collectionBG);
				container.remove(donationBG);
				container.revalidate();
				container.repaint();
				container.add(practiceBG);
				
			}
		});
		btnPractice.setBounds(250,30, 250, 120);
		btnPractice.setContentAreaFilled(false);
		btnPractice.setBorderPainted(false);
		bottom.add(btnPractice);
		
		btnCollection = new JButton();
		btnCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCollection.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/collection.png")));
		btnCollection.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/collectionP.png")));
		btnCollection.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/collectionO.png")));
		btnCollection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CollectionMenu cmenu = new CollectionMenu(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(cmenu);
				cmenu.presentframe=presentframe;
				cmenu.presentpanel=cmenu;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(practiceBG);
				container.remove(reportBG);
				container.remove(donationBG);
				container.revalidate();
				container.repaint();
				container.add(collectionBG);
			}
		});
		btnCollection.setBounds(500, 30, 250, 120);
		btnCollection.setContentAreaFilled(false);
		btnCollection.setBorderPainted(false);
		bottom.add(btnCollection);
		
		btnDonation = new JButton();
		btnDonation.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/donation.png")));
		btnDonation.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/donationP.png")));
		btnDonation.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/portal/donationO.png")));
		btnDonation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				container.remove(practiceBG);
				container.remove(collectionBG);
				container.remove(reportBG);
				container.revalidate();
				container.repaint();
				container.add(donationBG);
			}
		});
		btnDonation.setBounds(750, 30,250, 120);
		btnDonation.setContentAreaFilled(false);
		btnDonation.setBorderPainted(false);
		bottom.add(btnDonation);
		
		
		
		portrait = new JLabel();
		portrait.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonalPane pPane = new PersonalPane(userID);
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(pPane);
				pPane.presentframe=presentframe;
				pPane.presentpanel=pPane;
			}
		});
		portrait.setBounds(450, 0, 100, 100);
		portrait.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(path)));
		bottom.add(portrait);
	
	}
	public static String query(String userID) {
		String a="";
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row
				String userid = rs.getString("UserID");
				if (userid.equals(userID)) {		
					a=rs.getString("Profile");		
					}
				}	
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}

	public static Connection getConnection() {
		Connection c = null;

		String ur1 = "jdbc:mysql://localhost/teacoo";
		String username = "root";
		String password = "root";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			c = DriverManager.getConnection(ur1, username, password);

		} catch (ClassNotFoundException cnfex) {
			System.out.println("Failed to load JDBC driver.");
			cnfex.printStackTrace();
			System.exit(1);
		} catch (SQLException sqlex) {
			System.err.println("Unable to connect");
			sqlex.printStackTrace();
		}
		return c;
	}
	public static void main(String[] args) throws SQLException {
		String userID = "10000";
		PortalPane pPanel = new PortalPane(userID);
		MainFrame pFrame = new MainFrame(userID);
		pFrame.setVisible(true);
		pFrame.getContentPane().add(pPanel);
		pPanel.presentframe = pFrame;

	}
}
