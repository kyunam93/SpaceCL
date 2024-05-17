package chapter2;

public class ExplicitConversion {

	public static void main(String[] args) {
		int age = 10;
		if(age < 10) {
			System.out.println("당신의 나이는 10세 미만입니다.");
		}
		else if(age == 10) {
			System.out.println("당신의 나이는 10세 입니다.");
		}
		else {
			System.out.println("당신의 나이는 10세 초과니다.");
		}
	}

}
