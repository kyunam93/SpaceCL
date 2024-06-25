package chapter12;

public class Point<T, V> {

	T x;
	V y;

	Point(T x, V y) {
		this.x = x;
		this.y = y;
	}// end Point Constructor

	// 제네릭 메서드
	public T getX() {
		return x;
	}// end getX method

	// 제네릭 메서드
	public V getY() {
		return y;
	}// end getY method

	public static void main(String[] args) {
		Point<Integer, Double> p1 = new Point<Integer, Double>(900, 9.9);
	}// end main

}// end class
