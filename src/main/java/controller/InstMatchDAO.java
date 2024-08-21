package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DBPoolUtil;
import model.InstMatchVO;
import oracle.jdbc.proxy.annotation.Pre;

public class InstMatchDAO {
	private static InstMatchDAO instance = null;

	private InstMatchDAO() {
	}

	public static InstMatchDAO getInstance() {
		if (instance == null) {
			synchronized (InstMatchDAO.class) {
				instance = new InstMatchDAO();
			}
		}
		return instance;
	}
	
	public int insertMatch(InstMatchVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("INSERT INTO INSTMATCH VALUES (INSTMATCH_SEQ.NEXTVAL, ?, ?)");
			pstmt.setString(1, vo.getMemberID());
			pstmt.setString(2, vo.getInstructorID());
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		return flag;	
	}
	
	public int checkMatch(String id) {
		int flag = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM INSTMATCH WHERE MEMBERID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return flag;
	}
	
	public String getInstName(String id) {
		String instName = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT MEMBERNAME FROM INSTMATCH I INNER JOIN USERT U ON I.INSTRUCTORID = U.MEMBERID WHERE I.MEMBERID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				instName = rs.getString("MEMBERNAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return instName;
	}
	
	public int cancleMatch(String id) {
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("DELETE FROM INSTMATCH WHERE MEMBERID = ?");
			pstmt.setString(1, id);
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		return flag;
	}
}
