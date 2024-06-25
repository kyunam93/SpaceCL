package chapter11;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StringClassTest {

	public static void main(String[] args) throws ClassNotFoundException {

		// 클래스 이름으로 가져오기
		Class strClass = Class.forName("java.lang.String");

		// 모든 생성자 가져오기
		Constructor[] cons = strClass.getConstructors();
		for (Constructor c : cons) {
			System.out.println(c);
		}// end for

		System.out.println();
		// 모든 멤버변수 가져오기
		Field[] fields = strClass.getFields();
		for (Field f : fields) {
			System.out.println(f);
		}// end for

		System.out.println();
		// 모든 메서드 가져오기
		Method[] methods = strClass.getMethods();
		for (Method m : methods) {
			System.out.println(m);
		}// end for

	}// end main

}// end class
