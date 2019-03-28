package collection;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class MYTable {// �Լ�����TableModel�������ݿ�����

	private static Connection connection;
	private static PreparedStatement prestatment;
	public static String main, oA, oB, oC, oD, answer, analysis;

	public static JTable table;
	public JButton button;

	String sql = null;
	String[] headNames = new String[4];
	static String tableName;

	/* star,wrong,application �е�list����Ҫ ��ʾ��ͬ������ݣ�������Ҫ��SQL���Ҳ��ͬ */
	public MYTable(String sql, JButton button, String[] headNames,
			String tableName) {
		MYTable.tableName = tableName;
		this.sql = sql;
		this.button = button;
		this.headNames = headNames;
		int recordno = queryCount();

		Object[][] tableDatas = new Object[recordno][4];
		for (int i = 0; i < recordno; i++)
			for (int j = 0; j < 4; j++) {
				tableDatas[i][j] = null;

			}

		table = new JTable(new MyTableModel(headNames, tableDatas));

		table.getTableHeader().setFont(
				new Font("Century Gothic", Font.BOLD, 14)); // ��ͷ����
		table.getTableHeader().setBackground(Color.getHSBColor(111, 139, 120)); // ��ͷ�Լ���ɫ
		table.getTableHeader().setBorder(null);
		table.getTableHeader().setPreferredSize(new Dimension(1, 35)); // ���ñ�ͷ�߶�
		table.setFont(new Font("Century Gothic", Font.PLAIN, 14)); // ���õ�Ԫ������
		table.setRowHeight(35); // �����и�
		table.getTableHeader().setForeground(Color.WHITE);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer(); // ��Ԫ�������ʾ
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		table.getColumnModel().getColumn(0).setPreferredWidth(60); // �̶��п����ֵ����Сֵһ����
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(0).setMinWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMinWidth(200);

		table.getColumnModel().getColumn(3)
				.setCellRenderer(new ButtonCellRenderer(button));
		table.getColumnModel().getColumn(3)
				.setCellEditor(new ButtonCellEditor(button));

		try {
			getDataToTable(sql);
		} catch (Exception e) {

		}

	}

	/* �����ݿ���Ӧ���е�����ֱ�Ӹ�ֵ��TableModel��SQL�����ݲ�ͬ����Ҫ�ı� */
	public static void getDataToTable(String sql) {
		connection = getConnection();
		ResultSet rs;

		try {

			prestatment = connection.prepareStatement(sql);
			rs = prestatment.executeQuery();
			int i = 0;

			while (rs.next()) {
				table.getModel().setValueAt(rs.getString(1), i, 0);

				/* ������ղؿ�ʹ������ã��ڶ�����ʾ������Ŀ���ͣ����ݿ��д洢����������ʾ�����Ĳ�ͬ��Ҫ���ж�Ӧת�� */
				if (tableName.equals("apply"))
					table.getModel().setValueAt(rs.getString(2), i, 1);
				else {
					if (rs.getString(2).equals("Type1"))
						table.getModel().setValueAt("principle of management",
								i, 1);
					else if (rs.getString(2).equals("Type2"))
						table.getModel().setValueAt("financial management", i,
								1);
					else
						table.getModel().setValueAt("corporate governance", i,
								1);
				}
				table.getModel().setValueAt(rs.getString(3), i, 2);

				i++;
			}

			shutDown();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			shutDown();
		}
	}

	/* ��ѯ�������м�¼������Ϊ table��ʼ��ʱ������ */
	public static int queryCount() {
		connection = getConnection();
		ResultSet rs;
		Statement statment;

		try {
			String sql = "select count(*) from " + tableName;
			statment = connection.createStatement();
			rs = statment.executeQuery(sql);

			while (rs.next()) {
				int count = rs.getInt(1);
				if(count==0)
					count=count+1;
				return count;
			}
			shutDown();
			return 1;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			shutDown();
			return 1;
		}
	}

	public static void getText(String id) {
		connection = getConnection();
		ResultSet rs;
		String key;
		if (tableName.equals("apply"))
			key = "applyid";
		else
			key = "qid";

		try {

			prestatment = connection
					.prepareStatement("SELECT mainquestion,optionA,optionB,optionC,optionD,answer,analysis FROM "
							+ tableName + " where " + key + "=?");
			prestatment.setString(1, id);

			rs = prestatment.executeQuery();
			while (rs.next()) {
				main = rs.getString("mainquestion");
				oA = rs.getString("optionA");
				oB = rs.getString("optionB");
				oC = rs.getString("optionC");
				oD = rs.getString("optionD");
				answer = rs.getString("answer");
				analysis = rs.getString("analysis");

			}
			shutDown();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			shutDown();
		}
	}

	/* �Լ����Ƶ�TableModel */
	class MyTableModel extends AbstractTableModel {

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

		public Class<?> getColumnClass(int columnIndex) {
			return columnTypes[columnIndex].getClass();
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return true;
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			obj[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	/*Ϊ�˽���ť��ӵ�table�У����ı��ı༭������Ⱦ��*/

	class ButtonCellEditor extends DefaultCellEditor {

		private static final long serialVersionUID = 1L;
		private JButton button;

		public ButtonCellEditor(JButton button) {
			super(new JTextField());
			this.setClickCountToStart(1);
			this.button = button;
			this.button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					ButtonCellEditor.this.fireEditingCanceled();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {

			this.button.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/collection/btnDetail.png")));
			this.button.setContentAreaFilled(false);
			this.button.setBorderPainted(false);
			return this.button;
		}
		public Object getCellEditorValue() {
			return this.button.getText();
		}
	}

	class ButtonCellRenderer implements TableCellRenderer {

		private JButton button;

		public ButtonCellRenderer(JButton button) {
			this.button = button;

		}
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			this.button.setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage("image/collection/btnDetail.png")));
			this.button.setContentAreaFilled(false);
			this.button.setBorderPainted(false);
			return this.button;
		}
	}
	public static Connection getConnection() {
		Connection c = null;

		String url = "jdbc:mysql://localhost/teacoo";
		String username = "root";
		String password = "root";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			c = DriverManager.getConnection(url, username, password);
			System.out.println("Connect Success");

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

	public static void shutDown() {
		try {
			connection.close();
			System.out.println("Disconnect successfully");
		} catch (SQLException sqlex) {
			System.err.println("Unable to disconnect");
			sqlex.printStackTrace();
		}
	}
}
