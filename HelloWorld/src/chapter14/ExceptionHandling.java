package chapter14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionHandling {

	public static void main(String[] args) {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream("a.txt");
		} // end try

		catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
			return;
		} // end catch

		finally {
			
			if (fis != null) {
				
				try {
					// 파일 입력 스트림 종료
					fis.close();
				} // end try

				catch (IOException e) {
					e.printStackTrace();
				} // end catch

			} // end if

			System.out.println("finally : 항상 수행되는 영역");

		} // end finally

		System.out.println("try~catch~finally가 끝난 영역입니다. 여기도 수행됩니다.");

	} // end main

} // end class
