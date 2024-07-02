package BoardTest.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardCRUD extends CommonCRUD {

	private Connection conn;

	public BoardCRUD() {
		System.out.println("\n[CALL] BoardCRUD");

		conn = getConnection();
	}// constructor

	public int insertBoard(MemberBean mBean, String title, String contents) {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		int cnt = 0;

		String sql = "INSERT INTO board (title, contents, member_no) VALUES (?, ? , ?)";
		System.out.println("[SQL] " + sql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, mBean.getMember_no());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
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

	public int getTotBoardCnt(String searchWord) {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		int totBoardCnt = 0;

		String sql = "SELECT COUNT(*) FROM board ";
		sql += "WHERE title LIKE '%" + searchWord + "%' OR contents LIKE '%" + searchWord + "%'";
		System.out.println("[SQL] " + sql);

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				totBoardCnt = rs.getInt(1);
			} // if

			if (totBoardCnt > 0) {
				System.out.println("[성공] 게시글 총 개수 조회");
			} else {
				System.out.println("[실패] 게시글 총 개수 조회");
			} // if ~ else

		} catch (Exception e) {
			System.out.println("[에러] 게시글 총 개수 조회");
			e.printStackTrace();
		} // try ~ catch

		return totBoardCnt;
	}// method getTotBoardCnt

	public List<BoardBean> getBoardList(String searchWord) {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		List<BoardBean> list = new ArrayList<BoardBean>();

		String sql = "SELECT board_no, title, (SELECT id FROM member WHERE member_no = b.member_no) writer, count, reg_dt FROM board b ";
		sql += " WHERE title LIKE '%" + searchWord + "%' OR contents LIKE '%" + searchWord + "%'";
		sql += " ORDER BY board_no DESC";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setBoard_no(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setWriter(rs.getString(3));
				bean.setCount(rs.getString(4));
				bean.setReg_dt(rs.getString(5));

				list.add(bean);
			} // while

		} catch (Exception e) {
			e.printStackTrace();
		} // try ~ catch

		return list;
	}// method getBoardList

}// class
