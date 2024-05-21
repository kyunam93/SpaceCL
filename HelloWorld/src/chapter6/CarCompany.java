package chapter6;

/**
 *  카드회사
 */
public class CarCompany {
	private static int cardNum = 1000;
	private static CarCompany instance = new CarCompany();

	// 생성자를 new 못하게 막는 방법 - private
	private CarCompany() {	}

	
	// 메소드를 사용하여 객체에 접근하는 방법
	public static CarCompany getInstance() {
		if (instance == null) {
			instance = new CarCompany();
		}
		return instance;
	}

	// 단순한 형태
	public Card createCard() {
		return new Card(cardNum += 1);
	}

}
