package collection;

import java.sql.*;

public class InsertToDB {
	private static Connection connection;
	private static PreparedStatement prestatment;
	
	public String qmain,a,b,c,d,anwr,type;
	
	public InsertToDB(){
		
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
	public static void insert(String userID,String qmain,String a,String b,String c,String d,String anwr,String type,String anlys) {

		connection = getConnection();
		try {
			prestatment = connection
					.prepareStatement("insert into star"+userID+"(qid,mainquestion,optionA,optionB,optionC,optionD,answer,qtype,analysis) values (?,?,?,?,?,?,?,?,?)");
			prestatment.setString(1, findStarNo(userID));
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
	/*insert方法重载，向apply表中插入申请*/
	public static void insert(String userid,String date,String stem,String a,String b,String c,String d,String anwr,String type,String anlys) {
		int max=findMaxApplyNo();
		int newapplyid=max+1;
		Integer in=new Integer(newapplyid);
		String username=findUserName(userid);
		connection = getConnection();
		try {
			prestatment = connection
					.prepareStatement("insert into apply(applyid,qid,userid,username,applydate,applystatus,mainquestion,optionA,optionB,optionC,optionD,answer,qtype,analysis) values (?,null,?,?,?,'Applying',?,?,?,?,?,?,?,?)");
			prestatment.setString(1, in.toString());
			prestatment.setString(2, userid);
			prestatment.setString(3, username);
			prestatment.setString(4, date);
			prestatment.setString(5, stem);
			prestatment.setString(6, a);
			prestatment.setString(7, b);
			prestatment.setString(8,c);
			prestatment.setString(9,d);
			prestatment.setString(10, anwr);
			prestatment.setString(11, type);
			prestatment.setString(12, anlys);
		
			prestatment.executeUpdate();
		//	System.out.println( in.toString());

			connection.close();

		} catch (SQLException e) {
			System.out.println("insert record failed" + e.getMessage());
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
	
	/*为了给新的申请分配编号，先查找当前表中最大的编号*/
	public static int findMaxApplyNo() {//查找当前最大的apply编号
		int max=0;
		connection = getConnection();
		try {
			String sql = "select"+ " "+"max"+"("+"applyid"+"+"+0+")" +" "+"from apply ";
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
	/*向star中添加一道自己的题，给这道题编号，查找当前表中最大的题号，然后将该题号的第一个数字改成字母a，就是新插入题的编号*/
	public static String findStarNo(String userID) {
		int max=0;

		connection = getConnection();
	
		try {
			String  sql = "select"+ " "+"max"+"("+"QID"+"+"+0+")" +" "+"from star"+userID;
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				max=rs.getInt(1);
				
			}
			Integer m=new Integer(max);
			System.out.println(m.toString().replace("1","a"));
			return m.toString().replace("1","a");
		} catch (SQLException e) {
			System.out.println("failed" + e.getMessage());
			return null;
			

		}
	}
	public static String findUserName(String userID){
		connection = getConnection();
		
		try {
			
			String  sql = "select Username from userinfo where userid="+userID;
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
		
			while(rs.next()){
				String name=rs.getString("username");
				return name;
			}			
				connection.close();
				return null;
		} catch (SQLException e) {
			System.out.println("failed" + e.getMessage());
			return null;

		}
		
	}
	public static int findUserLevel(String userID){
		connection =getConnection();
		try{
			String sql="select Authority from userinfo where userid="+userID;
			Statement statement = (Statement) connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
		
			while(rs.next()){
				int userlevel=rs.getInt("Authority");
				return userlevel;
			}			
				connection.close();
				return 0;
		}catch (SQLException e){
			System.out.println("failed" + e.getMessage());
			return 0;
			
		}
	}

	

}
