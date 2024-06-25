package chapter4;

public class Triangle2 {

	public static void main(String[] args) {

		int row = 5;
		int col = row + (row - 1);
		final int FIRST_COL_IDX = col / 2; // 제일 처음 *이 찍히는 컬럼 인덱스
		final int CENTER_ROW_IDX = row / 2;	// 가운데 행 인덱스 구하기

		String stars[][] = new String[row][col]; // 문자열형 2차원 배열

		/** 배열 초기화, 모든 배열에 공백을 입력해 준다. **/
		for (int i = 0; i < stars.length; i++) {

			for (int j = 0; j < stars[i].length; j++) {
				stars[i][j] = " ";

				// 열의 마지막 부분에는 개행 문자를 넣는다.
				if (j == stars[i].length - 1) {
					stars[i][j] += "\n";
				} // end if
			} // end for

		} // end for

		/* 배열에 별 입력 */
		// 행의 반개 수만큼 도는 반복문
		for (int i = 0; i < stars.length / 2; i++) {

			// 변수 초기화
			int colIdx = 0;
			int jCntNum = 0; // 열의 반복 횟수 값을 가지고 있는 변수
			boolean isStartNewCol = true;

			// 가운데 행까지만 별의 개수가 증가한다.
			if (i <= CENTER_ROW_IDX) {
				// 시작하는 열의 인덱스 번호를 추출한다.
				colIdx = FIRST_COL_IDX - i;
				jCntNum = i;
			}

			// 가운데 행 아래부터 별의 개수가 감소한다.
			else {
				colIdx = FIRST_COL_IDX - (FIRST_COL_IDX - i);
				jCntNum = stars.length - (i + 1);
			}

			// 열의 개수만큼 도는 for
			for (int j = 0; j <= jCntNum; j++) {
				if (!isStartNewCol) {
					colIdx += 2;
				}
				stars[i][colIdx] = "*";
				isStartNewCol = false;
			} // end for
		} // end for

		/* 배열 값 출력 */
		for (int i = 0; i < stars.length; i++) {

			for (int j = 0; j < stars[i].length; j++) {
				// System.out.print("stars[" + i + "][" + j + "] = " + stars[i][j] + "\t");
				System.out.print(stars[i][j]);
			} // end for

		} // end for

	}

}
