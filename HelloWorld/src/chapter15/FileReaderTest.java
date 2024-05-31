package chapter15;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

	public static void main(String[] args) {

		FileReader fr = null;

		try {
			fr = new FileReader("C:\\DEV\\WORKS\\SpaceCL-JapanClass1\\README.md");
			int i = 0;

			while ((i = fr.read()) != -1) {
				System.out.print((char) i);
			} // end while
			
		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

		finally {

			try {
				fr.close();
			} // end try

			catch (IOException e) {
				e.printStackTrace();
			} // end catchs

		} // end finally

	} // end main

}// end class
