package chapter11;

public class Q4 {
	public static void main(String[] args) {
		MyDog dog = new MyDog("멍멍이", "진돗개");
		System.out.println(dog);

		String a = new String("abc");
		String d = new String("def");

		StringBuffer sd = new StringBuffer();
		sd.append(a);
		sd.append(d);

	}// end main
	
	class Dog{}
	
}// end class

class MyDog {
	
	String name;
	String type;

	public MyDog(String name, String type) {
		this.name = name;
		this.type = type;
	}// end MyDog Constructor

	@Override
	public String toString() {
		return type + " " + name;
	}// end toString method

}// end class