package BoardTest.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.mysql.cj.util.StringUtils;

import BoardTest.DB.BoardBean;
import BoardTest.DB.BoardCRUD;
import BoardTest.DB.MemberBean;

public class BoardMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlBoard;
	private JTextField txtSearch;
	private JTable table;
	private JButton btnCatting;
	private JButton btnWrite;
	private JButton btnSearch;

	private MemberBean memBean;
	private BoardCRUD bCRUD = new BoardCRUD();
	private DefaultTableModel model;
	private String searchWord = "";
	private int totBoardCnt = 0;

	/**
	 * Create the frame.
	 */
	public BoardMain(MemberBean mBean) {
		System.out.println("\n[CALL] BoardMain");

		this.memBean = mBean;

		// 화면 만들기
		init();

		// 채팅 버튼 기능
		btnCatting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChatMain(memBean).setVisible(true);
			}
		});

		// 글쓰기 버튼 기능
		btnWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardWrite write = new BoardWrite(mBean);
				write.setModal(true);
				write.setVisible(true);
			}
		});

		// 검색 기능
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchWord = txtSearch.getText();

				if (StringUtils.isNullOrEmpty(searchWord)) {
					JOptionPane.showMessageDialog(null, "검색어를 입력해 주세요.");

					return;
				} else {
					showBoard();
				} // if ~ else
			}
		});
	}// consturctor

	public void setTableRows() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		List<BoardBean> list = bCRUD.getBoardList(searchWord);

		for (BoardBean bean : list) {
			Vector<String> row = new Vector<String>();

			row.add(bean.getBoard_no());
			row.add(bean.getTitle());
			row.add(bean.getWriter());
			row.add(bean.getCount());
			row.add(bean.getReg_dt());

			model.addRow(row);
		} // for
	}// method setTableRows

	public void showBoard() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		searchWord = txtSearch.getText();
		totBoardCnt = bCRUD.getTotBoardCnt(searchWord);

		JLabel lblTotBoard = new JLabel("총 " + totBoardCnt + " 건");
		pnlBoard.add(lblTotBoard, BorderLayout.NORTH);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		String header[] = { "번호", "제목", "작성자", "조회수", "작성일" };
		model = new DefaultTableModel(null, header);

		//
		setTableRows();

		table = new JTable(model);
		table.getColumnModel().getColumn(0).setCellRenderer(renderer);
		table.getColumnModel().getColumn(1).setCellRenderer(renderer);
		table.getColumnModel().getColumn(2).setCellRenderer(renderer);
		table.getColumnModel().getColumn(3).setCellRenderer(renderer);
		table.getColumnModel().getColumn(4).setCellRenderer(renderer);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(50);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pnlBoard.removeAll();
		pnlBoard.revalidate();
		pnlBoard.add(scrollPane, BorderLayout.CENTER);
	}

	public void init() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		setResizable(false);

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

		btnCatting = new JButton("채팅");
		btnCatting.setFocusable(false);
		pnlSearch.add(btnCatting);

		txtSearch = new JTextField();
		txtSearch.setColumns(28);
		txtSearch.setFocusable(true);
		pnlSearch.add(txtSearch);

		btnSearch = new JButton("검색");
		pnlSearch.add(btnSearch);

		btnWrite = new JButton("글쓰기");
		pnlSearch.add(btnWrite);

		pnlBoard = new JPanel();
		contentPane.add(pnlBoard, BorderLayout.CENTER);
		pnlBoard.setLayout(new BorderLayout(0, 0));
		
		showBoard();

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
