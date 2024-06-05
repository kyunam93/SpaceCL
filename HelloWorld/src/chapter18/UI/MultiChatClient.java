package chapter18.UI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {

	// member Variable init
	private Socket socket;
	private String name;
	private final String SERVER_IP = "192.168.0.49";
	private final int SERVER_PORT = 7777;
	
	public static void main(String[] args) {
		new MultiChatClient().run();
	}
	public void run() {
		
		try {
			
			socket = new Socket(SERVER_IP, SERVER_PORT);
			if(socket.isConnected())
			System.out.println("서버와 성공적으로 연결되었습니다. 대화명을 입력하세요: ");
			name = new Scanner(System.in).nextLine();
			
			ClientReceiver cReceiver = new ClientReceiver(socket);
			ClientSender cSender = new ClientSender(socket);
			
			cReceiver.run();
			cSender.run();
			
		}// end try
		
		catch(Exception e){
			e.printStackTrace();
		}// end catch
		
	}// end run method 
	
	class ClientReceiver extends Thread {
		
		// init
		Socket socket;
		DataInputStream inStream;
		
		// constructor init
		public ClientReceiver(Socket socket) {
			
			this.socket = socket;
			
			try {
				inStream = new DataInputStream(socket.getInputStream());
				
			} // end try
			
			catch (Exception e) {
				// TODO: handle exception
			}// end catch
			
		}// end constructor
		
		@Override
		public void run() {
			
			while(inStream != null) {
				
				try {
					System.out.println(inStream.readUTF());
				} // end try
				
				catch (Exception e) {
					e.printStackTrace();
				}// end catch
				
			}// end while
			
		}// end run method
		
	}// end inner class
	
	class ClientSender extends Thread {
		
		// init
		Socket socket;
		DataOutputStream outStream;
		
		public ClientSender(Socket socket) {
			this.socket = socket;
			
			try {
				
				outStream = new DataOutputStream(socket.getOutputStream());
				outStream.writeUTF(name);
				
				System.out.println("대화방에 입장 하였습니다.");
				
			}// end try
			
			catch (Exception e) {
				e.printStackTrace();
			}// end catch
		}// end constructor
		
		@Override
		public void run() {
			
			Scanner sc = new Scanner(System.in);
			String msg = "";
			
			while(outStream != null) {
				
				try {
					
					msg = sc.nextLine();
					
					if("exit".equals(msg))	System.exit(0);
					
					outStream.writeUTF("[" + name + "]" + msg);
					
				} // end try
				
				catch(Exception e) {
					e.printStackTrace();
				}// end catch
				
			}// end while
			
		}// end run method
		
	} // end inner class
	
}// end class
