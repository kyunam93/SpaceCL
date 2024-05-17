package chapter5;

public class Person {

	int age;
	String name;
	int height;
	int weight;

	// 생성자: 접근제어자 + 클래스명
	// 생성자를 만들어주지 않아도 안보이지만 만들어져 있다.
	// 인스턴트를 생성할 때 호출된다.
	// 초기화 할때 쓰인다.
	public Person() {
		System.out.println("I am Constructor");
	}

	public Person(int age, String name, int height, int weight) {
		// 생성자를 호출할때 쓰임, 항상 첫번째줄에서 해야함.
		this();
		// 멤버변수(this)에 매개변수의 값을 초기화
		this.age = age;
		this.name = name;
		this.height = height;
		this.weight = weight;
	}

	public void showInfo() {
		System.out.println("나이: " + age + " 세");
		System.out.println("이름: " + name);
		System.out.println("키: " + height + " Cm");
		System.out.println("몸무게: " + weight + " Kg");
	}

	public static void main(String[] args) {
		// 생성자 오버로드, 생성자가 2개 이상 있는 경우를 말한다.
		Person p1 = new Person(20, "홍길동", 185, 80);
		p1.showInfo();
		
		Person p2 = new Person();
		p2.showInfo();
	}

}
