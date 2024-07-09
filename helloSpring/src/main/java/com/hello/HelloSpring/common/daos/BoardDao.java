package com.hello.helloSpring.common.daos;

import org.apache.ibatis.annotations.Mapper;

import com.hello.helloSpring.common.bean.BoardBean;

@Mapper
public interface BoardDao {
		
	// 회원번호와 일치하는 게시글 삭제
	public int deleteBoardFromMemberNo(BoardBean bean);
	
}
