package com.hello.helloSpring.common.daos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hello.helloSpring.common.bean.BoardBean;

@Mapper
public interface BoardDao {
		
	// 회원번호와 일치하는 게시글 삭제
	public int deleteBoardFromMemberNo(BoardBean bean);

	public int insertBoard(BoardBean bean);

	public List<BoardBean> selectBoardList(BoardBean bean);

	public BoardBean selectBoard(BoardBean bean);

	public int updateBoard(BoardBean bean);

	public int deleteBoard(BoardBean bean);
	
}// class
