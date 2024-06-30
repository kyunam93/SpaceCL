package BoardTest.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.util.StringUtils;

public class MemberCRUD extends CommonCRUD {

	private Connection conn;

	public MemberCRUD() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		conn = getConnection();

	}// constructor

	public MemberBean checkLogin(String id, String pw) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		MemberBean mBean = new MemberBean();
		
		String sql = "SELECT member_no, id, name FROM member WHERE id = '" + id + "' AND pw = '" + pw + "'";
		System.out.println("[SQL] " + sql);
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				mBean.setMember_no(rs.getString(1));
				mBean.setId(rs.getString(2));
				mBean.setName(rs.getString(3));
				System.out.println("[DATA] " + mBean);
			} // if
			
			if (!StringUtils.isNullOrEmpty(mBean.getMember_no())) {
				System.out.println("[성공] 아이디 비번 체크");

				updateLastLoginDt(mBean);
			} else {
				System.out.println("[실패] 아이디 비번 체크");
			} // if ~ else

		} catch (Exception e) {
			System.out.println("[에러] 아이디 비번 체크");
			e.printStackTrace();
		} // try ~ catch

		return mBean;
	}// method checkLogin

	public void updateLastLoginDt(MemberBean mBean) {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		int cnt = 0;
		
		String sql = "UPDATE member SET last_login_dt = now() WHERE member_no = ?";
		System.out.println("[SQL] " + sql);
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mBean.getMember_no());
			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("[성공] 마지막 로그인 날짜 업데이트");
			} else {
				System.out.println("[성공] 마지막 로그인 날짜 업데이트");
			} // if ~ else

		} catch (Exception e) {
			e.printStackTrace();
		} // try ~ catch

	}// method updateLastLoginDt

}// class