package chapter12;

public class Shape<T> {
	
	public static <T, V> double makeRectangle(Point<T, V> p1, Point<T, V> p2) {
		return 0;
	} // end makeRectangle method
	
	public static void main(String[] args) {
		
		Shape<String> sp = new Shape<String>();
		
		// new 뒤의 제네릭 안의 자료형은 생략 가능하다.
		Point<Integer, Double> p1 = new Point<Integer, Double>(900, 9.9);
		Point<Integer, Double> p2 = new Point<Integer, Double>(77700, 77.9);
		
		// Shape의 제네릭을 생략 가능하다.
		double dd = Shape.makeRectangle(p1, p2);
		System.out.println(dd);
		
	} // end main
	
}
