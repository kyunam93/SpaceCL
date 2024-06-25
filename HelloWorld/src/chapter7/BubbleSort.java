package chapter7;

/*
 * 버블정렬을 해보자
 * 
*/
public class BubbleSort {

	public static void main(String[] args) {

		int data[] = { 2, 9, 10, 3, 7, 15, 5 };

		System.out.println("배열의 크기 : " + data.length + "\n");
		System.out.println("원본 데이터");
		for (int i = 0; i < data.length; i++) {

			System.out.print("data[" + i + "] = " + data[i] + "\t");

		} // end for
		System.out.println("\n");
		System.out.println("버블 정렬 데이터");

		int dLen = data.length;

		// 마지막 pivot은 오른쪽으로 검사할 대상이 없으므로 -1을 추가한다.
		for (int i = 1; i < dLen - 1; i++) {

			// pivot 값으로 오른쪽으로 계속 비교해주는 for문이다.
			for (int j = 0; j < (dLen - i); j++) {

				// 왼쪽 값이 오른쪽 값보다 큰가?
				if (data[j] > data[j + 1]) {

					// 왼쪽 값이 작아야 하기 때문에 swap 한다
					// swap 알고리즘 - 현재 왼쪽 값을 임시 변수 temp 에 저장한다.
					int temp = data[j];
					data[j] = data[j + 1];	// 왼쪽 자리에 오른쪽 값을 저장한다.
					data[j + 1] = temp;		// 오른쪽 자리에 왼쪽 값을 저장한다.

				} // end if

			} // end for

		} // end for

		for (int i = 0; i < data.length; i++) {

			System.out.print("data[" + i + "] = " + data[i] + "\t");

		} // end for

	}

}
