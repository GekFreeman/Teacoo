package report;

import java.sql.*;

public class Query {
	
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prestatment;
	
	public static double[] query_diff(String UserID) {
		
		double[] a=new double[5]; //存储正确率
		int[] num=new int[5];//存储做题次数
		int[] rig=new int[5];//存储正确次数
			
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from qinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row

				String userid = rs.getString("UserID");
				if (userid.equals(UserID)) {
					for (int i=0;i<5;i++) {
					num[i]=rs.getInt("Level"+(i+1));
					rig[i]=rs.getInt("Level"+(i+1)+"Right");
					a[i]=rig[i]*100/num[i];
					}
					break;
				}

			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}
	
public static double[] query_type(String UserID) {
		
		double[] a=new double[3]; //存储正确率
		int[] num=new int[3];//存储做题次数
		int[] rig=new int[3];//存储正确次数
			
		connection = getConnection(); // connect to database

		try {
			String sql = "select * from qinfo"; // select statement
			statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql); // execute statement
			while (rs.next()) { // not the last row

				String userid = rs.getString("UserID");
				if (userid.equals(UserID)) {
					for (int i=0;i<3;i++) {
					num[i]=rs.getInt("T"+(i+1)+"Num");
					rig[i]=rs.getInt("T"+(i+1)+"RightNum");
					a[i]=rig[i]*100/num[i];
					}
					break;
				}

			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Query failed" + e.getMessage());
		}
		return a;
	}

public static int[] query_time(String UserID) {
	
	int[] a=new int[5]; //存储正确率
	int[] b=new int[3]; //存储当前日期
	b=DateNow.DatePrint();
	int i = 4;
		
	connection = getConnection(); // connect to database

	try {
		String sql = "select * from numlog"; // select statement
		statement = (Statement) connection.createStatement();
		ResultSet rs = statement.executeQuery(sql); // execute statement
		while (rs.next()) { // not the last row

			String userid = rs.getString("UserID");
			String date=rs.getString("Date");
			if (userid.equals(UserID)) {
					if(i>=0) {
					if((b[0]+"-0"+b[1]+"-"+(b[2]-i)).equals(date)) {
						a[i]=rs.getInt("Numbers");
						i--;
					}
					}
					else break;


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


}
