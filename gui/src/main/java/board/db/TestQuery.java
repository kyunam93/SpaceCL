package board.db;

import java.util.List;

public class TestQuery {

	public static void main(String[] args) {

		MemberBean mBean = new MemberBean();
		MemberCRUD mCRUD = new MemberCRUD();

		// 회원가입
//		mBean.setId("spacecl12");
//		mBean.setPw("1234");
//		mBean.setName("임규남");
//		mBean.setEmail("spacecl1234@naver.com");
//		mBean.setAddr("서울특별시 구로구 고척동");
//		mBean.setBirthdate("19930127");
//		mBean.setHp("01074495355");
//
//		mCRUD.insertMember(mBean);

		// 회원 리스트 취득
		List<MemberBean> list = mCRUD.getMemberList();
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				mBean = list.get(i);
				mBean.printAll();

			} // for

		} // if

		System.out.println();

		// 회원 1건 정보 취득
		mBean = mCRUD.getMember("spacecl1");
		mBean.printAll();
		
		// 회원 아이디, 비번 조회
		boolean isFind = mCRUD.getFindMember("spacecl", "1234");
		System.out.println(isFind);

	}// main

}// class
