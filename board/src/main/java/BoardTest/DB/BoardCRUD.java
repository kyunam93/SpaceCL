package BoardTest.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.StringUtils;

public class BoardCRUD extends CommonCRUD {

	private Connection conn;

	public BoardCRUD() {
		System.out.println("\n[CALL] BoardCRUD");

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
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

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

	public List<BoardBean> getBoardList(String searchWord, int curPage, int pageRows) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		List<BoardBean> list = new ArrayList<BoardBean>();

		int offset = (curPage - 1) * pageRows;

		String sql = "SELECT board_no, title, (SELECT id FROM member WHERE member_no = b.member_no) writer, ";
		sql += "count, DATE_FORMAT(reg_dt, '%Y-%m-%d') reg_dt FROM board b ";
		sql += " WHERE title LIKE '%" + searchWord + "%' OR contents LIKE '%" + searchWord + "%'";
		sql += " ORDER BY board_no DESC LIMIT " + offset + ", " + pageRows;
		System.out.println("[SQL] " + sql);

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

	public BoardBean getBoard(String board_no) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		BoardBean bean = new BoardBean();

		String sql = "SELECT board_no, title, contents, member_no FROM board WHERE board_no = " + board_no;
		System.out.println("[SQL] " + sql);

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				bean.setBoard_no(rs.getString(1));
				bean.setTitle(rs.getString(2));
				bean.setContents(rs.getString(3));
				bean.setMember_no(rs.getString(4));
			} // if

			System.out.println("[DATA] bean " + bean);
			if (StringUtils.isNullOrEmpty(bean.getBoard_no())) {
				System.out.println("[실패] 게시글 조회");
			} else {
				System.out.println("[성공] 게시글 조회");
			} // if ~ else
		} catch (Exception e) {
			System.out.println("[에러] 게시글 조회");

			e.printStackTrace();
		} // try ~ catch

		return bean;
	}// method getBoard

	public int updateBoard(BoardBean bean) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		int cnt = 0;

		String sql = "UPDATE board SET title = ?, contents = ?, count = count + 1 WHERE board_no = ?";
		System.out.println("[SQL] " + sql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getContents());
			pstmt.setString(3, bean.getBoard_no());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("[성공] 게시글 수정");
			} else {
				System.out.println("[실패] 게시글 수정");
			} // if ~ else

		} catch (Exception e) {
			System.out.println("[에러] 게시글 수정");

			e.printStackTrace();
		} // try ~ catch

		return cnt;
	}// method updateBoard

	public int deleteBoard(BoardBean bean) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		int cnt = 0;

		String sql = "DELETE FROM board WHERE board_no = ?";
		System.out.println("[SQL] " + sql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBoard_no());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("[성공] 게시글 삭제");
			} else {
				System.out.println("[실패] 게시글 삭제");
			} // if ~ else

		} catch (Exception e) {
			System.out.println("[에러] 게시글 삭제");

			e.printStackTrace();
		} // try ~ catch

		return cnt;
	}

}// class
