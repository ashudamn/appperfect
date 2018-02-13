package org.appperfect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class AccessDB {
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn;
	private static String query;
	private static int recordSize;
	public static void connect2DB(String driver,String url,String username,String password) throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		conn=(Connection) DriverManager.getConnection(url,username,password);
		st=(Statement) conn.createStatement();
		
	}
	public static void execute(String query) throws SQLException{
		ResultSet rs=st.executeQuery(query);
		setRs(rs);
	}
	public static ResultSet getRs() {
		return rs;
	}
	public static void setRs(ResultSet rs) {
		AccessDB.rs = rs;
	}

	public static void closeAll(){
		try{
			AccessDB.rs.close();
		}
		catch(Exception e){
			System.out.println("problem in closing result set");
			e.printStackTrace();
		}
		try {
			AccessDB.st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problem in closing statement");
			e.printStackTrace();
		}
		try {
			AccessDB.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("problem in closing connection");
			e.printStackTrace();
		}
		
	}
	public static String getQuery() {
		return query;
	}
	public static void setQuery(String query) {
		AccessDB.query = query;
	}
	public static int getRecordSize() {
		return recordSize;
	}
	public static void setRecordSize(int recordSize) {
		AccessDB.recordSize = recordSize;
	}
}
