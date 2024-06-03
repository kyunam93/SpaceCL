package chapter17;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Swing1 extends JFrame {

	public static void main(String[] args) {

		// create instance
		Swing1 s1 = new Swing1();
		FlowLayout flow = new FlowLayout();
		JButton btn1 = new JButton("OK");
		JButton btn2 = new JButton("Cancel");
		JButton btn3 = new JButton("Ignore");

		JPanel panel = new JPanel();
		JTextField field = new JTextField(100);
		JTextArea area = new JTextArea(100, 200);

		// control instance
		s1.setVisible(true);
		s1.setSize(300, 300);
		s1.setTitle("Hi, I am Swing Program");
		// if you click 'x', you can close frame perfectly
		s1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// you can't set new size
		s1.setResizable(false);

		Container contentPane = s1.getContentPane();
		contentPane.setBackground(Color.ORANGE);
		// default 'Border Layout'
		contentPane.setLayout(flow);
		contentPane.add(btn1);
		contentPane.add(btn2);
		contentPane.add(btn3);

		
		// 중복된 이벤트 처리를 간략히 줄어주는 코드
		ActionListener btnAct = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand() + " Clicked!");
			}
		};
		
		btn1.addActionListener(btnAct);
		btn2.addActionListener(btnAct);
		btn3.addActionListener(btnAct);

	}// end main

}// end class
