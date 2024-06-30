package BoardTest.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BoardCRUD extends CommonCRUD {

	private Connection conn;

	public BoardCRUD() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		conn = getConnection();
	}// constructor

	public int insertBoard(MemberBean mBean, String title, String contents) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		int cnt = 0;
		
		String sql = "INSERT INTO board (title, contents, member_no) VALUES (?, ? , ?)";
		System.out.println("[SQL] " + sql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, mBean.getMember_no());
			
			cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("[성공] 글쓰기");
			} else {
				System.out.println("[실패] 글쓰기");
			}
			
		} catch (Exception e) {
			System.out.println("[에러] 글쓰기");
			e.printStackTrace();
		}

		return cnt;
	}// method insertBoard
	
}// class
