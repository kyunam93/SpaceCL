package BoardTest.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BoardTest.DB.MemberBean;

public class ChatMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField txtChat;
	private JButton btnSend;
	private JScrollPane scrollPane;
	private MemberBean mBean;

	// 채팅관련 상수
//	private final String SERVER_IP = "192.168.0.45";
	private final String SERVER_IP = "192.168.0.49";
	private final int SERVER_PORT = 7777;

	// 채팅 클라이언트 소켓
	private Socket client;

	/**
	 * Create the frame.
	 */
	public ChatMain(MemberBean mBean) {
		System.out.println("\n[CALL] ChatMain");

		this.mBean = mBean;

		init();

		txtChat.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					new SenderChat(client, txtChat.getText()).start();

					txtChat.setText("");
					txtChat.requestFocus();
					scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
				} // if
			}
		});

		btnSend.addActionListener(mSendClick);

		initChatSetup();

	}// constructor

	// 버튼이 많을 때는 이런 패턴으로 사용하는 것이 좋다.
	private ActionListener mSendClick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			new SenderChat(client, txtChat.getText()).start();

			txtChat.setText("");
			txtChat.requestFocus();
			scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
		}
	};

	// 서버 연결
	public void initChatSetup() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		try {
			// 서버와 연결
			client = new Socket(SERVER_IP, SERVER_PORT);
			textArea.append("서버와의 연결이 되었습니다.\n");
			textArea.append("대화명은 " + mBean.getName() + " 입니다.\n");

			// 수신 채팅 쓰레드 생성 및 시작
			ClientReciever reciever = new ClientReciever(client);
			reciever.start();

			// 서버로 닉네임을 전송
			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			output.writeUTF(mBean.getName());

		} catch (Exception e) {
			e.printStackTrace();
		} // try ~ catch
	}// method initChatSetup

	// 서버로 부터 메세지를 수신받을때 마다 스레도로 생성되어 화면에 표시해주는 클래스
	class ClientReciever extends Thread {

		Socket socket;
		DataInputStream input;

		public ClientReciever(Socket socket) {
			System.out.println("[CALL] ClientReciever");

			this.socket = socket;

			try {
				input = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			} // try ~ catch
		}// constructor

		@Override
		public void run() {
			System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

			while (input != null) {
				try {
					// 메세지를 출력
					textArea.append(input.readUTF() + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				} // try ~ catch
			} // while
		}// method run

	} // inner class

	// 채팅 내용을 서버로 전송하는 쓰레드 클래스
	class SenderChat extends Thread {

		Socket socket;
		String msg;

		public SenderChat(Socket socket, String msg) {
			System.out.println("\n[CALL] SenderChat");

			this.socket = socket;
			this.msg = msg;
		}// constructor

		@Override
		public void run() {
			System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

			// 서버로 메세지를 전송
			try {
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				output.writeUTF("[" + mBean.getName() + "]=> " + this.msg);
			} catch (Exception e) {
				e.printStackTrace();
			} // try ~ catch
		}// method run

	}// inner class SenderChat

	public void init() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
//		setLocationRelativeTo(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setEditable(false);

		scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		txtChat = new JTextField();
		panel.add(txtChat);
		txtChat.setColumns(10);

		btnSend = new JButton("전송");
		panel.add(btnSend, BorderLayout.EAST);
	}// method init

}// class
