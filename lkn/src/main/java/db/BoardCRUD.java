package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.StringUtils;

public class BoardCRUD extends CommonCRUD {

	// init member variable
	Connection conn;

	// init constructor
	public BoardCRUD() {
		System.out.println("[CALL] BoardCRUD 생성자");
		if (conn == null)
			conn = getConnection();
	}// constructor

	// init method
	public int insertBoard(BoardBean bBean) {
		System.out.println("[Call] Method - insertBoard()!");

		int cnt = 0;

		String sql = "INSERT INTO board (title, contents, member_no) VALUES(?, ?, ?)";

		try {

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bBean.getTitle());
			pstmt.setString(2, bBean.getContents());
			pstmt.setString(3, bBean.getMember_no());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("[SUCCESS] 게시판 입력 성공");
				conn.commit();
			} else {
				System.out.println("[FAIL] 게시판 입력 실패");
				conn.rollback();
			}

		} catch (Exception e) {

			System.out.println("[ERROR] insert failed!");
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception e2) {
			}
		} // try ~ catch

		return cnt;
	}

	public int getListCnt(String searchWord) {
		System.out.println("[Call] Method - getBoard()!");

		int listCnt = 0;

		String sql = "SELECT count(*) FROM board";
		sql += " WHERE title LIKE '%" + searchWord + "%' OR contents LIKE '%" + searchWord + "%' ";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				listCnt = rs.getInt(1);
				System.out.println(listCnt);
			}

			if (listCnt > 0) {
				System.out.println("[SUCCESS] 총 게시글 수 조회 성공");
			} else {
				System.out.println("[FAILED] 총 게시글 수 조회 성공");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCnt;

	}

	public BoardBean getBoard(String boardNo) {
		System.out.println("[Call] Method - getBoard()!");

		BoardBean bean = new BoardBean();

		String sql = "SELECT board_no, member_no, title, contents, "
				+ "(SELECT name FROM member where member_no = b.member_no) writer, "
				+ "count, secret_yn, date_format(reg_dt, '%Y-%m-%d') reg_dt " + "FROM board b ";
		sql += "WHERE board_no = '" + boardNo + "'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				bean.setBoard_no(rs.getString("board_no"));
				bean.setMember_no(rs.getString("member_no"));
				bean.setTitle(rs.getString("title"));
				bean.setContents(rs.getString("contents"));
				bean.setWriter(rs.getString("writer"));
				bean.setCount(rs.getString("count"));
				bean.setSecret_yn(rs.getString("secret_yn"));
				bean.setReg_dt(rs.getString("reg_dt"));
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 게시판 조회 실패");

			e.printStackTrace();
		}

		return bean;
	}

	public List<BoardBean> getBoardList(String searchWord) {
		System.out.println("[Call] Method - selectBoardList()!");
		System.out.println("[DATA] searchWord : " + searchWord);

		List<BoardBean> bList = new ArrayList<BoardBean>();

		String sql = "SELECT board_no, member_no, title, contents, "
				+ "(SELECT name FROM member where member_no = b.member_no) writer, "
				+ "count, secret_yn, date_format(reg_dt, '%Y-%m-%d') reg_dt " + "FROM board b ";
		if (!StringUtils.isNullOrEmpty(searchWord))
			sql += "WHERE title LIKE '%" + searchWord + "%' OR contents LIKE '%" + searchWord + "%' ";
		sql += "ORDER BY board_no DESC";

		System.out.println("[SQL] : " + sql);

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BoardBean bBean = new BoardBean();

				bBean.setBoard_no(rs.getString("board_no"));
				bBean.setMember_no(rs.getString("member_no"));
				bBean.setTitle(rs.getString("title"));
				bBean.setContents(rs.getString("contents"));
				bBean.setWriter(rs.getString("writer"));
				bBean.setCount(rs.getString("count"));
				bBean.setSecret_yn(rs.getString("secret_yn"));
				bBean.setReg_dt(rs.getString("reg_dt"));

				bList.add(bBean);
			}

			if (bList != null) {
				System.out.println("[SUCCESS] 게시판 리스트 조회 성공");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 게시판 리스트 조회 실패");

			e.printStackTrace();
		}

		return bList;
	}

	public int updateBoard(BoardBean bean) {
		System.out.println("[Call] Method - selectBoardList()!");
		System.out.println("[DATA] bean : " + bean);

		int cnt = 0;

		String sql = "UPDATE board SET title = ?, contents = ? WHERE board_no = ?";
		System.out.println("[SQL] : " + sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getContents());
			pstmt.setString(3, bean.getBoard_no());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("[SUECCESS] 게시글 업데이트 성공");
			} else {
				System.out.println("[FAILED] 게시글 업데이트 실패");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 게시글 업데이트 에러");
			e.printStackTrace();
		}

		return cnt;
	}

	public int deleteBoard(String boardNo) {
		System.out.println("[Call] Method - selectBoardList()!");
		System.out.println("[DATA] boardNo : " + boardNo);

		int cnt = 0;

		String sql = "DELETE FROM board WHERE board_no = ?";
		System.out.println("[SQL] : " + sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("[SUECCESS] 게시글 삭제 성공");
			} else {
				System.out.println("[FAILED] 게시글 삭제 실패");
			}

		} catch (Exception e) {
			System.out.println("[ERROR] 게시글 삭제 에러");
			e.printStackTrace();
		}

		return cnt;
	}

}// class
