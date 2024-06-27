package db;

import lombok.Data;

@Data
public class MemberBean {

	private String member_no;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String addr;
	private String birthdate;
	private String hp;
	private String last_login_dt;
	private String reg_dt;
	
}// class
