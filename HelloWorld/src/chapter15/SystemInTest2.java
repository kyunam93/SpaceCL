package chapter15;

import java.io.IOException;

public class SystemInTest2 {

	public static void main(String[] args) {

		System.out.println("알파벳 여러개 쓰고 엔터 누르세요");

		int i;

		try {

			// while 문에서 read() 메서드로 한 바이트를 반복해서 읽음
			while ((i = System.in.read()) != '\n') {
				System.out.print((char) i);
			} // end while

		} // end try

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

	} // end main

} // end class
