package chapter2;

public class Variable1 {

	public static void main(String[] args) {
		
		char ch1 = 'A';
		char ch2 = '한';
		char ch3 = 'a';
		String str1 = "hi there?";
		
		System.out.println(ch1 + ch2);
		System.out.print(ch1);	
		System.out.print(ch2);	
		System.out.println(str1+ ch1 + ch2);
		
		int ch4 = 67;
		// 자료형 변환(캐스팅)
		System.out.println((char)ch4);	// 다운캐스팅
		
		boolean b1 = true;
		boolean b2 = false;
		
		// 0과 1은 입력할 수 없다.
		// boolean b3 = 1;
		// boolean b4 = 0;
		
		// ! 반대로 반전하는 개념, 논리형에서만 사용가능
		boolean b3 = !b1;
		boolean b4 = !!b3;

		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		
		// var 자동형변환, 하지만 하위 호환성을 위해 사용안함.
//		var num = 10;
	
		
		if(1<2 | 2<3) {
			
		}

	}

}
