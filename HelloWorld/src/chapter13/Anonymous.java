package chapter13;

import chapter10.Calc;

public class Anonymous {

	public static void main(String[] args) {

		Calc calc = new Calc() {

			@Override
			public int substract(int a, int b) {
				return 0;
			}// end substract

			@Override
			public int add(int a, int b) {
				return 0;
			}// end add
			
		};// end anonymous class

	}// end main
	
}// end class
