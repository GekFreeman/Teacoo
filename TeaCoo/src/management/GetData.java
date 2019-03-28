package management;
import java.sql.*;
import java.util.*;

public class GetData{

	public static Connection connection;
	public static PreparedStatement prestatment;

	public Vector columnHeads = new Vector();
	public Vector rows = new Vector();
	
	
	public GetData(){
		
	}


	public void getConnection() {
		String ur1 = "jdbc:mysql://" + "localhost" + "/" + "teacoo";
		String username = "root";
		String password = "root";

		try {
			Class.forName("org.gjt.mm.mysql.Driver"); // register the JDBC driver
														
			connection = DriverManager.getConnection(ur1, username, password);
			System.out.println("Connect Success");
		} catch (ClassNotFoundException cnfex) {
			System.out.println("Failed to load JDBC driver.");
			cnfex.printStackTrace();
			System.exit(1);
		} catch (SQLException sqlex) {
			System.err.println("Unable to connect");
			sqlex.printStackTrace();
		}

	}

	private void displayResultSet(ResultSet rs) throws SQLException {
		// position to first record

		boolean moreRecords = rs.next();

		try {
			// get column heads
			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= rsmd.getColumnCount(); ++i)
				columnHeads.addElement(rsmd.getColumnName(i));

			// get row data
			do {
				rows.addElement(getNextRow(rs, rsmd));
			} while (rs.next());

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd)
			throws SQLException {
		Vector currentRow = new Vector();

		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			switch (rsmd.getColumnType(i)) {
			case Types.VARCHAR:
				/*if(i==2) {
					if(rs.getString(i)("Type1")) currentRow.addElement("principle of management");
					else if(rs.getString(i).equals("Type2")) currentRow.addElement("financial management");
					else if(rs.getString(i).equals("Type3")) currentRow.addElement("corporate governance");
				}
				else*/
				 currentRow.addElement(rs.getString(i));
				break;
				
			case Types.CHAR:
				currentRow.addElement(rs.getString(i));
				break;
			case Types.DATE:
				currentRow.addElement(rs.getDate(i));
				break;
			default:
				System.out.println("Type was: " + rsmd.getColumnTypeName(i));
			}

		return currentRow;
	}

	public void getTikuTable() {
		Statement statement;
		ResultSet resultSet;

		try {
			String query = "SELECT QID,Qtype,level FROM question";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			displayResultSet(resultSet);
			statement.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
//*******************************************************************
	public String main,oA,oB,oC,oD,answer,analysis,level,qtype,ulevel,authority;
	
	public String getMainQuestion(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select mainquestion from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				main = rs.getString("mainquestion");
				
				return (main);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getOptionA(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select optionA from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				oA = rs.getString("optionA");
				
				return (oA);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getOptionB(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select optionB from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				oB = rs.getString("optionB");
				
				return (oB);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getOptionC(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select optionC from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				oC = rs.getString("optionC");
				
				return (oC);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getOptionD(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select optionD from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				oD= rs.getString("optionD");
				
				return (oD);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getAnswer(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select answer from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				answer = rs.getString("answer");
				
				return (answer);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	
	public String getAnalysis(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select analysis from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				analysis = rs.getString("analysis");
				
				return (analysis);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getQtype(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select qtype from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				qtype = rs.getString("qtype");
				
				return (qtype);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getLevel(String QID) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select level from question where QID=?");
			prestatment.setString(1, QID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				level = rs.getString("level");
				
				return (level);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public void  getUserTable() {
		Statement statement;
		ResultSet resultSet;
		
		try {
			
			String query = "SELECT userID,username, gender,major FROM userinfo";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			displayResultSet(resultSet);
			statement.close();
			

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		
	}
	public String email,phone,grade,advantage,hobby,signature;
	public String getEmail(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select Email from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				email = rs.getString("Email");

				return (email);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getPhone(String userID) {
		ResultSet rs;
		
		try {
			prestatment = connection
					.prepareStatement("select phone from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				phone = rs.getString("phone");

				return (phone);
				//connection.close();
			}
			return "null";
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getGrade(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select Grade from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				grade = rs.getString("grade");

				return (grade);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getAdv(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select advantage from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				advantage= rs.getString("advantage");

				return (advantage);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getHobby(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select Hobby from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				hobby = rs.getString("hobby");

				return (hobby);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getSig(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select signature from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				signature = rs.getString("signature");

				return (signature);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}

	public String getULevel(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select level from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				ulevel = rs.getString("level");

				return (ulevel);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getAuthority(String userID) {
		ResultSet rs;
		//connection = getConnection();
		try {

			prestatment = connection.prepareStatement("select authority from userinfo where userID=?");
			prestatment.setString(1, userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				authority = rs.getString("authority");

				return (authority);
				//connection.close();
			}
			return "null";
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

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
