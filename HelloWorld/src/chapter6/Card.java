package chapter6;

public class Card {

	private int cardId;

	public Card(int cardNum) {
		cardId = cardNum;
	}

	public int getCardNum() {
		return cardId;
	}

	public void createCard(int cardId) {
		this.cardId = cardId;
	}

}
