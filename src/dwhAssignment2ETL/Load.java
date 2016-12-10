package dwhAssignment2ETL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

public class Load 
{
	/** The name of the MySQL account to use (or empty for anonymous) */
	public static String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	public static String password = "1234";

	/** The name of the computer running MySQL */
	public static String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	public static int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	public static String dbName = "etl";
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	public static Connection getConnection() throws SQLException {
		
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
	//	connectionProps.put("password", password);
		System.out.println("trying to get connection!! ");
		conn = DriverManager.getConnection("jdbc:mysql://"+ serverName + ":" + portNumber + "/" + dbName,connectionProps);		
		System.out.println(" Connection achieved!! ");
		return conn;
	}
	
	public static boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	public void dbinsert(ArrayList<Student> liststudents, ArrayList<SemesterRecord> listrecords)
	{
		
		Connection conn = null;
		try 
		{
			conn = getConnection();
			System.out.println("connection name is :: "+conn.getClass().getName());
			System.out.println("Connected to database");
		}
		catch (SQLException e)
		{
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
		/**/
		
		for(int i = 0 ; i < liststudents.size() ; i++)
		{
			try{
				String input="INSERT INTO student "+"VALUES ("+liststudents.get(i).toString()+")";
				executeUpdate(conn, input);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		for(int i = 0 ; i < listrecords.size(); i ++)
		{
			try{
				String input="INSERT INTO registration "+"VALUES ("+listrecords.get(i).toString()+")";
				executeUpdate(conn, input);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String time=dateFormat.format(cal.getTime()).toString();
		Random rand = new Random();
		String randr=""+ rand.nextInt(100);
		try
		{
			String input="INSERT INTO time "+"VALUES ("+"'"+time+"','"+randr+"')";
			executeUpdate(conn, input);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String input="INSERT INTO region "+"VALUES ("+"'"+"LHR"+"','"+"1"+"')";
			executeUpdate(conn, input);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String input="INSERT INTO region "+"VALUES ("+"'"+"ISL"+"','"+"2"+"')";
			executeUpdate(conn, input);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String input="INSERT INTO region "+"VALUES ("+"'"+"KAR"+"','"+"3"+"')";
			executeUpdate(conn, input);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			String input="INSERT INTO region "+"VALUES ("+"'"+"PSH"+"','"+"4"+"')";
			executeUpdate(conn, input);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int i = 0 ;i < listrecords.size() ; i++)
		{
			try
			{
				int regif = 0;
				if(listrecords.get(i).getRegion().equals("LHR"))
				{
					regif=1;
				}
				else if(listrecords.get(i).getRegion().equals("ISL"))
				{
					regif=2;
				}
				else if(listrecords.get(i).getRegion().equals("KAR"))
				{
					regif=3;
				}
				else if(listrecords.get(i).getRegion().equals("PSH"))
				{
					regif=4;
				}
				String input="INSERT INTO facttable "+"VALUES ("+"'"+regif+"','"+listrecords.get(i).getSID()+"','"+listrecords.get(i).getCourse()+"','"+listrecords.get(i).getMarks()+"','"+randr+"')";
				executeUpdate(conn, input);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
	}
}
