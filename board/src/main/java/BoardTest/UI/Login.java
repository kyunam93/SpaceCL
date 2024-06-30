package BoardTest.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.util.StringUtils;

import BoardTest.DB.MemberBean;
import BoardTest.DB.MemberCRUD;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPw;
	private JButton btnLogin;

	private MemberCRUD mCRUD = new MemberCRUD();

	/**
	 * Create the frame.
	 */
	public Login() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		init();

		txtPw.addKeyListener(new KeyAdapter() {

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
			}
		});

	}// constructor

	public void checkLogin() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		String id = txtId.getText();
		String pw = new String(txtPw.getPassword());

		if (StringUtils.isNullOrEmpty(id) || StringUtils.isNullOrEmpty(pw)) {
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력해주세요.");
			return;
		}
		
		MemberBean mBean = mCRUD.checkLogin(id, pw);

		if (id.equals(mBean.getId())) {
			JOptionPane.showMessageDialog(null, mBean.getName() + " 님 환영합니다.");
			new BoardMain(mBean).setVisible(true);
			Login.this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 확인해주세요.");
		} // if ~ else
	}// method

	public void init() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridLayout gl_panel = new GridLayout(2, 2);
		gl_panel.setHgap(2);
		panel.setLayout(gl_panel);

		JLabel lbId = new JLabel("ID : ");
		lbId.setPreferredSize(new Dimension(20, 15));
		lbId.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbId);

		txtId = new JTextField();
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblPw = new JLabel("PW : ");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPw);

		txtPw = new JPasswordField();
		panel.add(txtPw);

		btnLogin = new JButton("로그인");
		btnLogin.setPreferredSize(new Dimension(69, 30));
		contentPane.add(btnLogin, BorderLayout.SOUTH);
	}// method init

}// class
