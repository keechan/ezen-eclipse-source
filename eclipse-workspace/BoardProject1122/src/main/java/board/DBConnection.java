package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection conn = DriverManager.getConnection
		 ("jdbc:oracle:thin:@//localhost:1521/xe","system","1234");
		return conn;
	 }
	
	public static void close(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null) {pstmt.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null) {	conn.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {rs.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (pstmt != null) {pstmt.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null) {	conn.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void close(PreparedStatement pstmt) {
		
		try {
			if (pstmt != null) {pstmt.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}
	
	public static void close(Connection conn) {	
		
		try {
			if (conn != null) {	conn.close();}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
