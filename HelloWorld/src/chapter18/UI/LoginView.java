package chapter18.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

/**
 * Swing - 로그인 화면 구성
 * 
 * ID 텍스트박스
 * PW 텍스트박스
 * 	로그인 버튼
 * 
 * quiz 
 * id & pw value를 받아 
 * 조건 if id == abcd and pw ==  1234 
 * 결과 true : showMessage "로그인 되었습니다."
 *      false : show message "없는 아이디이거나 틀린 비밀번호를 입력하셨습니다."
 */
public class LoginView extends JFrame {

	// member variable init
	private String mIP;
	private String mNickName;
	private JTextArea area;

	public LoginView() {

		// init instance
		// JButton nBtn = new JButton("North");
		JButton loginBtn = new JButton("Login");
		// JButton sBtn = new JButton("South");
		// JButton wBtn = new JButton("West");
		// JButton eBtn = new JButton("East");
		// JButton cBtn = new JButton("Center");
		Panel panel = new Panel();
		Container con = getContentPane();
		Label ipLabel = new Label("IP");
		Label nickLabel = new Label("NickName");
		TextField ipTxt = new TextField();
		TextField nickTxt = new TextField();

		// basic setting
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Border Layout");
		setLocationRelativeTo(null); // 윈도우 정중앙에 위치

		panel.setLayout(new GridLayout(2, 2));

		// detail setting
		con.setLayout(new BorderLayout(30, 20));

		// add button in container
		// con.add(nBtn, BorderLayout.NORTH );
		// con.add(sBtn, BorderLayout.SOUTH );
		con.add(loginBtn, BorderLayout.SOUTH);
		// con.add(wBtn, BorderLayout.WEST );
		// con.add(eBtn, BorderLayout.EAST );
		// con.add(cBtn, BorderLayout.CENTER );

		// add panel in center layout
		con.add(panel, BorderLayout.CENTER);

		// label detail setting
		ipLabel.setAlignment(Label.CENTER);
		nickLabel.setAlignment(Label.CENTER);

		// IP 강제 설정
		ipTxt.setText("192.168.0.49");

		// add label and txtField in panel
		panel.add(ipLabel);
		panel.add(ipTxt);
		panel.add(nickLabel);
		panel.add(nickTxt);

		// show layout
		setVisible(true);

		// Login Button Event
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// check id and pw value
				System.out.println("ip : " + ipTxt.getText());
				System.out.println("nickname : " + nickTxt.getText());

				isLoginCheck(ipTxt, nickTxt);

			}// end method

		});

		// Password Enter Event
		nickTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("엔터");
					isLoginCheck(ipTxt, nickTxt);
				} // end if

			}// end method

		});

	}// end constructor
	
	/**
	 * 로그인 체크하는 기능
	 * 
	 * @param ipTxt
	 * @param nickTxt
	 * @return true: login success, false: is wrong ID or Password
	 */
	public boolean isLoginCheck(TextField ipTxt, TextField nickTxt) {

		// check id
		if ("".equals(ipTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please, Input your IP");
			ipTxt.requestFocus();
			return false;
		}

		else if ("".equals(nickTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please, Input your NickName");
			nickTxt.requestFocus();
			return false;
		}

		// create 'chat view' instance
		ChatView chat = new ChatView(ipTxt.getText(), nickTxt.getText());

		setVisible(false); // hide login view
		chat.setVisible(true); // show chat view

		JOptionPane.showMessageDialog(null, "Login Success");

		return true;

	}// end method

}// end class
