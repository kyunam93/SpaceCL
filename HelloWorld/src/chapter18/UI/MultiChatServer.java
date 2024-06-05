package chapter18.UI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiChatServer {

	// member variable init
	private HashMap<String, DataOutputStream> clients;
	private ServerSocket server;

	// constructor init
	public MultiChatServer() {

		clients = new HashMap<String, DataOutputStream>();

		// 여러 쓰레드에서 접근하기때문에 동기화시킨다.
		Collections.synchronizedMap(clients);

	} // end constructor

	// method init

	/**
	 * 서버를 실행하기 위한 메소
	 */
	public void run() {
		try {

			// 서버 소켓 생성
			server = new ServerSocket(7777);
			System.out.println("running Server...");

			// 무한루프로 클라이언트와 연결되면 스레드를 생성하여 소켓을 초기화 한다.
			while (true) {

				Socket socket = server.accept();

				// 쓰레드 시작
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
				
			} // end while

		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

	}// end method

	public static void main(String[] args) {

		new MultiChatServer().run();// 서버 실행
	}// end main

	class ServerReceiver extends Thread {

		// member variable init
		Socket socket;
		DataInputStream inStream;
		DataOutputStream outStream;

		// constructor init
		public ServerReceiver(Socket socket) {
			this.socket = socket;

			try {

				inStream = new DataInputStream(socket.getInputStream());
				outStream = new DataOutputStream(socket.getOutputStream());

			} // end try

			catch (Exception e) {
				e.printStackTrace();
			} // end catch

		}// end constructor

		@Override
		public void run() {

			String name = "";

			try {

				name = inStream.readUTF();
				
				// 접속하는 클라이언트를 Map에 넣는다.
				clients.put(name, outStream);
				
				String msg = name + " 님이 접속 하셨습니다.";
				sendToAll(msg);
				
				System.out.println(msg);
				System.out.println("현재 "  + clients.size() + " 명이 대화방에 접속 중입니다.");
				
				// 메세지 전송
				while(inStream != null) {
					sendToAll(inStream.readUTF());
				}// end while
				
			} // end try

			catch (Exception e) {
				e.printStackTrace();
			} // end catch
			
			finally {
				
				// 접속이 종료되면
				clients.remove(name);
				String msg = name + " 님이 나가셨습니다.";
				sendToAll(msg);
				System.out.println(msg);
				
			}// end finally

		}// end run method

		/**
		 * 모든 클라이언트에게 메세지 발송
		 */
		public void sendToAll(String msg) {

			Iterator<String> it = clients.keySet().iterator();

			while (it.hasNext()) {

				try {

					DataOutputStream outStream = clients.get(it.next());
					outStream.writeUTF(msg);

				} // end try

				catch (Exception e) {
					e.printStackTrace();
				} // end catch

			} // end while

		}// end sendToAll method

	}// end class

};// end class
