package chapter4;

public class Quiz3 {

	public static void main(String[] args) {
		// 짝수단만 출력하는 구구단

		int dan;
		int num = 1;

		
		for (dan = 1; dan <= 9; dan++) {
			for (num = 1; num <= 9; num++) {

				int result = dan * num;
				System.out.println(dan + " x " + num + " = " + result);
				if (dan == num) {	// 단과 곱의 숫자가 같으면 더이상 해당 단은 출력하지 않고 다음 단으로 넘어감
					break;
				}
			}
			System.out.println();

		}
	}

}
