package swingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class MainBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchWord;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBoard frame = new MainBoard();
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
	public MainBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlNav = new JPanel();
		contentPane.add(pnlNav, BorderLayout.NORTH);

		searchWord = new JTextField();
		searchWord.setSize(new Dimension(300, 0));

		pnlNav.add(searchWord);
		searchWord.setColumns(10);

		JButton btnSearch = new JButton("검색");
		pnlNav.add(btnSearch);

		JButton btnWrite = new JButton("글쓰기");
		pnlNav.add(btnWrite);

		JPanel pnlBoard = new JPanel();
		contentPane.add(pnlBoard, BorderLayout.CENTER);

		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setSize(new Dimension(pnlBoard.getWidth(), pnlBoard.getHeight()));
		scrollTable.setLocation(0, 0);
		scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		pnlBoard.add(scrollTable);

		table = new JTable();
		scrollTable.add(table);

		JPanel pnlPaging = new JPanel();
		contentPane.add(pnlPaging, BorderLayout.SOUTH);
		pnlPaging.setLayout(new BorderLayout(0, 0));

		JButton btnPre = new JButton("이전");
		pnlPaging.add(btnPre, BorderLayout.WEST);

		JPanel panel = new JPanel();
		pnlPaging.add(panel, BorderLayout.CENTER);

		JButton btnNext = new JButton("다음");
		pnlPaging.add(btnNext, BorderLayout.EAST);
	}

}
