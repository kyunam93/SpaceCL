package chapter12;

import java.util.ArrayList;
import java.util.Iterator;

public class StackTest {

	public static void main(String[] args) {

		MyStack stack = new MyStack();
		stack.push("A");
		stack.push("B");
		stack.push("C");

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}// end main

}// end class

class MyStack {

	private ArrayList<String> arrayStack = new ArrayList<String>();

	// 스택의 맨 뒤에 요소를 추가
	public void push(String data) {
		arrayStack.add(data);
	}// end push

	// 스택의 맨 뒤에서 요소를 꺼냄
	public String pop() {
		// ArrayList에 저장된 유효한 자료의 개수
		int len = arrayStack.size();

		if (len == 0) {
			System.out.println("stack is empty");
			return null;
		}

		// 맨 뒤에있는 자료 반환하고 배열에서 제거
		return (arrayStack.remove(len - 1));

	}// end pop

	public void deleteAll() {

		Iterator<String> it = arrayStack.iterator();

		while (it.hasNext()) {
			// remove가 되면 배열의 인덱스가 0으로 땡겨지기 때문에 인덱스를 0으로 설정해노으면 전부 지워짐
			arrayStack.remove(0);
		}
	}

}// end class
