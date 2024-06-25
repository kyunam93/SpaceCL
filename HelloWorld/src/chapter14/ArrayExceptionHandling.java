package chapter14;

public class ArrayExceptionHandling {

	public static void main(String[] args) {

		int[] arr = new int[5];

		// 예외가 발생할 수 있는 부분은 try 블록에 작성한다.
		try {

			for (int i = 0; i <= 5; i++) {
				arr[i] = i;
				System.out.println(arr[i]);
			} // end for

		} // end try

		catch (ArithmeticException ae) {
			System.out.println("알리스매틱 예외 발생");
		} // end catch
		
		// 예외가 발생되면 catch 블럭을 실행한다.
		// 예외처리는 순차적으로 진행한다.
		// 만약 Exception 클래스를 먼저 선언한다면 자식의 내용을 전부 가지고 있기 때문에
		// 하단의 자식 클래스에 접근할 필요가 없다.
		catch (ArrayIndexOutOfBoundsException aioobe) {

			System.out.println("배열 인덱스 예외 발생, 예외 처리 부분");
			aioobe.printStackTrace();

		} // end catch
		
		
		// 자식 클래스가 위에 부모 클래스가 아래에 있으면 에러가 발생되지 않는다.
		catch (Exception e) {
			
			if(e instanceof ArithmeticException){
				System.out.println("알리스매틱 예외 발생");
			} // end if
			
			else {
				System.out.println("예외 발생");
			} // end else
			
			// 예외 내용 출력
			e.printStackTrace();

		} // end catch

	} // end main

} // end class
