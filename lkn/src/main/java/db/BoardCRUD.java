package db;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BoardCRUD extends CommonCRUD {

	// init member variable
	Connection conn;

	
	// init constructor
	public BoardCRUD(){
		conn = getConnection();
	}// constructor
	
	
	// init method
	public int insertBoard() {
		int cnt = 0;

		return cnt;
	}

	public BoardBean selectBoard() {

		BoardBean bBean = new BoardBean();

		return bBean;
	}

	public List<BoardBean> selectBoardList() {

		BoardBean bBean = new BoardBean();
		List<BoardBean> bList = new ArrayList<BoardBean>();

		return bList;

	}

	public int updateBoard() {

		int cnt = 0;

		return cnt;
	}

	public int deleteBoard() {

		int cnt = 0;

		return cnt;
	}

}// class
