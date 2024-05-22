package chapter8;

public class CustomerTest {

	public static void main(String[] args) {
		VIPCustomer vip = new VIPCustomer(500);
		vip.calcPrice(100);
		System.out.println(vip.showCustorInfo());

//		Customer cu = new Customer();
//		cu.calcPrice(200);
//		System.out.println( cu.showCustorInfo() );

//		// 다형성
//		VIPCustomer cpp = new Customer();
//		//
//		VIPCustomer cp2 = (VIPCustomer) cpp;

		// 다형성
		Object cpp = new Customer();
		// cpp는 오브젝트 타입인데 없는 데이터인 VIPCustomer 타입으로 캐스팅 하면 
		// 에러가 뜨면서 프로그램이 종료됨
		VIPCustomer cp2 = (VIPCustomer) cpp;
		
		// 오버라이딩 : 메소드 재정의 - 자식이 부모의 메소드를 재정의
		// 부모의 껍데기를 가지고 자식이 마음대로 사용하기 위해서 사용
		
//		@Deprecated	// 사용할 수 는 있지만 앞으로 개발하면서 사라질수도 있다는 표시
		

	}

}
