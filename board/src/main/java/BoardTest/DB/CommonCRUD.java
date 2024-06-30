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

				String url = "jdbc:mysql://localhost:3306/mydb";

				conn = DriverManager.getConnection(url, user, password);

			} catch (Exception e) {
				e.printStackTrace();
			} // try ~ catch
		} // if

		System.out.println("[성공] 데이터베이스 연결");

		return conn;
	}// method getConnection

}// class
