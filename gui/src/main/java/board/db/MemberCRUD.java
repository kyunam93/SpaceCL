package board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberCRUD extends CommonCRUD {

	/**
	 * 회원추가
	 * 
	 * @param conn
	 * @param mBean
	 * @return
	 */
	public int insertMember(MemberBean mBean) {

//		MemberBean mBean = new MemberBean();
		Connection conn = getConnection();
		int cnt = 0;
		String sql = "INSERT INTO member (id, pw, name, email, addr, birthdate, hp) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mBean.getId());
			pstmt.setString(2, mBean.getPw());
			pstmt.setString(3, mBean.getName());
			pstmt.setString(4, mBean.getEmail());
			pstmt.setString(5, mBean.getAddr());
			pstmt.setString(6, mBean.getBirthdate());
			pstmt.setString(7, mBean.getHp());

			cnt = pstmt.executeUpdate();

			if (cnt == 1)
				System.out.println("데이터 입력 성공");
			else {

				System.out.println("데이터 입력 실패");
				throw new Exception("insert Fail");

			} // else

		} // try

		catch (Exception e) {
			e.printStackTrace();
		} // catch

		return cnt;

	}// method

	public List<MemberBean> getMemberList() {
		Connection conn = getConnection();
		List<MemberBean> list = new ArrayList<MemberBean>();

		String sql = "SELECT member_no, id, pw, name, email, addr, birthdate, hp, reg_dt, last_login_dt FROM member";

		try {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberBean mBean = new MemberBean();
				mBean.setMemberNo(rs.getString("member_no"));
				mBean.setId(rs.getString("id"));
				mBean.setPw(rs.getString("pw"));
				mBean.setName(rs.getString("name"));
				mBean.setEmail(rs.getString("email"));
				mBean.setAddr(rs.getString("addr"));
				mBean.setBirthdate(rs.getString("birthdate"));
				mBean.setHp(rs.getString("hp"));
				mBean.setRegDt(rs.getString("reg_dt"));
				mBean.setLastLoginDt(rs.getString("last_login_dt"));

				list.add(mBean);

			} // while

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}// method

	public MemberBean getMember(String id) {

		Connection conn = getConnection();
		MemberBean mBean = new MemberBean();

		String sql = "SELECT member_no, id, pw, name, email, addr, birthdate, hp, reg_dt, last_login_dt";
		sql += " FROM member WHERE id = '" + id + "'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					mBean.setMemberNo(rs.getString("member_no"));
					mBean.setId(rs.getString("id"));
					mBean.setPw(rs.getString("pw"));
					mBean.setName(rs.getString("name"));
					mBean.setEmail(rs.getString("email"));
					mBean.setAddr(rs.getString("addr"));
					mBean.setBirthdate(rs.getString("birthdate"));
					mBean.setHp(rs.getString("hp"));
					mBean.setRegDt(rs.getString("reg_dt"));
					mBean.setLastLoginDt(rs.getString("last_login_dt"));
					
					
					System.out.println(mBean);
				} // while

				System.out.println("성공");
			} // if

			else {
				System.out.println("실패");
			} // else

		} catch (Exception e) {
			e.printStackTrace();
		} // try catch

		return mBean;

	}// method

	// TODO
	/**
	 * 아이디 비번으로 멤버찾는 로직
	 * 
	 * @param id
	 * @param pw
	 * @return true : 해당 존재, false : 멤버 부재
	 */
	public boolean getFindMember(String id, String pw) {

		boolean isRtn = false;

		Connection conn = getConnection();
		MemberBean mBean = new MemberBean();

		String sql = "SELECT count(*)";
		sql += " FROM member WHERE id = '" + id + "' AND pw = '" + pw + "'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			int cnt = rs.getInt(1);
			if (cnt > 0) {

				isRtn = true;
				System.out.println("아이디 비번 조회 성공");

			} else {
				System.out.println("아이디 비번 조회 실패!");
			} // if ~ else

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러!");
		} // try catch

		return isRtn;
	}

}// class
