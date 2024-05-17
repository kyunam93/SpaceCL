package chapter4;

public class Quiz2 {

	public static void main(String[] args) {
		// 짝수단만 출력하는 구구단

		int dan;
		int num = 1;

		for (dan = 1; dan <= 9; dan++) {
			if (dan % 2 != 0) {
				continue;
			}

			for (num = 1; num <= 9; num++) {
				int result = dan * num;
				System.out.println(dan + " x " + num + " = " + result);
			}
			System.out.println();

		}
	}

}
