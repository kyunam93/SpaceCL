package chapter8;

// Customer 클래스를 상속받음
public class VIPCustomer extends Customer {

	// 멤버변수
	private int agentID;	// VIP 고객 상담원 아이디
	double saleRatio;		// 할인율

	// 생성자
	public VIPCustomer() {
//		customerGrade = "VIP";	// 상위 클래스에서 private 변수이므로 오류 발생
//		super(); == Customer();	// 자식 클래스의 모든 생성자는 상위 클래스의 디폴트 생성자를 자동으로 호출한다 
								// 평상시 눈에 보이지 않게 생략되어 있다
		bonusRatio = 0.05;
		saleRatio = 0.1;
	}// end VIPCustomer
	
	public VIPCustomer(int agentID) {
		this.agentID = agentID;
	}// end VipCustomer

	
	// VIP 고객에게만 필요한 메서드
	public int getAgentID() {
		return agentID;
	}// end getAgentID

	
	//오버라이딩 : 자식이 부모가 가진 메소드를 재정의한다.
	
	// 부모 클래스 Customer
	@Override	// 어노테이션 : 표식이면서 코드
	public int calcPrice(int price) {	

		return 0;
	}// end calcPrice
	
	// 부모 클래스 Object
	@Override
	public String toString() {
		return "I am VIPCustomer Class";
	}// end toString
	
}// end class
