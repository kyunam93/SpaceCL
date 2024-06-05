package chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {

		// init
		try {

			// 클라이언트가 접속할 서버 객체 생성
			ServerSocket server = new ServerSocket(2012);// port number
			System.out.println(new Date() + " ==> " + "SERVER : I'm wating client");

			// 클라이언트가 접속을 하면 소켓 객체로 반환된다.
			Socket socket = server.accept();

			// 접속한 클라이언트 정보 출력
			String msg = new Date() + " ==> " + "SERVER : " + socket.getInetAddress() + " Connected Client By ";
			msg += socket.getLocalPort() + " Port";
			// System.out.println(msg);
			System.out.println(msg);

			// 클라이언트의 메세지를 받기 위해 스트림을 가져온다.
			BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 클라이언트로부터 한줄을 읽어온다.
			String cMsg = bReader.readLine();
			// System.out.println("SERVER(receive) : " + cMsg);
			System.out.println(new Date() + " ==> " + "SERVER(receive) : " + cMsg);

			// 서버가 클라이언트로 메세지를 보낸다.
//			PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//			pWriter.write("Hi, Client. Welcome to Server");
//
//			// 사용한 스트림 및 소켓을 닫아준다.
//			// 닫아주지 않으면 메모리를 계속 유지하여 좀비프로그램이 된다.
//			pWriter.close();
			bReader.close();
			socket.close();
			server.close();

		} // end try

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

	}// end main

}// end class
