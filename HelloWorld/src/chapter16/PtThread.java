package chapter16;

public class PtThread extends Thread {

	private int seq;

	public PtThread(int seq) {
		this.seq = seq;
	}// end consturctor

	@Override
	public void run() {

		System.out.println(seq + " 쓰레드 - 시작");

		try {
			Thread.sleep(1000);
		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

		System.out.println(seq + " 쓰레드 - 종료");

	}// end method

}// end class
