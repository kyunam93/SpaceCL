package chapter15;

import java.io.IOException;

public class SystemInTest1 {

	public static void main(String[] args) {

		System.out.println("알파벳 하나 쓰고 엔터 누르세요");

		int i;

		try {

			// read() 메서드로 한 바이트 읽음
			i = System.in.read();
			System.out.println(i);
			// 문자로 변환하여 출력
			System.out.println((char) i);
		} // end try

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

	} // end main

} // end class
