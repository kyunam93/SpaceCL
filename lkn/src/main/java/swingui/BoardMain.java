package swingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.util.StringUtils;

import db.BoardBean;
import db.BoardCRUD;
import db.MemberBean;

public class BoardMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlPaging;
	private JTextField txtSearch;

	private MemberBean mBean;
	private BoardCRUD bCRUD = new BoardCRUD();

	private String searchWord = "";

	/** 한 페이지당 게시글 수 **/
	private int pageSize = 10;

	/** 현재 페이지 **/
	private int curPage = 1;

	/** 총 게시글 수 **/
	private int listCnt;

	/** 총 페이지 수 **/
	private int pageCnt;

	/**
	 * Create the frame.
	 */
	public BoardMain(MemberBean mBean) {

		System.out.println("[CALL] BoardMain 생성자");

		this.mBean = mBean;

		init();

	}// method

	// 화면 구성
	private void init() {
		System.out.println("[Call] Method - init()!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlSearch = new JPanel();
		contentPane.add(pnlSearch, BorderLayout.NORTH);
		pnlSearch.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel pnlBoard = new JPanel();
		contentPane.add(pnlBoard, BorderLayout.CENTER);
		pnlBoard.setLayout(new BorderLayout(0, 0));

		pnlPaging = new JPanel();
		contentPane.add(pnlPaging, BorderLayout.SOUTH);
		pnlPaging.setLayout(new BorderLayout(0, 0));

		this.showTable(pnlBoard, pnlPaging, null);
		this.showSearchBar(pnlSearch, pnlBoard);

	}

	public void showSearchBar(JPanel pnlSearch, JPanel pnlBoard) {
		System.out.println("[Call] Method - showSearchBar()!");

		txtSearch = new JTextField();
		txtSearch.setPreferredSize(new Dimension(7, 23));
		pnlSearch.add(txtSearch);
		txtSearch.setColumns(30);

		JButton btnSearch = new JButton("검색");
		pnlSearch.add(btnSearch);

		JButton btnWrite = new JButton("글쓰기");
		pnlSearch.add(btnWrite);

		/******************** 기능 ********************/
		// 검색 버튼
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[CLICKED] 검색 버튼");
				searchWord = txtSearch.getText();

				if (StringUtils.isNullOrEmpty(searchWord)) {
					JOptionPane.showMessageDialog(null, "검색어를 입력해주세요.");
					txtSearch.setFocusable(true);
				} else {
					BoardMain.this.showTable(pnlBoard, pnlPaging, searchWord);
				}

			}
		});

		// 글쓰기
		btnWrite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("[CLICKED] 글쓰기 버튼");
				BoardWriteModal modal = new BoardWriteModal(pnlBoard, pnlPaging, mBean, BoardMain.this);
				modal.setModal(true);
				modal.setVisible(true);
			}
		});

	}

	public void showTable(JPanel pnlBoard, JPanel pnlPaging, String searchWord) {
		System.out.println("[Call] Method - showTable()!");
		System.out.println(listCnt);

		this.showPagingBar(pnlPaging, pnlBoard);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblListCnt = new JLabel("총 " + listCnt + " 건");
		panel.add(lblListCnt);

		// 테이블 내용 채우기
		// 테이블 헤더 생성
		String cNames[] = { "번호", "제목", "작성자", "조회수", "작성일" };
		DefaultTableModel tModel = new DefaultTableModel(null, cNames);

		List<BoardBean> list = bCRUD.getBoardList(searchWord);

		if (list != null) {
			for (BoardBean bean : list) {

				Vector<String> rowData = new Vector<String>();
				rowData.add(bean.getBoard_no());
				rowData.add(bean.getTitle());
				rowData.add(bean.getWriter());
				rowData.add(bean.getCount());
				rowData.add(bean.getReg_dt());

				tModel.addRow(rowData);

				System.out.println(rowData);

			}
		}

		JTable table = new JTable(tModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("[CLIECKED] 테이블 클릭 : " + e);

				JTable table = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = table.rowAtPoint(point); // 테이블 행
				String boardNo = (String) table.getValueAt(row, 0);

				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {

					BoardBean bBean = bCRUD.getBoard(boardNo);

					new BoardWriteDetail(bBean, BoardMain.this, pnlBoard, pnlPaging).setVisible(true);
					;
				}
			}
		});

		JScrollPane scroll = new JScrollPane(table);
		scroll.setLocation(0, 0);
		scroll.setPreferredSize(new Dimension(pnlBoard.getWidth(), 0));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// 스크롤에 테이블 추가
		pnlBoard.removeAll();
		pnlBoard.revalidate();
		pnlBoard.add(scroll, BorderLayout.CENTER);
		pnlBoard.add(panel, BorderLayout.NORTH);

	}

	public void showPagingBar(JPanel pnlPaging, JPanel pnlBoard) {
		System.out.println("[Call] Method - showPagingBar()!");

		JButton btnPre = new JButton("이전");
		pnlPaging.add(btnPre, BorderLayout.WEST);

		JPanel pages = new JPanel();
		pnlPaging.add(pages, BorderLayout.CENTER);

		JButton btnNext = new JButton("다음");
		pnlPaging.add(btnNext, BorderLayout.EAST);

		this.paging(pages, pnlBoard);
	}

	public void paging(JPanel pages, JPanel pnlBoard) {

		// 총 게시글 수
		listCnt = bCRUD.getListCnt(searchWord);
		System.out.println("[DATA] 총 게시글 수 : " + listCnt);
		// 총 페이지 수
		pageCnt = (int) Math.ceil(listCnt / (double) pageSize);
		System.out.println("[DATA] 총 페이지 수 : " + pageCnt);

		for (int i = 1; i <= pageCnt; i++) {
			JButton btnPage;
			String name = "" + i;

			if (i == curPage) {
				name = "[" + i + "]";
				btnPage = new JButton(name);
			} else {
				btnPage = new JButton(name);
			}

			btnPage.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					String btnPageNo = btnPage.getText();
					System.out.println("[DATA] 클릭한 페이지 : " + btnPageNo);

					if(btnPageNo.startsWith("[")) {
						return;
					}
					
					int pageNo = Integer.parseInt(btnPageNo);
					
					showTable(pnlBoard, pnlPaging, null);
				}
			});

			pages.add(btnPage);
		}

	}

}// class
