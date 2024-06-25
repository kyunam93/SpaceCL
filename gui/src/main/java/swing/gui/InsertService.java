package swing.gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertService {

	// init variable;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private String lastInsertId = null;
	private String sql1 = null;
	private String sql2 = null;
	private int result = 0;

	// init method
	
	/**
	 * 입력 메소드
	 * @param conn 	: 연결된 디비 객체
	 * @param bean 	: 입력할 데이터 정보
	 * @return 		: 데이터베이스에 입력한 마지막 행의 키값
	 */
	public String insert(Connection conn, Doit2Bean bean) {

		sql1 = "INSERT INTO doit2 (col2, col4, col5, col6, col7) VALUES(?, ?, ?, ?, ?)";

		try {
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, bean.getCol2());
			pstmt.setString(2, bean.getCol4());
			pstmt.setString(3, bean.getCol5());
			pstmt.setString(4, bean.getCol6());
			pstmt.setString(5, bean.getCol7());

			result = pstmt.executeUpdate();

			if (result != 0) {

				sql2 = "SELECT last_insert_id()";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql2);

				if (rs.next()) {

					lastInsertId = rs.getString(1);
					System.out.println("Last Insert Id: " + lastInsertId);

				} // if
				
				System.out.println("데이터 입력 성공");
				conn.commit();

			} // if

			else {
				
				System.out.println("데이터 입력 실패");
				conn.rollback();
				
			} // else

		} // try

		catch (SQLException e) {
			e.printStackTrace();
		} // catch

		finally {

			try {

				if (rs != null)
					rs.close();

				if (stmt != null)
					stmt.close();

				if (pstmt != null)
					pstmt.close();

			} // try

			catch (SQLException e) {
				e.printStackTrace();
			} // catch

		} // finally

		return lastInsertId;

	}// method

}// class
