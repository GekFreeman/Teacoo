package check;
import practice.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TablePane extends JPanel {
	String UserID;
	/**
	 * Create the panel.
	 */
	Questions zuo[];
	JFrame presentframe;// 用于panel切换
	JPanel presentpanel = this;

	private static final long serialVersionUID = 1L;
	public static JTable table;
	public JButton btn;
	public JScrollPane scroll;
	int headHeight = 25;
	String sql = null;
	public int num;

	// 用table来展示题目的相关信息
	public TablePane(Questions[] zuoti, String userID,int length) {
		this.num=length;
		this.zuo = zuoti;
		setBounds(0, 0, 1000, 700);// 设置panel的大小和位置
		initTable(zuoti, userID);// 绘制表格
		setLayout(null);
		setVisible(true);

		JPanel checkList = new JPanel() {// 用于展示Table的panel
			protected void paintComponent(Graphics g) {
				ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage("image/check/checklist.png"));
				Image img = BG.getImage();
				g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
						BG.getImageObserver());
			}
		};
		checkList.setBounds(203, 31, 610, 560);
		checkList.setOpaque(false);
		add(checkList);
		checkList.setLayout(null);

		scroll = new JScrollPane();
		scroll.setBounds(5, 136, 589, 324);// 设置Scroll的大小
		scroll.setViewportView(table);
		checkList.add(scroll);

		// 返回上一个界面的按钮
		JButton btnBack = new JButton();
		btnBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/back.png")));
		btnBack.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("image/backO.png")));
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Report cPane = new Report(zuo, userID,num);
				presentpanel.setVisible(false);
				cPane.setVisible(true);
				presentframe.getContentPane().add(cPane);
				cPane.presentframe = presentframe;
				cPane.presentpanel = cPane;
			}
		});
		btnBack.setBounds(0, 0, 121, 42);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		add(btnBack);

	}

	// 绘制Table
	public void initTable(Questions[] zuoti, String userID) {
		int i = 1;
		String[] headNames = new String[] { "No.", "Question", "Selected",
				"Correct", "Details" };// Table的首行标题
		Object[][] tableDatas = new Object[num][5];// 设置列数
		for (; i <= num; i++)// 将zuoti中的信息传递给tableDatas
		{
			tableDatas[i-1 ][0] = i;
			tableDatas[i-1 ][1] = zuoti[i].MainQuestion;
			tableDatas[i-1 ][2] = zuoti[i].userAnswer;
			tableDatas[i-1 ][3] = zuoti[i].Answer;
			tableDatas[i-1 ][4] = null;
		}

		table = new JTable(new MyTableModel(headNames, tableDatas));// 画出表格的基本结构
		table.getTableHeader().setFont(
				new Font("Century Gothic", Font.BOLD, 14));// 表头字体
		table.getTableHeader().setBackground(Color.getHSBColor(111, 139, 120)); // 表头自己调色
		table.getTableHeader().setBorder(null); // 表格无边框
		table.getTableHeader().setPreferredSize(new Dimension(1, 35)); // 设置表头高度
		table.setFont(new Font("Century Gothic", Font.PLAIN, 14)); // 设置单元格字体
		table.setRowHeight(35); // 设置行高

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER); // 单元格居中显示
		table.setDefaultRenderer(Object.class, r);

		// 设置每一列的宽度，同时max与min的值相等时无法在运行的界面中变化宽度，注意从0开始
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(270);
		table.getColumnModel().getColumn(1).setMaxWidth(270);
		table.getColumnModel().getColumn(1).setMinWidth(270);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setMaxWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(155);
		table.getColumnModel().getColumn(4).setMaxWidth(155);
		table.getColumnModel().getColumn(4).setMinWidth(155);

		// 在第五列中添加button
		table.getColumnModel().getColumn(4)
				.setCellRenderer(new ButtonCellRenderer());
		table.getColumnModel().getColumn(4)
				.setCellEditor(new ButtonCellEditor(table, userID));

	}

	class MyTableModel extends AbstractTableModel {
		// 设置Table中的一些属性
		private static final long serialVersionUID = 1L;
		private String headName[];
		private Object obj[][];
		private Class[] columnTypes = new Class[] { Object.class, Object.class,
				Object.class, Object.class };

		public MyTableModel() {
			super();
		}

		public MyTableModel(String[] headName, Object[][] obj) {
			this();
			this.headName = headName;
			this.obj = obj;
		}

		public int getColumnCount() {
			return headName.length;
		}

		public int getRowCount() {
			return obj.length;
		}

		public Object getValueAt(int r, int c) {
			return obj[r][c];
		}

		public String getColumnName(int c) {
			return headName[c];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}
	}

	class ButtonCellEditor extends DefaultCellEditor {// 编辑器
		private static final long serialVersionUID = 1L;
		private JButton button;
		private JTable table;

		public ButtonCellEditor(JTable table, String userID) {
			super(new JTextField());
			this.setClickCountToStart(1);
			this.initButton(userID);
			this.table = table;
		}

		// 画出button
		private void initButton(String userID) {
			this.button = new JButton();
			this.button.setSize(200, 35);
			this.button.setContentAreaFilled(false);
			this.button.setBorderPainted(false);// 无边框
			this.button.addMouseListener(new MouseAdapter() {// 监听器
						@Override
						public void mouseClicked(MouseEvent e) {
							ButtonCellEditor.this.fireEditingCanceled();
							int r = table.getSelectedRow();
							Details det = new Details(r+1, zuo, userID,num);
							presentpanel.setVisible(false);
							presentframe.getContentPane().add(det);
							det.presentframe = presentframe;
							det.presentpanel = det;
						}
					});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			this.button.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/check/btnDetailO.png")));
			return this.button;
		}

		@Override
		public Object getCellEditorValue() {
			return this.button.getText();
		}
	}

	class ButtonCellRenderer implements TableCellRenderer {

		private JButton button;

		public ButtonCellRenderer() {
			this.button = new JButton();
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			this.button.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/check/btnDetail.png")));
			return this.button;
		}
	}

	// 设置panel的背景图片
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/checkBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}
