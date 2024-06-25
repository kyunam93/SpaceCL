package chapter14;

public class ThrowsTest {

	public static void main(String[] args) {

		try {
			showPrint("Please, let me throw!!");
		} // end try

		catch (Exception e) {
			System.out.println("에러 수정 완료");

			// 알리스메틱 에러가 났지만 널포인트 익셉트를 강제로 던짐
			throw new NullPointerException();
		} // end catch

	} // end main

	// throws : throws 가 붙은 메소드를 호출하는 영역(main)에서 예외처리 작업을 넘긴다.
	public static void showPrint(String str) throws Exception {

		System.out.println(str);

		int a = 10;
		a /= 0;
	} // end showPrint

} // end class
