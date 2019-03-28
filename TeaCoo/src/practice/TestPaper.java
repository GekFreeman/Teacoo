package practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class TestPaper {
	//试卷类，用于存储试卷信息
	
	int i = 0;//循环变量，用于控制做题数组
	int num;//试卷题量
	int type1;//题型1的类型
	int type2;//题型2的类型
	int type3;//题型3的类型
	Questions[] zuoti;//存储题目的数组

	public TestPaper() {

	}
	//构造方法，确定三种题型的题量
	public TestPaper(int type1,int type2,int type3){
		this.type1=type1;
		this.type2=type2;
		this.type3=type3;		
	}

//连接数据库的类
	public static Connection getConnection() {
		Connection c = null;

		String ur1 = "jdbc:mysql://localhost:3306/Teacoo?characterEncoding=utf8";
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
	
//抽题的方法
	public Questions[] getPaper() {
		num=type1+type2+type3;//确定总题量
		Questions[] x = new Questions[num + 5];//确定数组长度，多写几个，以防溢出
		zuoti = x;//将实例化后的数组指向zuoti
		for (i = 0; i < num + 5; i++) {
			zuoti[i] = new Questions();//实例化每个数组变量
		}
		i = 1;
		Connection connection;
		connection = getConnection();
		Statement sta = null;
		try {
			sta = (Statement) connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet res1 = null;
		try {
			res1 = sta
					.executeQuery("select * from Question where Qtype='type1'  order by rand() limit  "
							+ this.type1 + "  ");

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();

		}
		try {
			while (res1.next()) {
				zuoti[i].QID = res1.getString("QID");
				zuoti[i].MainQuestion = res1.getString("MainQuestion");
				zuoti[i].OptionA = res1.getString("OptionA");
				zuoti[i].OptionB = res1.getString("OptionB");
				zuoti[i].OptionC = res1.getString("OptionC");
				zuoti[i].OptionD = res1.getString("OptionD");
				zuoti[i].Answer = res1.getString("Answer").charAt(0);
				zuoti[i].Qtype = res1.getString("Qtype");
				zuoti[i].Analysis = res1.getString("Analysis");
				zuoti[i].level=res1.getString("level");
				System.out.println(i+"这是第N题");
				zuoti[i].display();
	            
				i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sta = (Statement) connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			res1 = sta
					.executeQuery("select * from Question where Qtype='type2'  order by rand() limit  "
							+ this.type2 + "  ");
			System.out.println("查询完了type2");

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while (res1.next()) {
				zuoti[i].QID = res1.getString("QID");
				zuoti[i].MainQuestion = res1.getString("MainQuestion");
				zuoti[i].OptionA = res1.getString("OptionA");
				zuoti[i].OptionB = res1.getString("OptionB");
				zuoti[i].OptionC = res1.getString("OptionC");
				zuoti[i].OptionD = res1.getString("OptionD");
				zuoti[i].Answer = res1.getString("Answer").charAt(0);
				zuoti[i].Qtype = res1.getString("Qtype");
				zuoti[i].Analysis = res1.getString("Analysis");
				zuoti[i].level=res1.getString("level");
				zuoti[i].display();
				i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sta = (Statement) connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print("查询前的type3：" + this.type3);
		try {
			res1 = sta
					.executeQuery("select * from Question where Qtype='type3'  order by rand() limit  "
							+ this.type3 + "  ");
			System.out.println("查询完了type3");

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while (res1.next()) {
				zuoti[i].QID = res1.getString("QID");
				zuoti[i].MainQuestion = res1.getString("MainQuestion");
				zuoti[i].OptionA = res1.getString("OptionA");
				zuoti[i].OptionB = res1.getString("OptionB");
				zuoti[i].OptionC = res1.getString("OptionC");
				zuoti[i].OptionD = res1.getString("OptionD");
				zuoti[i].Answer = res1.getString("Answer").charAt(0);
				zuoti[i].Qtype = res1.getString("Qtype");
				zuoti[i].Analysis = res1.getString("Analysis");
				zuoti[i].level=res1.getString("level");
				zuoti[i].display();
				i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return zuoti;

	}

}
