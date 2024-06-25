package swing.gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectService {

	// init variable
	private ResultSet rs = null;
	private Statement stmt = null;
	private String sql = null;

	// init method
	
	/**
	 * 조회 메소드
	 * @param conn			: 연결된 디비 객체
	 * @param lastInsertId	: 테이블의 마지막 데이터 행의 키값, null 이면 전체 데이터 조회
	 * @return				: 조회한 데이터 객체
	 */
	public Doit2Bean select(Connection conn, String lastInsertId) {

		// init instance
		Doit2Bean bean = new Doit2Bean();

		try {

			sql = "SELECT * FROM doit2";

			if (lastInsertId != null)
				sql += " WHERE col1 = " + lastInsertId;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs != null) {
				
				System.out.println("데이터 조회 성공!");

				while (rs.next()) {

					if (rs.getRow() == 1) {

						bean.setCol1(rs.getInt(1));
						bean.setCol2(rs.getString(2));
						bean.setCol2(rs.getString(3));
						bean.setCol2(rs.getString(4));
						bean.setCol2(rs.getString(5));
						bean.setCol2(rs.getString(6));
						bean.setCol2(rs.getString(7));

					} // if

					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
									+ "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7));
				} // while

			} // if

			else {
				System.out.println("데이터 조회 실패!");
			} // else

		} // try

		catch (SQLException e) {
			e.printStackTrace();
		} // catch

		return bean;

	}// method

}// class
