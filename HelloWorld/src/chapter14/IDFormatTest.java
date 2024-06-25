package chapter14;

public class IDFormatTest {
	private String userID;

	public void setUserID(String userID) throws IDFormatException {
		
		if(userID == null) {
			throw new IDFormatException("아이디는 null일 수 없습니다.");
		} // end if
		
		else if(userID.length() < 8 || userID.length() > 20) {
			throw new IDFormatException("아이디는 8 자 이상 20 자 이하로 쓰세요.");
		} // end else if
		
		this.userID = userID;
	} // end method

	public static void main(String[] args) {
		IDFormatTest test = new IDFormatTest();

		// 아이디 값이 null 인 경우
		String userID = null;

		try {
			test.setUserID(userID);
		} // end try

		catch (IDFormatException e) {
			System.out.println(e.getMessage());
		} // end catch

		// 아이디값이 8자 이하인 경우
		userID = "1234567";

		try {
			test.setUserID(userID);
		} // end try

		catch (IDFormatException e) {
			System.out.println(e.getMessage());
		} // end catch

	} // end main

} // end class
