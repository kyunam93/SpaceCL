package board.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BoardWrite extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardWrite frame = new BoardWrite();
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
	public BoardWrite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		setLocationRelativeTo(null); // 윈도우 정중앙에 위치
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel writeNorth = new JPanel();
		contentPane.add(writeNorth, BorderLayout.NORTH);
		writeNorth.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel("제목: ");
		writeNorth.add(lblTitle, BorderLayout.WEST);
		
		textTitle = new JTextField();
		textTitle.setColumns(50);
		writeNorth.add(textTitle, BorderLayout.CENTER);
		
		JPanel writeCenter = new JPanel();
		contentPane.add(writeCenter, BorderLayout.CENTER);
		writeCenter.setLayout(new BorderLayout(0, 0));
		
		JLabel lblContent = new JLabel("내용: ");
		writeCenter.add(lblContent, BorderLayout.WEST);
		
		JTextArea textArea = new JTextArea();
		writeCenter.add(textArea);
		
		JPanel writeSouth = new JPanel();
		contentPane.add(writeSouth, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("확인");
		writeSouth.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		writeSouth.add(btnNewButton_1);
	}

}// class
