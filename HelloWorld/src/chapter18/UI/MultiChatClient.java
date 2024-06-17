package chapter18.UI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {

	// member Variable init
	private Socket socket;
	private String name;
	private final String SERVER_IP = "192.168.0.49";
	private final int SERVER_PORT = 7777;
	private ChatView chat;
	
//	public static void main(String[] args) {
//		new MultiChatClient().start();
//	}// end main
	
	public MultiChatClient() {
		
	}
	
	public MultiChatClient(ChatView chat, String name) {
		this.chat = chat;
		this.name = name;
	}
	
	public void start() {
		
		try {
			
			socket = new Socket(SERVER_IP, SERVER_PORT);
			
			if(socket.isConnected()) {
//				System.out.println("서버와 성공적으로 연결되었습니다. 대화명은 : ");
				String msg = "서버와 성공적으로 연결되었습니다. 대화명은 " + name + " 입니다\n";
				chat.addViewMsg(msg);
			}
			
//			name = new Scanner(System.in).nextLine();
			
			ClientReceiver cReceiver = new ClientReceiver(socket);
			cReceiver.start();
			
//			ClientSender cSender = new ClientSender(socket);
//			cSender.run();
			
			// 닉네임을 처음에 서버로 날려준다.
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			output.writeUTF(name);
			chat.addViewMsg("대화방에 입장 하였습니다.");
			
		}// end try
		
		catch(Exception e){
			e.printStackTrace();
		}// end catch
		
	}// end run method
	
	public void sendMsg(String msg) {
		new ClientSenderUI(socket, msg).start();
	}
	
	class ClientReceiver extends Thread {
		
		// init
		Socket socket;
		DataInputStream input;
		
		// constructor init
		public ClientReceiver(Socket socket) {
			
			this.socket = socket;
			
			try {
				input = new DataInputStream(socket.getInputStream());
			} // end try
			
			catch (Exception e) {
				e.printStackTrace();
			}// end catch
			
		}// end constructor
		
		@Override
		public void run() {
			
			while(input != null) {
				
				try {
//					System.out.println(input.readUTF());
					chat.addViewMsg(input.readUTF());
				} // end try
				
				catch (Exception e) {
					e.printStackTrace();
				}// end catch
				
			}// end while
			
		}// end run method
		
	};// end inner class
	
	class ClientSender extends Thread {
		
		// init
		Socket socket;
		DataOutputStream output;
		
		public ClientSender(Socket socket) {
			
			this.socket = socket;
			
			try {
				
				output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF(name);
				
//				System.out.println("대화방에 입장 하였습니다.");
				chat.addViewMsg("대화방에 입장 하였습니다.");
				
			}// end try
			
			catch (Exception e) {
				e.printStackTrace();
			}// end catch
			
		}// end constructor
		
		@Override
		public void run() {
			
			Scanner sc = new Scanner(System.in);
			String msg = "";
			
			while(output != null) {
				
				try {
					
					msg = sc.nextLine();
					
					// 프로그램 종료
					if("exit".equals(msg))
						System.exit(0);
					
					// 메세지를 전송하는 부분
					output.writeUTF("[" + name + "] " + msg);
					
				} // end try
				
				catch(Exception e) {
					e.printStackTrace();
				}// end catch
				
			}// end while
			
		}// end run method
		
	}; // end inner class
	
	
	/**
	 * 서버로 메세지를 전송
	 * 메세지
	 *  
	 */
	class ClientSenderUI extends Thread {

		// init
		Socket socket;
		DataOutputStream output;
		String msg;
		
		
		// init constructor
		public ClientSenderUI(Socket socket, String msg) {
		
			this.socket = socket;
			this.msg = msg;

		}// end constructor
		
		// 메소드 초기화
		@Override
		public void run() {
			
			// 서버로 메세지를 전송
			DataOutputStream output;
			
			try {
				
				output = new DataOutputStream( socket.getOutputStream());
				output.writeUTF("[" + name + "] " + msg);

			}// end try
			
			catch (IOException e) {
				e.printStackTrace();
			}// end catch
			
		}// end method run
		
	};// end inner class
	
}// end class
