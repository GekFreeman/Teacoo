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
	JFrame presentframe;// ����panel�л�
	JPanel presentpanel = this;

	private static final long serialVersionUID = 1L;
	public static JTable table;
	public JButton btn;
	public JScrollPane scroll;
	int headHeight = 25;
	String sql = null;
	public int num;

	// ��table��չʾ��Ŀ�������Ϣ
	public TablePane(Questions[] zuoti, String userID,int length) {
		this.num=length;
		this.zuo = zuoti;
		setBounds(0, 0, 1000, 700);// ����panel�Ĵ�С��λ��
		initTable(zuoti, userID);// ���Ʊ��
		setLayout(null);
		setVisible(true);

		JPanel checkList = new JPanel() {// ����չʾTable��panel
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
		scroll.setBounds(5, 136, 589, 324);// ����Scroll�Ĵ�С
		scroll.setViewportView(table);
		checkList.add(scroll);

		// ������һ������İ�ť
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

	// ����Table
	public void initTable(Questions[] zuoti, String userID) {
		int i = 1;
		String[] headNames = new String[] { "No.", "Question", "Selected",
				"Correct", "Details" };// Table�����б���
		Object[][] tableDatas = new Object[num][5];// ��������
		for (; i <= num; i++)// ��zuoti�е���Ϣ���ݸ�tableDatas
		{
			tableDatas[i-1 ][0] = i;
			tableDatas[i-1 ][1] = zuoti[i].MainQuestion;
			tableDatas[i-1 ][2] = zuoti[i].userAnswer;
			tableDatas[i-1 ][3] = zuoti[i].Answer;
			tableDatas[i-1 ][4] = null;
		}

		table = new JTable(new MyTableModel(headNames, tableDatas));// �������Ļ����ṹ
		table.getTableHeader().setFont(
				new Font("Century Gothic", Font.BOLD, 14));// ��ͷ����
		table.getTableHeader().setBackground(Color.getHSBColor(111, 139, 120)); // ��ͷ�Լ���ɫ
		table.getTableHeader().setBorder(null); // ����ޱ߿�
		table.getTableHeader().setPreferredSize(new Dimension(1, 35)); // ���ñ�ͷ�߶�
		table.setFont(new Font("Century Gothic", Font.PLAIN, 14)); // ���õ�Ԫ������
		table.setRowHeight(35); // �����и�

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER); // ��Ԫ�������ʾ
		table.setDefaultRenderer(Object.class, r);

		// ����ÿһ�еĿ�ȣ�ͬʱmax��min��ֵ���ʱ�޷������еĽ����б仯��ȣ�ע���0��ʼ
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

		// �ڵ����������button
		table.getColumnModel().getColumn(4)
				.setCellRenderer(new ButtonCellRenderer());
		table.getColumnModel().getColumn(4)
				.setCellEditor(new ButtonCellEditor(table, userID));

	}

	class MyTableModel extends AbstractTableModel {
		// ����Table�е�һЩ����
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

	class ButtonCellEditor extends DefaultCellEditor {// �༭��
		private static final long serialVersionUID = 1L;
		private JButton button;
		private JTable table;

		public ButtonCellEditor(JTable table, String userID) {
			super(new JTextField());
			this.setClickCountToStart(1);
			this.initButton(userID);
			this.table = table;
		}

		// ����button
		private void initButton(String userID) {
			this.button = new JButton();
			this.button.setSize(200, 35);
			this.button.setContentAreaFilled(false);
			this.button.setBorderPainted(false);// �ޱ߿�
			this.button.addMouseListener(new MouseAdapter() {// ������
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

	// ����panel�ı���ͼƬ
	protected void paintComponent(Graphics g) {
		ImageIcon BG = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/check/checkBG.png"));
		Image img = BG.getImage();
		g.drawImage(img, 0, 0, BG.getIconWidth(), BG.getIconHeight(),
				BG.getImageObserver());
	}
}
