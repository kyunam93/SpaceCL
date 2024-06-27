package board.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import board.db.BoardBean;
import board.db.BoardCRUD;
import board.db.MemberBean;

public class BoardDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextArea txtContent;
	BoardCRUD bCRUD = new BoardCRUD();
	MainBoard mainBoard;
	MemberBean mBean;
	BoardBean bBean;

	/**
	 * Create the dialog.
	 */
	public BoardDetail(MemberBean mBean, BoardBean bBean, MainBoard mainBoard) {
		this.mBean = mBean;
		this.bBean = bBean;
		this.mainBoard = mainBoard;

		setBounds(100, 100, 700, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel writeNorth = new JPanel();
		contentPanel.add(writeNorth, BorderLayout.NORTH);
		writeNorth.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("제목: ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setPreferredSize(new Dimension(50, 30));
		writeNorth.add(lblTitle, BorderLayout.WEST);

		txtTitle = new JTextField();
		txtTitle.setColumns(50);
		writeNorth.add(txtTitle, BorderLayout.CENTER);

		JPanel writeCenter = new JPanel();
		contentPanel.add(writeCenter, BorderLayout.CENTER);
		writeCenter.setLayout(new BorderLayout(0, 0));

		JLabel lblContent = new JLabel("내용: ");
		lblContent.setPreferredSize(new Dimension(50, 0));
		lblContent.setHorizontalAlignment(SwingConstants.CENTER);
		writeCenter.add(lblContent, BorderLayout.WEST);

		txtContent = new JTextArea();
		writeCenter.add(txtContent, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnUpdate = new JButton("수정");
		buttonPane.add(btnUpdate);
		getRootPane().setDefaultButton(btnUpdate);

		JButton btnDel = new JButton("삭제");
		btnDel.setActionCommand("OK");
		buttonPane.add(btnDel);

		JButton cancelButton = new JButton("취소");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		// 수정버튼 클릭 이벤트
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardBean bBean = new BoardBean();
				bBean.setTitle(txtTitle.getText());
				bBean.setContents(txtContent.getText());
				bBean.setBoardNo(bBean.getBoardNo());
				bBean.setMemberNo(mBean.getMemberNo());

				int result = bCRUD.updateBoard(bBean);

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "게시글이 수정되었습니다.");
					BoardDetail.this.dispose();

					mainBoard.showTable(0);
				} else {
					JOptionPane.showMessageDialog(null, "게시글 수정 실패!");
				} // if~else
			}// method

		});

		// 삭제버튼 클릭 이벤트
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int check = 0;

				check = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "게시글 삭제 알림" , JOptionPane.YES_NO_OPTION);

				if (check == JOptionPane.YES_OPTION) {

					System.out.println(bBean.getBoardNo());
					
					int result = bCRUD.deleteBoard(bBean.getBoardNo());

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다.");
						BoardDetail.this.dispose();

						mainBoard.showTable(mainBoard.mCurPageNo);
					} else {
						JOptionPane.showMessageDialog(null, "게시글 삭제 실패!");
					} // if~else
					
				}// if

			}// method

		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BoardDetail.this.dispose();
			}// method
			
		});


		// 표시
		txtTitle.setText(bBean.getTitle());
		txtContent.setText(bBean.getContents());

	}// method

	public void clearInputs() {
		
		txtTitle.setText("");
		txtContent.setText("");
		
	}// method
	
}// class
