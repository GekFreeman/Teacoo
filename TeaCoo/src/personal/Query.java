package personal;

import java.sql.*;

import report.DateNow;

public class Query {
	
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prestatment;
	
	//存储用户信息的字符串数组
	public static String[] a=new String[13];

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
	public static String[] query_perinfo(String userID) {
		
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row

				String userid = rs.getString("UserID");
				if (userid.equals(userID)) {
					a[0]=rs.getString("Username");
					a[1]=rs.getString("Gender");
					a[2]=rs.getString("Phone");
					a[3]=rs.getString("Email");
					a[4]=rs.getString("Major");
					a[5]=rs.getString("Grade");
					a[6]=rs.getString("Advantage");
					a[7]=rs.getString("Hobby");
					a[8]=rs.getString("Signature");
					a[9]=rs.getString("Level");
					a[10]=rs.getString("Profile");
					a[11]=rs.getString("Rank");
					a[12]=rs.getString("Exp");
					}
				}

			
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}
	public static String query_pw(String userID) {

		String a=null;
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
//			System.out.println("Query results are锛�");
			while (rs.next()) { // not the last row

				String userid = rs.getString("UserID");
				String user_password = rs.getString("Password");
				if (userid.equalsIgnoreCase(userID)) {
					a=user_password;
					break;
				}

			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		System.out.println(a);
		return a;
	}
	public static String[] query_rank(int ranknum) {
		
		String[] a=new String[3]; //存储正确率
			
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row

				int rank = rs.getInt("Rank");
				String name=rs.getString("Username");
				String level=rs.getString("Level");
				String exp=rs.getString("Exp");
				if (rank==ranknum) {
							a[0]=name;
							a[1]=level;
							a[2]=exp;
					}
				}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}
	public static int usernums() {
		
		int count=0;
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row
				count++;
				}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return count;
	}
public static String query_path(String userID) {
		
		String path=null; //存储正确率
			
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from userinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row

				String userid = rs.getString("UserID");
				if (userid.equalsIgnoreCase(userID)) {
							path=rs.getString("Profile");
					}
				}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		System.out.println(path);
		return path;
	}
public static String query_level(String userID) {

	String level = null; // 存储级别

	connection = getConnection(); // connect to database

	try {
		String sql = "select * from userinfo"; // select statement
		statement = (Statement) connection.createStatement();
		ResultSet rs = statement.executeQuery(sql); // execute statement
		while (rs.next()) { // not the last row

			String userid = rs.getString("UserID");
			if (userid.equalsIgnoreCase(userID)) {
				level = rs.getString("Level");
			}
		}
		connection.close();

	} catch (SQLException e) {
		System.out.println("Query failed" + e.getMessage());
	}
	return level;
}
	
	

}

