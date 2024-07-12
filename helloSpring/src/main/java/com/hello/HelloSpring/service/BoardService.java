package com.hello.helloSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hello.helloSpring.common.bean.BoardBean;
import com.hello.helloSpring.common.daos.BoardDao;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public int deleteBoardFromMemberNo(BoardBean bean) {
		return boardDao.deleteBoardFromMemberNo(bean);
	}// method deleteBoardFromMemberNo
	
	public int insertBoard(BoardBean bean) {
		return boardDao.insertBoard(bean); 
	}// method insertBoard
	
	public List<BoardBean> selectBoardList(BoardBean bean){
		return boardDao.selectBoardList(bean);
	}// method selectBoardList
	
	public BoardBean selectBoard(BoardBean bean) {
		return boardDao.selectBoard(bean);
	}// method selectBoard

	public int updateBoard(BoardBean bean) {
		return boardDao.updateBoard(bean); 
	}// method updateBoard
	
	public int deleteBoard(BoardBean bean) {
		return boardDao.deleteBoard(bean); 
	}// method deleteBoard
	
}// class
