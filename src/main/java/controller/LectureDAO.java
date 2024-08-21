package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DBPoolUtil;
import model.LectureVO;

public class LectureDAO {
	private static LectureDAO instance = null;

	private LectureDAO() {
	}

	public static LectureDAO getInstance() {
		if (instance == null) {
			synchronized (LectureDAO.class) {
				instance = new LectureDAO();
			}
		}
		return instance;
	}
	
	public int insertLecture(LectureVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("INSERT INTO LECTURE VALUES (MEAL_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, ?)");
			
			pstmt.setString(1, vo.getInstructorId());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getL_description());
			pstmt.setString(4, vo.getL_type());
			pstmt.setString(5, vo.getDate_time());
			pstmt.setString(6, vo.getL_location());
			pstmt.setInt(7, vo.getL_capacity());
			pstmt.setString(8, vo.getL_level());
			pstmt.setString(9, vo.getRequirements());
			pstmt.setInt(10, vo.getFee());
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public ArrayList<LectureVO> getLecture(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LectureVO vo = null;
		ArrayList<LectureVO> list = new ArrayList<LectureVO>();
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM LECTURE ORDER BY DATE_TIME DESC");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new LectureVO();
				vo.setCode(rs.getInt("CODE"));
				vo.setInstructorId(rs.getString("INSTRUCTORID"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setL_description(rs.getString("L_DESCRIPTION"));
				vo.setL_type(rs.getString("L_TYPE"));
				vo.setDate_time(rs.getString("DATE_TIME"));
				vo.setL_location(rs.getString("L_LOCATION"));
				vo.setApplicants(rs.getInt("APPLICANTS"));
				vo.setL_capacity(rs.getInt("L_CAPACITY"));
				vo.setL_level(rs.getString("L_LEVEL"));
				vo.setRequirements(rs.getString("REQUIREMENTS"));
				vo.setFee(rs.getInt("FEE"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		
		return list;
	}
}
