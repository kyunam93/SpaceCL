package chapter10;

public interface Buy {
	void buy();

	default void order() {
		System.out.println("구매 주문");
	}// end order
}// end interface
