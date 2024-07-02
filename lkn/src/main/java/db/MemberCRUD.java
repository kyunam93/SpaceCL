package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberCRUD extends CommonCRUD {

	// init member variable
	Connection conn;

	// init constructor
	public MemberCRUD() {
		System.out.println("[CALL] MemberCRUD 생성자");

		if(conn == null) {
			conn = getConnection();	
		}
	}// constructor

	// init method
	public MemberBean getMember(String id, String pw) {
		System.out.println("[Call] Method getMember()!");

		MemberBean mBean = new MemberBean();
		String sql = "SELECT member_no, id, name "
				+ "FROM member "
				+ "WHERE id = '" + id + "' AND pw = '" + pw + "'";

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				mBean.setMember_no(rs.getString(1));
				mBean.setId(rs.getString(2));
				mBean.setName(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR] 회원 조회 실패!");
		}

		return mBean;

	}// method

	public void updateLastLoginDt(String member_no) {

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}// class
