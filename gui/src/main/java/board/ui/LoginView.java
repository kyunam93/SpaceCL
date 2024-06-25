package board.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import board.db.MemberBean;
import board.db.MemberCRUD;

public class LoginView extends JFrame {
	
	private TextField txtId = null;
	private JPasswordField txtPw = null;
	private MemberCRUD mCRUD = new MemberCRUD();
	
	public static void main(String[] args) {
		new LoginView();
	}// main

	public LoginView() {

		// center
		Label lblId = new Label("ID : ");
		txtId = new TextField();
		Label lblPw = new Label("PW : ");
		txtPw = new JPasswordField();
		txtPw.addKeyListener(mFieldLogin);

		// south
		Button btnLogin = new Button("Login");
		btnLogin.addActionListener(mBtnLogin );

		// 패널 설정
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(2, 2));
		panel.add(lblId);
		panel.add(txtId);
		panel.add(lblPw);
		panel.add(txtPw);

		// 컨테이너 설정
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(btnLogin, BorderLayout.SOUTH);
		btnLogin.setPreferredSize(new Dimension(200, 30));

		container.add(panel, BorderLayout.CENTER);

		// 프레임 설정
		setSize(200, 130);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null); // 윈도우 정중앙에 위치

	}// constructor
	
	ActionListener mBtnLogin = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			checkLogin();

		}// method
	};
	
	KeyListener mFieldLogin = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyChar() == e.VK_ENTER) {
				checkLogin();
			}
		}
	};
	
	private void checkLogin() {
		String id = txtId.getText().trim();
		String pw = new String(txtPw.getPassword());

		boolean isFind = mCRUD.getFindMember(id, pw);

		if (isFind == true) {
			
			JOptionPane.showMessageDialog(null, "로그인 성공");
			
			// 멤버 조회
			MemberBean mBean = mCRUD.getMember(id);
			MainBoard board = new MainBoard(mBean);
			
			board.setVisible(true);
			LoginView.this.setVisible(false);
			
		} else {
			txtId.setFocusable(true);
			JOptionPane.showMessageDialog(null, "로그인 실패");
		} // if ~ else
	}

}// class
