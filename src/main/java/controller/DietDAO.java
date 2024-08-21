package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jdbc.DBPoolUtil;
import model.DietVO;

public class DietDAO {
	private static DietDAO instance = null;

	private DietDAO() {
	}

	public static DietDAO getInstance() {
		if (instance == null) {
			synchronized (DietDAO.class) {
				instance = new DietDAO();
			}
		}
		return instance;
	}

	public boolean insertArticle(ArrayList<DietVO> list) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;

		int i = 0;

		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO MEAL VALUES (MEAL_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			for (DietVO data : list) {
				pstmt.setString(1, data.getMeal_clsf_nm());
				pstmt.setString(2, data.getMeal_nm());
				pstmt.setString(3, data.getCook_mth_cont());
				pstmt.setString(4, data.getMatrl_nm());
				pstmt.setDouble(5, data.getCalorie_qy());
				pstmt.setDouble(6, data.getCarboh_qy());
				pstmt.setDouble(7, data.getProtein_qy());
				pstmt.setDouble(8, data.getFat_qy());
				pstmt.setDouble(9, data.getCellu_qy());
				pstmt.setDouble(10, data.getCalcium_qy());
				pstmt.setDouble(11, data.getPhosph_qy());
				pstmt.setDouble(12, data.getFe_qy());
				pstmt.setDouble(13, data.getNatrium_qy());
				pstmt.setDouble(14, data.getPotassium_qy());
				pstmt.setDouble(15, data.getVitamina_qy());
				pstmt.setDouble(16, data.getThiamin_qy());
				pstmt.setDouble(17, data.getRiboflamin_qy());
				pstmt.setDouble(18, data.getNiacin_qy());
				pstmt.setDouble(19, data.getVitaminc_qy());
				pstmt.setString(20, data.getMeal_pictr_file_nm());

				i += pstmt.executeUpdate();
			}

			if (i > 0) {
				flag = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(pstmt, conn);
		}

		return flag;
	}

	public ArrayList<DietVO> getDiet() {
		ArrayList<DietVO> list = new ArrayList<DietVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEAL ORDER BY NO ASC");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DietVO data = new DietVO();
				data.setMeal_clsf_nm(rs.getString("MEAL_CLSF_NM"));
				data.setMeal_nm(rs.getString("MEAL_NM"));
				data.setCook_mth_cont(rs.getString("COOK_MTH_CONT"));
				data.setMatrl_nm(rs.getString("MATRL_NM"));
				data.setCalorie_qy(rs.getDouble("CALORIE_QY"));
				data.setCarboh_qy(rs.getDouble("CARBOH_QY"));
				data.setProtein_qy(rs.getDouble("PROTEIN_QY"));
				data.setFat_qy(rs.getDouble("FAT_QY"));
				data.setCellu_qy(rs.getDouble("CELLU_QY"));
				data.setCalcium_qy(rs.getDouble("CALCIUM_QY"));
				data.setPhosph_qy(rs.getDouble("PHOSPH_QY"));
				data.setFe_qy(rs.getDouble("FE_QY"));
				data.setNatrium_qy(rs.getDouble("NATRIUM_QY"));
				data.setPotassium_qy(rs.getDouble("POTASSIUM_QY"));
				data.setVitamina_qy(rs.getDouble("VITAMINA_QY"));
				data.setThiamin_qy(rs.getDouble("THIAMIN_QY"));
				data.setRiboflamin_qy(rs.getDouble("RIBOFLAMIN_QY"));
				data.setNiacin_qy(rs.getDouble("NIACIN_QY"));
				data.setVitaminc_qy(rs.getDouble("VITAMINC_QY"));

				list.add(data);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return list;
	}
	
	public DietVO getDietOne() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DietVO vo = null;
		
		try {
			conn = DBPoolUtil.makeConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEAL WHERE NO = 1");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo= new DietVO();
				vo.setMeal_clsf_nm(rs.getString("MEAL_CLSF_NM"));
				vo.setMeal_nm(rs.getString("MEAL_NM"));
				vo.setCook_mth_cont(rs.getString("COOK_MTH_CONT"));
				vo.setMatrl_nm(rs.getString("MATRL_NM"));
				vo.setCalorie_qy(rs.getDouble("CALORIE_QY"));
				vo.setCarboh_qy(rs.getDouble("CARBOH_QY"));
				vo.setProtein_qy(rs.getDouble("PROTEIN_QY"));
				vo.setFat_qy(rs.getDouble("FAT_QY"));
				vo.setCellu_qy(rs.getDouble("CELLU_QY"));
				vo.setCalcium_qy(rs.getDouble("CALCIUM_QY"));
				vo.setPhosph_qy(rs.getDouble("PHOSPH_QY"));
				vo.setFe_qy(rs.getDouble("FE_QY"));
				vo.setNatrium_qy(rs.getDouble("NATRIUM_QY"));
				vo.setPotassium_qy(rs.getDouble("POTASSIUM_QY"));
				vo.setVitamina_qy(rs.getDouble("VITAMINA_QY"));
				vo.setThiamin_qy(rs.getDouble("THIAMIN_QY"));
				vo.setRiboflamin_qy(rs.getDouble("RIBOFLAMIN_QY"));
				vo.setNiacin_qy(rs.getDouble("NIACIN_QY"));
				vo.setVitaminc_qy(rs.getDouble("VITAMINC_QY"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBPoolUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return vo;
	}
	public static void main(String[] args) {
		DietDAO dao = DietDAO.getInstance();
		
		ArrayList<DietVO> list = dao.getDiet();
		
		for(DietVO data : list) {
			System.out.println(data.toString());
		}
	}
}
