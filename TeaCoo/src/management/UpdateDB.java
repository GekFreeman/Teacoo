package management;

import java.sql.*;

public class UpdateDB {
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prestatment;
	
	public String qmain,a,b,c,d,anwr,type="Multiple chioce";

	public static Connection getConnection() {
		Connection c = null;

		String ur1 = "jdbc:mysql://localhost/teacoo";
		String username = "root";
		String password = "root";

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			c = DriverManager.getConnection(ur1, username, password);
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

	public static void update(String applyid, String status) {
		connection = getConnection();
		try {

			prestatment = connection
					.prepareStatement("update apply set applystatus =? where applyid  = ?");
			prestatment.setString(1, status);
			prestatment.setString(2, applyid);

			prestatment.executeUpdate();
			System.out.println("Update " + status);

			connection.close();

		} catch (SQLException e) {
			System.out.println("Update failed " + e.getMessage());
		}
	}

	public static void insert(String qmain,String a,String b,String c,String d,String anwr,String type) {
		int max=findMaxNo();
		int newqid=max+1;
		Integer in=new Integer(newqid);
		connection = getConnection();
		try {
			prestatment = connection
					.prepareStatement("insert into question(qid,mainquestion,optionA,optionB,optionC,optionD,answer,qtype) values (?,?,?,?,?,?,?,?)");
			prestatment.setString(1, in.toString());
			prestatment.setString(2, qmain);
			prestatment.setString(3, a);
			prestatment.setString(4, b);
			prestatment.setString(5, c);
			prestatment.setString(6, d);
			prestatment.setString(7, anwr);
			prestatment.setString(8, type);

			prestatment.executeUpdate();
			System.out.println( in.toString());

			connection.close();

		} catch (SQLException e) {
			System.out.println("insert record failed" + e.getMessage());
		}

	}
	public static void deletequestion(String QID) {
		
		 connection = getConnection();  
	        try {  
	            String sql = "delete from question  where QID = "+QID;
	            statement = connection.createStatement();  
	              
	            int count = statement.executeUpdate(sql);
	           
			System.out.println("delete succeeded");
			connection.close();

		} catch (SQLException e) {
			System.out.println("delete record failed" + e.getMessage());
		}

	}
	public void deleteuser(String userID) {
		
		 connection = getConnection();  
	        try {  
	            String sql = "delete from userinfo  where userID = "+userID;
	            statement = connection.createStatement();  
	              
	            int count = statement.executeUpdate(sql);
	           
			System.out.println("delete succeeded");
			connection.close();

		} catch (SQLException e) {
			System.out.println("delete record failed" + e.getMessage());
		}

	}
	public static int findMaxNo() {//查找当前最大的题目编号
		int max=0;
		connection = getConnection();
		try {
			String sql = "select"+ " "+"max"+"("+"QID"+"+"+0+")" +" "+"from question ";//SQL语句，注意字符串与数字的差别，传进数据库的0一定要是数字而不是字符
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				max=rs.getInt(1);
			}
			return max;
		} catch (SQLException e) {
			System.out.println("failed" + e.getMessage());
			return -1;

		}

	}
	public static void addtoTeacoo(String QID,String Qtype, String mainquestion,String optionA,
			String optionB,String optionC,String optionD,String analysis,String answer,String level){
		
		connection = getConnection();
		try {
			String sql="insert into question(QID,Qtype,mainquestion,optionA,optionB,"
					+ "optionC,optionD,analysis,answer,level) values (?,?,?,?,?,?,?,?,?,?)";
			prestatment = connection
					.prepareStatement(sql);
		    
			prestatment.setString(1, QID);
			prestatment.setString(2, Qtype);
			prestatment.setString(3, mainquestion);
			prestatment.setString(4,optionA);
			prestatment.setString(5, optionB);
			prestatment.setString(6, optionC);
			prestatment.setString(7, optionD);
			prestatment.setString(8, analysis);
			prestatment.setString(9, answer);
			prestatment.setString(10, level);

			prestatment.executeUpdate();
			System.out.println("insert record succeeded");

			connection.close();

		} catch (SQLException e) {
			System.out.println("insert record failed" + e.getMessage());
		}

		
	}
	public static void updateTeacoo(String Qtype, String mainquestion,String optionA,
			String optionB,String optionC,String optionD,String analysis,String answer,String level,String QID){
		
		connection = getConnection();
		try {
			String sql="update question set Qtype=?,mainquestion=?,optionA=?,optionB=?,optionC=?,optionD=?,analysis=?,answer=?,level=? where QID=?";
			prestatment = connection.prepareStatement(sql);
			
			
			prestatment.setString(1, Qtype);
			prestatment.setString(2, mainquestion);
			prestatment.setString(3, optionA);
			prestatment.setString(4, optionB);
			prestatment.setString(5, optionC);
			prestatment.setString(6, optionD);
			prestatment.setString(7, analysis);
			prestatment.setString(8, answer);
			prestatment.setString(9, level);
			prestatment.setString(10, QID);

			prestatment.executeUpdate();
			System.out.println("update record succeeded");

			connection.close();

		} catch (SQLException e) {
			System.out.println("update record failed" + e.getMessage());
		}

		
	}
	public static void updateUser(String userl,String userID){
		
		connection = getConnection();
		try {
			String sql="update userinfo set authority=? where userid="+userID;
			prestatment = connection.prepareStatement(sql);
			prestatment.setString(1, userl);

			prestatment.executeUpdate();
			System.out.println("update record succeeded");

			connection.close();

		} catch (SQLException e) {
			System.out.println("update record failed" + e.getMessage());
		}

		
	}


	
}
