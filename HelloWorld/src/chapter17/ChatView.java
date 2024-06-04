package chapter17;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatView extends JFrame {

	public ChatView() {

		// init instance
		Container con = getContentPane();
		Panel panel = new Panel();
		JTextArea area = new JTextArea();
		JTextField txt = new JTextField();
		JButton entBtn = new JButton("Enter");

		// view(Container) basic setting
		setSize(300, 600);
		setTitle("Chatting");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	// 윈도우 정중앙에 위치

		// panel setting
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(0, 40));
		panel.add(txt, BorderLayout.CENTER);
		panel.add(entBtn, BorderLayout.EAST);

		area.setEditable(false);

		con.setLayout(new BorderLayout(20, 10));
		con.add(area, BorderLayout.CENTER);
		con.add(panel, BorderLayout.SOUTH);

		entBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sendText(area, txt);

			}// end method

		});

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

	}// end constructor

	public void sendText(JTextArea area, JTextField txt) {

		area.append(txt.getText() + "\n");
		txt.setText("");

	}// end method

}// end class
