package BoardTest.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	// 화면 멤버변수
	private JPanel contentPane;
	private JPanel pnlBoard;
	private JPanel pnlPages;
	private JPanel pnlPaging;
	private JLabel lblTotBoard;
	private JTable table;
	private JButton btnHome;
	private JButton btnCatting;
	private JButton btnWrite;
	private JButton btnSearch;
	private JTextField txtSearch;

	// 기능 멤버변수
	private String searchWord = "";
	private BoardCRUD bCRUD = new BoardCRUD();
	private MemberBean mBean;
	private BoardBean bBean;
	private List<BoardBean> boardList;
	private DefaultTableModel model;
	private int totBoardCnt = 0; // 총 게시물 수
	private int curPage = 1; // 현재페이지
	private int totPage = 0; // 총 페이지 수
	private int pageRows = 20; // 한 페이지 당 보여줄 게시글 수

	/**
	 * Create the frame.
	 */
	public BoardMain(MemberBean mBean) {
		System.out.println("\n[CALL] BoardMain");

		this.mBean = mBean;

		// 화면 만들기
		init();

		// 홈 버튼 기능
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				curPage = 1;
				txtSearch.setText("");
				showBoard();
			}
		});

		// 채팅 버튼 기능
		btnCatting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChatMain(mBean).setVisible(true);
			}
		});

		// 검색어 엔터 기능
		txtSearch.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					searching();
				} // if
			}
		});

		// 검색 버튼 기능
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searching();
			}
		});

		// 글쓰기 버튼 기능
		btnWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardWrite write = new BoardWrite(mBean, BoardMain.this);
				write.setModal(true);
				write.setVisible(true);
			}
		});
	}// consturctor

	public void searching() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		searchWord = txtSearch.getText();

		if (StringUtils.isNullOrEmpty(searchWord)) {
			JOptionPane.showMessageDialog(null, "검색어를 입력해 주세요.");

			return;
		} else {
			showBoard();
		} // if ~ else
	}// method searching

	public void setTableRows() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		boardList = bCRUD.getBoardList(searchWord, curPage, pageRows);

		for (BoardBean bean : boardList) {
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
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		searchWord = txtSearch.getText();
		totBoardCnt = bCRUD.getTotBoardCnt(searchWord);

		lblTotBoard = new JLabel("총 " + totBoardCnt + " 건");

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		String header[] = { "번호", "제목", "작성자", "조회수", "작성일" };
		model = new DefaultTableModel(null, header);

		setTableRows();

		table = new JTable(model) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.getColumnModel().getColumn(0).setCellRenderer(renderer);
		table.getColumnModel().getColumn(1).setCellRenderer(renderer);
		table.getColumnModel().getColumn(2).setCellRenderer(renderer);
		table.getColumnModel().getColumn(3).setCellRenderer(renderer);
		table.getColumnModel().getColumn(4).setCellRenderer(renderer);
		table.getTableHeader().setPreferredSize(new Dimension(0, 30));
		table.setRowHeight(30);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				JTable table = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = table.rowAtPoint(point);

				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					String board_no = (String) table.getValueAt(row, 0);
					System.out.println("[DATA] board_no " + board_no);

					bBean = bCRUD.getBoard(board_no);

					new BoardDetail(mBean, bBean, BoardMain.this).setVisible(true);
				} // if
			}
		});

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(50);
		columnModel.getColumn(3).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(50);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		showPages();

		pnlBoard.removeAll();
		pnlBoard.revalidate();
		pnlBoard.add(lblTotBoard, BorderLayout.NORTH);
		pnlBoard.add(scrollPane, BorderLayout.CENTER);
	}// method showBoard

	public void showPages() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		JButton btnPre = new JButton("이전");
		JButton btnNext = new JButton("다음");
		pnlPages = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlPages.getLayout();
		flowLayout.setVgap(0);

		paging();

		btnPre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (curPage > 1) {
					curPage = curPage - 1;
					showBoard();
				} else {
					return;
				} // if ~ else
			}
		});

		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (curPage < (totPage - 1)) {
					curPage = curPage + 1;
					showBoard();
				} else {
					return;
				} // if ~ else
			}
		});

		pnlPaging.removeAll();
		pnlPaging.revalidate();
		pnlPaging.add(btnPre, BorderLayout.WEST);
		pnlPaging.add(btnNext, BorderLayout.EAST);
		pnlPaging.add(pnlPages, BorderLayout.CENTER);
	}// method showPages

	public void paging() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		totPage = (int) Math.ceil(totBoardCnt / pageRows * 1.0) + 1;
		System.out.println("[DATA] totBoardCnt " + totBoardCnt);
		System.out.println("[DATA] pageRows " + pageRows);
		System.out.println("[DATA] totPage " + totPage);
		System.out.println("[DATA] curPage " + curPage);

		for (int i = 1; i < totPage; i++) {
			String page = "";

			if (i == curPage) {
				page = "[" + i + "]";
			} else {
				page = "" + i;
			} // if ~ else

			JButton btnPage = new JButton(page);

			btnPage.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btnPage = (JButton) e.getSource();
					String page = btnPage.getText();

					if (page.startsWith("[")) {
						return;
					} else {
						curPage = Integer.parseInt(page);
						showBoard();
					}// if ~ else
				}
			});

			pnlPages.add(btnPage);
		} // for
	}// method paging

	public void init() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

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

		btnHome = new JButton("홈");
		btnHome.setFocusable(false);
		pnlSearch.add(btnHome);

		btnCatting = new JButton("채팅");
		btnCatting.setFocusable(false);
		pnlSearch.add(btnCatting);

		txtSearch = new JTextField();
		txtSearch.setPreferredSize(new Dimension(0, 26));
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

		pnlPaging = new JPanel();
		contentPane.add(pnlPaging, BorderLayout.SOUTH);
		pnlPaging.setLayout(new BorderLayout(0, 0));

		showBoard();
	}// method init

}// class
