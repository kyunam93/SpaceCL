package chapter18.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.channels.ClosedByInterruptException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatView extends JFrame {

	// member variable init
	String ip;
	String nickname;
	public JTextArea area;
	private MultiChatClient client;
	
	// constructor init
	public ChatView(String ip, String nickname) {

		this.ip = ip;
		this.nickname = nickname;
		
		// init instance
		Container con = getContentPane();
		Panel panel = new Panel();
		// JTextArea area = new JTextArea();
		JTextField txt = new JTextField();
		JButton entBtn = new JButton("Enter");

		// view(Container) basic setting
		setSize(300, 600);
		setTitle("Chatting");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 윈도우 정중앙에 위치

		// panel setting
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(0, 40));
		panel.add(txt, BorderLayout.CENTER);
		panel.add(entBtn, BorderLayout.EAST);

		area = new JTextArea();
		area.setEditable(false);

		// 스크롤 뷰에 텍스트 에리아 추가
		JScrollPane scroll = new JScrollPane(area);
		scroll.scrollRectToVisible(area.getBounds());
		
		// container setting
		con.setLayout(new BorderLayout(20, 10));
		con.add(scroll, BorderLayout.CENTER);
		con.add(panel, BorderLayout.SOUTH);


		// send button event 
		entBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sendText(area, txt);

			}// end method

		});

		// text field key event
		txt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {	}

			@Override
			public void keyReleased(KeyEvent e) {	}

			@Override
			public void keyPressed(KeyEvent e) {

				// 엔터키 이벤트 시 동작
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					sendText(area, txt);
				}

			}// end method

		});

		client = new MultiChatClient(this, this.nickname);
		client.start();
		
	}// end constructor

	/**
	 * Area 화면에 출력
	 * @param msg : 채팅 메세지
	 */
	public void addViewMsg(String msg) {
		area.append(msg + "\n");
	}
	
	/**
	 * 입력한 메세지를 Area 화면에 메세지를 띄운다
	 * 서버에 메세지를 보낸다.
	 * 
	 * @param area
	 * @param txt
	 */
	public void sendText(JTextArea area, JTextField txt) {

		// 서버로 메세지 전송
//		client.sendMessage(nickname + " ==> " + txt.getText());
		client.sendMsg(txt.getText());
		
		// TextArea로 메세지 적용\
//		area.setText(area.getText() + txt.getText() + "\n");
		area.append(txt.getText() + "\n");
		txt.setText("");
		txt.requestFocus();

	}// end method

}// end class
