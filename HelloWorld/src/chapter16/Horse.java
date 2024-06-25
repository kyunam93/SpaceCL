package chapter16;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Random;

public class Horse extends Thread {

	// 멤버변수 초기화
	private static Canvas canvas;
	private int y = 0;
	private String str = null;
	private Random rand = new Random(); // 랜덤 숫자 발생
	
	// 생성자 초기화
	public Horse(Canvas canvas, int num, String name) {
		if (this.canvas == null)	this.canvas = canvas;
		this.y = num;
		this.str = name;
	}// end counstructor

	// 메소드 초기화
	@Override
	public void run() {

		Graphics g = canvas.getGraphics();
		g.drawString(str, 20, y);

		for (int i = 0; i < 300; i++) {

			try {

				Thread.sleep(100); // 0.1초 sleep
				i += rand.nextInt(9); // 0~9 까지의 랜덤한 숫자의 거리로 달리게 된다.

				if (i > 300) {
					i = 300; // 말의 종료지점으로 이동.
					HorseTest.horseList.add(this);

				} // end if

				g.fillRect(50, y, i, 20);

			} // end try

			catch (Exception e) {
				e.printStackTrace();
			} // end catch

		} // end for

	}// end method

	public void setStr(String str) {
		this.str = str;
	}

	public String getStr() {

		return str;
	}

}// end class
