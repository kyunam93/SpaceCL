package board.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonCRUD {

	// init variable
	public static final String DB_NAME = "board";
	private Connection mConn;

	// init method
	public Connection getConnection() {

		try {

			if (mConn == null) {

				// 1.드라이버 로딩
				Class.forName("com.mysql.cj.jdbc.Driver");// JDBC Mysql Driver (pom.xml)

				// 2.연결하기
				String url = "jdbc:mysql://localhost/" + DB_NAME;
				mConn = DriverManager.getConnection(url, "root", "1234"); // id, pw로 디비연결
			} // if
			
			System.out.println("디비 연결 성공!!!");

		} // try

		catch (Exception e) {
			e.printStackTrace();
		} // catch

		return mConn;

	}// method

}// class
