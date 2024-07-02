package BoardTest.UI;

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

import BoardTest.DB.BoardBean;
import BoardTest.DB.BoardCRUD;
import BoardTest.DB.MemberBean;

public class BoardDetail extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextArea txtContents;
	private JButton btnCancel;
	private JButton btnDelete;
	private JButton btnUpdate;

	private BoardCRUD bCRUD = new BoardCRUD();
	private MemberBean mBean;
	private BoardBean bBean;

	/**
	 * Create the dialog.
	 */
	public BoardDetail(MemberBean mBean, BoardBean bBean, BoardMain main) {
		System.out.println("\n[CALL] BoardDetail");

		this.mBean = mBean;
		this.bBean = bBean;

		init();

		txtTitle.setText(bBean.getTitle());
		txtContents.setText(bBean.getContents());

		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

				bBean.setTitle(txtTitle.getText());
				bBean.setContents(txtContents.getText());

				int confirm = JOptionPane.showConfirmDialog(null, "게시글을 수정하시겠습니까?", "수정 확인", JOptionPane.YES_NO_OPTION);
				System.out.println("[DATA] confirm " + confirm);

				if (confirm == JOptionPane.YES_OPTION) {
					int result = bCRUD.updateBoard(bBean);

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "게시글이 수정되었습니다.");
						main.showBoard();
						BoardDetail.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "게시글이 수정되지 않았습니다.");

						return;
					} // if ~ else
				} else {
					return;
				} // if ~ else
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int confirm = JOptionPane.showConfirmDialog(null, "게시글을 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);

				System.out.println("[DATA] confirm " + confirm);

				if (confirm == JOptionPane.YES_OPTION) {
					int result = bCRUD.deleteBoard(bBean);

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다.");
						main.showBoard();
						BoardDetail.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "게시글이 삭제되지 않았습니다.");

						return;
					} // if ~ else
				} else {
					return;
				} // if ~ else
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardDetail.this.dispose();
			}
		});

	}// constructor

	public void init() {
		System.out.println("\n[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

		setBounds(100, 100, 600, 700);
		setLocationRelativeTo(contentPanel);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel pnlTitle = new JPanel();
		contentPanel.add(pnlTitle, BorderLayout.NORTH);
		pnlTitle.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = new JLabel("제목 : ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setPreferredSize(new Dimension(60, 30));
		pnlTitle.add(lblTitle, BorderLayout.WEST);

		txtTitle = new JTextField();
		pnlTitle.add(txtTitle, BorderLayout.CENTER);
		txtTitle.setColumns(10);

		JPanel pnlContent = new JPanel();
		contentPanel.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));

		JLabel lblContents = new JLabel("제목 : ");
		lblContents.setPreferredSize(new Dimension(60, 30));
		lblContents.setHorizontalAlignment(SwingConstants.CENTER);
		pnlContent.add(lblContents, BorderLayout.WEST);

		txtContents = new JTextArea();
		pnlContent.add(txtContents, BorderLayout.CENTER);

		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(pnlButton, BorderLayout.SOUTH);

		String writer = bBean.getMember_no();
		String loginUser = mBean.getMember_no();

		System.out.println("[DATA] writer " + writer);
		System.out.println("[DATA] loginUser " + loginUser);
		
		btnUpdate = new JButton("수정");
		btnDelete = new JButton("삭제");

		if (writer.equals(loginUser)) {
			btnUpdate.setActionCommand("OK");
			pnlButton.add(btnUpdate);
			getRootPane().setDefaultButton(btnUpdate);

			btnDelete.setActionCommand("Cancel");
			pnlButton.add(btnDelete);
		}

		btnCancel = new JButton("취소");
		btnCancel.setActionCommand("OK");
		pnlButton.add(btnCancel);
	}// method init

}// class
