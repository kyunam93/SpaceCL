package chapter10;

public interface Sell {
	void sell();
	
	default void order() {
		System.out.println("판매 주문");
	}// end order
}// end interface
