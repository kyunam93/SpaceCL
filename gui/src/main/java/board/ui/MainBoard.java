package board.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.table.TableModel;

import board.db.BoardBean;
import board.db.BoardCRUD;
import board.db.MemberBean;

public class MainBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCenter;
	private JTextField txtSearch;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblPage1;
	private JLabel lblPage2;
	private JLabel lblPage3;
	private JLabel lblPage4;
	private JLabel lblPage5;
	private JLabel lblPage6;
	private JLabel lblPage7;
	private JLabel lblPage8;
	private JLabel lblPage9;
	private JLabel lblPage10;
	private JTable boardTable;
	private JButton btnWrite;

	private BoardWriteModal dialog;
	private MemberBean mBean;
	private BoardCRUD bCRUD = new BoardCRUD();

	/**
	 * Create the frame.
	 */
	public MainBoard(MemberBean mBean) {
		this.mBean = mBean;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		setLocationRelativeTo(null); // 윈도우 정중앙에 위치
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// 상단
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);

		txtSearch = new JTextField();
		panelNorth.add(txtSearch);
		txtSearch.setColumns(30);

		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(txtSearch.getText() != "") {
					showTable(0);
				}
				
			}
			
		});
		panelNorth.add(btnSearch);

		btnWrite = new JButton("글쓰기");
		panelNorth.add(btnWrite);
		btnWrite.addActionListener(writeAction);

		// 중단

		panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		// 리스트 호출
		showTable(0);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("windowOpened()");
			}

			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("windowActivated()");
			}

			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("windowIconified()");
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("windowDeiconified()");
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("windowDeactivated()");
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("windowClosing()");
			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("windowActivated()");

			}

		});

		// 하단
		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton("이전");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSouth.add(btnNewButton_1);

		lblPage1 = new JLabel("1");
		panelSouth.add(lblPage1);

		lblPage2 = new JLabel("2");
		panelSouth.add(lblPage2);

		lblPage3 = new JLabel("3");
		panelSouth.add(lblPage3);

		lblPage4 = new JLabel("4");
		panelSouth.add(lblPage4);

		lblPage5 = new JLabel("5");
		panelSouth.add(lblPage5);

		lblPage6 = new JLabel("[6]");
		panelSouth.add(lblPage6);

		lblPage7 = new JLabel("7");
		panelSouth.add(lblPage7);

		lblPage8 = new JLabel("8");
		panelSouth.add(lblPage8);

		lblPage9 = new JLabel("9");
		panelSouth.add(lblPage9);

		lblPage10 = new JLabel("10");
		panelSouth.add(lblPage10);

		btnNewButton = new JButton("다음");
		panelSouth.add(btnNewButton);

	}// constructor

	// method 선언

	// 리스트 출력
	public void showTable(int pageNo) {

		// DB 조회
		List<BoardBean> boardList = bCRUD.getBoardList(pageNo, txtSearch.getText());
		
		// TODO 출력
		String header[] = { "번호", "제목", "작성자", "조회수", "작성일" };
		String contents[][] = {};

		DefaultTableModel tableModel = new DefaultTableModel(contents, header);

		if (boardList != null) {

			for (int i = 0; i < boardList.size(); i++) {
				
				Vector<String> vector = new Vector<String>();

				BoardBean bean = boardList.get(i);
				vector.add(bean.getBoardNo());
				vector.add(bean.getTitle());
				vector.add(bean.getMemberName());
				vector.add(bean.getCount());
				vector.add(bean.getRegDt());

				tableModel.addRow(vector);

			} // for

		} // if

		boardTable = new JTable(tableModel) {

			// 셀 편집 방지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};

		};

		// 셀 값 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		boardTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		boardTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		// 컬럼크기
		TableColumnModel colModel = boardTable.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(10);
		colModel.getColumn(1).setPreferredWidth(200);
		colModel.getColumn(2).setPreferredWidth(30);
		colModel.getColumn(3).setPreferredWidth(10);

		JScrollPane scrollTable = new JScrollPane(boardTable);
		scrollTable.setLocation(0, 0);
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollTable.setSize(panelCenter.getWidth(), panelCenter.getHeight());

		// add component
		panelCenter.removeAll();
		panelCenter.add(scrollTable, BorderLayout.CENTER);

	}// method showTable

	// 리스너 변수 초기화

	private ActionListener writeAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (dialog == null) {
				dialog = new BoardWriteModal(mBean, MainBoard.this);
			}
			
			if (!dialog.isVisible()) {
				
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.clearInputs();
				dialog.setVisible(true);
				
			} else {
				
				dialog.clearInputs();
				dialog.setVisible(true);
				
			}// if~else

		}// method actionPerformed
		
	};


}// class
