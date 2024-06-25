package chapter6;

public class Subway {
	private String lineNumber; // 지하철 노선
	private int passengerCount; // 승객수
	private int money; // 수입액
	public final int COAST; // 지하철 탑승 요금

	public Subway(String lineNumber, int coast) {
		this.lineNumber = lineNumber;
		COAST = coast;
	}// end constructor Subway

	/**
	 * 지하철 탑승 + 수익 계산
	 * @param money
	 */
	public void take(Student student) {
		// 가독성을 위해 삼항연산자는 한번만 수행한다.
		// 아래와 같이 코드를 작성하려면 if문을 써서 가독성을 높힌다.
//		student.money -= student.isBusTransfar ? (COAST/2) : student.isSubwayTransfar ? 0 : COAST;
//		money += student.isBusTransfar ? (COAST/2) : student.isSubwayTransfar ? 0 : COAST;
		
		if(student.isBusTransfar) {
			System.out.println("50% 환승");
			student.money -= COAST/2;
			money += COAST/2;
		} 
		else {
			if(student.isSubwayTransfar) {
				System.out.println("무료 환승");
			}
			else {
				student.money -= COAST;
				money += COAST;
			}
		}

		passengerCount++; // 승객 수 증가
	}// end method take

	public void takeOff() {
		// TODO 여러분이 코딩해보세요
		if (passengerCount > 0) {
			passengerCount--;
		}
	}

	public void showInfo() {
		System.out.println(lineNumber + "의 승객은 " + passengerCount + "명이고, 수입은 " + money + "입니다.");
	}// end method showInfo
	
}
