package com.hello.helloSpring.common.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hello.helloSpring.common.bean.MemberBean;

@Mapper
public interface MemberDao {

	// 1건 조회
	public MemberBean selectMember(MemberBean bean);
	
	// 여러건 조회
	public List<MemberBean> selectMemberList(MemberBean bean);
	
	// 회원 1명 등록
	public int insertMember(MemberBean bean);
	
	// 회원 1명 수정
	public int updateMember(MemberBean bean);
	
	// 회원 1명 삭제
	public int deleteMember(MemberBean bean);
	
}
