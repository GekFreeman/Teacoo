package management;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import portal.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPane extends JPanel {
	//String userID;
	public JFrame presentframe;
	public JPanel presentpanel = this;
	
	private JPanel container;
	
	private JButton userM;
	private JButton appM;
	private JButton teacooM;
	/**
	 * Create the panel.
	 */
	public AdminPane() {
		//this.userID=userID;
		
		setVisible(true);
		setBounds(0, 0, 1000, 700);
		setLayout(null);

		container = new JPanel();
		container.setBounds(0, 0, 1000, 700);
		container.setOpaque(false);
		add(container);
		container.setLayout(null);
		
		userM=new JButton();
		userM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			UserManagement uPane = new UserManagement();
			presentpanel.setVisible(false);
			presentframe.getContentPane().add(uPane);
			uPane.presentframe=presentframe;
			uPane.presentpanel=uPane;
			}
		});
		//当鼠标放到按钮上时，按钮的图片发生替换，颜色变亮（所有按钮均采用此方法）
		userM.setBounds(356, 143, 300, 520);
		userM.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage
				("image/management/usermanagement.png")));
	    userM.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage
	    		("image/management/usermanagementO.png")));
		userM.setContentAreaFilled(false);
		userM.setBorderPainted(false);
		container.add(userM);
		
		appM=new JButton();
		appM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ApplicationManagement aPane = new ApplicationManagement();
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(aPane);
				aPane.presentframe=presentframe;
				aPane.presentpanel=aPane;
			}
		});
		appM.setBounds(37, 158, 300, 520);
		appM.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/applymanagement.png")));
	    appM.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/applymanagementO.png")));
		appM.setContentAreaFilled(false);
		appM.setBorderPainted(false);
		container.add(appM);

		teacooM=new JButton();
		teacooM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TeaCooManagement tPane = new TeaCooManagement();
				presentpanel.setVisible(false);
				presentframe.getContentPane().add(tPane);
				tPane.presentframe=presentframe;
				tPane.presentpanel=tPane;
			}
		});
		teacooM.setBounds(679, 170, 300, 520);
		teacooM.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/teacoomanagement.png")));
	    teacooM.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/teacoomanagementO.png")));
		teacooM.setContentAreaFilled(false);
		teacooM.setBorderPainted(false);
		container.add(teacooM);

	}

	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/managementMenu.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
	
	public static void main(String[] args) throws SQLException {
		String userID = "10000";
		AdminPane pPanel = new AdminPane();
		MainFrame pFrame = new MainFrame(userID);
		pFrame.setVisible(true);
		pFrame.getContentPane().add(pPanel);
		pPanel.presentframe = pFrame;
	}

	
}
