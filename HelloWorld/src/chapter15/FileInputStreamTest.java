package chapter15;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStreamTest {

	public static void main(String[] args) {

		FileInputStream fis = null;

		FileReader fr = null;

		try {

			System.out.println("FileInputStream");
			// 파일 입력 스트림 생성
			fis = new FileInputStream("C:\\DEV\\WORKS\\SpaceCL-JapanClass1\\README.md");

			int i = 0;

			// i 값이 -1이 아닌 동안 read() 메서드로 한 바이트를 반복해 읽음
			// 한글 출력 시 깨짐
			while ((i = fis.read()) != -1) {
				System.out.print((char) i);
			} // end while

			System.out.println();
			
			// 파일 입력 리더 생성
			// 한글 출력 시 정상 출력됨
			// 바이트 스트림 이외에 문자 스트림을 사용하면 한글이 깨지지 않고 출력 가능
			System.out.println("FileReader");
			fr = new FileReader("C:\\DEV\\WORKS\\SpaceCL-JapanClass1\\README.md");
			int e = 0;

			while ((e = fr.read()) != -1) {
				System.out.print((char) e);
			} // end while

		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

		finally {

			if (fis != null) {

				try {
					fis.close();
				} // end try

				catch (IOException e) {
					e.printStackTrace();
				} // end catch

			} // end if

		} // end finally

	}// end main

}// end class
