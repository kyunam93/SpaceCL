package chapter15;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {

	public static void main(String[] args) {

		double millsecond = 0;

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		try {
			fis = new FileInputStream("C:\\Users\\kosmo\\Downloads\\기업설명회 프레젠테이션.pdf");
			bis = new BufferedInputStream(fis);

			fos = new FileOutputStream("copy.pdf");
			bos = new BufferedOutputStream(fos);

			// 파일을 복사하기 전 시간
			millsecond = System.currentTimeMillis();

			int i;
			while ((i = bis.read()) != -1) {
				bos.write(i);
			} // end while

			// 파일을 복사하는데 거리는 시간 계산
			millsecond = (System.currentTimeMillis() - millsecond) / 1000 / 60;

		} // end try

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

		finally {

			try {

				if (bos != null) {
					bos.close();
				} // end if

				if (fos != null) {
					fos.close();
				} // end if

				if (bis != null) {
					bis.close();
				} // end if

				if (fis != null) {
					fis.close();
				} // end if

			} // end try

			catch (IOException e) {
				e.printStackTrace();
			} // end catch

		} // finally

		System.out.println("파일 복사하는 데 " + millsecond + " millseconds 소요되었습니다.");

	}// end main

}
// end class