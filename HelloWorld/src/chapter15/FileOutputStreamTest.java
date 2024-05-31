package chapter15;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {

	public static void main(String[] args) {

		FileOutputStream fos = null;

		try {
			
			// append 
			fos = new FileOutputStream("test.txt", true);
			fos.write(65);// 65 = 'A'
			fos.write(66);// 66 = 'B'
			fos.write(67);// 67 = 'C'

		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

		finally {
			
			try {
				fos.close();
			} // end try
			
			catch (IOException e) {
				e.printStackTrace();
			} // end catch
			
		} // end finally

	}// end main

}// end class
