package chapter4;

public class Triangle {

	public static void main(String[] args) {

		int row = 5;
		int col = row + (row - 1);
		final int FIRST_COL_IDX = col / 2; // 제일 처음 *이 찍히는 컬럼 인덱스
		final int CENTER_COL_IDX = row / 2;

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
			int colIdx = FIRST_COL_IDX - i;
			boolean isStartNewCol = true;

			// 열의 개수만큼 도는 for
			for (int j = 0; j <= i; j++) {
				if (!isStartNewCol) {
					colIdx += 2;
				}
				stars[i][colIdx] = "*";
				isStartNewCol = false;
			}
		}

		/* 배열 값 출력 */
		for (int i = 0; i < stars.length; i++) {

			for (int j = 0; j < stars[i].length; j++) {
				// System.out.print("stars[" + i + "][" + j + "] = " + stars[i][j] + "\t");
				System.out.print(stars[i][j]);
			} // end for

		} // end for

	}

}
