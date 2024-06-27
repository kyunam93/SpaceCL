package db;

import lombok.Data;

@Data
public class BoardBean {

	private String board_no;
	private String member_no;
	private String title;
	private String contents;
	private String count;
	private String secret_yn;
	private String reg_dt;
	
}// class
