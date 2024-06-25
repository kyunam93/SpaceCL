package chapter4;

public class WhileExam1 {

	public static void main(String[] args) {

//		1부터 10까지의 짝수, 홀수 각각의 합 출력해보기

		final int LOOP_NUM = 100;
		int num = 1;
		int even = 0, odd = 0;

		while (num <= LOOP_NUM) {
			// 짝수값을 더하기
			if(num % 2 == 0) {	// 2로 나눈 나머지가 0이면 짝수
				even += num;
			}
			else {						// 홀수
				odd += num;
			}
			num++;	// 증가하지 않으면 무한루프
		} // end while

		System.out.println("짝:" + even);
		System.out.println("홀:" + odd);
	}

}
