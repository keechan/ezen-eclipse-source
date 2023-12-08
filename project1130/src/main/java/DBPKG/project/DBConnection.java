package DBPKG.project;

import java.sql.*;

public class DBConnection {
   public  static  Connection  getConnection() throws  Exception{
	Class.forName("oracle.jdbc.OracleDriver");
	Connection con
	   =DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","system","1234");
    System.out.println("Connection 실행:" + con);
	return con;	   
   }
   public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
   }
   
   public static void close(PreparedStatement pstmt, Connection conn) {
	   try {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
}
