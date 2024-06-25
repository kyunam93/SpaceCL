package chapter3;

import java.util.Scanner;

public class If {

	public static void main(String[] args) {

		/*
		 * 1. 20 미만 1천원 
		 * 2. 20 이상 40미만 1천원 추가 
		 * 3. 40 이상 60미만 1천원 추가 
		 * 4. 65이상 20프로 할인
		 * 
		 * 41 65 19 61
		 */

		Scanner sc = new Scanner(System.in);
		System.out.println("나이를 입력하세요.");

		int age = sc.nextInt();
		int charging = 1000;

		if (age >= 20) {
			charging += 1000;
		}
		if (age > 40) {
			charging += 1000;
		}
		if (age >= 65) {
			charging -= charging * 0.2;
		}

		System.out.println("입장료 : " + charging);

//		
//		int price = 1000;
//		
//		if (age >= 20 && age <= 40) {
//			price += 1000;
//		} else if (age >= 41) {
//			price += 2000;
//			if (age >= 65) {
//				price -= price * 0.2;
//			}
//		}
//		
//		System.out.println("입장료 : " + price);

		int age1 = 1;

		String str = "한글";

		switch (str) {
		case "대한민국":
			System.out.println(str);
		case "한국":
			System.out.println(str);
		case "한글":
			System.out.println(str);

			switch (age1) {
			case 10:
				System.out.println("10");
			case 1:
				System.out.println("1");
				break; // switch문을 벗어남
			case 5:
				System.out.println("5");
			default:
				System.out.println("age: " + age1);
			}
			
			// 클래스 끼리 비교하는 것은 올바른 방법이 아님
			if(str == "한글") {
				System.out.println("if" + str);
			}
			
			if("한글".equals(str)) {
				System.out.println("if2" + str);
			}
		}
	}
}
