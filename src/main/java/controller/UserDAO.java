package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBPoolUtil;
import model.UserVO;

public class UserDAO {
	// 정적 자신 멤버 필드
	private static UserDAO instance = null;

	// private 생성자
	private UserDAO() {
	}

	// 외부 접근 메소드
	public static UserDAO getInstance() {
		if (instance == null) {
			synchronized (UserDAO.class) {
				instance = new UserDAO();
			}
		}
		return instance;
	}

	// 회원가입 쿼리변경
	public boolean memberInsert(UserVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = DBPoolUtil.makeConnection();
			String strQuery = "INSERT INTO USERT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, vo.getMemberID());
			pstmt.setString(2, vo.getMemberPW());
			pstmt.setString(3, vo.getMemberName());
			pstmt.setString(4, vo.getMemberEmail());
			pstmt.setString(5, vo.getMemberType());
			pstmt.setString(6, vo.getPostcode());
			pstmt.setString(7, vo.getAddress());
			pstmt.setString(8, vo.getDetailAddress());
			pstmt.setString(9, vo.getExtraAddress());
			pstmt.setString(10, vo.getPhoneNum());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return flag;
	}

	// 아이디 찾기
	public String idSearch(String memberName, String phoneNum) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberID = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT MEMBERID FROM USERT WHERE MEMBERNAME=? AND PHONENUM=?");
			pstmt.setString(1, memberName);
			pstmt.setString(2, phoneNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberID = rs.getString("MEMBERID");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return memberID;
	}

	// 비밀번호 찾기
	public String pwSearch(String memberID, String memberName, String phoneNum) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberpw = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn
					.prepareStatement("SELECT MEMBERPW FROM USERT WHERE MEMBERID=? AND MEMBERNAME=? AND PHONENUM=?");
			pstmt.setString(1, memberID);
			pstmt.setString(2, memberName);
			pstmt.setString(3, phoneNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberpw = rs.getString("MEMBERPW");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return memberpw;
	}

	// 아이디 중복 확인
	public boolean idCheck(String id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM USERT WHERE MEMBERID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return result;
	}

	// 로그인
	public int loginCheck(String memberID, String memberPW) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;
		try {
			conn = DBPoolUtil.makeConnection();
			String strQuery = "SELECT MEMBERPW FROM USERT WHERE MEMBERID=?";
			pstmt = conn.prepareStatement(strQuery);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPass = rs.getString("MEMBERPW");
				if (memberPW.equals(dbPass))
					check = 1;
				else
					check = 0;
			}
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return check;
	}

	// 내 정보 보여주기
	public UserVO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO vo = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM USERT WHERE MEMBERID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 해당 아이디에 대한 회원이 존재
				vo = new UserVO();
				vo.setMemberID(rs.getString("MEMBERID"));
				vo.setMemberPW(rs.getString("MEMBERPW"));
				vo.setMemberName(rs.getString("MEMBERNAME"));
				vo.setMemberEmail(rs.getString("MEMBEREMAIL"));
				vo.setMemberType(rs.getString("MEMBERTYPE"));
				vo.setPostcode(rs.getString("POSTCODE"));
				vo.setAddress(rs.getString("ADDRESS"));
				vo.setDetailAddress(rs.getString("DETAILADDRESS"));
				vo.setExtraAddress(rs.getString("EXTRAADDRESS"));
				vo.setPhoneNum(rs.getString("PHONENUM"));
				vo.setRegDate(rs.getDate("REGDATE"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return vo;
	}

	// 정보수정
	public int updateMember(UserVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement(
					"UPDATE USERT SET MEMBERPW=?, MEMBERNAME=?, MEMBEREMAIL=?, POSTCODE=?, ADDRESS=?, DETAILADDRESS=?, EXTRAADDRESS=?, PHONENUM=? WHERE MEMBERID=?");
			pstmt.setString(1, vo.getMemberPW());
			pstmt.setString(2, vo.getMemberName());
			pstmt.setString(3, vo.getMemberEmail());
			pstmt.setString(4, vo.getPostcode());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getDetailAddress());
			pstmt.setString(7, vo.getExtraAddress());
			pstmt.setString(8, vo.getPhoneNum());
			pstmt.setString(9, vo.getMemberID());
			count = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		return count;
	}

	// 회원탈퇴
	public int deleteMember(String id, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbPass = null;// 데이터베이스에 실제 저장된 패스워드
		int result = -1;// 결과치
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT MEMBERPW FROM USERT WHERE MEMBERID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPass = rs.getString("MEMBERPW");
				if (dbPass.equals(pass)) {// true - 본인 확인
					pstmt = conn.prepareStatement("DELETE FROM USERT WHERE MEMBERID = ?");
					pstmt.setString(1, id);
					result = pstmt.executeUpdate();// 회원탈퇴 성공
				} else { // 본인확인 실패 - 비밀번호 오류
					result = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return result;
	}

	// 관리자 전용 멤버 리스트 불러오기
	public List<UserVO> getAllMembers() {
		List<UserVO> list = new ArrayList<UserVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO vo = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM USERT ORDER BY REGDATE ASC");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (!rs.getString("MEMBERID").equals("admin1234")) {
					vo = new UserVO();
					vo.setMemberID(rs.getString("MEMBERID"));
					vo.setMemberPW(rs.getString("MEMBERPW"));
					vo.setMemberName(rs.getString("MEMBERNAME"));
					vo.setMemberEmail(rs.getString("MEMBEREMAIL"));
					vo.setMemberType(rs.getString("MEMBERTYPE"));
					vo.setPostcode(rs.getString("POSTCODE"));
					vo.setAddress(rs.getString("ADDRESS"));
					vo.setDetailAddress(rs.getString("DETAILADDRESS"));
					vo.setExtraAddress(rs.getString("EXTRAADDRESS"));
					vo.setPhoneNum(rs.getString("PHONENUM"));
					vo.setRegDate(rs.getDate("REGDATE"));
					list.add(vo);
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return list;
	}

	// 삭제
	public int deleteAllMembers(String[] memberIDs) {
		if (memberIDs == null) {
			return -1;
		}
		String sql = "DELETE FROM USERT WHERE MEMBERID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;// 결과치
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement(sql);
			for (String data : memberIDs) {
				pstmt.setString(1, data);
				result += pstmt.executeUpdate();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		return result;
	}

	// 멤버 타입 가져오기
	public String getMemType(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String type = null;

		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT MEMBERTYPE FROM USERT WHERE MEMBERID = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				type = rs.getString("MEMBERTYPE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return type;
	}
}
