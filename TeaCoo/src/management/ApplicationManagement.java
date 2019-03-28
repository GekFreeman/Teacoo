package management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApplicationManagement extends JPanel {
	public static String  userID;
	public static String applyID;
	public JFrame presentframe;
	public JPanel presentpanel = this;

	private JPanel container;
	
	private JPanel applicationList;
	private JPanel applicationDetail;
	
	private JButton btnBack;
	private JButton btnReturn;
	private JButton btnTry;
	private JButton btnAccept;
	private JButton btnRefuse;
	private JTable applytable;
	private MultilineLabel1 userid,userlevel,content,analysis,answer,qlevel;
	

	/**
	 * Create the panel.
	 */
	public ApplicationManagement() {
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
		
		applicationList=new JPanel(){protected void paintComponent(Graphics g) {
			ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/applicationlist.png"));
			Image img = BG.getImage();
			g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
		}};
		applicationList.setBounds(200, 166, 610, 510);
		applicationList.setLayout(null);
		container.add(applicationList);
		applicationList.setVisible(true);
		
		//由申请列表切换到单个申请具体，图片在文件夹里自寻。。。
				applicationDetail=new JPanel(){protected void paintComponent(Graphics g) {
					ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/applicationdetail.png"));
					Image img = BG.getImage();
					g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
				}};
				applicationDetail.setBounds(200, 166, 610, 510);
				applicationDetail.setLayout(null);
				//container.add(applicationDetail);
				
		
		userid=new MultilineLabel1();
        userid.setBounds(150,55,80,30);
        applicationDetail.add(userid);
        userlevel=new MultilineLabel1();
        userlevel.setBounds(420,55,80,30);
        applicationDetail.add(userlevel);
        answer=new MultilineLabel1();
        answer.setBounds(150,410,80,30);
        applicationDetail.add(answer);
        qlevel=new MultilineLabel1();
        qlevel.setBounds(420,410,80,30);
        applicationDetail.add(qlevel);
        
        content=new MultilineLabel1();
        JScrollPane scr1 = new JScrollPane(content);
        scr1.setBounds(150,110,380,180);
		scr1.setViewportView(content);
		applicationDetail.add(scr1);
		
		analysis=new MultilineLabel1();
        JScrollPane scr2 = new JScrollPane(analysis);
        scr2.setBounds(150,320,380,80);
		scr2.setViewportView(analysis);
		applicationDetail.add(scr2);
		
		//添加用户申请列表
		GetDataFromDB showapplydata = new GetDataFromDB();
		showapplydata.getConnection();
		showapplydata.getTable();
	    validate();
	    applytable = new JTable(showapplydata.rows, showapplydata.columnHeads);
	    applytable.setBounds(new Rectangle(35, 75, 458, 373));
	    JScrollPane scr = new JScrollPane(applytable);
	    scr.setBounds(0, 42, 600,400);
	    applytable.setVisible(true);
		scr.setVisible(true);
		applicationList.add(scr);

		// applytable.getTableHeader().setFont(new Font("Century Gothic",
		// Font.BOLD, 14)); // 表头字体

		applytable.getTableHeader().setBackground(
				Color.getHSBColor(111, 139, 120)); // 表头颜色
		applytable.getTableHeader().setBorder(null);
		applytable.getTableHeader().setPreferredSize(new Dimension(1, 35));
		// 设置表头高度

		// applytable.setFont(new Font("Century Gothic", Font.PLAIN, 14)); //
		// 设置单元格字体

		applytable.setRowHeight(35); // 设置行高

		// 单元格居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		applytable.setDefaultRenderer(Object.class, r);

		// 固定列宽（最大值和最小值一样）
		applytable.getColumnModel().getColumn(0).setPreferredWidth(120);
		applytable.getColumnModel().getColumn(0).setMaxWidth(120);
		applytable.getColumnModel().getColumn(0).setMinWidth(120);

		applytable.getColumnModel().getColumn(1).setPreferredWidth(120);
		applytable.getColumnModel().getColumn(1).setMaxWidth(120);
		applytable.getColumnModel().getColumn(1).setMinWidth(120);

		applytable.getColumnModel().getColumn(2).setPreferredWidth(120);
		applytable.getColumnModel().getColumn(2).setMaxWidth(120);
		applytable.getColumnModel().getColumn(2).setMinWidth(120);

		applytable.getColumnModel().getColumn(3).setPreferredWidth(120);
		applytable.getColumnModel().getColumn(3).setMaxWidth(120);
		applytable.getColumnModel().getColumn(3).setMinWidth(120);

		applytable.getColumnModel().getColumn(4).setPreferredWidth(120);
		applytable.getColumnModel().getColumn(4).setMaxWidth(120);
		applytable.getColumnModel().getColumn(4).setMinWidth(120);

		applytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int r = applytable.getSelectedRow();
				int c = applytable.getSelectedColumn();
				if (c == 4) {
					showapplydata.getConnection();
					applyID = applytable.getValueAt(r, 0).toString();
					userID = applytable.getValueAt(r, 1).toString();
					content.setText(showapplydata.getText(applyID));// 表中的题干加选项
					answer.setText(showapplydata.getAnswer(applytable
							.getValueAt(r, 0).toString()));
					analysis.setText(showapplydata.getAnalysis(applytable
							.getValueAt(r, 0).toString()));
					qlevel.setText(showapplydata.getQtype(applyID));
					userid.setText(userID);
					userlevel.setText(showapplydata.getUserLevel(applytable
							.getValueAt(r, 1).toString()));
					showapplydata.shutDown();

					container.remove(applicationList);
					applicationList.setVisible(false);
					container.add(applicationDetail);
					applicationDetail.setVisible(true);
					container.revalidate();
					container.repaint();

				}
			}
		});

		btnReturn = new JButton();
		btnReturn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/management/return.png")));
		btnReturn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/management/returnO.png")));
		btnReturn.setBounds(505, 20, 110, 50);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setBorderPainted(false);
		applicationDetail.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				container.remove(applicationDetail);
				applicationDetail.setVisible(false);
				container.add(applicationList);
				applicationList.setVisible(true);
				container.revalidate();
				container.repaint();
			}
		});

		btnTry = new JButton();
		btnTry.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/management/try.png")));
		btnTry.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/management/tryO.png")));
		btnTry.setBounds(0, 454, 200, 46);
		btnTry.setContentAreaFilled(false);
		btnTry.setBorderPainted(false);
		applicationDetail.add(btnTry);

		btnAccept = new JButton();
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcceptPop a = new AcceptPop(applyID);
				a.setVisible(true);
			}
		});
		btnAccept.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/management/accept.png")));
		btnAccept.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/management/acceptO.png")));
		btnAccept.setBounds(200, 454, 200, 46);
		btnAccept.setContentAreaFilled(false);
		btnAccept.setBorderPainted(false);
		applicationDetail.add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcceptPop r = new AcceptPop(applyID);
				r.setVisible(true);
			}
		});

		btnRefuse = new JButton();
		btnRefuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefusePop r = new RefusePop(applyID);
				r.setVisible(true);
	}
		});
		btnRefuse.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/refuse.png")));
		btnRefuse.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/refuseO.png")));
		btnRefuse.setBounds(400,454,200,46);
		btnRefuse.setContentAreaFilled(false);
		btnRefuse.setBorderPainted(false);
		applicationDetail.add(btnRefuse);
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/management/managementMenu.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(), BG.getImageObserver());
	}
	
}
