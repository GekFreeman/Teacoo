package practice;

import java.sql.*;

public class InsertToStar {
	private static Connection connection;
	private static PreparedStatement prestatment;
	
	public String qmain,a,b,c,d,anwr,type;
	
	public InsertToStar(){
		
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
  /*向收藏库中插题*/
	public static void insert(String userID,String qid,String qmain,String a,String b,String c,String d,String anwr,String type,String anlys) {

		connection = getConnection();
		try {
			prestatment = connection
					.prepareStatement("insert into Star"+userID+"(qid,mainquestion,optionA,optionB,optionC,optionD,answer,qtype,analysis) values (?,?,?,?,?,?,?,?,?)");
			prestatment.setString(1, qid);
			prestatment.setString(2, qmain);
			prestatment.setString(3, a);
			prestatment.setString(4, b);
			prestatment.setString(5, c);
			prestatment.setString(6, d);
			prestatment.setString(7, anwr);
			prestatment.setString(8, type);
			prestatment.setString(9, anlys);

			prestatment.executeUpdate();
		//	System.out.println(findStarNo(userID));

			connection.close();

		} catch (SQLException e) {
			System.out.println("insert record failed" + e.getMessage());
		}

	}
	}
	