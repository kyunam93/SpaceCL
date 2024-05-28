package chapter12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MemberHashMapTest {

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "Hi");
		map.put("B", "I'm kyunam");
		
//		System.out.println(map.get("B"));
		
		// map의 키값을 순서대로 받아오는 이터레이터
		Iterator<String> it = map.keySet().iterator();

		// map이 지니고 있는 값을 전체 출력하는 로직
		while (it.hasNext()) {
			
			String key = it.next();
			// 해당하는 키의 값을 불러옴
			String value = map.get(key);
			
			System.out.println("Key: " + key + "\tValue: " + value);
			
		}// end while

	}// end main

}// end class
