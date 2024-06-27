package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberCRUD extends CommonCRUD {

	// init member variable
	Connection conn;

	// init constructor
	public MemberCRUD() {
		conn = getConnection();
	}// constructor

	// init method
	public int getMember(String id, String pw) {
		System.out.println("Call Method getMember()!");
		
		int cnt = 0;

		String sql = "SELECT count(*) FROM member WHERE id = '" + id + "' AND pw = '" + pw + "'";

		try {
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				cnt = rs.getInt(1);
				System.out.println("cnt : " + cnt);
			}
			
			if(cnt > 0){
				System.out.println("회원 조회 성공!");
			}else {
				System.out.println("회원 조회 실패!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 조회 실패!");
		}

		return cnt;
		
	}// method

}// class
