package chapter12;

// 제네릭 클래스, T : type의 약자, 자료형 매개변수
// extends 예약어로 사용할 수 있는 자료형에 제한을 둔다.
public class GenericPrinter<T extends Material> {

	private T material;

	public void setMaterial(T material) {
		this.material = material;
	}// end setMaterial

	public T getMaterial() {
		return material;
	}// end getMaterial
	
	public static void main(String[] args) {
		GenericPrinter<Material> gp = new GenericPrinter<Material>();
		GenericPrinter<Plastic> gp1 = new GenericPrinter<Plastic>();
		//GenericPrinter<Object> gp2 = new GenericPrinter<Object>();
	}// end main

}// end class
