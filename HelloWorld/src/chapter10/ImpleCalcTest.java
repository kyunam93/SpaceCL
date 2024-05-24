package chapter10;

public class ImpleCalcTest {

	public static void main(String[] args) {
		
		// 다형성
		// 구현하는 인터페스나 자기자신, 또는 최상위 Object로 캐스팅 가능
		Calc calc1 = new ImplCalc();
		Calc2 calc2 = new ImplCalc();
		ImplCalc calc3 = new ImplCalc();
		Object calc4 = new ImplCalc();
		
		calc3.calcP(calc1);
		
		// 각기 서로 다른 자료이기 때문에 사용 못함
		// but 인터페이스 끼리도 상속이 가능하기 때문에
		// 상속된 인터페이스를 매개변수에 들어갈 수 있다. 
//		calc3.calcP(calc2);
		
		// Object는 클래스, Calc는 인터페이스이므로 
		// Object는 클래스임, 인터페이스가 아니기 때문에 못들어감
		// calcP의 매개변수는 인터페이스 형 데이터이기 때문에 클래스가 올수 없다.
//		calc3.calcP(calc4);		
		
		
		
	}

}
