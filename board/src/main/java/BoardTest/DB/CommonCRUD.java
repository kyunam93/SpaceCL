package BoardTest.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonCRUD {

	private Connection conn;
	private String user = "root";
	private String password = "1234";

	public Connection getConnection() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		if (conn == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost:3306/board";

				conn = DriverManager.getConnection(url, user, password);

				if (conn != null) {
					System.out.println("[성공] 데이터베이스 연결");
				} else {
					System.out.println("[실패] 데이터베이스 연결");
				} // if ~ else

			} catch (Exception e) {
				System.out.println("[에러] 데이터베이스 연결");
				e.printStackTrace();
			} // try ~ catch
		} // if

		return conn;
	}// method getConnection

}// class
