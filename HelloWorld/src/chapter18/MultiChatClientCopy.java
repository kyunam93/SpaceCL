package chapter18;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import chapter18.UI.ChatView;

public class MultiChatClientCopy {

	// member variable init
	private ChatView chat;
	private Socket socket;

	// constructor init
	public MultiChatClientCopy(ChatView chat) {

		this.chat = chat;

		try {

			// 서버에 접속하기
			socket = new Socket(chat.ip, 9999);

			String msg = "Connected server... " + socket.isConnected();
			chat.area.append(msg + '\n');

		} // end try

		catch (Exception e) {
			e.printStackTrace();
		} // end catch

	}// end constructor
	
	// method init

	/**
	 * 서버로 메세지를 전송하는 메소드
	 * 
	 * @param msg
	 */
	public void sendMessage(String msg) {

		try {

			OutputStream oStream = socket.getOutputStream();
			
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//			bWriter.write(msg.getBytes());
			bWriter.flush();// 메세지를 바로 송신(전송)

			
//			BufferedOutputStream boStream = new BufferedOutputStream(oStream);
//			byte[] bytes = msg.getBytes();
//			boStream.write(bytes);

		} // end try

		catch (IOException e) {
			e.printStackTrace();
		} // end catch

	}// end method

}// end class
