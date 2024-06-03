package chapter17;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * https://bskwak.tistory.com/181 
 * 그리드 레이아웃 샘플 따라 만들어보기
 */

public class Swing2 extends JFrame{

	public static void main(String[] args) {
		
		Swing2 s2 = new Swing2();
		

		s2.setTitle("Grid Layout Sample");
		s2.setDefaultCloseOperation(EXIT_ON_CLOSE);
		s2.setResizable(false);		
		
		GridLayout grid = new GridLayout(4, 2);
		grid.setVgap(5);
		
		Container con = s2.getContentPane();
		con.setLayout(grid);
		
		con.add(new JLabel("이름"));
		con.add(new JTextField(""));
		con.add(new JLabel("학번"));
		con.add(new JTextField(""));
		con.add(new JLabel("학과"));
		con.add(new JTextField(""));
		con.add(new JLabel("과목"));
		con.add(new JTextField(""));
		
		s2.setVisible(true);
		s2.setSize(300, 200);
		
	}// end main
	
}// end class
