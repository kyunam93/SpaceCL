package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class BoardCRUD extends CommonCRUD {

	Connection conn = getConnection();

	public int insertBoard(BoardBean bBean) {

		int result = 0;
		String sql = "INSERT INTO BOARD (member_no, title, contents) VALUES(?, ?, ?)";

		try {
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bBean.getMemberNo());
			pstmt.setString(2, bBean.getTitle());
			pstmt.setString(3, bBean.getContents());
			result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("게시판 입력 성공");
				conn.commit();
			} else {
				System.out.println("게시판 입력 실패");
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} // try~catch

		return result;

	}// method

	/**
	 * 리스트 취득(최신목록 10개만 가져온다)
	 * 
	 * @param pageNo 페이지 목록
	 * @return
	 */
	public List<BoardBean> getBoardList(int pageNo, String searchWord) {

		List<BoardBean> list = new ArrayList<BoardBean>();
		int startOffset = ((pageNo - 1) * 10); // 공식

		String sql = "SELECT board_no, title, contents, count, secret_yn, member_no, "
				+ "(SELECT name FROM member WHERE member_no = b.member_no) memberName, reg_dt " + "FROM board b "
				+ "WHERE title LIKE '%" + searchWord + "%' " + "OR contents LIKE '%" + searchWord + "%' "
				+ "ORDER BY board_no DESC " + "LIMIT " + startOffset + ", 10";

		System.out.println(sql);

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				BoardBean bean = new BoardBean();

				bean.setBoardNo(rs.getString("board_no"));
				bean.setTitle(rs.getString("title"));
				bean.setContents(rs.getString("contents"));
				bean.setCount(rs.getString("count"));
				bean.setSecretYn(rs.getString("secret_yn"));
				bean.setMemberNo(rs.getString("member_no"));
				bean.setMemberName(rs.getString("memberName"));
				bean.setRegDt(rs.getString("reg_dt"));

				list.add(bean);

			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		} // try~catch

		return list;

	}// method getBoardList

	/**
	 * 총 글 개수 확인
	 * 
	 * @return
	 */
	public int getTotalListCnt(String searchWord) {
		int cnt = 0;
		String sql = "SELECT count(*) FROM board " + "WHERE title LIKE '%" + searchWord + "%' " + "OR contents LIKE '%"
				+ searchWord + "%'";

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cnt;
	}// method

	/**
	 * 1건의 상세 게시물 취득한다.
	 * 
	 * @param boardNo
	 * @return
	 */
	public BoardBean getBoard(String boardNo) {

		BoardBean bean = new BoardBean();

		String sql = "SELECT board_no, title, contents, count, secret_yn, member_no, "
				+ "(SELECT name FROM member WHERE member_no = b.member_no) memberName, reg_dt " + "FROM board b "
				+ "WHERE board_no = " + boardNo;

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				bean.setBoardNo(rs.getString("board_no"));
				bean.setTitle(rs.getString("title"));
				bean.setContents(rs.getString("contents"));
				bean.setCount(rs.getString("count"));
				bean.setSecretYn(rs.getString("secret_yn"));
				bean.setMemberNo(rs.getString("member_no"));
				bean.setMemberName(rs.getString("memberName"));
				bean.setRegDt(rs.getString("reg_dt"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} // try ~ catch

		return bean;
	}// method

	/**
	 * 1건 업데이트
	 * 
	 * @param bBean
	 * @return 업데이트 된 로우 개수
	 */
	public int updateBoard(BoardBean bBean) {

		int cntRow = 0;

		if (StringUtils.isNotEmpty(bBean.getBoardNo())) {
			return cntRow;
		} // if

		String sql = "UPDATE board SET reg_dt = now() ";
		
		if (StringUtils.isNotEmpty(bBean.getTitle()))
			sql += ", title = '" + bBean.getTitle() + "'";
		if (StringUtils.isNotEmpty(bBean.getContents()))
			sql += ", contents = '" + bBean.getContents() + "'";
		sql += ", count = count + 1";
		sql += " WHERE board_no = ?";
		
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bBean.getBoardNo());
			
			cntRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}// try ~ catch

		return cntRow;

	}// method

	public int deleteBoard(String boardNo) {

		System.out.println(boardNo);
		
		int cntRow = 0;

		if (StringUtils.isEmpty(boardNo)) {
			return cntRow;
		} // if

		String sql = "DELETE FROM board WHERE board_no = ?";
		
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			cntRow = pstmt.executeUpdate();
			System.out.println("삭제성공");
		} catch (Exception e) {
			e.printStackTrace();
		}// try ~ catch
		
		return cntRow;

	}// method

}// class
