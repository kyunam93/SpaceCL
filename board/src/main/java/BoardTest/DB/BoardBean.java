package BoardTest.DB;

import lombok.Data;

@Data
public class BoardBean {

	private String board_no;
	private String title;
	private String contents;
	private String secret_yn;
	private String count;
	private String reg_dt;
	private String member_no;
	private String writer;
	
}
