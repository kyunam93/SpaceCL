package chapter15;

import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("이름: ");
		String name = sc.nextLine(); // 엔터를 칠 때까지의 값을 읽어온다

		System.out.println("직업: ");
		String job = sc.nextLine();

		System.out.println("사번: ");
		int num = sc.nextInt();

		System.out.println("\n이름: " + name);
		System.out.println("직업: " + job);
		System.out.println("사번: " + num);

	}// end main

}// end class
