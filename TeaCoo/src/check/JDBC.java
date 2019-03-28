package check;
import java.sql.*;

public class JDBC {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prestatment;

	// qinfo�����޸ķ���
	public static void update(String b, int a,String c) {
		connection = getConnection();
		try {
			String sql = "update qinfo set "+b+" = '" + a
					+ "' where UserID  = '"+c+"'";
			statement = (Statement) connection.createStatement();
			int count = statement.executeUpdate(sql);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Update failed " + e.getMessage());
		}
	}
	
	//userinfo�����޸ķ���
	public static void update1(String b, int a,String c) {
		connection = getConnection();
		try {
			String sql = "update userinfo set "+b+" = '" + a
					+ "' where UserID  = '"+c+"'";
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(sql);
			connection.close();//�ر�����
		} catch (SQLException e) {
			System.out.println("Update failed " + e.getMessage());
		}
	}

	//question�����޸ķ���
	public static void update2(String b, int a,String c) {
		connection = getConnection();
		try {
			String sql = "update question set "+b+" = '" + a
					+ "' where QID  = '"+c+"'";
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(sql);
			connection.close();//�ر�����
		} catch (SQLException e) {
			System.out.println("Update failed " + e.getMessage());
		}
	}
	
	//��ѯ�û�������
	public static int query(String userID) {
		int a=0;
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row
				String userid = rs.getString("UserID");
				int rank = rs.getInt("Rank");
				if (userid.equals(userID)) {
					a = rank;
					break;
				}

			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}
	
	//��ѯ��Ŀ��������
		public static int query1(String qID) {
			int a=0;
			connection = getConnection(); // connect to database

			try {
				String sql = "select * from question"; // select statement
				statement = (Statement) connection.createStatement();
				ResultSet rs = statement.executeQuery(sql); // execute statement
				while (rs.next()) { // not the last row
					String userid = rs.getString("QID");
					int total = rs.getInt("TotalTimes");
					if (userid.equals(qID)) {
						a = total;
						break;
					}

				}
				connection.close();

			} catch (SQLException e) {
				System.out.println("Query failed" + e.getMessage());
			}
			return a;
		}
		
		//��ѯ��Ŀ��������Ŀ
		public static int query2(String qID) {
			int a=0;
			connection = getConnection(); // connect to database

			try {
				String sql = "select * from question"; // select statement
				statement = (Statement) connection.createStatement();
				ResultSet rs = statement.executeQuery(sql); // execute statement
				while (rs.next()) { // not the last row
					String userid = rs.getString("QID");
					int total = rs.getInt("RightTimes");
					if (userid.equals(qID)) {
						a = total;
						break;
					}

				}
				connection.close();

			} catch (SQLException e) {
				System.out.println("Query failed" + e.getMessage());
			}
			return a;
		}
		
	
	public static Connection getConnection() {//��������
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

}