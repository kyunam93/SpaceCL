package chapter14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileException {

	public static void main(String[] args) {

		try {
			FileInputStream fs = new FileInputStream("a.txt");
		} // end try

		catch (FileNotFoundException fnfe) {
			System.out.println("FileNotFoundException 발생");
			fnfe.printStackTrace();
		} // end catch

		// try 영역에서 에러가 나든 안나든 무조건 실행되는 부분
		// db connnection 연결이 끊어졌는데도 열려있을 때 종료해야 할 때
		finally {
			System.out.println("excute finally");
		} // end finally
		
	} // end main

} // end class