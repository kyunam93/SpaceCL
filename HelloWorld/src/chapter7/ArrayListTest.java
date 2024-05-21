package chapter7;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {

		// 어떤 객체<>를 닮을건지 명확히 해야 에러가 덜 난다.
		ArrayList al = new ArrayList();
		Book book = new Book();
		
		al.add(1);
		al.add(1.1);
		al.add("안녕하세요?");
		al.add(book);
		
		for(int i = 0 ; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		
		ArrayList<String> arrayList = new ArrayList<String>();

		
		
	}
	
}
