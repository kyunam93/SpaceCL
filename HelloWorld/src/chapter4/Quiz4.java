package chapter4;

import java.util.Scanner;

public class Quiz4 {

	public static void main(String[] args) {

		System.out.println("산의 층수를 입력해주세요");
		Scanner sc = new Scanner(System.in);
		
		final int HEIGHT = sc.nextInt();
		final int WIDTH = 1 + (HEIGHT - 1) * 2;

		for (int i = 1; i < WIDTH + 1; i += 2) {

			for (int j = WIDTH; j > i; j -= 2) {
				System.out.print(".");
			}

			for (int k = 0; k < i; k++) {
				System.out.print("*");
			}

			System.out.println("");

		}

	}

}
