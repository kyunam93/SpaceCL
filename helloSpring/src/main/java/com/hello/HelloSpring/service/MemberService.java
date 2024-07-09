package com.hello.helloSpring.service;

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

	@Transactional //
	public int deleteMember(MemberBean bean) throws Exception {
		int res = 0;

		BoardBean boardBean = new BoardBean();
		boardBean.setMemberNo(bean.getMemberNo());

		// 1. board 테이블에서 삭제
		boardDao.deleteBoardFromMemberNo(boardBean);

		// 2. member 테이블에서 삭제
		// memberDao.deleteMember();
		throw new Exception("일부러 에러 발생");

//		return res;
	}

}// class
