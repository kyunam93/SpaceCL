package chapter17;

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
import javax.swing.JTextField;

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

	// 멤버 변수 선언
	ChatView chat = null;

	public LoginView() {

		// init instance
		//JButton nBtn = new JButton("North");
		JButton loginBtn = new JButton("Login");
		//JButton sBtn = new JButton("South");
		//JButton wBtn = new JButton("West");
		//JButton eBtn = new JButton("East");
		//JButton cBtn = new JButton("Center");
		Panel panel = new Panel();
		Container con = getContentPane();
		Label idLabel = new Label("ID");
		Label pwLabel = new Label("PW");
		TextField idTxt = new TextField();
		JPasswordField pwTxt = new JPasswordField();
		
		// basic setting
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Border Layout");
		setLocationRelativeTo(null);	// 윈도우 정중앙에 위치
		
		panel.setLayout(new GridLayout(2,2));
		
		// detail setting
		con.setLayout(new BorderLayout(30, 20));

		// add button in container
		//con.add(nBtn, BorderLayout.NORTH );
		// con.add(sBtn, BorderLayout.SOUTH );
		con.add(loginBtn, BorderLayout.SOUTH );
		//con.add(wBtn, BorderLayout.WEST );
		//con.add(eBtn, BorderLayout.EAST );
		//con.add(cBtn, BorderLayout.CENTER );
		
		// add panel in center layout
		con.add(panel, BorderLayout.CENTER );

		// label detail setting
		idLabel.setAlignment(Label.CENTER);
		pwLabel.setAlignment(Label.CENTER);
		
		// add label and txtField in panel 
		panel.add(idLabel);		panel.add(idTxt);
		panel.add(pwLabel);		panel.add(pwTxt);
		
		// show layout
		setVisible(true);
		
		// Login Button Event 
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// check id and pw value
				System.out.println("id : " + idTxt.getText());
				System.out.println("pw : " + pwTxt.getText());

				isLoginCheck(idTxt, pwTxt);

			}// end method

		});
		
		// Password Enter Event
		pwTxt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
	
			@Override
			public void keyPressed(KeyEvent e) {
				
				if( e.getKeyChar() == KeyEvent.VK_ENTER ) {
					System.out.println("엔터");
					isLoginCheck(idTxt, pwTxt);
				}
				
			}// end method
			
		});
		
	}// end constructor
	
	/**
	 * 로그인 체크하는 기능
	 * @param idTxt
	 * @param pwTxt
	 * @return true: login success, false: is wrong ID or Password
	 */
	public boolean isLoginCheck(TextField idTxt, JPasswordField pwTxt) {
		
		// check id
		if ("".equals(idTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please, Input your ID");
			idTxt.requestFocus();
			return false;
		}
		
		else if ("".equals(pwTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please, Input your Password");
			pwTxt.requestFocus();
			return false;
		}
	
		else if("abcd".equals(idTxt.getText()) && "0987!".equals(pwTxt.getText())) {
			chat = new ChatView();
			
			setVisible(false);	
			chat.setVisible(true);
			
			JOptionPane.showMessageDialog(null, "Login Success");
			
			return true;
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Is wrong ID or Password");
			idTxt.requestFocus();
			return false;
		}
		
	}// end method
	
}// end class
