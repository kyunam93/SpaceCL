package swing.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateService {

	// init variable;
	private PreparedStatement pstmt = null;
	private String sql = null;
	private int result = 0;

	// init method
	
	/**
	 * 수정 메소드
	 * @param conn	: 연결된 디비 객체
	 * @param bean	: 수정할 데이터 정보
	 */
	public void update(Connection conn, Doit2Bean bean) {

		sql = "UPDATE doit2 SET col2 = ?, col4 = ?, col5 = ? WHERE col1 = ?";

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getCol2());
			pstmt.setString(2, bean.getCol4());
			pstmt.setString(3, bean.getCol5());
			pstmt.setInt(4, bean.getCol1());

			result = pstmt.executeUpdate();

			if (result != 0) {
				
				System.out.println("데이터 업데이트 성공!");
				conn.commit();
				
			} // if

			else {
				
				System.out.println("데이터 업데이트 실패!");
				conn.rollback();
				
			} // else

		} // try

		catch (SQLException e) {
			e.printStackTrace();
		} // catch

		finally {

			try {

				if (pstmt != null)
					pstmt.close();

			} // try

			catch (SQLException e) {
				e.printStackTrace();
			} // catch

		} // finally

	}// method

}// class
