package chapter7;

public class ArrayExam {

	public static void main(String[] args) {

		int a = 0;

		int b[] = new int[10]; // 배열 10칸 초기화
		long ll[] = new long[5];
		
		int[] studentIDs = new int[10]; // 위와 같이 배열 초기화, 문법은 다르나 기능적으로 같음

		for (int i = 0; i < b.length; i++) { // .length 배열의 크기값을 리턴하는 함수
			System.out.println("b[" + i + "]값:" + b[i]);
		}

		int c[]; // 배열 선언
		c = new int[10]; // 배열 초기화시 반드시 new를 사용해야 한다.

		int d[] = { 1, 2, 3 }; // 배열 초기화 및 값 셋팅

		for(int i = 0; i < d.length; i++) {
			System.out.println("d[" + i + "]값:" + d[i]);
		}
		
		//b = {1, 2, 3};	// 값을 초기화 할 수 없다.
							// 값의 개수만큼 자동으로 배열의 크기를 잡아주기 때문에 배열 크기를 초기화할 필요가 없다.
							// but, 배열의 크기를 고정해서 사용하는 방법이 좋다.
		b[0] = 1;	
		b[1] = 2;
		b[2] = 3;	// 각 배열에 값을 초기화하는 방법.
		
	}

}
