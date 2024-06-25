package chapter13;

public class TestStringConcat {

	public static void main(String[] args) {

		String s1 = "hi";
		String s2 = "world";

		
		// 인터페이스를 객체화 할수 없지만 익명 클래스로 바로 메소드를 선언하여 사용할 수 있음. 
		new StringConcat() {

			@Override
			public void makeString(String s1, String s2) {
				System.out.println(s1 + s2);
			}// end makeString

		}.makeString(s1, s2);

	}// end main

}// end class
