package personal;

import java.sql.*;

public class JDBC {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prestatment;

	public static void insert() {
		connection = getConnection();
		try {

			String sql = "insert into departments(dept_no,dept_name) values ('d011','Test')";
			statement = (Statement) connection.createStatement();
			int count = statement.executeUpdate(sql);// execute statement
			System.out.println("Insert " + count
					+ " records into departments table");
			connection.close();

		} catch (SQLException e) {
			System.out.println("insert record failed" + e.getMessage());
		}

	}

	public static boolean query(String password, String name) {

		boolean a = false;
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from user"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			System.out.println("Query results are锛�");
			while (rs.next()) { // not the last row

				String user_name = rs.getString("name");
				String user_password = rs.getString("password");
				if (name.equals(user_name) && password.equals(user_password)) {
					a = true;
					break;
				}

			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}

	// 删除
	public static void delete() {

		connection = getConnection();
		try {
			String sql = "delete from departments  where dept_no = 'd009'";
			statement = (Statement) connection.createStatement();

			int count = statement.executeUpdate(sql);

			System.out.println("delete " + count
					+ " record(s) from departments");

			connection.close();

		} catch (SQLException e) {
			System.out.println("Delete record failed" + e.getMessage());
		}

	}

	// 更新
	public static void update(String a, String b, String userID) {
		connection = getConnection();
		try {
			// 将userinfo表中userID用户的第a列的数据改为b
			String sql = "update userinfo set " + b + " = '" + a
					+ "' where UserID  = '" + userID + "'";
			statement = (Statement) connection.createStatement();

			int count = statement.executeUpdate(sql);

			// System.out.println("update " + count +
			// " records in departments");

			connection.close();

		} catch (SQLException e) {
			System.out.println("Update failed " + e.getMessage());
		}
	}

	public static Connection getConnection() {
		Connection c = null;

		String ur1 = "jdbc:mysql://localhost/teacoo";
		String username = "root";
		String password = "root";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			c = DriverManager.getConnection(ur1, username, password);
			// System.out.println("Connect Success");

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
