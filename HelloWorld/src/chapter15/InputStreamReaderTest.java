package chapter15;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {

	public static void main(String[] args) {

		InputStreamReader isr = null;
		String path = "C:\\\\DEV\\\\WORKS\\\\SpaceCL-JapanClass1\\\\README.md";
		try {
			FileInputStream fis = new FileInputStream(path);

			isr = new InputStreamReader(fis);

			int i = 0;
			while ((i = isr.read()) != -1) {
				System.out.print((char) i);
			} // end while

		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

		finally {

			try {
				isr.close();
			} // end try

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end catch

		} // end finally

	}// end main

}// end class
