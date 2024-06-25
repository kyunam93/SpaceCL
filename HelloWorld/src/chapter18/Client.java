package chapter18;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client {

	public static void main(String[] args) throws InterruptedException {

		String msg = "Hi, I'm Client";

		try {

			// 포트번호 2012 번으로 서버 연결 시도
			Socket socket = new Socket("127.0.0.1", 2012);

			// 2012 포트로 정상적으로 접근했을 경우
			if (socket.isConnected())
				System.out.println(new Date() + " ==> " + "2012 port successfuly connected ");

			// 클라이언트가 서버로 메세지 전송
			Thread.sleep(1000);
			System.out.println(new Date() + " ==> " + "CLIENT send msg to SERVER");
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bWriter.write(msg);
			bWriter.flush();// 메세지를 바로 송신(전송)

			// 클라이언트가 서버의 메세지를 받는다.
//			BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			String sMsg = bReader.readLine();
//			System.out.println("SERVER : " + sMsg);
//
//			// 스트림과 소켓 연결 종료
//			bReader.close();
			bWriter.close();
			socket.close();

		} // end try

		catch (UnknownHostException e) {
			e.printStackTrace();
		} // end catch

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

	}// end main

}// end class
