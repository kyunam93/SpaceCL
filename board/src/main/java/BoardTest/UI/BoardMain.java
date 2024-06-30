package BoardTest.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import BoardTest.DB.MemberBean;

public class BoardMain extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;
	private JButton btnWrite;

	private MemberBean mBean;

	/**
	 * Create the frame.
	 */
	public BoardMain(MemberBean mBean) {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		this.mBean = mBean;

		init();

		// 글쓰기 버튼 기능
		btnWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardWrite write = new BoardWrite(mBean);
				write.setModal(true);
				write.setVisible(true);
			}
		});
	}// consturctor

	public void init() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlSearch = new JPanel();
		contentPane.add(pnlSearch, BorderLayout.NORTH);

		JButton btnHome = new JButton("홈");
		btnHome.setFocusable(false);
		pnlSearch.add(btnHome);

		txtSearch = new JTextField();
		txtSearch.setColumns(30);
		txtSearch.setFocusable(true);
		pnlSearch.add(txtSearch);

		JButton btnSearch = new JButton("검색");
		pnlSearch.add(btnSearch);

		btnWrite = new JButton("글쓰기");
		pnlSearch.add(btnWrite);

		JPanel pnlBoard = new JPanel();
		contentPane.add(pnlBoard, BorderLayout.CENTER);
		pnlBoard.setLayout(new BorderLayout(0, 0));

		JLabel lblTotBoard = new JLabel("총 0 건");
		pnlBoard.add(lblTotBoard, BorderLayout.NORTH);

		table = new JTable();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlBoard.add(scrollPane, BorderLayout.CENTER);

		JPanel pnlPaging = new JPanel();
		contentPane.add(pnlPaging, BorderLayout.SOUTH);
		pnlPaging.setLayout(new BorderLayout(0, 0));

		JButton btnPre = new JButton("이전");
		pnlPaging.add(btnPre, BorderLayout.WEST);

		JButton btnNext = new JButton("다음");
		pnlPaging.add(btnNext, BorderLayout.EAST);

		JPanel pnlPages = new JPanel();
		pnlPaging.add(pnlPages, BorderLayout.CENTER);
	}// method init

}// class
