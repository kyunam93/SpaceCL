package swing.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteService {

	// init variable;
	private PreparedStatement pstmt = null;
	private String sql = null;
	private int result = 0;

	// init method
	
	/**
	 * 삭제 메소드
	 * @param conn	: 연결된 디비 객체
	 * @param bean	: 삭제할 데이터 정보
	 */
	public void delete(Connection conn, Doit2Bean bean) {

		sql = "DELETE FROM doit2 WHERE col1 = ?";

		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getCol1());
			
			result = pstmt.executeUpdate();

			if (result != 0) {
				
				System.out.println("데이터 삭제 성공!");
				conn.commit();
				
			} // if

			else {
				
				System.out.println("데이터 삭제 실패!");
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
