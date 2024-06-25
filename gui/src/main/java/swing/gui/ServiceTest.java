package swing.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceTest {

	// start app
	public static void main(String[] args) {

		// init variable
		Connection conn = null;
		String lastInsertId = null;
		Doit2Bean selBean = null;

		// create instance
		Doit2Bean bean = new Doit2Bean();
		InsertService is = new InsertService();
		SelectService ss = new SelectService();
		UpdateService us = new UpdateService();
		DeleteService ds = new DeleteService();

		try {
			
			// 1.드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");// JDBC Mysql Driver (pom.xml)

			// 2.연결하기
			String url = "jdbc:mysql://localhost/studydb1";
			conn = DriverManager.getConnection(url, "spacecl", "1234"); // id, pw로 디비연결
			if (conn != null)
				System.out.println("디비 연결 성공!!!");

			// 오토커밋 비활성화
			conn.setAutoCommit(false);

			// 데이터 입력 및 조회
			bean.setCol2("임규남");
			bean.setCol4("1");
			bean.setCol5("2");
			bean.setCol6("3");
			bean.setCol7("4");
			lastInsertId = is.insert(conn, bean);
			selBean = ss.select(conn, lastInsertId);

			System.out.println();

			// 데이터 업데이트 및 조회
			bean.setCol1(selBean.getCol1());
			bean.setCol2("임영웅");
			bean.setCol4("11111");
			bean.setCol5("22222");
			us.update(conn, bean);
			ss.select(conn, null);

			System.out.println();

			// 데이터 삭제 및 조회
			ds.delete(conn, bean);
			ss.select(conn, null);

		} // try

		catch (Exception e) {
			e.printStackTrace();
		} // catch

		finally {

			try {

				if (conn != null)
					conn.close();

			} // try

			catch (SQLException e) {
				e.printStackTrace();
			} // catch

		} // finally

	}// main

}// class
