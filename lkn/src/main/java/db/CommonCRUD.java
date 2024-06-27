package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonCRUD {

	// init member variable
	private Connection conn;

	
	// init method
	public Connection getConnection() {

		if (conn == null) {

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://localhost/board";

				conn = DriverManager.getConnection(url, "root", "1234");
				
			} catch (Exception e) {
				e.printStackTrace();
			} // try ~ catch

		} // if
		
		System.out.println("DataBase Connection Complite!");

		return conn;
		
	}// method

}// class
