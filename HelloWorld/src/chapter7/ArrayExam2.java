package chapter7;

/*
 * 클래스의 주석 : 해당 클래스가 어떤 일을 하는 건지 요약하는 주석
 * 2차원 배열을 만들기 위한 클래스
 * 
 * @author kyunam
 * @since 2024.5.14
 * @version 1.0
*/

public class ArrayExam2 {

	public static void main(String[] args) {

		//  a[행(row)][열(col)]
		int a[][] = new int[3][3];	// 2차원 배열, 3~4차원 배열도 생성가능하지만
									// 실무에서는 2차원까지만 사용함

		a[0][2] = 100;
		a[1][1] = 200;
		a[2][0] = 300;

		for (int i = 0; i < a.length; i++) {	// 행의 길이
			
			for (int j = 0; j < a[i].length; j++) {	// 열의 길이
				
				System.out.println("a[" + i + "][" + j + "] = " + a[i][j]);
				
			}
			
		}

	}

}
