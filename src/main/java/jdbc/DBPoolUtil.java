package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBPoolUtil {
	public static Connection makeConnection() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = null;
		try {
			con = pool.getConnection();
			System.out.println("데이타베이스 접속 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이타베이스 연결 실패");
		}
		return con;
	}

	public static void dbReleaseClose(ResultSet rs, Statement stmt, Connection con) {
		ConnectionPool pool = ConnectionPool.getInstance();		// 싱글톤이기 때문에 객체가 이미 존재할 시 객체를 더 생성하지 않음
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (con != null) {
			try {
				pool.releaseConnection(con);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void dbReleaseClose(Statement stmt, Connection con) {
		ConnectionPool pool = ConnectionPool.getInstance();		// 싱글톤이기 때문에 객체가 이미 존재할 시 객체를 더 생성하지 않음
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (con != null) {
			try {
				pool.releaseConnection(con);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
