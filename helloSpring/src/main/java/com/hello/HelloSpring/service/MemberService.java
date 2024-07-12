package com.hello.helloSpring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.helloSpring.common.bean.BoardBean;
import com.hello.helloSpring.common.bean.MemberBean;
import com.hello.helloSpring.common.daos.BoardDao;
import com.hello.helloSpring.common.daos.MemberDao;

/**
 * 서비스 여러개의 DAO 객체를 관리해서 일을 수행하는 클래스
 */
@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private BoardDao boardDao;

	@Transactional(rollbackFor = Exception.class) //
	public int deleteMember(MemberBean bean) throws Exception {
		int res = 0;

		BoardBean boardBean = new BoardBean();
		boardBean.setMemberNo(bean.getMemberNo());

		// 1. board 테이블에서 삭제
		int delBoardRow = boardDao.deleteBoardFromMemberNo(boardBean);

		// 2. member 테이블에서 삭제
		int delMemRow = memberDao.deleteMember(bean);

		if (delBoardRow >= 0 && delMemRow > 0) {
			return 1;
		} else {
			throw new Exception("멤버 테이블의 멤버가 삭제되지 않았습니다.");
		} // if ~ else

	}// method deleteMember

	
	public int insertMember(MemberBean bean) {
		return memberDao.insertMember(bean);
	}
	
	public int updateMember(MemberBean bean) {
		return updateMember(bean);
	}
	
	public MemberBean selectMember(MemberBean bean) {
		return memberDao.selectMember(bean);
	}
	
	public List<MemberBean> selectMemberList(MemberBean bean) {
		return memberDao.selectMemberList(bean);
	}
	
}// class
