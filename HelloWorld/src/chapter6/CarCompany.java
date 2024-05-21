package chapter6;

/**
 *  카드회사
 */
public class CarCompany {
	private static int cardNum = 1000;
	private static CarCompany instance = new CarCompany();

	private CarCompany() {	}

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
