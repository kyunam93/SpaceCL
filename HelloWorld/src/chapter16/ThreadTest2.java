package chapter16;

public class ThreadTest2 {

	public static void main(String[] args) {

		for (int i = 1; i <= 10; i++) {
			PtRun run = new PtRun(i);
			new Thread(run).start();// 다름
		} // end for

		System.out.println("ThreadTest - main() 종료");

	}// end main

}// end class
