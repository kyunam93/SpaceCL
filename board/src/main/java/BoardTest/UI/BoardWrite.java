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

import com.mysql.cj.util.StringUtils;

import BoardTest.DB.BoardCRUD;
import BoardTest.DB.MemberBean;

public class BoardWrite extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextArea txtContents;
	private JButton btnOk;
	private JButton btnCancel;

	private BoardCRUD bCRUD = new BoardCRUD();

	/**
	 * Create the dialog.
	 */
	public BoardWrite(MemberBean mBean) {
		System.out.println("\n[CALL] BoardWrite");

		init();

		// 작성버튼 기능
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String title = txtTitle.getText();
				String contents = txtContents.getText();

				if (StringUtils.isNullOrEmpty(title)) {
					JOptionPane.showMessageDialog(null, "제목을 입력해 주세요.");
					txtTitle.setFocusable(true);

					return;
				} else if (StringUtils.isNullOrEmpty(contents)) {
					JOptionPane.showMessageDialog(null, "내용을 입력해 주세요.");
					txtContents.setFocusable(true);

					return;
				} // if ~ else if

				int cnt = bCRUD.insertBoard(mBean, title, contents);
				if (cnt > 0) {
					JOptionPane.showMessageDialog(null, "게시글이 작성되었습니다.");
					
					BoardWrite.this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "게시글이 작성이 실패되었습니다.");
					
					return;
				} // if ~ else
			}
		});

		// 취소버튼 기능
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BoardWrite.this.dispose();
			}
		});
	}// constructor

	public void init() {
		System.out.println("[CALL] " + new Throwable().getStackTrace()[0].getMethodName());

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

		btnOk = new JButton("작성");
		btnOk.setActionCommand("OK");
		pnlButton.add(btnOk);
		getRootPane().setDefaultButton(btnOk);

		btnCancel = new JButton("취소");
		btnCancel.setActionCommand("Cancel");
		pnlButton.add(btnCancel);
	}// method init

}// class
