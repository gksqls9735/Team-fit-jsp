package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DBPoolUtil;
import model.CartVO;
import model.JoinData;
import model.LectureVO;
import model.UserVO;

public class CartDAO {
	private static CartDAO instance = null;

	private CartDAO() {
	}

	public static CartDAO getInstance() {
		if (instance == null) {
			synchronized (CartDAO.class) {
				instance = new CartDAO();
			}
		}
		return instance;
	}
	
	public int insertCart(CartVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("INSERT INTO CART VALUES (CART_SEQ.NEXTVAL, ?, ?)");
			pstmt.setInt(1, vo.getCode());
			pstmt.setString(2, vo.getMemberID());
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		
		return flag;
	}
	
	public int checkCart(CartVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flag = -1;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CART WHERE CODE = ? AND MEMBERID = ?");
			pstmt.setInt(1, vo.getCode());
			pstmt.setString(2, vo.getMemberID());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				flag = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// 3가지의 정보를 모두 담을 class를 하나 더 만들어야 하나...
	public ArrayList<JoinData> getMyList(String memberID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<JoinData> list = new ArrayList<JoinData>();
		CartVO cvo = null;
		LectureVO lvo = null;
		UserVO uvo = null;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT C.NO, C.CODE, L.TITLE, L.L_DESCRIPTION, L.L_TYPE, L.DATE_TIME, L.L_LOCATION, L.FEE, L.REQUIREMENTS, U.MEMBERNAME FROM CART C INNER JOIN LECTURE L ON C.CODE = L.CODE INNER JOIN USERT U ON L.INSTRUCTORID = U.MEMBERID WHERE C.MEMBERID = ? ORDER BY NO DESC");
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cvo = new CartVO();
				cvo.setNo(rs.getInt("NO"));
				cvo.setCode(rs.getInt("CODE"));
				
				lvo = new LectureVO();
				lvo.setTitle(rs.getString("TITLE"));
				lvo.setL_description(rs.getString("L_DESCRIPTION"));
				lvo.setL_type(rs.getString("L_TYPE"));
				lvo.setDate_time(rs.getString("DATE_TIME"));
				lvo.setL_location(rs.getString("L_LOCATION"));
				lvo.setFee(rs.getInt("FEE"));
				lvo.setRequirements(rs.getString("REQUIREMENTS"));
				
				uvo = new UserVO();
				uvo.setMemberName(rs.getString("MEMBERNAME"));
				
				JoinData jd = new JoinData(cvo, lvo, uvo);
				System.out.println(jd.toString());
				list.add(jd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public int cancelCart(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("DELETE FROM CART WHERE NO = ?");
			pstmt.setInt(1, no);
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(null, pstmt, conn);
		}
		
		return flag;
	}
}
