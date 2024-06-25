class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "x = " + x + ", " + "y = " + y;
	}

}

class Circle implements Cloneable {
	Point point;
	int radius;

	Circle(int x, int y, int radius) {
		this.radius = radius;
		point = new Point(x, y);
	}

	public String toString() {
		return "원점은 " + point + "이고, " + "반지름은 " + radius + "입니다.";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}

public class chapter11 {

	public static void main(String[] args) throws CloneNotSupportedException {

		Circle circle = new Circle(10, 20, 30);
		Circle copyCircle = (Circle) circle.clone();	// 명시적 다운 캐스팅

		System.out.println(circle);
		System.out.println(copyCircle);
		System.out.println(System.identityHashCode(circle));
		System.out.println(System.identityHashCode(copyCircle));
		
		

	}

}
