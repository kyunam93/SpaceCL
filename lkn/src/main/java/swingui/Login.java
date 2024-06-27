package swingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;

import db.MemberCRUD;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 150);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2));
		
		JLabel lblId = new JLabel("ID : ");
		lblId.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblId);
		
		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblPw = new JLabel("PW :");
		lblPw.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblPw);
		
		txtPw = new JPasswordField();
		panel.add(txtPw);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.setSize(new Dimension(contentPane.getWidth(), 50));
		contentPane.add(btnLogin, BorderLayout.SOUTH);
		
		txtPw.addKeyListener(new KeyListener() {
			
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
				
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					checkLogin();				
				}
				
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkLogin();				
			}// method
		});
		
	}// method
	
	public void checkLogin() {
		
		String id = txtId.getText();
		String pw = new String(txtPw.getPassword());
		
		if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(pw)) {
			MemberCRUD mCRUD = new MemberCRUD();
			
			int loginResult = mCRUD.getMember(id, pw);
			
			if(loginResult > 0) {
				JOptionPane.showMessageDialog(null, id + "님 환영합니다.");
				Login.this.dispose();
				new MainBoard().setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호 입력이 틀렸습니다.");
			}// if~ else
			
		} else {
			JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호 입력을 확인해주세요");
			txtId.setFocusable(true);
		}// if ~ else
		
	}// method
	
}// class
