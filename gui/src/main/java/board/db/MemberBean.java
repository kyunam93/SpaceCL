package board.db;

import lombok.Data;

@Data
public class MemberBean {

	// init variable
	private String memberNo;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String addr;
	private String birthdate;
	private String hp;
	private String lastLoginDt;
	private String regDt;

	public void printAll() {
		System.out.println(memberNo + "\t" + id + "\t" + pw + "\t" + name + "\t" + email + "\t" + addr + "\t"
				+ birthdate + "\t" + hp + "\t" + lastLoginDt + "\t" + regDt);
	}

}// class
