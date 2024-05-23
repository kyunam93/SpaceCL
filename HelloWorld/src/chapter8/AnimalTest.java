package chapter8;

import java.util.ArrayList;

/**
 * 상위 클래스 Animal
 */
class Animal {

	public void move() {
		System.out.println("동물이 움직입니다.");
	}// end move()
}// end class

/**
 * Animal을 상속받는 Human 클래스
 */
class Human extends Animal {

	@Override
	public void move() {
		System.out.println("사람이 두 발로 걷습니다.");
	}// end move()

	public void readBook() {
		System.out.println("사람이 책을 읽습니다.");
	}// end readBook()
}// end class Human

/**
 * Animal을 상속받는 Tiger 클래스
 */
class Tiger extends Animal {

	@Override
	public void move() {
		System.out.println("호랑이가 네 발로 뜁니다..");
	}// end move

	public void hunting() {
		System.out.println("호랑이가 사냥을 합니다.");
	}// end hunting()
}// end class Tiger

/**
 * Animal을 상속받는 Eagle 클래스
 */
class Eagle extends Animal {

	@Override
	public void move() {
		System.out.println("독수리가 하늘을 납니다.");
	}// end move()

	public void flying() {
		System.out.println("독수리가 날개를 쭉 펴고 멀리 날라갑니다.");
	}// end flying()

}// end class Eagle

/**
 * instanceof로 원래 인스턴스형 확인 후 다운캐스팅하기
 */
public class AnimalTest {

	// 배열의 자료형은 Animal 로 지정
	ArrayList<Animal> aniList = new ArrayList<Animal>();

	public static void main(String[] args) {
		AnimalTest aTest = new AnimalTest();
		aTest.addAnimal();
		System.out.println("\n원래 형으로 다운 캐스팅\n");
		aTest.testCasting();
	}

	public void addAnimal() {

		// ArrayList 에 추가되면서 Animal 형으로 형 변환
		aniList.add(new Human());
		aniList.add(new Tiger());
		aniList.add(new Eagle());

		// 배열 요소를 Animal 형으로 꺼내서
		// move()를 호출하면 재정의된 함수가 호출됨
		for (Animal ani : aniList) {
			ani.move();
		} // end for

	}// end addAnimal()

	public void testCasting() {

		for (int i = 0; i < aniList.size(); i++) { 	// 모든 배열 요소를 하나씩 돌면서
			Animal ani = aniList.get(i); 			// Animal 형으로 가져옴
			if (ani instanceof Human) { 			// Human 이면
				Human h = (Human) ani; 				// Human 형으로 다운캐스팅
				h.readBook();
			} else if (ani instanceof Tiger) {
				Tiger t = (Tiger) ani;
				t.hunting();
			} else if (ani instanceof Eagle) {
				Eagle e = (Eagle) ani;
				e.flying();
			} else {
				System.out.println("지원되지 않는 형입니다.");
			}

		} // end for

	}// end testCasting()

}// end class