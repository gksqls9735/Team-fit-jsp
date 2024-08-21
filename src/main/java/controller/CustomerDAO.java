package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBPoolUtil;
import model.CustomerVO;

public class CustomerDAO {
	private static CustomerDAO instance = null;

	private CustomerDAO() {
	}

	public static CustomerDAO getInstance() {
		if (instance == null) {
			synchronized (CustomerDAO.class) {
				instance = new CustomerDAO();
			}
		}
		return instance;
	}

	public boolean insertArticle(CustomerVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		int num = vo.getNum();
		int ref = vo.getRef();
		int step = vo.getStep();
		int depth = vo.getDepth();
		int number = 0;
		String sql = null;

		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT NVL(MAX(NUM), 0) + 1 FROM CUSTOMER");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			} else {
				number = 1;
			}
			if (num != 0) {// 답변글일경우 해당 답변글의 STEP들에 +1을 해주기
				sql = "UPDATE CUSTOMER SET STEP=STEP+1 WHERE REF= ? AND STEP > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1; // 위로 올라가면 STEP >= 이 된다.
				depth = depth + 1;
			} else {// 새 글일 경우
				ref = number;
				step = 0;
				depth = 0;
			} // 쿼리를 작성
			sql = "INSERT INTO CUSTOMER VALUES (CUSTOMER_SEQ.NEXTVAL, ?, ?, ?, 0, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, step);
			pstmt.setInt(3, depth);
			pstmt.setString(4, vo.getMemberID());
			pstmt.setString(5, vo.getType());
			pstmt.setString(6, vo.getTitle());
			pstmt.setString(7, vo.getMessage());
			int i = pstmt.executeUpdate();
			System.out.println(i);
			if (i != 0) {
				flag = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return flag;
	}

	// 하나의 글을 가져오는데 count를 1증가시키지 않음 글수정에 사용
	public CustomerVO updateGet(int num) {
		System.out.println("upadte num= " + num);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO vo = null;
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new CustomerVO();
				vo.setNum(rs.getInt("NUM"));
				vo.setRef(rs.getInt("REF"));
				vo.setStep(rs.getInt("STEP"));
				vo.setDepth(rs.getInt("DEPTH"));
				vo.setReadcount(rs.getInt("READCOUNT"));
				vo.setMemberID(rs.getString("MEMBERID"));
				vo.setType(rs.getString("TYPE"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setMessage(rs.getString("MESSAGE"));
				vo.setCreatedDate(rs.getTimestamp("CREATEDDATE"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return vo;
	}

	// 글 수정하기
	public int updateArticle(CustomerVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String dbID = null;
		String sql = null;
		int result = -1;// 결과값(데이터베이스 오류)
		try {
			conn = DBPoolUtil.makeConnection();
			sql = "UPDATE CUSTOMER SET TYPE=?, TITLE=?, MESSAGE=? WHERE NUM=?";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getType());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getMessage());
				pstmt.setInt(4, vo.getNum());
				result = pstmt.executeUpdate();
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}
		return result;
	}
	// 하나의 글을 가져오는데 count를 1증가 시킴
		public CustomerVO getArticle(int num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CustomerVO vo = null;
			try {
				conn = DBPoolUtil.makeConnection();
				pstmt = conn.prepareStatement("UPDATE CUSTOMER SET READCOUNT=READCOUNT+1 WHERE NUM = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE NUM = ?");
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					vo = new CustomerVO();
			
					vo.setNum(rs.getInt("NUM"));
					vo.setRef(rs.getInt("REF"));
					vo.setStep(rs.getInt("STEP"));
					vo.setDepth(rs.getInt("DEPTH"));
					vo.setReadcount(rs.getInt("READCOUNT"));
					vo.setMemberID(rs.getString("MEMBERID"));
					vo.setType(rs.getString("TYPE"));
					vo.setTitle(rs.getString("TITLE"));
					vo.setMessage(rs.getString("MESSAGE"));
					vo.setCreatedDate(rs.getTimestamp("CREATEDDATE"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
			}
			return vo;
		}
		// 글 삭제하기 원글 삭제 시 답변글도 삭제하기
		public int deleteArticle(int num, String id) {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    String dbID = null;
		    int result = -1;
		    try {
		        conn = DBPoolUtil.makeConnection();
		        pstmt = conn.prepareStatement("SELECT MEMBERID FROM CUSTOMER WHERE NUM = ?");
		        pstmt.setInt(1, num);
		        rs = pstmt.executeQuery();
		        if (rs.next()) {
		            dbID = rs.getString("MEMBERID");
		            if (dbID.equals(id)) {
		                // Delete the article and related answers
		                pstmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE NUM = ? OR REF = ?");
		                pstmt.setInt(1, num);
		                pstmt.setInt(2, num);
		                pstmt.executeUpdate();
		                result = 1; // 글삭제 성공
		            } else {
		                result = 0; // 작성자 불일치
		            }
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    } finally {
		        DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		    }
		    return result;
		}


		
		//글 전체 개수 가져오기
		public int getArticleCount() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			try {
				conn = DBPoolUtil.makeConnection();
				pstmt = conn.prepareStatement("SELECT COUNT(*) FROM CUSTOMER");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
			}
			return x;
		}
		
		//글 전체 목록 데이터 가져오기
		public List<CustomerVO> getArticles(int start, int end) {
			System.out.println("메소드에 제대로 들어옴");
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CustomerVO> articleList = null;
			int count = 0;
			try {
				conn = DBPoolUtil.makeConnection();
				/* 게시판의 글을 참조 순으로 내림차순 하고 step 순으로 오름차순한 정렬 테이블을 만든다.이런 순으로 rownum을 임의로 생성 */
				// 페이지 정렬가능 7페이지 = 61~70번까지 표시 (page -1) * 10 + 1 ~ page * 10
				String sql = "SELECT * FROM (SELECT ROWNUM RNUM, NUM, REF, STEP, DEPTH, READCOUNT, MEMBERID, TYPE, TITLE, MESSAGE, CREATEDDATE FROM (SELECT * FROM CUSTOMER ORDER BY REF DESC, STEP ASC)) WHERE RNUM >= ? AND RNUM <= ?";
				pstmt = conn.prepareStatement(sql);// 수정 <3>
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					articleList = new ArrayList<CustomerVO>(end - start + 1);// 수정<4>
					do {
						System.out.println("몇번이나 도는지 확인하자 " + count++);
						CustomerVO vo = new CustomerVO();
						vo.setNum(rs.getInt("NUM"));
						vo.setRef(rs.getInt("REF"));
						vo.setStep(rs.getInt("STEP"));
						vo.setDepth(rs.getInt("DEPTH"));
						vo.setReadcount(rs.getInt("READCOUNT"));
						vo.setMemberID(rs.getString("MEMBERID"));
						vo.setType(rs.getString("TYPE"));
						vo.setTitle(rs.getString("TITLE"));
						vo.setMessage(rs.getString("MESSAGE"));
						vo.setCreatedDate(rs.getTimestamp("CREATEDDATE"));
						articleList.add(vo);
						System.out.println(vo.toString());
					} while (rs.next());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
			}
			return articleList;
		}
		
}
