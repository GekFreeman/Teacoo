package management;

import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDataFromDB {

	public static Connection connection;
	private static PreparedStatement prestatment;

	public Vector columnHeads = new Vector();
	public Vector rows = new Vector();
	
	
	public GetDataFromDB(){
		
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

	public void getTable() {
		Statement statement;
		ResultSet resultSet;

		try {
			String query = "SELECT applyid,userid,username,applydate,applystatus FROM apply";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			displayResultSet(resultSet);
			statement.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		shutDown();
	}
//*******************************************************************
	public String main,oA,oB,oC,oD,answer,analysis,userID,userlevel,qtype;
	
	public String getText(String applyid) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select mainquestion,optionA,optionB,optionC,optionD,answer from apply where applyid=?");
			prestatment.setString(1, applyid);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				main = rs.getString("mainquestion");
				oA = rs.getString("optionA");
				oB = rs.getString("optionB");
				oC = rs.getString("optionC");
				oD = rs.getString("optionD");
				
				return (main + " \nA\t " + oA + "\nB\t" + oB + "\nC\t" + oC
						+ "\nD\t" + oD + "\n");
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
			
		}

	}

	public String getAnswer(String applyid) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select answer from apply where applyid=?");
			prestatment.setString(1, applyid);

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
	public String getAnalysis(String applyid) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select analysis from apply where applyid=?");
			prestatment.setString(1, applyid);

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
	public String getUserID(String applyid) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select userid from apply where applyid=?");
			prestatment.setString(1, applyid);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				userID = rs.getString("userid");

				return (userID);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getUserLevel(String userID) {
		
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select level from userinfo where userid=?");
			prestatment.setString(1,userID);

			rs = prestatment.executeQuery();
			while (rs.next()) {

				userlevel = rs.getString("level");

				return (userlevel);
			}

			return "null";

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			return null;
		}

	}
	public String getQtype(String applyid) {
		ResultSet rs;
		try {
			prestatment = connection
					.prepareStatement("select qtype from apply where applyid=?");
			prestatment.setString(1, applyid);

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
