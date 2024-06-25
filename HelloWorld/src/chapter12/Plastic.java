package chapter12;

public class Plastic extends Material{

	@Override
	public void doPrinting() {
		System.out.println("Plastic 재로로 출력합니다.");
	}
	
	public String toString() {
		return "재료는 Plastic 입니다.";
	}
	

}
