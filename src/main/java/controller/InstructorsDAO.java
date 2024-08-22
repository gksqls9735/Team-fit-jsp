package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DBPoolUtil;
import model.InstJoinData;
import model.InstructorsVO;

public class InstructorsDAO {
	private static InstructorsDAO instance = null;

	private InstructorsDAO() {
	}

	public static InstructorsDAO getInstance() {
		if (instance == null) {
			synchronized (InstructorsDAO.class) {
				instance = new InstructorsDAO();
			}
		}
		return instance;
	}
	
	public int insertInst(InstructorsVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		System.out.println("id" + vo.getInstructorID());
		System.out.println("getSportType" + vo.getSportType());
		System.out.println("getBio" + vo.getBio());
		System.out.println("getExperienceYears" + vo.getExperienceYears());
		System.out.println("getCertifications" + vo.getCertifications());
		System.out.println("getLocation" + vo.getLocation());
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("INSERT INTO INSTRUCTORS VALUES(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, vo.getInstructorID());
			pstmt.setString(2, vo.getSportType());
			pstmt.setString(3, vo.getBio());
			pstmt.setInt(4, vo.getExperienceYears());
			pstmt.setString(5, vo.getCertifications());
			pstmt.setString(6, vo.getLocation());
			flag = pstmt.executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		
		return flag;
	}
	
	public ArrayList<InstJoinData> getInstList(){
		ArrayList<InstJoinData> list = new ArrayList<InstJoinData>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT I.*, U.MEMBERNAME FROM INSTRUCTORS I INNER JOIN USERT U ON I.InstructorID = U. MEMBERID");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				InstJoinData vo = new InstJoinData();
				vo.setInstructorID(rs.getString("INSTRUCTORID"));
				vo.setMemberName(rs.getString("MEMBERNAME"));
				vo.setSportType(rs.getString("SPORTTYPE"));
				vo.setBio(rs.getString("BIO"));
				vo.setExperienceYears(rs.getInt("EXPERIENCEYEARS"));
				vo.setCertifications(rs.getString("CERTIFICATIONS"));
				vo.setLocation(rs.getString("LOCATION"));
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
