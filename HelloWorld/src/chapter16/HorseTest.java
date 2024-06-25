package chapter16;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class HorseTest {
	static ArrayList<Horse> horseList = new ArrayList<Horse>();

	public static void main(String[] args) {

		ArrayList<Horse> moveHorse = new ArrayList<Horse>();
		
		Frame frame = new Frame("쓰레드 경마장");
		Canvas canvas = new Canvas();

		frame.setSize(400, 400);
		frame.add(canvas);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		Horse h1 = new Horse(canvas, 30, "1번말");
		Horse h2 = new Horse(canvas, 70, "2번말");
		Horse h3 = new Horse(canvas, 110, "3번말");
		Horse h4 = new Horse(canvas, 150, "4번말");
		Horse h5 = new Horse(canvas, 190, "5번말");
		Horse h6 = new Horse(canvas, 230, "6번말");
		Horse h7 = new Horse(canvas, 270, "7번말");

		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		h7.start();

		System.out.println(HorseTest.horseList.size());
		
		showHorseRanking();

	}// end main

	public static void showHorseRanking() {

		for (int i = 0; i < HorseTest.horseList.size(); i++) {
//			if(i != HorseTest.horseList.size()) {
			System.out.print((i + 1) + "등 : " + HorseTest.horseList.get(i).getStr() + ", ");
//			} else {
//				System.out.print((i+1) + "등 : " + HorseTest.horseList.get(i).getStr() + "\n");
//			}
		} // end for

	}// end method

}// end class
