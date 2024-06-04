package chapter17;

import javax.swing.JOptionPane;

public class PaneTest {

	public static void main(String[] args) {

		JOptionPane.showConfirmDialog(null, "is your not password");
		JOptionPane.showMessageDialog(null, "is your not password");
		
		String age = JOptionPane.showInputDialog(null, "how old are you?");
		JOptionPane.showMessageDialog(null, "your age is " + age);
	
	}// end main

}// end class
